package class12_Dynamic_Programming;

/**
 * @author mingyan wang
 * @date 2021/3/21 3:12 PM
 */
public class Code03_knapsack {
    /**
     * @param w   物品重量数组， [10, 50, 40, 20]
     * @param v   物品价值数组， [1, 5, 2, 3]
     * @param bag 总载重
     * @return 能装下的最多的价值
     */
    public static int getMaxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, bag);
    }

    /**
     * @param w          物品重量数组， [10, 50, 40, 20]
     * @param v          物品价值数组， [1, 5, 2, 3]
     * @param index
     * @param leftWeight
     * @return 返回当前选择下的最大价值
     */
    private static int process(int[] w, int[] v, int index, int leftWeight) {
        int newValue = 0;
        if (index == w.length) {
            return newValue;
        }
        // do not select current index item.
        newValue = process(w, v, index + 1, leftWeight);
        // select current index item.
        if (w[index] <= leftWeight) {
            newValue = Math.max(newValue, v[index] + process(w, v, index + 1, leftWeight - w[index]));
        }
        return newValue;
    }

    /**
     * 动态规划求解
     * index和leftWeight是变量，组成dp表
     * @param w   物品重量数组， [10, 50, 40, 20]
     * @param v   物品价值数组， [1, 5, 2, 3]
     * @param bag 总载重
     * @return 能装下的最多的价值
     */
    public static int getMaxValueDp(int[] w, int[] v, int bag) {
//        return process(w, v, 0, bag);
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        // dp[N][...] = 0
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest < bag + 1; rest++) {
                int res1 = dp[index + 1][rest];
                int res2 = -1;
                if (rest - w[index] >= 0) {
                    res2 = v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(res1, res2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(getMaxValue(weights, values, bag));
        System.out.println(getMaxValueDp(weights, values, bag));
    }
}
