package class12_Dynamic_Programming;

/**
 * 假设有排成一行的N个位置，记为1~N，
 * N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，
 * 最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 * @author mingyan wang
 * @date 2021/3/21 3:12 PM
 */
public class Code01_RobotWalk {

    /**
     * 暴力递归
     * @param N N个位置，1到N
     * @param M 起始位置M，1到N之间
     * @param K 机器人必须走K步
     * @param P 终点🏁位置
     * @return 总共的方法数量
     */
    public static int ways1(int N, int M, int K, int P) {
        if (N < 2 || K < 1 || M < 1 || M > N || P > N || P < 1) {
            return 0;
        }
        return process1(N, M, K, P);
    }

    /**
     * N和targetPos不变
     * @param N N个位置，1到N
     * @param curPos 当前位置
     * @param rest 剩余步数
     * @param targetPos 目的地
     * @return 总共的方法数量
     */
    private static int process1(int N, int curPos, int rest, int targetPos) {
        if (rest == 0) {
            return curPos == targetPos ? 1 : 0;
        }
        int res = 0;
        // 当前在1位置，只能往2走
        if (curPos == 1) {
            return process1(N, 2, rest - 1, targetPos);
        }
        // 当前在N位置，只能往N-1走
        if (curPos == N) {
            return process1(N, N - 1, rest - 1, targetPos);
        }
        // 往左走
        res += process1(N, curPos - 1, rest - 1, targetPos);
        // 往右走
        res += process1(N, curPos + 1, rest - 1, targetPos);
        return res;
    }

    public static int ways2(int N, int M, int K, int P) {
        if (N < 2 || K < 1 || M < 1 || M > N || P > N || P < 1) {
            return 0;
        }
        return process2(N, M, K, P);
    }
    /**
     * 动态规划，变化的只有当前位置和剩余步数。
     * @param N N个位置，1到N
     * @param curPos 当前位置
     * @param rest 剩余步数
     * @param targetPos 目的地
     * @return 总共的方法数量
     */
    private static int process2(int N, int curPos, int rest, int targetPos) {
        int[][] dp = new int[N+1][rest+1];
        dp[targetPos][0] = 1;

        // 根据依赖关系，开始填dp表
        for (int j = 1; j < rest + 1; j++) {
            for (int i = 1; i < N + 1; i++) {
                if (i == 1) {
                    dp[i][j] += dp[i + 1][j - 1];
                    continue;
                }
                if (i == N) {
                    dp[i][j] += dp[i - 1][j - 1];
                    continue;
                }
                dp[i][j] += dp[i - 1][j - 1];
                dp[i][j] += dp[i + 1][j - 1];
            }
        }
        return dp[curPos][rest];
    }

    public static void main(String[] args) {
        System.out.println(ways1(7, 4, 9, 5));
        System.out.println(ways2(7, 4, 9, 5));
    }
}
