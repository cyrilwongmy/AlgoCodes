package class11_Recursion;

/**
 * 范围型问题：
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
 * 玩家A和玩家B依次拿走每张纸牌，
 * 规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 * { 10, 100, 7, 1 }
 * @author mingyan wang
 * @date 2021/3/8 11:18 AM
 */
public class Code07_CardsInLine {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    /**
     * 在L到R范围先手情况下，获得的最大分数
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        // take leftmost
        int leftMost = 0;
        leftMost = arr[L] + s(arr, L + 1, R);
        // take rightmost
        int rightMost = 0;
        rightMost = arr[R] + s(arr, L, R - 1);

        return Math.max(leftMost, rightMost);
    }

    /**
     * 在L到R范围后手情况下，获得的最大分数。
     * 会被先手counter。
     * NOTE: 如果 i == j，表示就剩一张牌了。并且自己是后手，那肯定没有自己的事情了，直接返回。
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int s(int[] arr, int L, int R) {
        if (L == R) {
//            return arr[L];
            return 0;
        }
        int leftMost = 0;
        int rightMost = 0;
//        leftMost = arr[L] + f(arr, L + 1, R);
//        rightMost = arr[R] + f(arr, L, R - 1);
        // arr[i] 被对手拿了，自己相当于从 i+1到j "先手" 取最大
        leftMost = f(arr, L + 1, R);
        rightMost = f(arr, L, R - 1);
        // arr[j] 被对手拿了，自己相当于从 j-1到i "先手" 找最大
        // because current I am second pick up.
        // First picker will leave the lowest score for me.
        return Math.min(leftMost, rightMost);

    }



    public static void main(String[] args) {
        int[] arr = { 1, 9, 1 };
        System.out.println(win1(arr));
//        System.out.println(win2(arr));

    }
}
