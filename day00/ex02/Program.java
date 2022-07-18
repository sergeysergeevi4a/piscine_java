/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/17 17:26:01 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/18 12:56:58 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.Scanner;

class Program {
	
	public static final int EXIT = 42;
	
	public static int isSimple(int num) {
		int i = 2;
		
		while ((i * i) <= num) {
			if ((num % i) == 0) {
				return (0);
			}
			i++;
		}
		return (1);
	}
	
	public static int numSum(int num) {
		int res = 0;

		while (num > 0) {
			res = res + num % 10;
			num = num / 10;
		}
		return(res);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int cup_count = 0, num = 0;
		
		while (scanner.hasNext()) {
			num = scanner.nextInt();
			if (num == EXIT) {
				break ;
			}
			num = numSum(num);
			if (isSimple(num) == 1) {
				cup_count++;
			}
		}
		
		System.out.println("Count of coffee-request - " + cup_count);
		
		scanner.close();
	}
}
