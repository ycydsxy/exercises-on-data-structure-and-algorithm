package sxy.algorithm.nowcoder.chapter04;

/**
 * ����DP�й���Ŀ�ص㣺�����ÿ���ڵ��йأ���������������
 * 
 * �ⷨ��
 * 
 * 1) ���������ԣ�һ���ڵ�����������Ҫ���������ֱ�����ʲô����������������ʱ������ʲô����
 * 
 * 2) ������Ϣ�ࣺ��Ҫ������������Ϣ���Լ���������ʱ����Ҫʲô��Ϣ������һ����
 * 
 * 3) д�ݹ麯��
 * 
 * 4) �������������ķ���ֵ
 * 
 * @author Kevin
 * 
 */
public class BinaryTreeDP {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * �Ƿ�����ȫ������
	 * 
	 * @param head
	 * @return
	 */
	public static boolean isCompleteBinaryTree(Node head) {
		return processCBT(head).isCompleteBinaryTree;
	}

	private static class CBT_Info {
		public boolean isCompleteBinaryTree;
		public int depth;
		public int count;

		public CBT_Info(boolean isCompleteBinaryTree, int depth, int count) {
			this.isCompleteBinaryTree = isCompleteBinaryTree;
			this.depth = depth;
			this.count = count;
		}
	}

	private static CBT_Info processCBT(Node head) {
		if (head == null) {
			return new CBT_Info(true, 0, 0);
		}

		CBT_Info left = processCBT(head.left);
		CBT_Info right = processCBT(head.right);

		boolean flag = false;

		if (left.isCompleteBinaryTree && right.isCompleteBinaryTree) {// ���Ҷ�����ȫ������
			if (left.depth == right.depth) {// ��������ȵ�������
				if (left.count == ((1 << left.depth) - 1)) {// ���������ģ������Ϊ��ȫ������
					flag = true;
				} else {// ��������

				}
			} else if (left.depth == right.depth + 1) {// ��������ȵ���������ȼ�һ
				if (right.count == ((1 << right.depth) - 1)) {// ���������ģ������Ϊ��ȫ������
					flag = true;
				}
			} else {// ���������֮�����һ

			}
		} else {// ������������һ��������ȫ������

		}

		return new CBT_Info(flag, Math.max(left.depth, right.depth) + 1,
				left.count + right.count + 1);
	}

	/**
	 * �Ƿ��Ƕ���������
	 * 
	 * @param head
	 * @return
	 */
	public static boolean isBinarySearchTree(Node head) {
		if (head == null) {
			return true;
		}
		return processBST(head).isBinarySearchTree;
	}

	private static BST_Info processBST(Node head) {
		if (head.left == null && head.right == null) {
			return new BST_Info(head.value, true);
		} else if (head.left == null && head.right != null) {
			return new BST_Info(head.value, head.value <= head.right.value);
		} else if (head.left != null && head.right == null) {
			return new BST_Info(head.value, head.value >= head.left.value);
		}

		boolean flag = false;

		BST_Info left = processBST(head.left);
		BST_Info right = processBST(head.right);

		if (left.isBinarySearchTree && right.isBinarySearchTree) {
			if (head.value >= left.value && head.value <= right.value) {
				flag = true;
			}
		}
		return new BST_Info(head.value, flag);
	}

	private static class BST_Info {
		public int value;
		public boolean isBinarySearchTree;

		public BST_Info(int value, boolean isBinarySearchTree) {
			this.value = value;
			this.isBinarySearchTree = isBinarySearchTree;
		}
	}

	/**
	 * �Ƿ���ƽ�������
	 * 
	 * @param head
	 * @return
	 */
	public static boolean isBalancedTree(Node head) {
		return processBT(head).isBalancedTree;
	}

	private static class BT_Info {
		public int height;
		public boolean isBalancedTree;

		public BT_Info(int height, boolean isBalancedTree) {
			this.isBalancedTree = isBalancedTree;
			this.height = height;
		}
	}

	private static BT_Info processBT(Node head) {
		if (head == null) {
			return new BT_Info(0, true);
		}

		boolean flag = false;

		BT_Info left = processBT(head.left);
		BT_Info right = processBT(head.right);

		if (left.isBalancedTree && right.isBalancedTree) {
			if (Math.abs(left.height - right.height) <= 1) {
				flag = true;
			}
		}

		return new BT_Info(Math.max(left.height, right.height) + 1, flag);
	}

	/**
	 * ������������ľ���
	 * 
	 * @param head
	 * @return
	 */
	public static int findMaxDistance(Node head) {
		return processFindDistance(head).distance;
	}

	private static class Dis_Info {
		public int distance;
		public int height;

		public Dis_Info(int distance, int height) {
			this.distance = distance;
			this.height = height;
		}
	}

	private static Dis_Info processFindDistance(Node head) {
		if (head == null) {
			return new Dis_Info(0, 0);
		}

		Dis_Info left = processFindDistance(head.left);
		Dis_Info right = processFindDistance(head.right);

		int height = Math.max(left.height, right.height) + 1;
		int distance1 = left.height + right.height + 1;// ����head�ڵ��������
		int distance2 = Math.max(left.distance, right.distance); // ������head�ڵ��������

		return new Dis_Info(Math.max(distance1, distance2), height);
	}

	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.left.left = new Node(1);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		head.right.right = new Node(10);
		head.right.right.left = new Node(9);
		head.right.right.right = new Node(11);

		printTree(head);
		System.out.println(isBalancedTree(head));
		System.out.println(isBinarySearchTree(head));
		System.out.println(isCompleteBinaryTree(head));
		System.out.println(findMaxDistance(head));

		head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);

		printTree(head);
		System.out.println(isBalancedTree(head));
		System.out.println(isBinarySearchTree(head));
		System.out.println(isCompleteBinaryTree(head));
		System.out.println(findMaxDistance(head));

		head = new Node(4);
		head.left = new Node(3);
		head.right = new Node(5);
		head.left.left = new Node(2);
		head.left.left.right = new Node(3);

		printTree(head);
		System.out.println(isBalancedTree(head));
		System.out.println(isBinarySearchTree(head));
		System.out.println(isCompleteBinaryTree(head));
		System.out.println(findMaxDistance(head));

		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		printTree(head);
		System.out.println(isBalancedTree(head));
		System.out.println(isBinarySearchTree(head));
		System.out.println(isCompleteBinaryTree(head));
		System.out.println(findMaxDistance(head));

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
