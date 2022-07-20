/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/20 17:37:51 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/20 18:40:29 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Program {
	
	public static String readFile(String fileName) {
		StringBuilder sb = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line = reader.readLine();

			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.err.println("Fatal: IOException");
			System.exit(-1);
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("bad args");
			return ;
		}

		String file1 = readFile(args[0]);
		String file2 = readFile(args[1]);

		if (file1.isEmpty() && file2.isEmpty()) {
            System.out.println("Similarity = " + 1);
            return;
        }

        if (file1.isEmpty() || file2.isEmpty()) {
            System.out.println("Similarity = " + 0);
            return;
        }
	}
}
