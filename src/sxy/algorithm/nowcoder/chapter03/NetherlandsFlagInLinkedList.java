package sxy.algorithm.nowcoder.chapter03;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式。如果链表长度为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
 * 
 * 进阶：在左、中、右三个部分的内部也做顺序要求，要求每部分里的节点从左 到右的 顺序与原链表中节点的先后次序一致。
 * 
 * 解法：1.将其变成数组，按照数组的荷兰国旗问题进行求解。【空间复杂度为O(N)?；不能满足进阶要求】2.组装成小、等、大三块小链表，
 * 再将其合并起来变成大链表。
 * 
 * @author Kevin
 * 
 */
public class NetherlandsFlagInLinkedList {
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}

		@Override
		public String toString() {
			return String.valueOf(this.value);
		}
	}

	public static Node listPartition(Node head, int pivot) {
		if (head == null) {
			return null;
		}

		Node smallHead = null, smallEnd = null;
		Node equalHead = null, equalEnd = null;
		Node bigHead = null, bigEnd = null;

		while (head != null) {
			Node next = head.next;
			head.next = null;
			if (head.value < pivot) {
				if (smallHead == null) {
					smallHead = head;
					smallEnd = head;
				} else {
					smallEnd.next = head;
					smallEnd = head;
				}
			} else if (head.value == pivot) {
				if (equalHead == null) {
					equalHead = head;
					equalEnd = head;
				} else {
					equalEnd.next = head;
					equalEnd = head;
				}
			} else {
				if (bigHead == null) {
					bigHead = head;
					bigEnd = head;
				} else {
					bigEnd.next = head;
					bigEnd = head;
				}
			}
			head = next;
		}

		// 开始连接三段
		if (equalHead == null) {
			equalHead = bigHead;
		} else {
			equalEnd.next = bigHead;
		}

		if (smallHead == null) {
			smallHead = equalHead;
		} else {
			smallEnd.next = equalHead;
		}

		return smallHead;
	}

	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head1 = new Node(7);
		head1.next = new Node(9);
		head1.next.next = new Node(1);
		head1.next.next.next = new Node(8);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(2);
		head1.next.next.next.next.next.next = new Node(5);
		printLinkedList(head1);
		head1 = listPartition(head1, 5);
		printLinkedList(head1);

	}
}
