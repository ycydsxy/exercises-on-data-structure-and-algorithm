package sxy.algorithm.nowcoder.basic.chapter04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化及反序列化二叉树（前序、层次）
 * 
 * @author Kevin
 * 
 */
public class SerializeAndReconstructTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static String serialByPre(Node head) {
		if (head == null) {
			return "#!";
		}

		String res = head.value + "!";
		res += serialByPre(head.left);
		res += serialByPre(head.right);
		return res;
	}

	public static Node reconByPreString(String preStr) {
		String[] values = preStr.split("!");
		Queue<String> queue = new LinkedList<String>();
		for (int i = 0; i != values.length; i++) {
			queue.offer(values[i]);
		}
		return reconPreOrder(queue);
	}

	// 和前序遍历的递归写法相似，也需要递归
	private static Node reconPreOrder(Queue<String> queue) {
		String value = queue.poll();
		if (value.equals("#")) {
			return null;
		}
		Node head = generateNodeByString(value);
		head.left = reconPreOrder(queue);
		head.right = reconPreOrder(queue);
		return head;
	}

	public static String serialByLevel(Node head) {
		StringBuilder res = new StringBuilder();
		Queue<Node> queue = new LinkedList<>();
		queue.offer(head);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			res.append(cur == null ? "#" : cur.value).append("!");
			if (cur != null) {
				queue.offer(cur.left);
				queue.offer(cur.right);
			}
		}
		return res.toString();
	}

	public static Node reconByLevelString(String levelStr) {
		String[] values = levelStr.split("!");
		int index = 0;

		// 洗参数
		Node head = generateNodeByString(values[index++]);
		if (head == null) {
			return null;
		}

		// 用一个辅助队列储存已重建的节点
		Queue<Node> help = new LinkedList<>();
		help.offer(head);
		while (!help.isEmpty()) {
			Node cur = help.poll();
			cur.left = generateNodeByString(values[index++]);
			cur.right = generateNodeByString(values[index++]);
			if (cur.left != null) {
				help.offer(cur.left);
			}
			if (cur.right != null) {
				help.offer(cur.right);
			}
		}
		return head;
	}

	private static Node generateNodeByString(String val) {
		if (val.equals("#")) {
			return null;
		}
		return new Node(Integer.valueOf(val));
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
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

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = null;
		printTree(head);

		String pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		String level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(1);
		printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.right = new Node(5);
		printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(100);
		head.left = new Node(21);
		head.left.left = new Node(37);
		head.right = new Node(-42);
		head.right.left = new Node(0);
		head.right.right = new Node(666);
		printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

	}
}
