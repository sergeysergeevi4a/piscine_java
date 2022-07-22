/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/22 09:58:06 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/22 18:48:57 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.printer.app;

import java.io.IOException;

import edu.school21.printer.logic.Logic;

public class Program {

	public static void main(String[] args) throws IOException {
		
		if (args.length != 3) {
			System.out.println("Wrong arguments");
			System.exit(-1);
		}
		
		char whiteColor = args[0].charAt(0);
		char blackColor = args[1].charAt(0);
		String path = args[2];

		int[][] mass = Logic.seeBMPImage(path, blackColor, whiteColor);
		
		for (int x = 0; x < mass.length; x++) {
			for (int y = 0; y < mass[x].length; y++) {
				System.out.print((char)mass[y][x]);
			}
			System.out.println();
		}
	}
}
