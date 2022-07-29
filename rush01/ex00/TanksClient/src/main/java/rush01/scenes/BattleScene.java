/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   BattleScene.java                                   :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:00:23 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:00:42 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package rush01.scenes;

import com.alibaba.fastjson.JSON;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import rush01.Constants;
import rush01.Main;
import rush01.objects.Bullet;
import rush01.objects.Player;
import rush01.objects.Tank;
import rush01.objects.World;

import java.io.IOException;
import java.util.ArrayList;

import static rush01.Main.out;
import static rush01.Main.primaryStage;

public class BattleScene implements Constants {
    private static final Background BACKGROUND = new Background(new BackgroundImage(new Image(PATH_IMAGE_BACKGROUND),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));

    private static final Background FAIL_BG = new Background(new BackgroundImage(new Image(PATH_IMAGE_FAIL), BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
    private static final int BG_WIDTH = (int) new Image(PATH_IMAGE_FAIL).getWidth();
    private static final int BG_HEIGHT = (int) new Image(PATH_IMAGE_FAIL).getHeight();

    public static Scene getScene(String json) {

        World data = null;

        if (!json.isEmpty()) {
            try {
                data = JSON.parseObject(json, World.class);
            } catch (Exception e) {
            }
        }

        System.out.println();
        System.out.println("data: " + data);
        System.out.println();

        Pane root = new Pane();

        if (data != null && data.players.get(0).tank.health <= 0) {
            return FailScene.getScene("");
        }

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        if (data != null) {
            draw(root, data);
        }

        // TODO if player dead - change to FAIL_BG and remove keyListeners
        root.setBackground(BACKGROUND);

        onKeyPress(scene);

        return scene;
    }

    private static void draw(Pane root, World data) {

        // Player ------------------------------------------
        Player player = data.players.get(0);
        System.out.println("Player: " + player);
        System.out.println();

        if (player.tank.id == 1) {
            ImageView playerTank = new ImageView(Tank.PLAYER_TANK);
            playerTank.setX(player.tank.getX());
            playerTank.setY(TANK_OFFSET_Y - player.tank.getY() - Tank.imageHeight / 2);
            playerTank.setFitHeight(Tank.imageHeight);
            playerTank.setFitWidth(Tank.imageWidth);
            root.getChildren().add(playerTank);
        } else {
            ImageView playerTank = new ImageView(Tank.PLAYER_TANK);
            playerTank.setX(player.tank.getX());
            playerTank.setY(player.tank.getY());
            playerTank.setFitHeight(Tank.imageHeight);
            playerTank.setFitWidth(Tank.imageWidth);
            root.getChildren().add(playerTank);
        }

        if (player.tank.id == 1) {
            for (Bullet bullet : player.bullets) {
                ImageView img = new ImageView(Bullet.PLAYER_BULLET);
                img.setX(bullet.getX() + Tank.imageWidth / 2);
                img.setY(TANK_OFFSET_Y - player.tank.getY() - bullet.getY());
                img.setFitHeight(Bullet.imageHeight);
                img.setFitWidth(Bullet.imageWidth);
                root.getChildren().add(img);
            }
        } else {
            for (Bullet bullet : player.bullets) {
                ImageView img = new ImageView(Bullet.PLAYER_BULLET);
                img.setX(bullet.getX() + Tank.imageWidth / 2);
                img.setY(bullet.getY());
                img.setFitHeight(Bullet.imageHeight);
                img.setFitWidth(Bullet.imageWidth);
                root.getChildren().add(img);
            }
        }

        // Player HP
        ImageView playerHp = new ImageView(IMAGE_HP);
        playerHp.setX(HP_OFFSET_X + HP_OFFSET_TO_BORDER);
        playerHp.setY(SCENE_HEIGHT - HP_HEIGHT - HP_OFFSET_Y);
        playerHp.setFitHeight(HP_HEIGHT);
        playerHp.setFitWidth(player.tank.health <= 0 ? 1 : (HP_WIDTH - 2 * HP_OFFSET_TO_BORDER) * (player.tank.health / 100.0));
        root.getChildren().add(playerHp);

        // Player HP border
        ImageView playerHpBorder = new ImageView(IMAGE_HP_BORDER);
        playerHpBorder.setX(HP_OFFSET_X);
        playerHpBorder.setY(SCENE_HEIGHT - HP_BORDER_HEIGHT - HP_OFFSET_Y);
        playerHpBorder.setFitHeight(HP_BORDER_HEIGHT);
        playerHpBorder.setFitWidth(HP_BORDER_WIDTH);
        root.getChildren().add(playerHpBorder);


        // Enemy --------------------------------------------
        Player enemy = data.players.get(1);
        System.out.println("Enemy: " + enemy);
        System.out.println();

        if (enemy.tank.id == 1) {
            ImageView enemyTank = new ImageView(Tank.ENEMY_TANK);
            enemyTank.setX(enemy.tank.getX());
            enemyTank.setY(enemy.tank.getY() - Tank.imageHeight / 2);
            enemyTank.setFitHeight(Tank.imageHeight);
            enemyTank.setFitWidth(Tank.imageWidth);
            root.getChildren().add(enemyTank);
        } else {
            ImageView enemyTank = new ImageView(Tank.ENEMY_TANK);
            enemyTank.setX(enemy.tank.getX());
            enemyTank.setY(TANK_OFFSET_Y - enemy.tank.getY() - Tank.imageHeight);
            enemyTank.setFitHeight(Tank.imageHeight);
            enemyTank.setFitWidth(Tank.imageWidth);
            root.getChildren().add(enemyTank);
        }

        if (enemy.tank.id == 1) {
            for (Bullet bullet : enemy.bullets) {
                ImageView img = new ImageView(Bullet.ENEMY_BULLET);
                img.setX(bullet.getX() + Tank.imageWidth / 2);
                img.setY(bullet.getY());
                img.setFitHeight(Bullet.imageHeight);
                img.setFitWidth(Bullet.imageWidth);
                root.getChildren().add(img);
            }
        } else {
            for (Bullet bullet : enemy.bullets) {
                ImageView img = new ImageView(Bullet.ENEMY_BULLET);
                img.setX(bullet.getX() + Tank.imageWidth / 2);
                img.setY(TANK_OFFSET_Y - Tank.imageHeight / 2  - bullet.getY());
                img.setFitHeight(Bullet.imageHeight);
                img.setFitWidth(Bullet.imageWidth);
                root.getChildren().add(img);
            }
        }

        // Enemy HP
        ImageView enemyHp = new ImageView(IMAGE_HP);
        enemyHp.setX(SCENE_WIDTH - HP_WIDTH - HP_OFFSET_X + HP_OFFSET_TO_BORDER);
        enemyHp.setY(HP_OFFSET_Y);
        enemyHp.setFitHeight(HP_HEIGHT);
        enemyHp.setFitWidth(enemy.tank.health <= 0 ? 1 : (HP_WIDTH - 2 * HP_OFFSET_TO_BORDER) * (enemy.tank.health / 100.0));
        root.getChildren().add(enemyHp);

        // Enemy HP border
        ImageView enemyHpBorder = new ImageView(IMAGE_HP_BORDER);
        enemyHpBorder.setX(SCENE_WIDTH - HP_BORDER_WIDTH - HP_OFFSET_X);
        enemyHpBorder.setY(HP_OFFSET_Y);
        enemyHpBorder.setFitHeight(HP_BORDER_HEIGHT);
        enemyHpBorder.setFitWidth(HP_BORDER_WIDTH);
        root.getChildren().add(enemyHpBorder);
    }

    private static void onKeyPress(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                System.out.println(event.getCode());
                switch (event.getCode()) {
                    case RIGHT:
                        Main.left = false;
                        Main.right = true;
                        break;
                    case LEFT:
                        Main.right = false;
                        Main.left = true;
                        break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                System.out.println(event.getCode());
                try {
                    switch (event.getCode()) {
                        case SPACE:
                            Main.out.write(COMMAND_SPACE);
                            out.flush();
                            break;
                        case RIGHT:
                            Main.right = false;
                            break;
                        case LEFT:
                            Main.left = false;
                            break;
                    }
                } catch (IOException e) {
                    System.out.println("Error onKeyReleased: " + e.getMessage());
                    System.exit(-1);
                }
            }
        });
    }

}
