package sxy.algorithm.nowcoder.chapter04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * �������ĸ��ֱ������� �ȡ��С���������ĵݹ�ͷǵݹ�д�����Լ�����α���
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
	 * ˼·��ʹ��ջ��¼������˳����ÿ����ջ�ڵ㶼�ǵ�ǰ�ڵ㣬�ȴ�ӡ���ٽ��ҽڵ����ڵ�ѹ��ջ��ջΪ����˵����������������
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
	 * ˼·��ʹ��ջ��¼������˳�򣬽���ǰ�ڵ����߽�ȫѹ��ջ��Ϊ����˵����һ���ڵ�������ߵĽڵ㣬��ʼ��ջ�õ�����ӡ��Ȼ��ǰ�ڵ����������ӽڵ㣬
	 * �ظ��ù��̡�ջΪ���ҵ�ǰ�ڵ�Ϊ����˵������������������һֱ�ڸ���������߽硿
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
	 * ʹ������ջ�ĺ������
	 * 
	 * ˼·:ǰ������������ң��ĳ�������Ȼ�����򼴿�
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
	 * ��ͳ�ĺ������
	 * 
	 * ˼·����һ��ջ��¼������˳��ÿ��peekһ���ڵ������Ϊ��ǰ�ڵ㡣��һ��last�����洢�ϴδ�ӡ�Ľڵ�(֮ǰ�ڵ�)�������ж��Ƿ��������Σ�
	 * 1.�����ǰ�ڵ�����ڵ���֮ǰ�ڵ㲻�������ӽڵ㣬˵������Ļ�û���ʣ�ѹ�����ӽڵ㣬����߽磻
	 * 2.�����ǰ�ڵ����ҽڵ���֮ǰ�ڵ㲻���������ӽڵ㣬��˵���ұߵĻ�û���ʹ���ѹ�����ӽڵ㣬�����ӽڵ����߽磻
	 * 3.�������˵����ǰ�ڵ���Ҷ�ڵ���ߵ�ǰ�ڵ���ӽڵ㶼����ˣ��򵯳�����ӡ������֮ǰ�ڵ�ָ��ǰ�ڵ㡣
	 * 
	 * @param head
	 */
	public static void posOrderUnrecur2(Node head) {
		if (head == null) {
			return;
		}

		Stack<Node> stack = new Stack<Node>();
		stack.push(head);
		Node cur = null;// ��ǰ�ڵ�
		Node last = head;// ��һ����ӡ�Ľڵ�
		while (!stack.isEmpty()) {
			cur = stack.peek();
			if (cur.left != null && last != cur.left && last != cur.right) {// ��ǰ�ڵ������ӽڵ㣬�Ҳ���ǰһ���ڵ�ĸ��ڵ�
				stack.push(cur.left);
			} else if (cur.right != null && last != cur.right) {// ��ǰ�ڵ������ӽڵ㣬��ǰһ���Ľڵ㲻�ǵ�ǰ�ڵ�����ӽڵ�
				stack.push(cur.right);
			} else {// ��ǰ�ڵ���Ҷ�ڵ�||ǰһ���ڵ��ǵ�ǰ�ڵ�����ӽڵ�||��ǰ�ڵ�û���ӽڵ㣬��ǰһ���ڵ��������ӽڵ�
				System.out.print(stack.pop().value + " ");
				last = cur;
			}
		}
	}

	/**
	 * ����ǰ����������ǿ�����ȱ���ʹ�ö��нṹ
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
