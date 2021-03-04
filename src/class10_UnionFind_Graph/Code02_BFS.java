package class10_UnionFind_Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author mingyan wang
 * @date 2021/3/2 12:27 PM
 */
public class Code02_BFS {

    /**
     * 从node出发，进行宽度优先遍历
     * 步骤：使用queue存储节点，遍历完一层再继续下一层
     * 要注意使用set来防止重复遍历
     * @param node
     */
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            // remove from the head of queue.
            Node node1 = queue.poll();
            System.out.println(node1);
            for (Node next : node1.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                }
            }
        }
    }
}
