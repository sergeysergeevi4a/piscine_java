/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/21 10:18:48 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/21 11:43:27 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

class Program {
	
	public static void main(String[] args) {
		
		if (args.length != 1 || !args[0].startsWith("--count=")) {
			System.out.println("Wrong number of arguments");
			System.exit(-1);
		}

		int count = Integer.parseInt(args[0].substring(8));
		
		Thread egg = new Egg(count);
		Thread hen = new Hen(count);

		egg.start();
		hen.start();

		try {
			hen.join();
			egg.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < count; i++) {
			System.out.println("HUMAN");
		}
	}
}