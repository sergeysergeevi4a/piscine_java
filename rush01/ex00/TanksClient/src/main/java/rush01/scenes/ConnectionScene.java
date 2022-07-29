/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ConnectionScene.java                               :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:01:03 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:01:04 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package rush01.scenes;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import rush01.Constants;
import rush01.Main;

import java.io.*;
import java.net.Socket;

import static rush01.Main.primaryStage;

public class ConnectionScene implements Constants {

    private static class ReadMsg extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    final String json = Main.in.readLine();
                    if ("EXIT".equals(json)) {
                        final String stat1 = Main.in.readLine();
                        final String stat2 = Main.in.readLine();
                        final String stat = stat1 + "\n\n" + stat2;
                        Platform.runLater(() -> {
                            primaryStage.setScene(FailScene.getScene(stat));
                            primaryStage.centerOnScreen();
                        });
                        break;
                    }
                    Platform.runLater(() -> {
                        primaryStage.setScene(BattleScene.getScene(json));
                    });
                }
            } catch (IOException ignored) {
            }
        }
    }

    private static class WriteMsg extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    if (Main.right) {
                        Main.out.write(COMMAND_RIGHT);
                        Main.out.flush();
                    }
                    if (Main.left) {
                        Main.out.write(COMMAND_LEFT);
                        Main.out.flush();
                    }
                    sleep(100);
                }
            } catch (IOException | InterruptedException ignored) {
            }
        }
    }

    public static Scene getScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 275);

        Text sceneTitle = new Text("Connect");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label serverTextLabel = new Label("Server:");
        grid.add(serverTextLabel, 0, 1);

        TextField serverAddressTextField = new TextField();
        serverAddressTextField.setText(DEFAULT_SERVER_ADDRESS);
        grid.add(serverAddressTextField, 1, 1);

        Label portLabel = new Label("Port:");
        grid.add(portLabel, 0, 2);

        TextField portTextField = new TextField();
        portTextField.setText(DEFAULT_PORT);
        grid.add(portTextField, 1, 2);

        Button btn = new Button("Connect");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (!validateInputData(serverAddressTextField.getText(), portTextField.getText())) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Wrong data");
                    return;
                }
                if (!connect(serverAddressTextField.getText(), portTextField.getText())) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Can't connect to this server");
                }
            }
        });

        return scene;
    }

    private static boolean validateInputData(String address, String port) {
        try {
            int p = Integer.parseInt(port);
            if (p < MIN_PORT_NUMBER || p > MAX_PORT_NUMBER) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private static boolean connect(String address, String port) {
        System.out.println("server: " + address + ", port: " + port);

        try {
            Main.client = new Socket(address, Integer.parseInt(port));
            Main.in = new BufferedReader(new InputStreamReader(Main.client.getInputStream()));
            Main.out = new BufferedWriter(new OutputStreamWriter(Main.client.getOutputStream()));
            System.out.println("connected to server port " + port);

            Thread readThread = new ReadMsg();
            Thread writeThread = new WriteMsg();
            readThread.setDaemon(true);
            writeThread.setDaemon(true);
            readThread.start();
            writeThread.start();
        } catch (IOException e) {
            System.err.println(e);
            return false;
        }

        primaryStage.setScene(BattleScene.getScene(""));
        primaryStage.centerOnScreen();

        return true;
    }

}
