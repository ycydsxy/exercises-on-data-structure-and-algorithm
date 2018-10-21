package sxy.algorithm.nowcoder.advanced.chapter03;

/**
 * Morris遍历
 * 
 * @author Kevin
 * 
 */
public class MorrisTraversal {

	// 递归，每个节点访问三次
	public static void process(Node head) {
		if (head == null) {
			return;
		}
		// 1
		// System.out.println(head.value);
		process(head.left);
		// 2
		// System.out.println(head.value);
		process(head.right);
		// 3
		// System.out.println(head.value);
	}

	public static class Node {
		public int value;
		Node left;
		Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// Morris序，有左子树的节点会到达两次，没有左子树的节点只会到达一次。(使用cur的左子树的最右节点的右指针来标记是否到达过cur，只有两个可能指向空和指向cur)
	public static void morris(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {// 有左子树
				while (mostRight.right != null && mostRight.right != cur) {// 找左子树上最右的节点
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// 第一次到达cur
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {// 第二次到达cur
					mostRight.right = null;
				}
			}
			cur = cur.right;
		}
	}

	// 先序遍历，打印行为放在第一次到达节点时
	public static void morrisPre(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {// 有左子树
				while (mostRight.right != null && mostRight.right != cur) {// 找左子树上最右的节点
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// 第一次到达cur
					System.out.println(cur.value);
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {// 第二次到达cur
					mostRight.right = null;
				}
			} else {// 没有左子树，只能到达一次，直接打印
				System.out.println(cur.value);
			}

			cur = cur.right;
		}
	}

	// 中序遍历，只能访问一次直接打印，能够访问两次则第二次打印，整合成向右移动之前打印即可
	public static void morrisIn(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {// 有左子树
				while (mostRight.right != null && mostRight.right != cur) {// 找左子树上最右的节点
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// 第一次到达cur
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {// 第二次到达cur
					mostRight.right = null;
				}
			}
			System.out.println(cur.value);
			cur = cur.right;
		}
	}

	// 后序遍历，打印时机放在第二次回到节点时，逆序打印该节点的左子树右边界，最后逆序打印整棵树右边界
	public static void morrisPos(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
					printEdge(cur.left);
				}
			}
			cur = cur.right;
		}
		printEdge(head);
		System.out.println();
	}

	// 逆序打印右边界,O(1)的额外空间复杂度
	private static void printEdge(Node head) {
		Node tail = reverseEdge(head);
		Node cur = tail;
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		reverseEdge(tail);
	}

	private static Node reverseEdge(Node from) {
		Node pre = null;
		Node next = null;
		while (from != null) {
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}

	private static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
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

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
		printTree(head);
		morrisIn(head);
		morrisPre(head);
		morrisPos(head);
		printTree(head);

	}

}
