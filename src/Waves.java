package src;

import java.util.LinkedList;
import java.util.List;

public final class Waves {
    private static String[][] waves = new String[][] {
            new String[]{
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "        111        ",
                    "                   ",
                    "     1       1     ",
                    "                   ",
                    "                   ",
                    "                   ",
            },
            new String[]{
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "     1 1 1 1 1     ",
                    "      0  0  0      ",
                    "     1 1 1 1 1     ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
            },
            new String[]{
                    "   0 0 0 0 0 0 0   ",
                    "                   ",
                    "     1 11111 1     ",
                    "      1     1      ",
                    "         0         ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
            },
            new String[]{
                    "         0         ",
                    "                   ",
                    "         2         ",
                    "        111        ",
                    "         1         ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
            },
            new String[]{
                    "  0      0      0  ",
                    "                   ",
                    "         2         ",
                    "      1  1  1      ",
                    "      1  2  1      ",
                    "         1         ",
                    "         0         ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
            },
            new String[]{
                    "                   ",
                    "    111     111    ",
                    "    111     111    ",
                    "                   ",
                    "    11       11    ",
                    "    11111111111    ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
            },
            new String[]{
                    "  000000000000000  ",
                    "         2         ",
                    "  1 1 1 1 1 1 1 1  ",
                    "   1 1 1 1 1 1 1   ",
                    "  1 1 1 1 1 1 1 1  ",
                    "                   ",
                    "      2     2      ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
            },
            new String[]{
                    "  0      0      0  ",
                    "                   ",
                    "  2 2 2  2  2 2 2  ",
                    "                   ",
                    "  1 1 1 1 1 1 1 1  ",
                    "   1 101 1 101 1   ",
                    "  1 1 1 1 1 1 1 1  ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
            },
    };

    public static List<Enemy> getWaveEnemies(int index) {
        List<Enemy> enemies = new LinkedList<>();
        if (waves.length == 0) return enemies;
        if (index < 0) {
            index = 0;
        }
        else if (index >= waves.length) {
            index = waves.length - 1;
        }
        String[] wave = waves[index];
        int cellY = Params.GAME_HEIGHT / wave.length;
        for (int y = 0; y < wave.length; y++) {
            String line = wave[y];
            int cellX = Params.GAME_WIDTH / line.length();
            for (int x = 0; x < line.length(); x++) {
                switch (line.charAt(x)) {
                    case '0':
                        enemies.add(new Spaceship(x * cellX, y * cellY));
                        break;
                    case '1':
                        enemies.add(new GroupEnemy(x * cellX, y * cellY));
                        break;
                    case '2':
                        enemies.add(new TimerEnemy(x * cellX, y * cellY, 5 + (int)Math.floor(Math.random() * 5)));
                        break;
                }
            }
        }
        return enemies;
    }
}
