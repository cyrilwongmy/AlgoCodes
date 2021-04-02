package FirstIntroduction.class12_Dynamic_Programming;

/**
 * @author mingyan wang
 * @date 2021/3/21 3:13 PM
 */
public class Code07_HorseJump {

    // 10*9
    // 0~9 y
    // 0~8 x
    public static int ways(int a, int b, int step) {
        return f(a, b, step);
    }

    private static int f(int x, int y, int step) {
        if (step == 0) {
            return x == 0 && y == 0 ? 1 : 0;
        }
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }
        return f(x + 2, y + 1, step - 1) +
                f(x + 2, y - 1, step - 1) +
                f(x + 1, y + 2, step - 1) +
                f(x + 1, y - 2, step - 1) +
                f(x - 1, y + 2, step - 1) +
                f(x - 1, y - 2, step - 1) +
                f(x - 2, y + 1, step - 1) +
                f(x - 2, y - 1, step - 1);
    }

    public static int waysdp(int x, int y, int level) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }

        int[][][] dp = new int[9][10][level + 1];
        dp[0][0][0] = 1;
        for (int curLevel = 1; curLevel < level + 1; curLevel ++) {
            for (int curX = 0; curX < 9 ; curX++) {
                for (int curY = 0; curY < 10; curY++) {
                    dp[curX][curY][curLevel] =
                            getValue(curX + 2, curY + 1, curLevel - 1, dp)+
                            getValue(curX + 2, curY - 1, curLevel - 1, dp)+
                            getValue(curX + 1, curY + 2, curLevel - 1, dp)+
                            getValue(curX + 1, curY - 2, curLevel - 1, dp)+
                            getValue(curX - 1, curY + 2, curLevel - 1, dp)+
                            getValue(curX - 1, curY - 2, curLevel - 1, dp)+
                            getValue(curX - 2, curY + 1, curLevel - 1, dp)+
                            getValue(curX - 2, curY - 1, curLevel - 1, dp);
                }
            }
        }
        return dp[x][y][level];
    }

    public static int getValue(int x, int y, int level, int[][][] dp) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }
        return dp[x][y][level];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(ways(x, y, step));
        System.out.println(waysdp(x, y, step));
    }
}
