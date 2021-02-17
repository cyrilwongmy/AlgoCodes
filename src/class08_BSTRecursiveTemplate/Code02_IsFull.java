package class08_BSTRecursiveTemplate;

public class Code02_IsFull {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isFull1(Node head) {
		return true;
	}

	public static int h(Node head) {
	    return 0;
	}

	public static int n(Node head) {
		return 0;
	}

	public static boolean isFull2(Node head) {
		return true;
	}

	public static class Info {
		public int height;
		public int nodes;

		public Info(int h, int n) {
			height = h;
			nodes = n;
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
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isFull1(head) != isFull2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
