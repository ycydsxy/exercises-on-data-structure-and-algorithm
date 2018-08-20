package sxy.algorithm.nowcoder.chapter04;

/**
 * �ڶ��������ҵ�һ���ڵ�ĺ�̽ڵ㣬����ÿ���ڵ㶼��һ��ָ�򸸽ڵ��ָ�룬ֻ��һ���� �������е�ĳ���ڵ� node����ʵ�ַ���node�ĺ�̽ڵ�ĺ������ڶ�
 * ��������������������У� node����һ���ڵ����node�ĺ�̽ڵ㡣
 * 
 * @author Kevin
 * 
 */
public class SuccessorNode {

	private static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node getSuccessorNode(Node node) {
		if (node == null) {
			return null;
		}

		if (node.right != null) {// �����ӽڵ�
			return getMostLeftNode(node.right);
		} else {// û���ӽڵ�
			if (node.parent == null) {// û���ڵ�
				return null;
			} else {// �и��ڵ�
				if (node == node.parent.left) {// ��ǰ�ڵ��Ǹ��ڵ�����ӽڵ�
					return node.parent;
				} else {// ��ǰ�ڵ��Ǹ��ڵ�����ӽڵ�
					Node cur = node;
					while (cur.parent != null && cur.parent.right == cur) {
						cur = cur.parent;
					}
					return cur.parent;
				}

			}
		}
	}

	private static Node getMostLeftNode(Node node) {
		if (node == null) {
			return null;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	public static void main(String[] args) {
		Node head = new Node(6);
		head.parent = null;
		head.left = new Node(3);
		head.left.parent = head;
		head.left.left = new Node(1);
		head.left.left.parent = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right = new Node(4);
		head.left.right.parent = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right = new Node(9);
		head.right.parent = head;
		head.right.left = new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = new Node(10);
		head.right.right.parent = head.right;

		printTree(head);

		Node test = head.left.left;
		System.out.println(test.value + " next: "
				+ getSuccessorNode(test).value);
		test = head.left.left.right;
		System.out.println(test.value + " next: "
				+ getSuccessorNode(test).value);
		test = head.left;
		System.out.println(test.value + " next: "
				+ getSuccessorNode(test).value);
		test = head.left.right;
		System.out.println(test.value + " next: "
				+ getSuccessorNode(test).value);
		test = head.left.right.right;
		System.out.println(test.value + " next: "
				+ getSuccessorNode(test).value);
		test = head;
		System.out.println(test.value + " next: "
				+ getSuccessorNode(test).value);
		test = head.right.left.left;
		System.out.println(test.value + " next: "
				+ getSuccessorNode(test).value);
		test = head.right.left;
		System.out.println(test.value + " next: "
				+ getSuccessorNode(test).value);
		test = head.right;
		System.out.println(test.value + " next: "
				+ getSuccessorNode(test).value);
		test = head.right.right; // 10's next is null
		System.out.println(test.value + " next: " + getSuccessorNode(test));
	}

	private static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 7);
		System.out.println();
	}

	private static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	private static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}
}
