package class10_UnionFind_Graph;

import java.util.*;

/**
 * @author mingyan wang
 * @date 2021/3/2 12:27 PM
 */
public class Code03_TopologySort {

    // directed graph and no loop

    /**
     * 拓扑排序必须是有向图，并且是无环图
     * 算法步骤：
     * 1. 先把所有节点放到一个inMap表示入度有多少个。
     * 2. 准备一个queue，只有in=0的才进入队列。
     * 3. 遍历queue，不断弹出，将该节点加入最终结果的队列。并且需要消除该节点对于所有next的inMap的影响。
     * 4. 判断消除完影响后，是否有next in=0。如果有加入队列。
     * @param graph 需要排序的有向图
     * @return
     */
    public static List<Node> sortedTopology(Graph graph) {
        if (graph == null) {
            return null;
        }
        // key：某一个node
        // value：node剩余的.in
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            // 入度为0的点，才能进这个队列
            if (node.in == 0) {
                zeroInQueue.offer(node);
            }
        }
        ArrayList<Node> list = new ArrayList<>();
        // 拓扑排序的结果，依次加入result
        while (!zeroInQueue.isEmpty()) {
            Node node = zeroInQueue.poll();
            list.add(node);
            for (Node next : node.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.offer(next);
                }
            }
        }
        return list;
    }
}
