package sxy.algorithm.nowcoder.advanced.chapter04;

/**
 * 约瑟夫环问题
 * 
 * 给你一个环形单链表和一个正整数m，从head开始按1,2,3,...进行顺序标记，每标记到m就将该节点剔除，从下一个节点重新标记为1，求最终环内幸存的节点
 * .
 * 
 * 思路：暴力解就一直数，最终时间复杂度为O(M*N)。注意到最后一个幸存节点最后的编号是1，若能够找到一个函数f得到其在上一步的编号，
 * 一直向前递推则能够知道其在最初时候的编号。
 * 
 * @author Kevin
 * 
 */
public class JosephusProblem {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node josephusKill1(Node head, int m) {
		if (head == null || head.next == head || m < 1) {
			return head;
		}
		Node last = head;
		while (last.next != head) {
			last = last.next;
		}
		int count = 0;
		while (head != last) {
			if (++count == m) {
				last.next = head.next;
				count = 0;
			} else {
				last = last.next;
			}
			head = last.next;
		}
		return head;
	}

	public static Node josephusKill2(Node head, int m) {
		if (head == null || head.next == head || m < 1) {
			return head;
		}
		Node cur = head.next;
		int tmp = 1; // tmp -> list size
		while (cur != head) {
			tmp++;
			cur = cur.next;
		}
		// tmp等于最初的总长度
		tmp = getLive(tmp, m); // tmp为生存的节点编号
		while (--tmp != 0) {
			head = head.next;
		}
		// 找到了生存节点
		head.next = head;
		return head;
	}

	// 长度为i，数到m就剔除，返回最后剩余的节点在原链表中的编号
	public static int getLive(int i, int m) {
		if (i == 1) {
			return 1;
		}
		return (getLive(i - 1, m) + m - 1) % i + 1;
	}

	public static void printCircularList(Node head) {
		if (head == null) {
			return;
		}
		System.out.print("Circular List: " + head.value + " ");
		Node cur = head.next;
		while (cur != head) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println("-> " + head.value);
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = head1;
		printCircularList(head1);
		head1 = josephusKill1(head1, 3);
		printCircularList(head1);

		Node head2 = new Node(1);
		head2.next = new Node(2);
		head2.next.next = new Node(3);
		head2.next.next.next = new Node(4);
		head2.next.next.next.next = new Node(5);
		head2.next.next.next.next.next = head2;
		printCircularList(head2);
		head2 = josephusKill2(head2, 3);
		printCircularList(head2);

	}

}