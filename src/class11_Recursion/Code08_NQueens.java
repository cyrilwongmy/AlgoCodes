package class11_Recursion;

/**
 * N 皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。
 * n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 * @author mingyan wang
 * @date 2021/3/8 11:18 AM
 */
public class Code08_NQueens {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        // record 数组的元素代表，i行的皇后放在了第几列的位置。
        int[] record = new int[n];
        // 递归尝试整个nxn方格放置皇后，寻找出总共的可能方案。
        return process1(0, record, n);
    }

    /**
     *
     * @param currentRow 当前尝试到第几行
     * @param record 记录每一行的皇后放在了哪个列
     * @param n 总共有多少行，多少列
     * @return 从当前行继续尝试，总共能放置n皇后的结果。
     */
    private static int process1(int currentRow, int[] record, int n) {
        // 来到终止行，当前有一种方法，结束递归。
        if (currentRow == n) {
            return 1;
        }
        int res = 0;
        // 尝试当前行皇后放置的位置
        for (int currentCol = 0; currentCol < n; currentCol++) {
            if (isValid(record, currentCol, currentRow)) {
                record[currentRow] = currentCol;
                res += process1(currentRow + 1, record, n);
            }
        }
        return res;
    }

    private static boolean isValid(int[] record, int currentColumn, int currentRow) {
        for (int row = 0; row < currentRow; row++) {
            if (record[row] == currentColumn || Math.abs(record[row] - currentColumn) == Math.abs(row - currentRow)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 不能尝试超过32位的情况
     * 根据位运算来计算
     * 步骤：
     * 1. 用一个limit来表示限制，1...11 1111 1111
     * 2. 列限制 0...00 0000 0000
     * 3. zuo对角线限制 0...00 0000 0000
     * 4. you对角线限制 0...00 0000 0000
     * @param n
     * @return
     */
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // colLim 列的限制，1的位置不能放皇后，0的位置可以
    // leftDiaLim 左斜线的限制，1的位置不能放皇后，0的位置可以
    // rightDiaLim 右斜线的限制，1的位置不能放皇后，0的位置可以
    private static int process2(int limit, int colLimit, int leftDiaLimit, int rightDiaLimit) {
        // 所有col都被占满了，表示结束base case
        if (colLimit == limit) {
            return 1;
        }
        int res = 0;
        // 计算当前行可以放置的位置
        // 总限制，这时候所有1是limit，0是可放置位置，但是32位 - n的部分的0会干扰选择。
        int totalLimit = colLimit | leftDiaLimit | rightDiaLimit;
        // 转换成0是limit，1是可放置位置，方便等会用最右边的1来寻找可防止位置，并且防止前面的0干扰选择。
        // limit = 000... 00 1111 1111
        // 000... 00 0000 1101
        // 000... 00 1111 0010
        int selectBit = limit & (~totalLimit);
        // get this 000... 00 0000 0010 first
        int mostRightOne = 0;
        while (selectBit != 0) {
            // ~selectBit + 1 = 000... 00 0000 1110
            // selectBit = 000... 00  1111 0010
            // mostRightOne = 000... 00 0000 0010
            mostRightOne = selectBit & (~selectBit + 1);
            selectBit -= mostRightOne;
            int newColLimit = colLimit | mostRightOne;
            int newLeftDiaLimit = (leftDiaLimit | mostRightOne) << 1;
            int newRightDiaLimit = (rightDiaLimit | mostRightOne) >>> 1;
            res += process2(limit, newColLimit, newLeftDiaLimit, newRightDiaLimit);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 14;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
