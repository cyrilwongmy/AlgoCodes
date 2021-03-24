package FirstIntroduction.class10_UnionFind_Graph;

import java.util.*;

/**
 * @author mingyan wang
 * @date 2021/3/2 12:27 PM
 */
public class Code04_Kruskal {
    // Union-Find Set
    public static class UnionFind {
        // key 某一个节点， value key节点往上的节点
        private HashMap<Node, Node> fatherMap;
        // key 某一个集合的代表节点, value key所在集合的节点个数。方便进行小挂大操作
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findFather(Node n) {
            Stack<Node> path = new Stack<>();
            while (fatherMap.get(n) != n) {
                path.add(n);
                n = fatherMap.get(n);
            }
            while (!path.isEmpty()) {
                Node node = path.pop();
                fatherMap.put(node, n);
            }
            return n;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aFather = findFather(a);
            Node bFather = findFather(b);
            if (aFather != bFather) {
                Integer aSize = sizeMap.get(aFather);
                Integer bSize = sizeMap.get(bFather);
                Node bigSet = aSize > bSize ? aFather : bFather;
                Node smallSet = aSize > bSize ? bFather : aFather;
                fatherMap.put(smallSet, bigSet);
                sizeMap.remove(smallSet);
                sizeMap.put(bigSet, aSize + bSize);
            }

        }
    }


    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    /**
     * 步骤：
     * 1. 建立一个heap保存边，最小权重先被取出来
     * 2. 判断是否两边的点都在一个set，不在的话加入同一个set。这条边加入result set.
     * @param graph
     * @return
     */
    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<Edge> minEdgeHeap = new PriorityQueue<>();
        for (Edge edge : graph.edges) {
            minEdgeHeap.offer(edge);
        }
        HashSet<Edge> resultSet = new HashSet<>();
        while (!minEdgeHeap.isEmpty()) {
            Edge edge = minEdgeHeap.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                resultSet.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return resultSet;
    }
}
