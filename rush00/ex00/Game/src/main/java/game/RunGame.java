/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   RunGame.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/24 08:52:10 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/24 08:53:52 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package game;

import java.util.Scanner;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import chaselogic.ChaseLogic;
import chaselogic.Map;

public class RunGame {

    private static final String MOVEMENTS = "1 - UP, 2 - DOWN, 3 - LEFT, 4 - RIGHT";
    private static final String ACCEPT = "Accept enemy move by entering 8";
    private static final String HELP = "1 - up; 2 - down, 3 - left, 4 - right, 9 - exit";
    private static final String MSG = "Your move, press 5 for help";
    private static final String BAD_COLOR = "Error: bad color in property!";
    private static final String DEV_MODE = "dev";
    private static final String UP = "1";
    private static final String DOWN = "2";
    private static final String LEFT = "3";
    private static final String RIGHT = "4";
    private static final String EXIT = "9";
    private static final String ACCEPT_SELECTOR = "8";
    private static final String HELP_SELECTOR = "5";

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
            System.out.println(MSG);
            String s = scanner.nextLine();

            if (s.equals(EXIT)) {
                break;
            }

            scanner.close();
            
            switch (s) {
                case UP:
                    move.up();
                    break;
                case DOWN:
                    move.down();
                    break;
                case LEFT:
                    move.left();
                    break;
                case RIGHT:
                    move.right();
                    break;
                case HELP_SELECTOR:
                    System.out.println(HELP);
                    continue;
                default:
                    System.out.println(MOVEMENTS);
                    continue;
            }


            if (profile.equals(DEV_MODE)) {
                printMatrix(coloredPrinter);
                System.out.println();
            }

            chaseLogic.move();
            printMatrix(coloredPrinter);

            if (profile.equals(DEV_MODE)) {
                System.out.println(ACCEPT);
                while (!scanner.nextLine().equals(ACCEPT_SELECTOR)) {
                    System.out.println(ACCEPT);
                }
            }
        }
    }

    private void printMatrix(ColoredPrinter coloredPrinter) {
        try {
            Ansi.BColor.valueOf(map.getEmptyColor());
            Ansi.BColor.valueOf(map.getPlayerColor());
            Ansi.BColor.valueOf(map.getGoalColor());
            Ansi.BColor.valueOf(map.getWallColor());
            Ansi.BColor.valueOf(map.getEnemyColor());
        } catch (IllegalArgumentException e) {
            System.err.println(BAD_COLOR);
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
