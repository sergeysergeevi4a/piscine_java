/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/17 14:46:43 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/18 12:55:42 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.Scanner;

public class Program {

	private static final int NEGATIVE_NUMBERS = 2;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int num = scanner.nextInt(), i = 2;
		
		if (num < NEGATIVE_NUMBERS) {
			scanner.close();
			System.err.println("IllegalArgument");
			System.exit(-1);
		}
		
		while ((i * i) <= num) {
			if ((num % i) == 0) {
				System.out.println("false " + (i - 1));
				scanner.close();
				return ;
			}
			i++;
		}
		
		System.out.println("true " + (i - 1));
		
		scanner.close();
	}
}
