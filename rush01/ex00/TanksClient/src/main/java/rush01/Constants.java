/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Constants.java                                     :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:01:21 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:01:22 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package rush01;

import javafx.scene.image.Image;

public interface Constants {
	String PATH_IMAGE_BACKGROUND = "/images/field.png";
	String PATH_IMAGE_PLAYER_TANK = "/images/player.png";
	String PATH_IMAGE_ENEMY_TANK = "/images/enemy.png";
	String PATH_IMAGE_PLAYER_BULLET = "/images/playerBullet.png";
	String PATH_IMAGE_ENEMY_BULLET = "/images/enemyBullet.png";
	String PATH_IMAGE_HP_BORDER = "/images/border.png";
	String PATH_IMAGE_HP = "/images/life.png";
	String PATH_IMAGE_FAIL = "/images/fail.png";

	Image IMAGE_HP_BORDER = new Image(PATH_IMAGE_HP_BORDER);
	Image IMAGE_HP = new Image(PATH_IMAGE_HP);

	int HP_OFFSET_X = 10;
	int HP_OFFSET_Y = 10;
	int HP_BORDER_WIDTH = 210;
	int HP_BORDER_HEIGHT = 34;
	int HP_WIDTH = 210;
	int HP_HEIGHT = 30;
	int HP_OFFSET_TO_BORDER = 5;

	String DEFAULT_SERVER_ADDRESS = "localhost";
	String DEFAULT_PORT = "8081";

	int MIN_PORT_NUMBER = 1024;
	int MAX_PORT_NUMBER = 65535;

	int SCENE_WIDTH = 1042;
	int SCENE_HEIGHT = 1042;

	int TANK_OFFSET_Y = SCENE_HEIGHT;


	int VERTICAL_OFFSET = 25;	// server
	int STEP = 10; // server

	String COMMAND_RIGHT = "RIGHT\n";
	String COMMAND_LEFT = "LEFT\n";
	String COMMAND_SPACE = "FIRE\n";

}
