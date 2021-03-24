package FirstIntroduction.class12_Dynamic_Programming;


/**
 * 给定一个数组，代表每个人喝完咖啡准备刷杯子的时间
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 返回让所有咖啡杯变干净的最早完成时间 三个参数：int[] arr、int a、int b
 * @author mingyan wang
 * @date 2021/3/21 3:13 PM
 */
public class Code06_Coffee {

    public static int minTime(int[] drinks, int a, int b) {
        if (b < a) {
            return drinks[drinks.length - 1] + b;
        }
        return process(drinks, a, b, 0, 0);
    }


    public static int process(int[] drinks, int a, int b, int index, int washLine) {
        if (index == drinks.length - 1) {
            return Math.min(drinks[index] + b, Math.max(washLine, drinks[index]) + a);
        }
        // 选择用机器洗
        // 机器洗，开始的时间
        int endWashTime = (Math.max(washLine, drinks[index]) + a);
        int washResult = Math.max(endWashTime, process(drinks, a, b, index + 1, endWashTime));

        // 选择自动风干
        int p2 = drinks[index] + b;
        int dryResult = Math.max(p2, process(drinks, a, b, index + 1, washLine));
        // 做决策
        return Math.min(washResult, dryResult);
    }

    public static int minTimeDp(int[] drinks, int a, int b) {
        if (a >= b) {
            return drinks[drinks.length - 1] + b;
        }

        int N = drinks.length;
        int limit = 0;
        for (int i = 0; i < N; i++) {
            limit = Math.max(drinks[i], limit) + a;
        }
        int[][] dp = new int[N][limit + 1];
        for (int washLine = 0; washLine < limit + 1; washLine++) {
            dp[N - 1][washLine] = Math.min(
                    drinks[N - 1] + b,
                    Math.max(washLine, drinks[N - 1]) + a);
        }
        for (int index = N - 2; index >= 0; index--) {
            for (int washLine = 0; washLine < limit + 1; washLine++) {
                // 选择用机器洗
                // 机器洗，开始的时间
                int washResult = Integer.MAX_VALUE;
                int endWashTime = (Math.max(washLine, drinks[index]) + a);
                if (endWashTime <= limit) {
                    washResult = Math.max(endWashTime, dp[index + 1][endWashTime]);
                }

                // 选择自动风干
                int p2 = drinks[index] + b;
                int dryResult = Math.max(p2, dp[index + 1][washLine]);
                // 做决策
                dp[index][washLine] = Math.min(washResult, dryResult);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {1,1,5,5,7,10,12,12,12,12,12,12,12,15};
        int a = 3;
        int b = 10;

        System.out.println(process(arr, a, b, 0, 0));
        System.out.println(minTimeDp(arr, a, b));
    }
}
