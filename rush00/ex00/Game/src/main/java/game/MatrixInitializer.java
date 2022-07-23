package game;

import chaselogic.Map;

import java.util.Random;

public class MatrixInitializer {

    private static final Character border = 'B';

    private char[][] matrix;
    private final Map map;
    private final Integer enemiesCount;
    private final Integer wallsCount;
    private final Integer size;
    private final Random random;

    public MatrixInitializer(Map map, Integer enemiesCount, Integer wallsCount, Integer size) {
        this.map = map;
        this.enemiesCount = enemiesCount;
        this.wallsCount = wallsCount;
        this.size = size;
        this.random = new Random();
    }


    public char[][] init() {
        initMatrixEmpty();
        initMatrixEnemy();
        initMatrixWall();
        initMatrixPlayer();
        initMatrixGoal();
        return matrix;
    }

    private void initMatrixGoal() {
        while (true) {
            Integer y = random.nextInt(size) + 1;
            Integer x = random.nextInt(size) + 1;
            if (y == 1 || y.equals(size) || x == 1 || x.equals(size)) {
                if (matrix[y][x] == map.getEmptyChar()) {
                    matrix[y][x] = map.getGoalChar();
                    break;
                }
            }
        }
    }

    private void initMatrixPlayer() {
        while (true) {
            Integer y = random.nextInt(size) + 1;
            Integer x = random.nextInt(size) + 1;
            if (y == 1 || y.equals(size) || x == 1 || x.equals(size)) {
                if (matrix[y][x] == map.getEmptyChar()) {
                    if (!checkAround(y, x)) {
                        matrix[y][x] = map.getPlayerChar();
                        break;
                    }
                }
            }
        }
    }

    private boolean checkAround(Integer y, Integer x) {
        if (matrix[y - 1][x - 1] == map.getEnemyChar()) {
            return true;
        } else if (matrix[y - 1][x] == map.getEnemyChar()) {
            return true;
        } else if (matrix[y - 1][x + 1] == map.getEnemyChar()) {
            return true;
        } else if (matrix[y + 1][x - 1] == map.getEnemyChar()) {
            return true;
        } else if (matrix[y + 1][x] == map.getEnemyChar()) {
            return true;
        } else if (matrix[y + 1][x + 1] == map.getEnemyChar()) {
            return true;
        } else if (matrix[y][x - 1] == map.getEnemyChar()) {
            return true;
        } else return matrix[y][x + 1] == map.getEnemyChar();
    }

    private void initMatrixWall() {
        Integer count = 0;
        while (count < wallsCount) {
            Integer y = random.nextInt(size) + 1;
            Integer x = random.nextInt(size) + 1;
            if (y == 1 || y.equals(size) || x == 1 || x.equals(size)) {
                continue;
            }
            if (matrix[y][x] == map.getEmptyChar()) {
                matrix[y][x] = map.getWallChar();
                count++;
            }
        }
    }

    private void initMatrixEnemy() {
        Integer count = 0;
        while (count < enemiesCount) {
            Integer y = random.nextInt(size) + 1;
            Integer x = random.nextInt(size) + 1;
            if (matrix[y][x] == map.getEmptyChar()) {
                matrix[y][x] = map.getEnemyChar();
                count++;
            }
        }
    }

    private void initMatrixEmpty() {
        matrix = new char[size + 2][size + 2];
        for (int y = 0; y < size + 2; y++) {
            for (int x = 0; x < size + 2; x++) {
                if (y == 0 || x == 0 || y == size + 1 || x == size + 1) {
                    matrix[y][x] = border;
                } else {
                    matrix[y][x] = map.getEmptyChar();
                }
            }
        }
    }
}
