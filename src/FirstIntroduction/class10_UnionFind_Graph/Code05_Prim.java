package FirstIntroduction.class10_UnionFind_Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 只限于使用无向图，有向图需要处理。
 * 算法步骤：
 * 1. 任意选择graph的点集中的一个点
 * 2. 解锁该点的所有边，加入到minHeap
 * 3. 从minHeap选择一条边，如果边的两头都在解锁的点set，那么放弃这条边。
 *                      如果没有在，那么把新的一个点加入set，并且把点的nexts全部加入minHeap
 * 4. 循环之前3. 操作
 * @author mingyan wang
 * @date 2021/3/2 12:28 PM
 */
public class Code05_Prim {
    /**
     * 给定一个graph返回最小生成图的所有边的集合
     * @param graph
     * @return
     */
    public static Set<Edge> primMST(Graph graph) {
        // assert graph != null，图上有节点
        HashSet<Node> unlockedNodeSet = new HashSet<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        HashSet<Edge> resultSet = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (!unlockedNodeSet.contains(node)) {
                unlockedNodeSet.add(node);
                for (Edge edge : node.edges) {
                    minHeap.offer(edge);
                }
                while (!minHeap.isEmpty()) {
                    Edge edge = minHeap.poll();
                    Node toNode = edge.to;
                    if (!unlockedNodeSet.contains(toNode)) {
                        unlockedNodeSet.add(toNode);
                        resultSet.add(edge);
                        for (Edge toNodeEdge : toNode.edges) {
                            minHeap.offer(toNodeEdge);
                        }
                    }
                }
            }
        }
        return resultSet;
    }

    // 请保证graph是连通图
    // graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
    // 返回值是最小连通图的路径之和
    public static int prim(int[][] graph) {
        return 0;
    }
}
