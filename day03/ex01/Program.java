/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/21 11:09:05 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/21 13:01:15 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

class Program {
	public static boolean isEggPrinted = false;
	
	public static synchronized void sayHen() {
		if (isEggPrinted == false) {
			try {
				Program.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Hen");
		
		isEggPrinted = false;
		
		Program.class.notify();
	}

	public static synchronized void sayEgg() {
		if (isEggPrinted == true) {
			try {
				Program.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Egg");

		isEggPrinted = true;

		Program.class.notify();
	}
	
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
	}
}