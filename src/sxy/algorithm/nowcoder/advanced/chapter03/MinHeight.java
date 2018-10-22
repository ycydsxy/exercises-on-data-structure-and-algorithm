package sxy.algorithm.nowcoder.advanced.chapter03;

/**
 * 求一个二叉树中的最浅深度，使得时间复杂度为O(N)，额外空间复杂度为O(1)。
 * 
 * 思路：利用Morris遍历，最浅深度即是所有叶节点的深度的最小值，找到所有叶节点的深度，答案一定在其中；所有的左子树最右节点（无左孩子）和整棵树的最右节点
 * （无左孩子）组成了所有了叶节点。
 * 
 * @author Kevin
 * 
 */
public class MinHeight {

	public static class Node {
		public int value;
		Node left;
		Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 树形DP，额外空间复杂度为O(log(N))，不满足要求
	public static int getMinHeight1(Node head) {
		return process(head);
	}

	public static int process(Node cur) {
		if (cur == null) {
			return 0;
		}
		int left = process(cur.left);
		int right = process(cur.right);
		int res = 0;

		if (left != 0 && right != 0) {// 两边均非空
			res = Math.min(left, right) + 1;
		} else if (left == 0 && right == 0) {// 两边均空
			res = 1;
		} else {// 两边只有一边为空
			res = Math.max(left, right) + 1;// 取非0值
		}
		return res;
	}

	// 利用Morris遍历，额外空间复杂度为O(1)
	public static int getMinHeight2(Node head) {
		if (head == null) {
			return 0;
		}

		int minHeight = Integer.MAX_VALUE;

		Node cur = head;
		Node mostRight = null;

		Node dummyHead = new Node(Integer.MAX_VALUE);
		dummyHead.left = head;
		Node pre = dummyHead;
		int preLevel = 0;

		while (cur != null) {
			int curLevel = getCurLevel(cur, pre, preLevel);// 从前一个节点的深度推断当前节点深度

			mostRight = cur.left;
			if (mostRight != null) {
				int count = 1;// 找当前节点左子树右边界有多少个节点
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
					count++;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;

					if (mostRight.left == null) {// 左子树的最右节点（无左孩子）是叶节点，可以统计深度
						minHeight = Math.min(minHeight, curLevel + count);
					}
					pre = cur;
					preLevel = curLevel;

					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			pre = cur;
			preLevel = curLevel;
			cur = cur.right;
		}

		// 搞整棵树的最右节点
		if (pre.left == null) {// Morris遍历完成时，最后一个到达的节点就是整棵树的最右节点，如果没左孩子则是叶节点
			minHeight = Math.min(minHeight, preLevel);
		}
		return minHeight;
	}

	// 知道前一个到达节点的深度，求当前节点的深度
	private static int getCurLevel(Node cur, Node pre, int preLevel) {
		if (cur.left == null) {// cur没左孩子，所以pre肯定是cur的父
			return preLevel + 1;
		} else {// cur有左孩子，找其左子树的最右节点
			Node mostRight = cur.left;
			int count = 1;
			while (mostRight.right != null && mostRight.right != cur) {
				mostRight = mostRight.right;
				count++;
			}

			if (mostRight == pre) {// 左孩子最右节点是pre（说明第二次来到）
				return preLevel - count;
			} else {// 左孩子最右节点不是pre（说明第一次来到，pre是父）
				return preLevel + 1;
			}
		}
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
		System.out.println(getMinHeight1(head));
		System.out.println(getMinHeight2(head));

		head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);

		printTree(head);
		System.out.println(getMinHeight1(head));
		System.out.println(getMinHeight2(head));

		head = new Node(4);
		head.left = new Node(3);
		head.right = new Node(5);
		head.left.left = new Node(2);
		head.left.left.right = new Node(3);

		printTree(head);
		System.out.println(getMinHeight1(head));
		System.out.println(getMinHeight2(head));

		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		printTree(head);
		System.out.println(getMinHeight1(head));
		System.out.println(getMinHeight2(head));

		head = new Node(1);
		head.right = new Node(2);
		head.right.right = new Node(3);
		head.right.right.right = new Node(4);

		printTree(head);
		System.out.println(getMinHeight1(head));
		System.out.println(getMinHeight2(head));

		head = new Node(1);
		head.left = new Node(2);
		head.left.left = new Node(3);
		head.left.left.left = new Node(4);

		printTree(head);
		System.out.println(getMinHeight1(head));
		System.out.println(getMinHeight2(head));

		head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.left.left = new Node(1);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		head.right.right = new Node(10);

		printTree(head);
		System.out.println(getMinHeight1(head));
		System.out.println(getMinHeight2(head));
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
}
