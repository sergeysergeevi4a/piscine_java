/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   FailScene.java                                     :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:01:14 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:01:15 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package rush01.scenes;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import rush01.Constants;

public class FailScene implements Constants {
    private static final Background FAIL_BG = new Background(new BackgroundImage(new Image(PATH_IMAGE_FAIL), BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
    private static final int BG_WIDTH = (int) new Image(PATH_IMAGE_FAIL).getWidth();
    private static final int BG_HEIGHT = (int) new Image(PATH_IMAGE_FAIL).getHeight();

    public static Scene getScene(String json) {

        Pane root = new Pane();
        Scene scene = new Scene(root, BG_WIDTH, BG_HEIGHT);

        Text text = new Text();
        text.setText(json);
        text.setFont(new Font(30));

        text.setX(50);
        text.setY(50);

        root.getChildren().add(text);

        root.setBackground(FAIL_BG);

        return scene;
    }
}
