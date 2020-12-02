package src;

import java.util.LinkedList;
import java.util.List;

public final class WavesNormal {
    private static String[][] waves = new String[][] {
            new String[]{
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "       0      0    ",
                    "     1  1   1  1   ",
                    "                   ",
                    "                   ",
                    "                   ",
            },
            new String[]{
                    "                   ",
                    "                   ",
                    "      0 0 0 0 0    ",
                    "                   ",
                    "     0 0 0 0 0 0   ",
                    "                   ",
                    "     1 1 1 1 1 1   ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
            },
            new String[]{
                    "   0 0 0 0 0 0 0   ",
                    "                   ",
                    "   1 1 0 1 1 0 1 1 ",
                    "                   ",
                    "   0      0     0  ",
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
                    "     1 1 0 1 1     ",
                    "         2         ",
                    "  0              0 ",
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
                    " 1 1 1 1 1 1 1 1 1 ",
                    "         0         ",
                    "         3         ",
                    "                   ",
                    "                   ",
                    "                   ",
            },
            new String[]{
                    "                   ",
                    "    111     111    ",
                    "    111     111    ",
                    "                   ",
                    "    11   3    11   ",
                    "    22222222222    ",
                    "     0    0   0    ",
                    "                   ",
                    "                   ",
                    "                   ",
                    "                   ",
            },
            new String[]{
                    "                   ",
                    "    0000   0000    ",
                    "  2 2 1 1 1 1 1 1  ",
                    "   1 1 2 1 1 1 1   ",
                    "  1 1 1 2 0 1 1 0  ",
                    "                   ",
                    "      3  3  3      ",
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
                    "  2 2 2 2 2 0 0 0  ",
                    "   3   3   3   3   ",
                    "                   ",
                    "                   ",
                    "                   ",
            },
    };

    public static List<StormTrooper> getWaveStorm(int index) {
        List<StormTrooper> enemies = new LinkedList<>();
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
                        enemies.add(new DarthVader(x * cellX, y * cellY, 1));
                        break;
                    case '1':
                        enemies.add(new GroupStormTrooper(x * cellX, y * cellY, 2));
                        break;
                    case '2':
                        enemies.add(new TimerStormTrooper(x * cellX, y * cellY, 5 + (int)Math.floor(Math.random() * 10), 1));
                        break;
                    case '3':
                        enemies.add(new DeathStar(x * cellX, y * cellY, 2) );
                        break;
                }
            }
        }
        return enemies;
    }
}
