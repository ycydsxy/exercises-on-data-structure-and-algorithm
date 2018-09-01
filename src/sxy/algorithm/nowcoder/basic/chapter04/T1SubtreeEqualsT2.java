package sxy.algorithm.nowcoder.basic.chapter04;

/**
 * 两棵二叉树T1和T2，判断T2是否是T1的子树
 * 
 * 解法：这里使用递归
 * 
 * @author Kevin
 * 
 */
public class T1SubtreeEqualsT2 {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 判断t2是否是t1的子树
	public static boolean isSubtree(Node t1, Node t2) {
		boolean res = process(t1, t2);// 首先看是不是从t1节点开始的子树

		if (t1.left != null) {
			res = res || isSubtree(t1.left, t2);
		}
		if (t1.right != null) {
			res = res || isSubtree(t1.right, t2);
		}
		return res;
	}

	// 判断t2是否是t1中从节点cur1开始的子树
	private static boolean process(Node cur1, Node t2) {
		if (t2 == null) {
			return true;
		}

		if (cur1 == null || cur1.value != t2.value) {
			return false;
		}
		return process(cur1.left, t2.left) && process(cur1.right, t2.right);
	}

	public static void main(String[] args) {
		Node t1 = new Node(1);
		t1.left = new Node(2);
		t1.right = new Node(3);
		t1.left.left = new Node(4);
		t1.left.right = new Node(5);
		t1.right.left = new Node(6);
		t1.right.right = new Node(7);
		t1.left.left.right = new Node(8);
		t1.left.right.left = new Node(9);

		Node t2 = new Node(2);
		t2.left = new Node(4);
		t2.left.right = new Node(8);
		t2.right = new Node(5);
		t2.right.left = new Node(9);

		System.out.println(isSubtree(t1, t2));

	}

}
