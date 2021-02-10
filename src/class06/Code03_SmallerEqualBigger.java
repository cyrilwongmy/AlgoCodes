package class06;

/**
 * @author mingyan wang
 * @date 2021/2/10 11:09 PM
 */
public class Code03_SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }


    /**
     * 最简单的办法，把链表的节点放入数组，在数组上进行Partition。
     * @param head
     * @param pivot
     * @return
     */
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArray = new Node[i];
        cur = head;
        i = 0;
        while (cur != null) {
            nodeArray[i] = cur;
            cur = cur.next;
        }

        arrPartition(nodeArray, pivot);
        int j;
        for (j = 1; j < nodeArray.length; j++) {
            nodeArray[j - 1].next = nodeArray[j];
        }
        nodeArray[j - 1].next = null;
        return nodeArray[0];

    }

    private static void arrPartition(Node[] nodeArray, int pivot) {
        if (nodeArray == null) {
            return;
        }
        int leftArea = -1;
        int rightArea = nodeArray.length;
        int i = 0;
        while (i < rightArea) {
            if (nodeArray[i].value < pivot) {
                swap(nodeArray, i, leftArea + 1);
                leftArea++;
                i++;
            } else if (nodeArray[i].value > pivot) {
                swap(nodeArray, i, rightArea - 1);
                rightArea--;
            } else { // equal
                i++;
            }
        }
    }

    private static void swap(Node[] nodeArray, int i, int j) {
        Node temp = nodeArray[i];
        nodeArray[i] = nodeArray[j];
        nodeArray[j] = temp;
    }


    public static Node listPartition2(Node head, int pivot) {
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node greaterHead = null;
        Node greaterTail = null;
        Node cur = head;
        while (cur != null) {
            if (cur.value < pivot) {
                if (smallHead == null) {
                    smallHead = cur;
                    smallTail = cur;
                } else {
                    smallTail.next = cur;
                    smallTail = smallTail.next;
                }
            } else if (cur.value == pivot){
                if (equalHead == null) {
                    equalHead = cur;
                    equalTail = cur;
                } else {
                    equalTail.next = cur;
                    equalTail = equalTail.next;
                }
            } else { // greater than pivot
                if (greaterHead == null) {
                    greaterHead = cur;
                    greaterTail = cur;
                } else {
                    greaterTail.next = cur;
                    greaterTail = greaterTail.next;
                }
            }
            cur = cur.next;
        }

        Node newHead = null;
        // wrap up three lists
        if (smallHead == null) {
            if (equalHead == null) {
                newHead = greaterHead;
            } else {
                if (greaterHead != null) {
                    equalTail.next = greaterHead;
                }
                newHead = equalHead;
            }
        } else {
            if (equalHead == null) {
                smallTail.next = greaterHead;
            } else {
                smallTail.next = equalHead;
                equalTail.next = greaterHead;
            }
            newHead = smallHead;
        }

        return newHead;

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
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }


}
