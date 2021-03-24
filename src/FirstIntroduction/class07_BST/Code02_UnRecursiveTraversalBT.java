package FirstIntroduction.class07_BST;

import java.util.Stack;

/**
 * @author mingyan wang
 * @date 2021/2/14 7:34 PM
 */
public class Code02_UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.value);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }


    /**
     * TODO: 易错题
     * @param head
     */
    public static void in(Node head) {
        Stack<Node> stack = new Stack<>();
        if (head != null) {
            while (!stack.isEmpty() || head != null) {
                if (head.left != null) {
                    stack.push(head.left);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.value);
                    head = head.right;
                }
            }
        }
        System.out.println();
    }


    /**
     * 使用两个stack完成后序遍历
     * 头节点，右节点，左节点。逆序就是后序遍历
     * @param head
     */
    public static void pos1(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Stack<Node> printStack = new Stack<>();
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            printStack.push(node);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        while (!printStack.isEmpty()) {
            Node node = printStack.pop();
            System.out.println(node.value);
        }
        System.out.println();
    }

    /**
     * 使用一个stack完成后序遍历
     * @param head
     */
    public static void pos2(Node head) {
        if (head != null) {
            Node h = head;
            Node c;
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                   stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.println(stack.pop().value);
                    h = c;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }
}
