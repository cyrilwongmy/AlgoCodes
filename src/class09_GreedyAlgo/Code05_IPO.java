package class09_GreedyAlgo;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author mingyan wang
 * @date 2021/2/27 7:08 PM
 */
public class Code05_IPO {
    /**
     *
     * @param K 串行最多做的k个项目
     * @param W 初始资金
     * @param Profits Profits[i]表示i号项目在扣除花费之后还能挣到的钱（利用）
     * @param Capital Capital[i]表示i号项目的花费
     * @return 最后获得的最大钱数
     */
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        if (Capital == null || Capital.length == 0) {
            return 0;
        }
        // 花费放入最小堆
        PriorityQueue<Program> minCostHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.c));
        // 解锁的项目放入大根堆
        PriorityQueue<Program> maxProfitHeap = new PriorityQueue<>((o1, o2) -> o2.p - o1.p);
        for (int i = 0; i < Capital.length; i++) {
            minCostHeap.add(new Program(Profits[i], Capital[i]));
        }
        int curTotalMoney = W;
        for (int j = 0; j < K; j++) {
            // 有项目
            while (!minCostHeap.isEmpty() && minCostHeap.peek().c <= W) {
                maxProfitHeap.add(maxProfitHeap.poll());
            }
            if (maxProfitHeap.isEmpty()) {
                return W;
            }
            W += maxProfitHeap.poll().p;
        }
        return curTotalMoney;
    }
    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }
}
