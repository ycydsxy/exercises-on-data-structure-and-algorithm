package sxy.algorithm.nowcoder.basic.chapter04;

/**
 * 树形DP有关题目特点：题设和每个节点有关（或者所有子树），或者求解流程可以定成以每个节点为头的子树如何如何...
 * 
 * 解法：
 * 
 * 1) 分析可能性：一个节点满足条件需要左、右子树分别满足什么条件，且整合起来时需满足什么条件
 * 
 * 2) 建立信息类：需要左、右子树的信息，以及整合起来时候需要什么信息，建立一个类
 * 
 * 3) 写递归函数
 * 
 * 4) 整合左、右子树的返回值
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
	 * 是否是完全二叉树（CBT即从左到右依次怼）
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

		if (left.isCompleteBinaryTree && right.isCompleteBinaryTree) {// 左右都是完全二叉树
			if (left.depth == right.depth) {// 若左树深度等于右树
				if (left.count == ((1 << left.depth) - 1)) {// 左树是满的，整体才为完全二叉树
					flag = true;
				} else {// 左树不满

				}
			} else if (left.depth == right.depth + 1) {// 若左树深度等于右树深度加一
				if (right.count == ((1 << right.depth) - 1)) {// 右树是满的，整体才为完全二叉树
					flag = true;
				}
			} else {// 左右树深度之差大于一

			}
		} else {// 左右中至少有一个不是完全二叉树

		}

		return new CBT_Info(flag, Math.max(left.depth, right.depth) + 1,
				left.count + right.count + 1);
	}

	/**
	 * 是否是二叉搜索树（BST即对每个节点而言都有，左<根<右）
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
	 * 是否是平衡二叉树（BBT即每个节点都有左右两个子树的高度差的绝对值不超过1）
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
	 * 求二叉树中最大的距离
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
		int distance1 = left.height + right.height + 1;// 经过head节点的最大距离
		int distance2 = Math.max(left.distance, right.distance); // 不经过head节点的最大距离

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
