/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Logic.java                                         :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/22 09:58:22 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/22 18:49:02 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.printer.logic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Logic {
	
	public static int index = 10;

	public static int[][] seeBMPImage(String path, char blackColor, char whiteColor) throws IOException {
		BufferedImage image = ImageIO.read(new FileInputStream(path));

		int mass[][] = new int[image.getWidth()][image.getHeight()];
		
		for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
			for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
				int color = image.getRGB(xPixel, yPixel);
				if (color == Color.BLACK.getRGB()) {
					mass[xPixel][yPixel] = blackColor;
				} else if (color == Color.WHITE.getRGB()){
					mass[xPixel][yPixel] = whiteColor;
				}
			}
		}
		return mass;
	}
}
