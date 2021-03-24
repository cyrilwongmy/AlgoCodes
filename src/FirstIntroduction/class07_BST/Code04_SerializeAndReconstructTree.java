package FirstIntroduction.class07_BST;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author mingyan wang
 * @date 2021/2/14 7:35 PM
 */
public class Code04_SerializeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 先序遍历，序列化二叉树，存储在一个队列中。
     * @param head 二叉树Root节点
     * @return 序列化后的二叉树队列
     */
    public static Queue<String> preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    private static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }


    /**
     * TODO: retry
     * @param prelist
     * @return
     */
    public static Node buildByPreQueue(Queue<String> prelist) {
        if (prelist == null || prelist.size() == 0) {
            return null;
        }
        return preb(prelist);
    }
    public static Node preb(Queue<String> prelist) {
        String nodeVal = prelist.poll();
        if (nodeVal == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(nodeVal));
        head.left = preb(prelist);
        head.right = preb(prelist);
        return head;
    }


    public static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        if (head == null) {
            ans.add(null);
            return ans;
        }
        ans.add(String.valueOf(head.value));
        queue.add(head);
        // root = 1
        // root.left = 2
        // root.right = 3
        // ans = 1 2 3 null null null null
        // queue =
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left != null) {
                ans.add(String.valueOf(node.left.value));
                queue.add(node.left);
            } else {
                ans.add(null);
            }
            if (node.right != null) {
                ans.add(String.valueOf(node.right.value));
                queue.add(node.right);
            } else {
                ans.add(null);
            }
        }
        return ans;
    }

    /**
     * TODO: retry
     * @param levelList
     * @return
     */
    public static Node buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        String headVal = levelList.poll();
        if (headVal == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(headVal));
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }
    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.valueOf(val));
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

    // for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> level = levelSerial(head);
            Node preBuild = buildByPreQueue(pre);
            Node levelBuild = buildByLevelQueue(level);
            if (!isSameValueStructure(preBuild, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
