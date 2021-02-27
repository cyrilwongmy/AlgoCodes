package class08_BSTRecursiveTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class Code07_lowestAncestor {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node lowestAncestor1(Node head, Node o1, Node o2) {
		return null;
	}

	public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
	}

	public static Node lowestAncestor2(Node head, Node o1, Node o2) {
		Info info = process(head, o1, o2);
		if (info != null) {
			if (info.findO1 && info.findO2 && info.ans != null) {
				return info.ans;
			}
		}
		return null;
	}

	public static class Info {
		public Node ans;
		public boolean findO1;
		public boolean findO2;

		public Info(Node a, boolean f1, boolean f2) {
			ans = a;
			findO1 = f1;
			findO2 = f2;
		}
	}

	public static Info process(Node head, Node o1, Node o2) {
	 	if (head == null) {
			return null;
		}
	 	Info leftInfo = process(head.left, o1, o2);
	 	Info rightInfo = process(head.right, o1, o2);
	 	Boolean findO1 = false;
		Boolean findO2 = false;
		if (leftInfo != null) {
	 	    if (leftInfo.findO1 && !leftInfo.findO2) {
	 	    	findO1 = true;
			} else if (!leftInfo.findO1 && leftInfo.findO2) {
				findO2 = true;
			} else {

			}
		}
	 	if (rightInfo != null) {

		}
	 	Node ans = null;


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

	// for test
	public static Node pickRandomOne(Node head) {
		if (head == null) {
			return null;
		}
		ArrayList<Node> arr = new ArrayList<>();
		fillPrelist(head, arr);
		int randomIndex = (int) (Math.random() * arr.size());
		return arr.get(randomIndex);
	}

	// for test
	public static void fillPrelist(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			Node o1 = pickRandomOne(head);
			Node o2 = pickRandomOne(head);
			if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
