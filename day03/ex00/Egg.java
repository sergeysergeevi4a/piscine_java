/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Egg.java                                           :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/21 10:25:56 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/21 10:59:30 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class Egg extends Thread {
	
	int count;
	
	public Egg(int count) {
		
		this.count = count;
	}

	@Override
	public void run() {
		for (int i = 0; i < this.count; i++) {
			System.out.println("EGG");
		}
	}
}
