/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   InitGame.java                                      :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/24 09:06:03 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/24 10:31:26 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package game;

import chaselogic.Map;

import java.io.IOException;
import java.util.Properties;

public class InitGame {

    private final String[] args;
    private String profile;
    private Integer size;
    private Integer wallsCount;
    private Integer enemiesCount;

    public InitGame(String[] args) {
        this.args = args;
    }

    public void run() {
        if (checkArgs()) {
            return;
        }

        if (checkValues()) {
            return;
        }

        Downloader downloader = new Downloader(profile);

        Properties properties;
        try {
            properties = downloader.download();
        } catch (IOException e) {
            System.err.println("Error: property not found!");
            return;
        }

        Map map = new Map(properties);

        MatrixInitializer matrixInitializer = new MatrixInitializer(map, enemiesCount, wallsCount, size);
        char[][] matrix = matrixInitializer.init();

        RunGame runGame = new RunGame(matrix, map, size, profile);
        runGame.run();
    }

    private boolean checkValues() {
        if (enemiesCount < 1 || wallsCount < 0 || size < 5 || size > 100) {
            throw new IllegalParametersException("Error: bad params!");
        }

        if (enemiesCount + wallsCount > (size * 4 - 4) / 2 - 1) {
            throw new IllegalParametersException("Error: bad params!");
        }
        return false;
    }

    private boolean checkArgs() {
        if (args.length != 4) {
            System.err.println("Error: bad ARGS!");
            return true;
        }

        if (!args[0].startsWith("--enemiesCount=") && !args[1].startsWith("--wallsCount=")
                && !args[2].startsWith("--size=") && !args[3].startsWith("--profile=")) {
            System.err.println("Error: bad params!");
            return true;
        }

        try {
            String s = args[0].replaceFirst("--enemiesCount=", "");
            enemiesCount = Integer.parseInt(s);
            s = args[1].replaceFirst("--wallsCount=", "");
            wallsCount = Integer.parseInt(s);
            s = args[2].replaceFirst("--size=", "");
            size = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.err.println("Error: bad params!");
            return true;
        }

        profile = args[3].replaceFirst("--profile=", "");
        return false;
    }
}
