/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/18 13:22:57 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/18 20:31:22 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.Scanner;

public class Program {
	
	public static final int MAX_SIZE = 10;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String s = scanner.nextLine();
		
		char massSymb[], massFinalSymb[] = new char[s.length() + 9], swapSymb;

		int massCountSymb[] = new int[s.length() + 9], swapNum, num;

		massSymb = s.toCharArray();

		scanner.close();
		
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
		
		if (s.length() == 0) {
			return ;
		}
		
		num = massCountSymb[0];
		for(int i = 0; i < MAX_SIZE; i++) {
			if(massCountSymb[i] * MAX_SIZE / num == MAX_SIZE)
				System.out.print(massCountSymb[i] + "    ");
		}
		System.out.println();

		for (int i = MAX_SIZE; i > 0; i--) {
			for (int j = 0; j < MAX_SIZE; j++) {
				if (massCountSymb[j] * MAX_SIZE / num >= i) {
					System.out.print("#    ");
				}
				if (massCountSymb[j] * MAX_SIZE / num == i - 1) {
					if (massCountSymb[j] != 0) {
						System.out.print(massCountSymb[j] + "    ");
					}
				}
			}
			System.out.println();
		}
		
		for (int i = 0; i < MAX_SIZE; i++) {
			if (massFinalSymb[i] != 0) {
				System.out.print(massFinalSymb[i] + "    ");
			}
		}
		System.out.println();
	}		
}