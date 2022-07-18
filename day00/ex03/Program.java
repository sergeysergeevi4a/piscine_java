/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/17 19:00:43 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/18 11:04:50 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.Scanner;

class Program {
	
	public static void errorExit(Scanner scanner) {	
		System.out.println("IllegalArgument");
		scanner.close();
		System.exit(-1);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String week;
		
		int day = 0, grade = 0, minGrade = 0;
		
		for (int i = 1; i <= 18; i++) {
			week = scanner.next();
			if (week.equals("42")) {
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

			for (int j = 0; j < 5; j++) {
				grade = scanner.nextInt();
				if (j == 0) {
					minGrade = grade;
				} else if (grade < minGrade) {
					minGrade = grade;
				}
			}

			System.out.println(minGrade);
		
			scanner.close();
		}
	}
}