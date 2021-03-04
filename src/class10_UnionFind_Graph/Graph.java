package class10_UnionFind_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author mingyan wang
 * @date 2021/3/2 12:28 PM
 */
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
