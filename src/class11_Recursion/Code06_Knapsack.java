package class11_Recursion;

/**
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 *
 * @author mingyan wang
 * @date 2021/3/8 11:18 AM
 */
public class Code06_Knapsack {
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
        if (w[index] <= leftWeight) {
            // choose current index item
            newValue = v[index] + process(w, v, index + 1, leftWeight - w[index]);
            // don't choose current index item
            newValue = Math.max(newValue, process(w, v, index + 1, leftWeight));
            return newValue;
        } else { // cannot put current item
            // go to next index
            return process(w, v, index + 1, leftWeight);
        }
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
//        System.out.println(maxValue(weights, values, bag));
//        System.out.println(dpWay(weights, values, bag));

//        int[] weights = {10, 50, 40, 20};
//        int[] values = {1, 5, 9, 3};
//        int bag = 50;
        System.out.println(getMaxValue(weights, values, bag));
    }
}
