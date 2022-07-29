/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Tank.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:00:14 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:00:15 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package rush01.objects;

import javafx.scene.image.Image;

public class Tank extends AbstractObject {
	public static final Image PLAYER_TANK = new Image(PATH_IMAGE_PLAYER_TANK);
	public static final Image ENEMY_TANK = new Image(PATH_IMAGE_ENEMY_TANK);
	public static final int imageWidth = (int) ENEMY_TANK.getWidth();
	public static final int imageHeight = (int) ENEMY_TANK.getHeight();

	public int health;
	public int id;


	public Tank(int x, int y, int id, int health) {
		super(x, y);
		this.health = health;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Tank{" +
				"health=" + health +
				", id=" + id +
				", x=" + x +
				", y=" + y +
				'}';
	}
}
