package sxy.algorithm.nowcoder.chapter03;

import java.util.HashMap;

/**
 * ���ƺ������ָ������� ����һ���� Node�ڵ�������ɵ��޻��������ͷ�ڵ�head����ʵ��һ�� �������
 * ������������нṹ�ĸ��ƣ������ظ��Ƶ��������ͷ�ڵ㡣
 * 
 * ���ף�����ռ临�Ӷ�O(1)������ʱ�临�Ӷ�Ϊ O(N) �����ԭ����Ҫʵ�ֵĺ���
 * 
 * @author Kevin
 * 
 */
public class CopyListWithRandom {

	public static class Node {
		public int value;
		public Node next;
		public Node rand;

		public Node(int data) {
			this.value = data;
		}

		@Override
		public String toString() {
			return String.valueOf(this.value);
		}

	}

	// ���ܴﵽ����Ҫ��ʹ���˶���ռ�
	public static Node copyListWithRand1(Node head) {
		if (head == null) {
			return null;
		}

		// �ȿ���������
		Node headCopy = new Node(head.value);
		Node cur = head;
		Node curCopy = headCopy;
		while (cur.next != null) {
			curCopy.next = new Node(cur.next.value);
			cur = cur.next;
			curCopy = curCopy.next;
		}

		// ���ӹ�ϣ��Ϊ�˱���ԭ�ڵ���¿����ڵ�֮��Ĺ�ϵ
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		cur = head;
		curCopy = headCopy;
		while (cur != null) {
			map.put(cur, curCopy);
			cur = cur.next;
			curCopy = curCopy.next;
		}

		// �ٱ���һ�飬��������������ָ�벹ȫ
		cur = head;
		curCopy = headCopy;
		while (cur != null) {
			curCopy.rand = map.get(cur.rand);
			cur = cur.next;
			curCopy = curCopy.next;
		}

		return headCopy;

	}

	// �ﵽ����Ҫ��
	public static Node copyListWithRand2(Node head) {
		if (head == null) {
			return null;
		}

		// �ȿ���������
		Node headCopy = new Node(head.value);
		Node cur = head;
		Node curCopy = headCopy;
		while (cur.next != null) {
			curCopy.next = new Node(cur.next.value);
			cur = cur.next;
			curCopy = curCopy.next;
		}

		// ���������ԭ�����ںϣ�ͬ���Ǳ�����Ӧ��ϵ��
		cur = head;
		curCopy = headCopy;
		while (cur != null) {
			Node curNext = cur.next;
			Node curCopyNext = curCopy.next;
			cur.next = curCopy;
			curCopy.next = curNext;
			cur = curNext;
			curCopy = curCopyNext;
		}

		// �ٱ���һ�飬��������������ָ�벹ȫ
		cur = head;
		curCopy = headCopy;
		while (cur != null) {
			curCopy.rand = cur.rand == null ? null : cur.rand.next;
			cur = cur.next.next;
			curCopy = curCopy.next == null ? null : curCopy.next.next;
		}

		// ��������
		cur = head;
		while (cur != null) {
			Node curNext = cur.next.next;
			cur.next.next = curNext == null ? null : curNext.next;
			cur.next = curNext;
			cur = curNext;
		}

		return headCopy;
	}

	public static void printRandLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		System.out.print("rand:  ");
		while (cur != null) {
			System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = null;
		Node res1 = null;
		Node res2 = null;
		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		head.rand = head.next.next.next.next.next; // 1 -> 6
		head.next.rand = head.next.next.next.next.next; // 2 -> 6
		head.next.next.rand = head.next.next.next.next; // 3 -> 5
		head.next.next.next.rand = head.next.next; // 4 -> 3
		head.next.next.next.next.rand = null; // 5 -> null
		head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

	}
}
