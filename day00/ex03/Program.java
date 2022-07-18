/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/17 19:00:43 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/18 19:06:40 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.Scanner;

class Program {
	
	private static final int MAX_NUMBERS_COUNT = 18;

	private static final int NUMBERS_OF_WEEK = 5;
	
	public static final String EXIT = "42";
	
	public static void errorExit(Scanner scanner) {	
		System.out.println("IllegalArgument");
		scanner.close();
		System.exit(-1);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String week;
		
		int day = 0, grade = 0, minGrade = 0;

		long nums = 0, print = 0, buffer = 1;
		
		for (int i = 1; i <= MAX_NUMBERS_COUNT; i++) {
			week = scanner.next();
			if (week.equals(EXIT)) {
				break ;
			}
			
			if (week.equals("week")) {
				day = scanner.nextInt();
				if (day != i) {
					errorExit(scanner);
				}
			} else {
				errorExit(scanner);
			}

			for (int j = 0; j < NUMBERS_OF_WEEK; j++) {
				grade = scanner.nextInt();
				if (grade < 1 || grade > 9) {
					errorExit(scanner);
				}
				if (j == 0) {
					minGrade = grade;
				} else if (grade < minGrade) {
					minGrade = grade;
				}
			}
			
			nums = nums + minGrade * buffer;
			buffer = buffer * 10;
		}
		
		for (int i = 1; nums > 0; i++) {
			System.out.print("Week ");
			System.out.print(i);
			System.out.print(" ");
			print = nums % 10;
			for (int j = 0; j < print; j++) {
				System.out.print("=");
			}
			System.out.println(">");
			nums = nums / 10;
		}

		scanner.close();
	}
}