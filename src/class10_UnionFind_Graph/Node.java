package class10_UnionFind_Graph;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author mingyan wang
 * @date 2021/3/2 12:28 PM
 */
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
