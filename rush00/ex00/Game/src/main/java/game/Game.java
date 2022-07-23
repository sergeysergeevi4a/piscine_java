package game;

import chaselogic.Map;

import java.io.IOException;
import java.util.Properties;

public class Game {

    public static final String ARGS_ERROR = "Error: bad ARGS!";
    public static final String PARAM_ERROR = "Error: bad params!";
    public static final String FILE_ERROR = "Error: property not found!";

    public static final String ENEMIES_KEY = "--enemiesCount=";
    public static final String WALLS_KEY = "--wallsCount=";
    public static final String SIZE_KEY = "--size=";
    public static final String PROFILE_KEY = "--profile=";

    private final String[] args;

    private Integer enemiesCount;
    private Integer wallsCount;
    private Integer size;
    private String profile;

    public Game(String[] args) {
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
            System.err.println(FILE_ERROR);
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
            throw new IllegalParametersException(PARAM_ERROR);
        }

        if (enemiesCount + wallsCount > (size * 4 - 4) / 2 - 1) {
            throw new IllegalParametersException(PARAM_ERROR);
        }
        return false;
    }

    private boolean checkArgs() {
        if (args.length != 4) {
            System.err.println(ARGS_ERROR);
            return true;
        }

        if (!args[0].startsWith(ENEMIES_KEY) && !args[1].startsWith(WALLS_KEY)
                && !args[2].startsWith(SIZE_KEY) && !args[3].startsWith(PROFILE_KEY)) {
            System.err.println(PARAM_ERROR);
            return true;
        }

        try {
            String s = args[0].replaceFirst(ENEMIES_KEY, "");
            enemiesCount = Integer.parseInt(s);
            s = args[1].replaceFirst(WALLS_KEY, "");
            wallsCount = Integer.parseInt(s);
            s = args[2].replaceFirst(SIZE_KEY, "");
            size = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.err.println(PARAM_ERROR);
            return true;
        }

        profile = args[3].replaceFirst(PROFILE_KEY, "");
        return false;
    }
}
