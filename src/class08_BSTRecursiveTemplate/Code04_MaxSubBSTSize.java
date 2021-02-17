package class08_BSTRecursiveTemplate;

import java.util.ArrayList;

public class Code04_MaxSubBSTSize {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int getBSTSize(Node head) {
		return 0;
	}

	public static void in(Node head, ArrayList<Node> arr) {
		return;
	}

	public static int maxSubBSTSize1(Node head) {
		return 0;
	}

	public static int maxSubBSTSize2(Node head) {
		return 0;
	}

	public static class Info {
		public boolean isBST;
		public int maxSubBSTSize;
		public int min;
		public int max;

		public Info(boolean is, int size, int mi, int ma) {
			isBST = is;
			maxSubBSTSize = size;
			min = mi;
			max = ma;
		}
	}

	public static Info process(Node head) {
		return null;
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
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
