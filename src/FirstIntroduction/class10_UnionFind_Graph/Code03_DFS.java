package FirstIntroduction.class10_UnionFind_Graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author mingyan wang
 * @date 2021/3/2 12:27 PM
 */
public class Code03_DFS {
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node node1 = stack.pop();
            for (Node next : node1.nexts) {
                if (!set.contains(next)) {
                    stack.push(node1);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
