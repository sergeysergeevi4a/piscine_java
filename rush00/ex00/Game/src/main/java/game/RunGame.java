/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   RunGame.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/24 09:07:16 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/24 10:17:25 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package game;

import java.util.Scanner;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import chaselogic.ChaseLogic;
import chaselogic.Map;

public class RunGame {
    
    private final char[][] matrix;
    private final Map map;
    private final Integer size;
    private final String profile;

    public RunGame(char[][] matrix, Map map, Integer size, String profile) {
        this.matrix = matrix;
        this.map = map;
        this.size = size;
        this.profile = profile;
    }

    public void run() {
        ColoredPrinter coloredPrinter = new ColoredPrinter
                .Builder(1, false)
                .foreground(Ansi.FColor.BLACK)
                .build();
        printMatrix(coloredPrinter);

        Scanner scanner = new Scanner(System.in);
        Move move = new Move(matrix, size, map);
        ChaseLogic chaseLogic = new ChaseLogic(matrix, size, map);

        while (true) {
            System.out.println("Your move, press 5 for help");
            String s = scanner.nextLine();

            if (s.equals("9")) {
                break;
            }
        
            switch (s) {
                case "1":
                    move.up();
                    break;
                case "2":
                    move.down();
                    break;
                case "3":
                    move.left();
                    break;
                case "4":
                    move.right();
                    break;
                case "5":
                    System.out.println("1 - up; 2 - down, 3 - left, 4 - right, 9 - exit");
                    continue;
                default:
                    System.out.println("1 - UP, 2 - DOWN, 3 - LEFT, 4 - RIGHT");
                    continue;
            }

            if (profile.equals("production")) {
                printMatrix(coloredPrinter);

                System.out.println();

            }

            chaseLogic.move();
            printMatrix(coloredPrinter);



            if (profile.equals("dev")) {
                System.out.println("Accept enemy move by entering 8");
                while (!scanner.nextLine().equals("8")) {
                    System.out.println("Accept enemy move by entering 8");
                }
            }
        }
        scanner.close();
    }

    private void printMatrix(ColoredPrinter coloredPrinter) {
        try {
            Ansi.BColor.valueOf(map.getEmptyColor());
            Ansi.BColor.valueOf(map.getPlayerColor());
            Ansi.BColor.valueOf(map.getGoalColor());
            Ansi.BColor.valueOf(map.getWallColor());
            Ansi.BColor.valueOf(map.getEnemyColor());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: bad color in property");
            System.exit(-1);
        }

        for (int y = 1; y < size + 1; y++) {
            for (int x = 1; x < size + 1; x++) {
                if (matrix[y][x] == map.getEmptyChar()) {
                    coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(map.getEmptyColor()));
                } else if (matrix[y][x] == map.getPlayerChar()) {
                    coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(map.getPlayerColor()));
                } else if (matrix[y][x] == map.getGoalChar()) {
                    coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(map.getGoalColor()));
                } else if (matrix[y][x] == map.getWallChar()) {
                    coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(map.getWallColor()));
                } else if (matrix[y][x] == map.getEnemyChar()) {
                    coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(map.getEnemyColor()));
                }
                coloredPrinter.print(matrix[y][x]);
            }
            System.out.println();
        }
    }
}
