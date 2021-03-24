package FirstIntroduction.class12_Dynamic_Programming;

/**
 * 范围上尝试的模型:
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
 * 玩家A和玩家B依次拿走每张纸牌， 规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 * @author mingyan wang
 * @date 2021/3/21 3:12 PM
 */
public class Code04_CardsInLine {
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }


    /**
     * 在L到R的范围上，先手拿牌
     * @param arr 可以选择的牌
     * @param L 左边界
     * @param R 右边界
     * @return 返回在L到R范围上先手拿牌可以获得的最多分数。
     */
    private static int f(int[] arr, int L, int R) {
        // 只剩一张牌
        if (L == R) {
            return arr[L];
        }
        // 拿最左边的牌
        int score1 = arr[L] + s(arr, L + 1, R);
        // 拿最右边的牌
        int score2 = arr[R] + s(arr, L, R - 1);
        return Math.max(score1, score2);
    }


    /**
     * 在L到R的范围上，后手拿牌
     * @param arr 可以选择的牌
     * @param L 左边界
     * @param R 右边界
     * @return 返回在L到R范围上后手拿牌可以获得的最多分数。
     */
    private static int s(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        // 拿最左边的牌
        int score1 = f(arr, L + 1, R);
        // 拿最右边的牌
        int score2 = f(arr, L, R - 1);
        return Math.min(score1, score2);
    }

    public static int windp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];
        for (int i = 0; i < N; i++) {
            f[i][i] = arr[i];
            s[i][i] = 0;
        }
        // 竖轴为L, 横轴为R，左上角为原点
        // f棋盘的L=R位置全为L
        // s棋盘的L=R位置全为0
        // 0,1 -> 1, 2 -> 2, 3
        for (int i = 1; i < N; i++) {
            int L = 0;
            int R = i;
            while (L < N && R < N) {
                f[L][R] = Math.max(arr[L] + s[L + 1][R], arr[R] + s[L][R - 1]);
                s[L][R] = Math.min(f[L + 1][R], f[L][R - 1]);
                // 对角线下一个值
                L++;
                R++;
            }
        }

        // f 依赖于s棋盘的(L + 1, R), (L, R - 1), 左边格子和下边格子
        // s 依赖于f棋盘的(L + 1, R), (L, R - 1)
        return Math.max(f[0][N - 1], s[0][N - 1]);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println(windp(arr));

    }

}
