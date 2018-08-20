package sxy.algorithm.nowcoder.chapter04;

import sxy.algorithm.nowcoder.chapter04.BinaryTreeDP.Node;

/**
 * 
 * ��֪һ����ȫ������������ڵ�ĸ��� Ҫ��ʱ�临�Ӷȵ���O(N)��NΪ������Ľڵ����
 * 
 * �ⷨ��ʱ�临�Ӷȵ���O(N)���ʲ��ܱ��������Է�Ϊ��������������һ������������Ⱥ�����һ�����������϶������ģ�ֻ�ø����������������϶������ģ�ֻ�ø�������
 * ����ȵ��㷨��O(logN)�ģ���ÿ�ζ�ֻ�ø�һ�룬��������㷨ʱ�临�Ӷ���O((logN)^2)
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
