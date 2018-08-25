package sxy.algorithm.nowcoder.basic.chapter04;

/**
 * 
 * 已知一棵完全二叉树，求其节点的个数 要求：时间复杂度低于O(N)，N为这棵树的节点个数
 * 
 * 解法：时间复杂度低于O(N)，故不能遍历，可以分为左右子树求其中一个。若左树深度和右树一样，则左树肯定是满的，只用搞右树；否则右树肯定是满的，只用搞左树。
 * 找深度的算法是O(logN)的，而每次都只用搞一半，故整体的算法时间复杂度是O((logN)^2)
 * 
 * @author Kevin
 * 
 */
public class CBTNodeNum {

	private static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + ", left=" + left + ", right="
					+ right + "]";
		}
	}

	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}

		int num = 0;
		while (head != null) {
			num++;
			int leftHeight = getCBTHeight(head.left);
			int rightHeight = getCBTHeight(head.right);

			if (leftHeight == rightHeight) {
				num += (1 << rightHeight) - 1;
				head = head.right;
			} else {
				num += (1 << rightHeight) - 1;
				head = head.left;

			}
		}

		return num;
	}

	private static int getCBTHeight(Node head) {
		if (head == null) {
			return 0;
		}

		int num = 0;
		while (head != null) {
			num++;
			head = head.left;
		}

		return num;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		printTree(head);
		System.out.println(nodeNum(head));

		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		printTree(head);
		System.out.println(nodeNum(head));

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
