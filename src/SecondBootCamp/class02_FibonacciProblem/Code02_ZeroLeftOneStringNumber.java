package SecondBootCamp.class02_FibonacciProblem;

/**
 * @author mingyan wang
 * @date 2021/4/2 6:11 PM
 */
public class Code02_ZeroLeftOneStringNumber {
    // 必然是1开头，
    // 101xxxxx
    // 11(0/1)

    public static int getNum3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = { {1, 1}, {1, 0} };
        int[][] result = matrixPower(base, n - 2);
        return 2 * result[0][0] + result[1][0];
    }

    private static int[][] matrixPower(int[][] base, int power) {
        int[][] res = new int[base.length][base[0].length];
        int[][] temp = base;
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        while (power != 0) {
            if ((power & 1) != 0) {
                res = multiMat(res, temp);
            }
            temp = multiMat(temp, temp);
            power >>= 1;
        }
        return res;
    }

    private static int[][] multiMat(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    m1[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }
}
