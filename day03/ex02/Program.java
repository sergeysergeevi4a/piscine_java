/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/21 13:30:25 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/23 13:26:31 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Program {
	
private static int sumOfList(List<Integer> list) {
	int tmp = 0;
	
	for (int x: list) {
		tmp += x;
	}
	return tmp;
}
	
	public static void main(String[] args) {
		Integer arraySize = Integer.parseInt(args[0].replaceFirst("--arraySize=", ""));
		
		Integer threadsCount = Integer.parseInt(args[1].replaceFirst("--threadsCount=", ""));;

		List<Integer> lists = new ArrayList<>(arraySize);
		
		for (int i = 0; i < arraySize; i++) {
			int rand = ThreadLocalRandom.current().nextInt() % 1000;
			if (rand < 0) {
				lists.add(rand *= -1);
			} else {
				lists.add(rand);
			}
		}
		
		System.out.println("Sum: " + sumOfList(lists));

		int range = (arraySize - 1) / threadsCount;
		
		List<Thread> listOfThreads = new ArrayList<>(threadsCount);

		int beginIndex = 0;
		int lastIndex = 0;

		for (int i = 0; i < threadsCount - 1; i++) {
			beginIndex = i * range;
			lastIndex = (i + 1) * range;
			listOfThreads.add(new InitThreads(lists.subList(beginIndex, lastIndex), beginIndex, lastIndex - 1));
		}
		
		beginIndex = (threadsCount - 1) * range;
		lastIndex = arraySize;

		listOfThreads.add(new InitThreads(lists.subList(beginIndex, lastIndex), beginIndex, lastIndex - 1));

		for (Thread thread : listOfThreads) {
			thread.start();
		}

		for (Thread thread : listOfThreads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Sum by threads: " + InitThreads.getSumOfThreads());
	}
}