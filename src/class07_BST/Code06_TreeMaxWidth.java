package class07_BST;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author mingyan wang
 * @date 2021/2/14 7:35 PM
 */
public class Code06_TreeMaxWidth {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        int max = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> hashMap = new HashMap<>();
        hashMap.put(head, 1);
        int curLevel = 1;
        int count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int curNodeLevel = hashMap.get(node);
            if (node.left != null) {
                queue.add(node.left);
                hashMap.put(node.left, curNodeLevel + 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                hashMap.put(node.right, curNodeLevel + 1);
            }
            if (curNodeLevel == curLevel) {
                count++;
            } else {
                max = Math.max(max, count);
                curLevel++;
                count = 1;
            }
        }
        // 由于我们更新的条件是依赖于新一层的节点，最后一层其实没有新一层的节点，所以这里要再更新一次count
        max = Math.max(max, count);
        return max;
    }

    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        int max = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curLevelEnd = head;
        Node nextLevelEnd = head;
        int count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                nextLevelEnd = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLevelEnd = node.right;
            }
            count++;
            if (node == curLevelEnd) {
                max = Math.max(max, count);
                curLevelEnd = nextLevelEnd;
                count = 0;
            }
        }
        return max;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }
}
