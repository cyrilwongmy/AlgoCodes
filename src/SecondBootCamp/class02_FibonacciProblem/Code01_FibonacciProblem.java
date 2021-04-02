package SecondBootCamp.class02_FibonacciProblem;

/**
 * @author mingyan wang
 * @date 2021/4/2 6:11 PM
 */
public class Code01_FibonacciProblem {

    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }



    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int first = 1;
        int second = 1;
        for (int order = 3; order <= n; order++) {
            int temp = first + second;
            first = second;
            second = temp;
        }
        return second;
    }

    public static int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = new int[][]{ {1, 1}, {1, 0}};
        int[][] result = matrixPower(base, n - 2);
        return result[0][0] + result[1][0];
    }

    private static int[][] matrixPower(int[][] base, int power) {
        int[][] temp = base;
        int[][] result = new int[base.length][base[0].length];
        for (int i = 0; i < result.length; i++) {
            result[i][i] = 1;
        }
        while (power > 0) {
            if ((power & 1) != 0) {
                result = multiMatrix(result, temp);
            }
            temp = multiMatrix(temp, temp);
            power >>= 1;
        }
        return result;
    }

    private static int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] result = new int[m1.length][m2[0].length];
        // 最外层遍历m1矩阵每一行
        for (int i = 0; i < m1.length; i++) {
            // 遍历m2矩阵的列
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f3(n));
        System.out.println("===");
    }
}
