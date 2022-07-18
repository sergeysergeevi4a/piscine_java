/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.Java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/18 13:22:57 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/18 18:44:49 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.Scanner;

public class Program {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String s = scanner.nextLine();
		
		char massSymb[], massFinalSymb[] = new char[s.length()], swapSymb;

		short massCountSymb[] = new short[s.length()], swapNum;

		massSymb = s.toCharArray();

		for (int i = 0; i < massSymb.length; i++) {
			for (int j = 0; j < massFinalSymb.length; j++) {
				if (massFinalSymb[j] == massSymb[i]) {
					if (massCountSymb[j] < 999) {
						massCountSymb[j] += 1;
					}
					break;
				} else if (massFinalSymb[j] == 0) {
					massFinalSymb[j] = massSymb[i];
					if (massCountSymb[j] < 999) {
						massCountSymb[j] += 1;
					}
					break;
				}
			}
		}
		
		for (int i = 0; i < massCountSymb.length; i++) {
			if (massCountSymb[i] > 0 && massCountSymb[i + 1] > 0
				&& (massCountSymb[i] < massCountSymb[i + 1]
				|| massCountSymb[i] == massCountSymb[i + 1]
				&& massFinalSymb[i] > massFinalSymb[i + 1])) {
					swapNum = massCountSymb[i];
					swapSymb = massFinalSymb[i];
					massCountSymb[i] = massCountSymb[i + 1];
					massFinalSymb[i] = massFinalSymb[i + 1];
					massCountSymb[i + 1] = swapNum;
					massFinalSymb[i + 1] = swapSymb;
					i = -1;
			}
		}
		
		for (char print: massFinalSymb) {
			System.out.println(print);
		}

		for (short print: massCountSymb) {
			System.out.println(print);
		}
		
		scanner.close();
	}
}
