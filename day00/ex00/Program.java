/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/17 10:15:39 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/18 10:48:52 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class Program {

	public static void main(String[] args) {
		
		int num = 479598;
		
		int res = 0;
		
		res = res + num % 10;
		num = num / 10;
		res = res + num % 10;
		num = num / 10;
		res = res + num % 10;
		num = num / 10;
		res = res + num % 10;
		num = num / 10;
		res = res + num % 10;
		num = num / 10;
		res = res + num % 10;
		
		System.out.println(res);
	}
}
