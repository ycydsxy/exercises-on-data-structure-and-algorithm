package sxy.algorithm.nowcoder.chapter03;

/**
 * ����������ĳֵ���ֳ����С���м���ȡ��ұߴ����ʽ�����������ΪN��ʱ�临�Ӷ���ﵽO(N)������ռ临�Ӷ���ﵽO(1)��
 * 
 * ���ף������С����������ֵ��ڲ�Ҳ��˳��Ҫ��Ҫ��ÿ������Ľڵ���� ���ҵ� ˳����ԭ�����нڵ���Ⱥ����һ�¡�
 * 
 * �ⷨ��1.���������飬��������ĺ����������������⡣���ռ临�Ӷ�ΪO(N)?�������������Ҫ��2.��װ��С���ȡ�������С����
 * �ٽ���ϲ�������ɴ�����
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

		// ��ʼ��������
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
