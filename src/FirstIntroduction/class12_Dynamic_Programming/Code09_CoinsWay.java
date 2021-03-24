package FirstIntroduction.class12_Dynamic_Programming;

/**
 * @author mingyan wang
 * @date 2021/3/21 3:13 PM
 */
public class Code09_CoinsWay {
    // arr中都是正数且无重复值，返回组成aim的方法数
    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    private static int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int result = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            result += process1(arr, index + 1, rest - zhang * arr[index]);
        }
        return result;
    }

    // arr中都是正数且无重复值，返回组成aim的方法数
    public static int waysdp(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // index, rest
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = { 5, 2, 3, 1 };
        int sum = 350;
        System.out.println(ways(arr, sum));
        System.out.println(waysdp(arr, sum));
    }
}
