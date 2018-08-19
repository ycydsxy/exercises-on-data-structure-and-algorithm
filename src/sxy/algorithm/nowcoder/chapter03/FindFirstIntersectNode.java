package sxy.algorithm.nowcoder.chapter03;

/**
 * 两个单链表相交的一系列问题
 * 
 * 在本题中，单链表可能有环，也可能无环。给定两个单链表的头节点 head1和head2，这两个链表可能相交，也可能不相交。请实现一个函数，
 * 如果两个链表相交，请返回相交的 第一个节点；如果不相交，返回null 即可。 要求：如果链表1的长度为N，链表2的长度为M，时间复杂度请达到
 * O(N+M)，额外 空间复杂度请达到O(1)。
 * 
 * 解法：有环和无环情况需要分开考虑：若两个都无环，则比较最后一个节点是否相同；若两个中一个有环，一个无环，则不可能；
 * 
 * @author Kevin
 * 
 */
public class FindFirstIntersectNode {
	private static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + ", next=" + next + "]";
		}
	}

	public static Node getIntersectNode(Node head1, Node head2) {
		Node loopNode1 = getLoopNode(head1);
		Node loopNode2 = getLoopNode(head2);
		if (loopNode1 == null && loopNode2 == null) {
			return findInNoLoop(head1, head2);
		} else if (loopNode1 != null && loopNode2 != null) {
			return findInBothLoop(head1, head2, loopNode1, loopNode2);
		} else {
			return null;
		}
	}

	/**
	 * 判断一个链表是否有环，有环返回第一个入环节点
	 * 
	 * @param head
	 * @return
	 */
	private static Node getLoopNode(Node head) {
		if (head == null) {
			return null;
		}
		Node fast = head;
		Node slow = head;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {// 追赶上了，有环
				fast = head;
				while (fast != slow) {// 出循环时 fast==slow
					fast = fast.next;
					slow = slow.next;
				}
				return fast;
			}
		}

		return null;
	}

	/**
	 * 两个有环链表相交的判断
	 * 
	 * @param head1
	 * @param head2
	 * @param loopNode1
	 * @param loopNode2
	 * @return
	 */
	private static Node findInBothLoop(Node head1, Node head2, Node loopNode1,
			Node loopNode2) {
		if (loopNode1 == loopNode2) {// 没有相交在环上
			Node nextNode = loopNode1.next;
			loopNode1.next = null;// 变成无环链表的相交问题
			Node res = findInNoLoop(head1, head2);
			// 还原链表
			loopNode1.next = nextNode;
			return res;
		} else {// 相交在环上
			Node cur = loopNode1;
			while (cur != loopNode2) {
				cur = cur.next;
				if (cur == loopNode1) {// 找了一圈发现没有loopNode2，不相交
					return null;
				}
			}
			return loopNode1;// 返回哪个入环节点都可以
		}
	}

	/**
	 * 两个无环链表相交的判断
	 * 
	 * @param head1
	 * @param head2
	 * @return
	 */
	private static Node findInNoLoop(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}

		int len1 = 1;
		int len2 = 1;
		Node cur1 = head1;
		Node cur2 = head2;

		while (cur1.next != null) {
			cur1 = cur1.next;
			len1++;
		}

		while (cur2.next != null) {
			cur2 = cur2.next;
			len2++;
		}

		if (cur1 != cur2) {
			return null;
		} else {
			cur1 = len1 > len2 ? head2 : head1;
			cur2 = len1 > len2 ? head1 : head2;
			for (int i = 0; i < Math.abs(len1 - len2); i++) {
				cur2 = cur2.next;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		}
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(getIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

	}
}
