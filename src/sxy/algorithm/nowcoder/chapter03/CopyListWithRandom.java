package sxy.algorithm.nowcoder.chapter03;

import java.util.HashMap;

/**
 * 复制含有随机指针的链表 给定一个由 Node节点类型组成的无环单链表的头节点head，请实现一个 函数完成
 * 这个链表中所有结构的复制，并返回复制的新链表的头节点。
 * 
 * 进阶：额外空间复杂度O(1)，且在时间复杂度为 O(N) 内完成原问题要实现的函数
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

	// 不能达到进阶要求，使用了额外空间
	public static Node copyListWithRand1(Node head) {
		if (head == null) {
			return null;
		}

		// 先拷贝单链表
		Node headCopy = new Node(head.value);
		Node cur = head;
		Node curCopy = headCopy;
		while (cur.next != null) {
			curCopy.next = new Node(cur.next.value);
			cur = cur.next;
			curCopy = curCopy.next;
		}

		// 增加哈希表，为了保存原节点和新拷贝节点之间的关系
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		cur = head;
		curCopy = headCopy;
		while (cur != null) {
			map.put(cur, curCopy);
			cur = cur.next;
			curCopy = curCopy.next;
		}

		// 再遍历一遍，将拷贝链表的随机指针补全
		cur = head;
		curCopy = headCopy;
		while (cur != null) {
			curCopy.rand = map.get(cur.rand);
			cur = cur.next;
			curCopy = curCopy.next;
		}

		return headCopy;

	}

	// 达到进阶要求
	public static Node copyListWithRand2(Node head) {
		if (head == null) {
			return null;
		}

		// 先拷贝单链表
		Node headCopy = new Node(head.value);
		Node cur = head;
		Node curCopy = headCopy;
		while (cur.next != null) {
			curCopy.next = new Node(cur.next.value);
			cur = cur.next;
			curCopy = curCopy.next;
		}

		// 拷贝链表和原链表融合（同样是保留对应关系）
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

		// 再遍历一遍，将拷贝链表的随机指针补全
		cur = head;
		curCopy = headCopy;
		while (cur != null) {
			curCopy.rand = cur.rand == null ? null : cur.rand.next;
			cur = cur.next.next;
			curCopy = curCopy.next == null ? null : curCopy.next.next;
		}

		// 将链表拆分
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
