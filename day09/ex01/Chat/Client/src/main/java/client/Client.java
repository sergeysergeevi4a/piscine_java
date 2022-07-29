/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Client.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 10:28:25 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 10:28:26 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final String[] args;
    private int port;

    public Client(String[] args) {
        this.args = args;
    }

    public void start() {
        if (checkArgs()) {
            return;
        }

        try (Socket socket = new Socket("localhost", port)) {
            try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                System.out.println(in.readLine());

                String signUp = console.readLine();

                while (true) {
                    out.write(signUp + "\n");
                    out.flush();

                    String answer = in.readLine();
                    System.out.println(answer);

                    if (answer.equals("Enter username:")) {
                        break;
                    }

                    signUp = console.readLine();
                }

                String userName = console.readLine();

                out.write(userName + "\n");
                out.flush();

                System.out.println(in.readLine());

                String password = console.readLine();

                out.write(password + "\n");
                out.flush();

                String answer = in.readLine();
                System.out.println(answer);

                if (answer.equals("Incorrect password!") ||
                        answer.equals("Successful!") ||
                        answer.equals("User with the name is already exist!")) {
                    out.write("\n");
                    out.flush();
                    return;
                }

                Thread thread = new Thread(() -> {
                    try {
                        while (true) {
                            String s = in.readLine();
                            if (s == null) {
                                break;
                            }
                            if (s.equals("You have left the chat.")) {
                                break;
                            }
                            System.out.println(s);
                        }
                    } catch (IOException ignored) {
                    }
                });

                thread.start();

                while (true) {
                    String s = console.readLine();
                    out.write(s + "\n");
                    out.flush();
                    if (s.equals("exit")) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error: server is down!");
        }
    }

    private boolean checkArgs() {
        if (args.length != 1) {
            System.err.println("Error: bad args");
            return true;
        }

        if (!args[0].startsWith("--server-port=")) {
            System.err.println("Error: bad params");
            return true;
        }

        try {
            port = Integer.parseInt(args[0].replaceFirst("--server-port=", ""));
        } catch (NumberFormatException e) {
            System.err.println("Error: put number!");
            return true;
        }

        return false;
    }
}
