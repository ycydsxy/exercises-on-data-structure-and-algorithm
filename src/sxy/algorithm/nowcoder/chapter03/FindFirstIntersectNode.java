package sxy.algorithm.nowcoder.chapter03;

/**
 * �����������ཻ��һϵ������
 * 
 * �ڱ����У�����������л���Ҳ�����޻������������������ͷ�ڵ� head1��head2����������������ཻ��Ҳ���ܲ��ཻ����ʵ��һ��������
 * ������������ཻ���뷵���ཻ�� ��һ���ڵ㣻������ཻ������null ���ɡ� Ҫ���������1�ĳ���ΪN������2�ĳ���ΪM��ʱ�临�Ӷ���ﵽ
 * O(N+M)������ �ռ临�Ӷ���ﵽO(1)��
 * 
 * �ⷨ���л����޻������Ҫ�ֿ����ǣ����������޻�����Ƚ����һ���ڵ��Ƿ���ͬ����������һ���л���һ���޻����򲻿��ܣ�
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
	 * �ж�һ�������Ƿ��л����л����ص�һ���뻷�ڵ�
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
			if (fast == slow) {// ׷�����ˣ��л�
				fast = head;
				while (fast != slow) {// ��ѭ��ʱ fast==slow
					fast = fast.next;
					slow = slow.next;
				}
				return fast;
			}
		}

		return null;
	}

	/**
	 * �����л������ཻ���ж�
	 * 
	 * @param head1
	 * @param head2
	 * @param loopNode1
	 * @param loopNode2
	 * @return
	 */
	private static Node findInBothLoop(Node head1, Node head2, Node loopNode1,
			Node loopNode2) {
		if (loopNode1 == loopNode2) {// û���ཻ�ڻ���
			Node nextNode = loopNode1.next;
			loopNode1.next = null;// ����޻�������ཻ����
			Node res = findInNoLoop(head1, head2);
			// ��ԭ����
			loopNode1.next = nextNode;
			return res;
		} else {// �ཻ�ڻ���
			Node cur = loopNode1;
			while (cur != loopNode2) {
				cur = cur.next;
				if (cur == loopNode1) {// ����һȦ����û��loopNode2�����ཻ
					return null;
				}
			}
			return loopNode1;// �����ĸ��뻷�ڵ㶼����
		}
	}

	/**
	 * �����޻������ཻ���ж�
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
