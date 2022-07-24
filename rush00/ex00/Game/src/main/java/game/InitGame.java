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
import com.beust.jcommander.ParameterException;
import game.parser.Parser;

import java.io.IOException;
import java.util.Properties;

public class InitGame {

    private String profile;
    private Integer size;
    private Integer wallsCount;
    private Integer enemiesCount;

    public InitGame(String[] args) {

        Parser parser;

        try {
            parser = new Parser(args);
        } catch (ParameterException e) {
            throw new IllegalParametersException(e.getMessage());
        }
        this.profile = parser.getProfile();
        this.size = parser.getSize();
        this.wallsCount = parser.getWallsCount();
        this.enemiesCount = parser.getEnemiesCount();

        System.out.println(parser);
    }

    public void run() {

        Downloader downloader = new Downloader(profile);

        Properties properties;
        try {
            properties = downloader.download();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Error: property not found!");
            return;
        }

        Map map = new Map(properties);

        MatrixInitializer matrixInitializer = new MatrixInitializer(map, enemiesCount, wallsCount, size);
        char[][] matrix = matrixInitializer.init();

        RunGame runGame = new RunGame(matrix, map, size, profile);
        runGame.run();
    }


}
