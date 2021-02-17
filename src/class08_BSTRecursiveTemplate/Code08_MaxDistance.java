package class08_BSTRecursiveTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class Code08_MaxDistance {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int maxDistance1(Node head) {
		return 0;
	}

	public static ArrayList<Node> getPrelist(Node head) {
	    return null;
	}

	public static void fillPrelist(Node head, ArrayList<Node> arr) {
		return;
	}

	public static HashMap<Node, Node> getParentMap(Node head) {
	    return null;
	}

	public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {

	}

	public static int distance(HashMap<Node, Node> parentMap, Node o1, Node o2) {
		return 0;
	}

	public static int maxDistance2(Node head) {
		return 0;
	}

	public static class Info {
		public int maxDistance;
		public int height;

		public Info(int dis, int h) {
			maxDistance = dis;
			height = h;
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
			if (maxDistance1(head) != maxDistance2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
