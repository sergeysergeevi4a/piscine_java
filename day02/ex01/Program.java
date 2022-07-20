/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/20 17:37:51 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/20 19:34:05 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Program {
	
	private static Integer countNumerator(Integer[] vector1, Integer[] vector2) {
        Integer numerator = 0;

        for (int i = 0; i < vector1.length; i++) {
            Integer integer1 = vector1[i];
            Integer integer2 = vector2[i];
            numerator += integer1 * integer2;
        }

        return numerator;
    }
	
	private static Double countDenominator(Integer[] vector1, Integer[] vector2) {
        Double sum1 = 0.0;
		
        Double sum2 = 0.0;

        for (int i = 0; i < vector1.length; i++) {
            sum1 += vector1[i] * vector1[i];
            sum2 += vector2[i] * vector2[i];
        }

        sum1 = Math.sqrt(sum1);
        sum2 = Math.sqrt(sum2);

        return sum1 * sum2;
    }
	
	private static void findSimilarity(Map<String, Integer> map1, Map<String, Integer> map2, HashSet<String> strings) {
        ArrayList<String> dictionary = new ArrayList<>(strings);
		
        Collections.sort(dictionary);

        Integer[] vector1 = new Integer[dictionary.size()];
		
        Integer[] vector2 = new Integer[dictionary.size()];

        for (int i = 0; i < dictionary.size(); i++) {
            if (map1.get(dictionary.get(i)) == null) {
                vector1[i] = 0;
            } else {
                vector1[i] = map1.get(dictionary.get(i));
            }
            if (map2.get(dictionary.get(i)) == null) {
                vector2[i] = 0;
            } else {
                vector2[i] = map2.get(dictionary.get(i));
            }
        }

        Integer numerator = countNumerator(vector1, vector2);
        Double denominator = countDenominator(vector1, vector2);

        System.out.println("Similarity = " + new BigDecimal(numerator / denominator).setScale(2, RoundingMode.FLOOR));
    }
	
	private static void writeSetToDictionary(HashSet<String> strings) {
        ArrayList<String> dictionary = new ArrayList<>(strings);
		
        Collections.sort(dictionary);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Dictionary.txt"))) {
            for (String string : dictionary) {
                writer.write(string);
                writer.write('\n');
            }
        } catch (IOException e) {
            System.err.println("Fatal: IOException");
        }
    }
	
	private static void fillMap(Map<String, Integer> map, ArrayList<String> fileArray) {
        for (String s : fileArray) {
            int frequency = Collections.frequency(fileArray, s);
            map.put(s, frequency);
        }
    }
	
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

		ArrayList<String> fileArray1 = new ArrayList<>(Arrays.asList(file1.split("\\s+")));
        ArrayList<String> fileArray2 = new ArrayList<>(Arrays.asList(file2.split("\\s+")));

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        fillMap(map1, fileArray1);
        fillMap(map2, fileArray2);

		HashSet<String> strings = new HashSet<>(map1.keySet());
        strings.addAll(map2.keySet());

        writeSetToDictionary(strings);
        findSimilarity(map1, map2, strings);
	}
}
