package sxy.algorithm.nowcoder.chapter04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的各种遍历方法 先、中、后序遍历的递归和非递归写法，以及按层次遍历
 * 
 * @author Kevin
 * 
 */
public class BinaryTreeTraversal {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static void preOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		preOrderRecur(head.left);
		preOrderRecur(head.right);

	}

	public static void inOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		inOrderRecur(head.left);
		System.out.print(head.value + " ");
		inOrderRecur(head.right);

	}

	public static void posOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		posOrderRecur(head.left);
		posOrderRecur(head.right);
		System.out.print(head.value + " ");

	}

	/**
	 * 思路：使用栈记录遍历的顺序，设每个出栈节点都是当前节点，先打印，再将右节点和左节点压入栈。栈为空则说明走完了整个树。
	 * 
	 * @param head
	 */
	public static void preOrderUnrecur(Node head) {
		if (head == null) {
			return;
		}

		Stack<Node> stack = new Stack<Node>();
		stack.push(head);
		while (!stack.isEmpty()) {
			head = stack.pop();
			System.out.print(head.value + " ");
			if (head.right != null) {
				stack.push(head.right);
			}
			if (head.left != null) {
				stack.push(head.left);
			}
		}
	}

	/**
	 * 思路：使用栈记录遍历的顺序，将当前节点的左边界全压进栈。为空则说明上一个节点是最左边的节点，开始弹栈得到并打印，然后当前节点来到其右子节点，
	 * 重复该过程。栈为空且当前节点为空则说明走完了整个树。【一直在搞子树的左边界】
	 * 
	 * @param head
	 */
	public static void inOrderUnrecur(Node head) {
		if (head == null) {
			return;
		}

		Stack<Node> stack = new Stack<Node>();

		while (!stack.isEmpty() || head != null) {
			if (head != null) {
				stack.push(head);
				head = head.left;
			} else {
				head = stack.pop();
				System.out.print(head.value + " ");
				head = head.right;
			}
		}

	}

	/**
	 * 使用两个栈的后序遍历
	 * 
	 * 思路:前序遍历是中左右，改成中右左，然后逆序即可
	 * 
	 * @param head
	 */
	public static void posOrderUnrecur1(Node head) {
		if (head == null) {
			return;
		}

		Stack<Node> stack = new Stack<Node>();
		Stack<Node> result = new Stack<Node>();

		stack.push(head);
		while (!stack.isEmpty()) {
			head = stack.pop();
			result.push(head);
			if (head.left != null) {
				stack.push(head.left);
			}
			if (head.right != null) {
				stack.push(head.right);
			}
		}

		while (!result.isEmpty()) {
			System.out.print(result.pop().value + " ");
		}

	}

	/**
	 * 传统的后序遍历
	 * 
	 * 思路：用一个栈记录遍历的顺序，每次peek一个节点出来作为当前节点。用一个last变量存储上次打印的节点(之前节点)，依次判断是否以下情形：
	 * 1.如果当前节点有左节点且之前节点不是它的子节点，说明下面的还没访问，压入左子节点，搞左边界；
	 * 2.如果当前节点有右节点且之前节点不是它的右子节点，则说明右边的还没访问过，压入右子节点，搞右子节点的左边界；
	 * 3.其它情况说明当前节点是叶节点或者当前节点的子节点都搞过了，则弹出并打印，并把之前节点指向当前节点。
	 * 
	 * @param head
	 */
	public static void posOrderUnrecur2(Node head) {
		if (head == null) {
			return;
		}

		Stack<Node> stack = new Stack<Node>();
		stack.push(head);
		Node cur = null;// 当前节点
		Node last = head;// 上一个打印的节点
		while (!stack.isEmpty()) {
			cur = stack.peek();
			if (cur.left != null && last != cur.left && last != cur.right) {// 当前节点有左子节点，且不是前一个节点的父节点
				stack.push(cur.left);
			} else if (cur.right != null && last != cur.right) {// 当前节点有右子节点，且前一个的节点不是当前节点的右子节点
				stack.push(cur.right);
			} else {// 当前节点是叶节点||前一个节点是当前节点的右子节点||当前节点没右子节点，且前一个节点是其左子节点
				System.out.print(stack.pop().value + " ");
				last = cur;
			}
		}
	}

	/**
	 * 类似前序遍历，但是宽度优先遍历使用队列结构
	 * 
	 * @param head
	 */
	public static void layerOrderUnrecur(Node head) {
		if (head == null) {
			return;
		}

		Queue<Node> queue = new LinkedList<>();
		queue.offer(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			System.out.print(head.value + " ");
			if (head.left != null) {
				queue.offer(head.left);
			}
			if (head.right != null) {
				queue.offer(head.right);
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

		// recursive
		System.out.println("==============recursive==============");
		System.out.print("pre-order: ");
		preOrderRecur(head);
		System.out.println();
		System.out.print("in-order: ");
		inOrderRecur(head);
		System.out.println();
		System.out.print("pos-order: ");
		posOrderRecur(head);
		System.out.println();

		// unrecursive
		System.out.println("============unrecursive=============");
		System.out.print("pre-order: ");
		preOrderUnrecur(head);
		System.out.println();
		System.out.print("in-order: ");
		inOrderUnrecur(head);
		System.out.println();
		System.out.print("pos-order1: ");
		posOrderUnrecur1(head);
		System.out.println();
		System.out.print("pos-order2: ");
		posOrderUnrecur2(head);
		System.out.println();
		System.out.print("layer-order: ");
		layerOrderUnrecur(head);
	}

}
