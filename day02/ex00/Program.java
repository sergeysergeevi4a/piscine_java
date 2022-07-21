/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/20 13:18:59 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/21 08:54:34 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Program {
	
	public static final String EXIT = "42";
	
	public static final Integer SING_SIZE = 8;

	public static final char[] HEX_CHAR_ARRAY = "0123456789ABCDEF".toCharArray();

	public static void main(String[] args) {
		Map<String, String> signatures = new HashMap<>();
		
		try (Scanner scanner = new Scanner(new FileInputStream("signatures.txt"))) {
			while (scanner.hasNextLine()) {
				String[] strings = scanner.nextLine().split(",");
				signatures.put(strings[0], strings[1].replaceAll(" ", ""));
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error: file not found");
		}
		if (signatures.size() == 0) {
			return ;
		}
		try (FileOutputStream fileOutputStream = new FileOutputStream("result.txt", true)) {
			try (Scanner scanner = new Scanner(System.in)) {
				String fileName;

				while (true) {
					fileName = scanner.nextLine();

					if (fileName.equals(EXIT)) {
						break ;
					}

					try (FileInputStream fis = new FileInputStream(fileName)) {
						byte[] bytes = new byte[SING_SIZE];

						Integer res = fis.read(bytes, 0, SING_SIZE);

						if (res < SING_SIZE) {
							System.err.println("error");
							return ;
						}
						
						char[] hexChars = new char[bytes.length * 2];

						for (int j = 0; j < bytes.length; j++) {
							int v = bytes[j] & 0xFF;
							hexChars[j * 2] = HEX_CHAR_ARRAY[v >>> 4];
							hexChars[j * 2 + 1] = HEX_CHAR_ARRAY[v & 0x0F];
						}
						
						String signature = new String(hexChars);
						
						for (Map.Entry<String, String> entry : signatures.entrySet()) {
							if (signature.contains(entry.getValue())) {
								fileOutputStream.write(entry.getKey().getBytes());
								fileOutputStream.write('\n');
								System.out.println("PROCESSED");
								return;
							}
						}
					}
				}
			} 
		} catch (FileNotFoundException e) {
			System.err.println("Error: file not found");	
		} catch (IOException e) {
			System.err.println("error");
		}
	}
}