package class10_UnionFind_Graph;

/**
 * @author mingyan wang
 * @date 2021/3/2 12:28 PM
 */
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
