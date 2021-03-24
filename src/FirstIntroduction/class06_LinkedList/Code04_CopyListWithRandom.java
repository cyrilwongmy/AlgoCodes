package FirstIntroduction.class06_LinkedList;

import java.util.HashMap;

/**
 * @author mingyan wang
 * @date 2021/2/10 11:09 PM
 */
public class Code04_CopyListWithRandom {
    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node copyListWithRand1(Node head) {
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            hashMap.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            Node curCopy = hashMap.get(cur);
            curCopy.next = hashMap.get(cur.next);
            curCopy.rand = hashMap.get(cur.rand);
            cur = cur.next;
        }
        return hashMap.get(head);
    }

    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        while (cur != null) {
            Node originNext = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = originNext;
            cur = originNext;
        }
        cur = head;
        while (cur != null && cur.next != null) {
            Node curCopy = cur.next;
            // check null pointer for cur.rand.next
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = cur.next.next;
        }
        cur = head;
        Node newHead = cur.next;
        while (cur != null) {
            Node originNext = cur.next.next;
            Node curCopy = cur.next;

            cur.next = originNext;
            // check null pointer for .next
            curCopy.next = originNext != null ? originNext.next : null;

            cur = originNext;
        }
        return newHead;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }
}
