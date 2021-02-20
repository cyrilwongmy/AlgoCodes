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
		if (head == null) {
			return 0;
		}
		ArrayList<Node> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).value <= arr.get(i - 1).value) {
				return 0;
			}
		}
		return arr.size();
	}

	public static void in(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}

	public static int maxSubBSTSize1(Node head) {
		if (head == null) {
			return 0;
		}
		int h = getBSTSize(head);
		if (h != 0) {
			return h;
		}
		return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
	}

	public static int maxSubBSTSize2(Node head) {
	    if (head == null) {
	    	return 0;
		}
		return process(head).maxSubBSTSize;
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

	/**
	 * TODO:
	 * 子树需要提供Info，包含子树最大值size，子树最小值min，子树是不是BST，子树的最大BST size。
	 * @param head
	 * @return
	 */
	public static Info process(Node head) {
		if (head == null) {
			return null;
		}
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		int min = head.value;
		int max = head.value;
		int maxBSTSize = 0;
		if (leftInfo != null) {
			min = Math.min(min, leftInfo.min);
			max = Math.max(max, leftInfo.max);
			maxBSTSize = Math.max(maxBSTSize, leftInfo.maxSubBSTSize);
		}
		if (rightInfo != null) {
			min = Math.min(min, rightInfo.min);
			max = Math.max(max, rightInfo.max);
			maxBSTSize = Math.max(maxBSTSize, rightInfo.maxSubBSTSize);
		}
		boolean isBST = false;
		if ((leftInfo == null || (leftInfo.isBST && leftInfo.max < head.value))
				&& (rightInfo == null || (rightInfo.isBST) && rightInfo.min > head.value)) {
			isBST = true;
			maxBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
					+ (rightInfo == null ? 0: rightInfo.maxSubBSTSize) + 1;
		}



		return new Info(isBST, maxBSTSize, min, max);
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
