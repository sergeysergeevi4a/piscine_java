/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ChaseLogic.java                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/24 09:03:24 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/24 10:18:44 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package chaselogic;

public class ChaseLogic {
    
    private final char[][] matrix;
    private final Integer size;
    private final Map map;
    private Integer playerY;
    private Integer playerX;

    public ChaseLogic(char[][] matrix, Integer size, Map map) {
        this.matrix = matrix;
        this.size = size;
        this.map = map;
    }

    private void initPlayerCoordinate() {
        for (int y = 1; y < size + 1; y++) {
            for (int x = 1; x < size + 1; x++) {
                if (matrix[y][x] == map.getPlayerChar()) {
                    playerY = y;
                    playerX = x;
                }
            }
        }
    }

    public void move() {
        initPlayerCoordinate();
        char[][] temp = new char[size + 2][size + 2];
        for (int y = 0; y < size + 2; y++) {
            System.arraycopy(matrix[y], 0, temp[y], 0, size + 2);
        }
        for (int y = 1; y < size + 1; y++) {
            for (int x = 1; x < size + 1; x++) {
                if (matrix[y][x] == map.getEnemyChar()) {
                    if (checkPlayer(y, x)) {
                        System.out.println("Game over");
                        System.exit(-1);
                    }
                    moveEnemy(y, x, temp);
                }
            }
        }
        for (int y = 0; y < size + 2; y++) {
            System.arraycopy(temp[y], 0, matrix[y], 0, size + 2);
        }
    }

    private boolean checkPlayer(int y, int x) {
        return matrix[y - 1][x] == map.getPlayerChar() || matrix[y + 1][x] == map.getPlayerChar() ||
                matrix[y][x - 1] == map.getPlayerChar() || matrix[y][x + 1] == map.getPlayerChar();
    }

    private void moveEnemy(int y, int x, char[][] matrix) {
        if (y > playerY) {
            if (x >= playerX) {
                if (x - playerX > y - playerY) {
                    if (this.matrix[y][x - 1] == map.getEmptyChar()
                            && matrix[y][x - 1] != map.getEnemyChar()) {
                        matrix[y][x - 1] = map.getEnemyChar();
                        matrix[y][x] = map.getEmptyChar();
                    }
                } else {
                    if (this.matrix[y - 1][x] == map.getEmptyChar()
                            && matrix[y - 1][x] != map.getEnemyChar()) {
                        matrix[y - 1][x] = map.getEnemyChar();
                        matrix[y][x] = map.getEmptyChar();
                    }
                }
            } else {
                if (playerX - x > y - playerY) {
                    if (this.matrix[y - 1][x] == map.getEmptyChar()
                            && matrix[y - 1][x] != map.getEnemyChar()) {
                        matrix[y - 1][x] = map.getEnemyChar();
                        matrix[y][x] = map.getEmptyChar();
                    }
                } else {
                    if (this.matrix[y][x + 1] == map.getEmptyChar()
                            && matrix[y][x + 1] != map.getEnemyChar()) {
                        matrix[y][x + 1] = map.getEnemyChar();
                        matrix[y][x] = map.getEmptyChar();
                    }
                }
            }
        } else {
            if (x > playerX) {
                if (x - playerX > playerY - y) {
                    if (this.matrix[y][x - 1] == map.getEmptyChar()
                            && matrix[y][x - 1] != map.getEnemyChar()) {
                        matrix[y][x - 1] = map.getEnemyChar();
                        matrix[y][x] = map.getEmptyChar();
                    }
                } else {
                    if (this.matrix[y + 1][x] == map.getEmptyChar()
                            && matrix[y + 1][x] != map.getEnemyChar()) {
                        matrix[y + 1][x] = map.getEnemyChar();
                        matrix[y][x] = map.getEmptyChar();
                    }
                }
            } else {
                if (playerX - x > playerY - y) {
                    if (this.matrix[y][x + 1] == map.getEmptyChar()
                            && matrix[y][x + 1] != map.getEnemyChar()) {
                        matrix[y][x + 1] = map.getEnemyChar();
                        matrix[y][x] = map.getEmptyChar();
                    }
                } else {
                    if (this.matrix[y + 1][x] == map.getEmptyChar()
                            && matrix[y + 1][x] != map.getEnemyChar()) {
                        matrix[y + 1][x] = map.getEnemyChar();
                        matrix[y][x] = map.getEmptyChar();
                    }
                }
            }
        }
    }
}
