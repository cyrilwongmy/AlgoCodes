package class06_LinkedList;

/**
 * @author mingyan wang
 * @date 2021/2/10 11:09 PM
 */
public class Code05_FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }


    public static Node getIntersectNode(Node head1, Node head2) {
        if(head1 == null || head2 == null) {
            return null;
        }

        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);

        // 两个链表都没有环
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }

        if (loop1 != null && loop2 != null) {
            return bothLoop(loop1, loop2, head1, head2);
        }

        return null;
    }

    private static Node bothLoop(Node loop1, Node loop2, Node head1, Node head2) {
        // 入环位置相同，需要找到相交位置
        if (loop1 == loop2) {
            Node cur1 = head1;
            Node cur2 = head2;
            int count = 0;
            while (cur1 != loop1) {
                count++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                count--;
                cur2 = cur2.next;
            }
            cur1 = count > 0 ? head1 : head2;
            cur2 = count > 0 ? head2 : head1;
            count = Math.abs(count);
            while (count > 0) {
                cur1 = cur1.next;
                count--;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            Node cur = loop1.next;
            while (cur != loop1) {
                if (cur == loop2) {
                    return loop1;
                }
                cur = cur.next;
            }
            return null;
        }

    }

    /**
     * 返回该链表第一个入环节点，如果无环，返回null。
     * 如果有环，返回第一个入环节点。
     * 如果是整个链表就是一个环，那么返回head。
     * @param head List to find first enter loop node
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast != slow) {
            // 如果出现null情况，说明是单链表无环。
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    public static Node noLoop(Node head1, Node head2) {
        Node cur1 = head1;
        Node pre1 = null;
        int count = 0;
        while (cur1 != null) {
            pre1 = cur1;
            cur1 = cur1.next;
            count++;
        }
        Node cur2 = head2;
        Node pre2 = null;
        while (cur2 != null) {
            pre2 = cur2;
            cur2 = cur2.next;
            count--;
        }
        if (pre1 != pre2) {
            return null;
        }
        // pre1 == pre2，最后相交了
        // long list head
        cur1 = count > 0 ? head1 : head2;
        // short list head
        cur2 = count > 0 ? head2 : head1;
        count = Math.abs(count);
        while (count > 0) {
            cur1 = cur1.next;
            count--;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }



    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }

}
