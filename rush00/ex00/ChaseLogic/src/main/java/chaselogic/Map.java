/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Map.java                                           :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/24 09:03:54 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/24 10:20:28 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package chaselogic;

import java.util.Properties;

public class Map {

    public static final Integer SIZE = 5;
    private char emptyChar;
    private char playerChar;
    private char wallChar;
    private char goalChar;
    private char enemyChar;
    private String enemyColor;
    private String emptyColor;
    private String playerColor;
    private String goalColor;
    private String wallColor;

    public Map(Properties properties){
        validationProperties(properties);
    }

    private void validationProperties(Properties properties) {
        setEmptyChar(properties.getProperty("empty.char"));
        setEnemyChar(properties.getProperty("enemy.char"));
        setGoalChar(properties.getProperty("goal.char"));
        setPlayerChar(properties.getProperty("player.char"));
        setEmptyColor(properties.getProperty("empty.color"));
        setEnemyColor(properties.getProperty("enemy.color"));
        setGoalColor(properties.getProperty("goal.color"));
        setWallChar(properties.getProperty("wall.char"));
        setWallColor(properties.getProperty("wall.color"));
        setPlayerColor(properties.getProperty("player.color"));
        checkRepeat();
    }

    private void checkRepeat() {
        char[] chars = new char[SIZE];
        chars[0] = emptyChar;
        chars[1] = playerChar;
        chars[2] = wallChar;
        chars[3] = goalChar;
        chars[4] = enemyChar;
        
        for (int i = 0; i < SIZE; i++) {
            Integer count = 0;
            for (int j = 0; j < SIZE; j++) {
                if (chars[i] == chars[j]) {
                    count++;
                }
            }
            if (count > 1) {
                System.err.println("Error duplicate chars");
                System.exit(-1);
            }
        }
    }

    public char getEmptyChar() {
        return emptyChar;
    }

    public char getPlayerChar() {
        return playerChar;
    }

    public char getWallChar() {
        return wallChar;
    }

    public char getGoalChar() {
        return goalChar;
    }

    public char getEnemyChar() {
        return enemyChar;
    }

    public String getEnemyColor() {
        return enemyColor;
    }

    public String getEmptyColor() {
        return emptyColor;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public String getGoalColor() {
        return goalColor;
    }

    public String getWallColor() {
        return wallColor;
    }

    private void setEmptyChar(String emptyLine) {
        if (emptyLine == null){
            System.err.println("Error properties key");
            System.exit(-1);
        }

        if (emptyLine.length() == 1){
            this.emptyChar = emptyLine.charAt(0);
        } else if (emptyLine.isEmpty()) {
            this.emptyChar = ' ';
        } else {
            System.err.println("Properties error");
            System.exit(-1);
        }
    }

    private void setPlayerChar(String playerLine) {
        if (playerLine == null){
            System.err.println("Error properties key");
            System.exit(-1);
        }

        if (playerLine.length() == 1){
            this.playerChar = playerLine.charAt(0);
        } else {
            System.err.println("Properties error");
            System.exit(-1);
        }
    }

    private void setWallChar(String wallLine) {
        if (wallLine == null){
            System.err.println("Error properties key");
            System.exit(-1);
        }

        if (wallLine.length() == 1){
            this.wallChar = wallLine.charAt(0);
        } else {
            System.err.println("Properties error");
            System.exit(-1);
        }
    }

    private void setGoalChar(String goalLIne) {
        if (goalLIne == null){
            System.err.println("Error properties key");
            System.exit(-1);
        }

        if (goalLIne.length() == 1){
            this.goalChar = goalLIne.charAt(0);
        } else {
            System.err.println("Properties error");
            System.exit(-1);
        }
    }

    private void setEnemyChar(String enemyLine) {
        if (enemyLine == null){
            System.err.println("Error properties key");
            System.exit(-1);
        }

        if (enemyLine.length() == 1){
            this.enemyChar = enemyLine.charAt(0);
        } else {
            System.err.println("Properties error");
            System.exit(-1);
        }
    }

    private void setEnemyColor(String color) {
        if (color == null){
            System.err.println("Error properties key");
            System.exit(-1);
        }

        if (color.isEmpty()){
            System.err.println("Properties error");
            System.exit(-1);
        }
        this.enemyColor = color;
    }

    private void setEmptyColor(String color) {
        if (color == null){
            System.err.println("Error properties key");
            System.exit(-1);
        }

        if (color.isEmpty()){
            System.err.println("Properties error");
            System.exit(-1);
        }
        this.emptyColor = color;
    }

    private void setPlayerColor(String color) {
        if (color == null){
            System.err.println("Error properties key");
            System.exit(-1);
        }

        if (color.isEmpty()){
            System.err.println("Properties error");
            System.exit(-1);
        }
        this.playerColor = color;
    }

    private void setGoalColor(String color) {
        if (color == null){
            System.err.println("Error properties key");
            System.exit(-1);
        }

        if (color.isEmpty()){
            System.err.println("Properties error");
            System.exit(-1);
        }
        this.goalColor = color;
    }

    public void setWallColor(String color) {
        if (color == null){
            System.err.println("Error properties key");
            System.exit(-1);
        }

        if (color.isEmpty()){
            System.err.println("Properties error");
            System.exit(-1);
        }
        this.wallColor = color;
    }
}