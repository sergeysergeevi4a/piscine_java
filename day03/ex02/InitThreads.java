/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   initThreads.java                                   :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/21 13:30:39 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/21 17:28:27 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.List;

public class InitThreads extends Thread {
	
	private static int SumOfThreads = 0;

	int beginIndex, lastIndex, localSum;

	private static synchronized void addToSum(int localSum, int beginIndex, int lastIndex) {
		System.out.println(Thread.currentThread().getName() +
			": from " + beginIndex + " to " + lastIndex + " sum is " + localSum);
		SumOfThreads += localSum;
	}

	public static int getSumOfThreads() {
		return SumOfThreads;
	}
	
	public InitThreads(List<Integer> subList, int beginIndex, int lastIndex) {
		this.beginIndex = beginIndex;
		this.lastIndex = lastIndex;
		localSum = 0;
		
		for (int x : subList) {
			localSum += x;
		}
	}

	@Override
	public void run() {
		addToSum(localSum, beginIndex, lastIndex);
	}
}
