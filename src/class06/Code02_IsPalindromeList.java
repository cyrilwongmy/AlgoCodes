package class06;

import java.util.Stack;

/**
 * @author mingyan wang
 * @date 2021/2/8 10:20 PM
 */
public class Code02_IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * Space complexity: O(N)
     * Time Complexity: O(N)
     * Procedure:
     * 1. push all elements into stack
     * 2. pop all elements out of stack and compare with element fetched from list
     */
    public static boolean isPalindrome1(Node head) {
        Stack<Node> nodeStack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            nodeStack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (!nodeStack.empty() && cur != null) {
            Node node = nodeStack.pop();
            if (node.value != cur.value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // need n/2 extra space

    /**
     * Push half elements into stack,
     * and then pop up elements and compare with latter half part of list
     */
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // Fast pointer
        Node cur = head;
        // Slow pointer
        Node right = head.next;
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> nodeStack = new Stack<>();
        while (right != null) {
            nodeStack.push(right);
            right = right.next;
        }
        while (!nodeStack.isEmpty()) {
            Node node = nodeStack.pop();
            if (node.value != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * need O(1) extra space
     */
    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node markSlowNext = slow.next;
        Node newNext = slow;
        Node newPre = slow.next;
        slow.next = null;
        while (newPre != null) {
            Node temp = newPre.next;
            newPre.next = newNext;
            newNext = newPre;
            newPre = temp;
        }
        Node markRightMost = newNext;
        Node rightListNode = newNext;
        while (head != null && rightListNode != null) {
            if (head.value != rightListNode.value) {
                return false;
            }
            rightListNode = rightListNode.next;
            head = head.next;
        }
        Node backPre = markRightMost.next;
        Node backNext = markRightMost;
        backNext.next = null;
        while (backPre != null) {
            Node tempBackPreNext = backPre.next;
            backPre.next = backNext;
            backNext = backPre;
            backPre = tempBackPreNext;
        }

        return true;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }
}
