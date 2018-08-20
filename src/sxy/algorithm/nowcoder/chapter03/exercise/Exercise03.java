package sxy.algorithm.nowcoder.chapter03.exercise;

import java.util.*;

/**
 * ���ӣ�https://www.nowcoder.com/questionTerminal/5f44c42fd21a42b8aeb889ab83b17ad0
 * ��Դ��ţ����
 * 
 * ����һ������K�Լ�һ��������L�����д����L��ÿK����㷴ת�����磺����LΪ1��2��3��4��5��6��KΪ3�������Ӧ��Ϊ
 * 3��2��1��6��5��4�����KΪ4�������Ӧ��Ϊ4��3��2��1��5��6������󲻵�K��Ԫ�ز���ת��
 * 
 * ��������:
 * 
 * ÿ���������1������������ÿ������������1�и�����1�����ĵ�ַ������ܸ���������N(<= 105)���Լ�������K(<=N)����Ҫ��ת��
 * �������ĸ��������ĵ�ַ��5λ�Ǹ�������NULL��ַ��-1��ʾ��
 * 
 * ��������N�У�ÿ�и�ʽΪ��
 * 
 * Address Data Next
 * 
 * ����Address�ǽ���ַ��Data�Ǹý�㱣����������ݣ�Next����һ���ĵ�ַ��
 * 
 * �������:
 * 
 * ��ÿ������������˳�������ת�����������ÿ�����ռһ�У���ʽ��������ͬ�� ʾ��1 ����
 * 
 * 00100 6 4
 * 
 * 00000 4 99999
 * 
 * 00100 1 12309
 * 
 * 68237 6 -1
 * 
 * 33218 3 00000
 * 
 * 99999 5 68237
 * 
 * 12309 2 33218
 * 
 * ���
 * 
 * 00000 4 33218
 * 
 * 33218 3 12309
 * 
 * 12309 2 00100
 * 
 * 00100 1 99999
 * 
 * 99999 5 68237
 * 
 * 68237 6 -1
 * 
 * @author Kevin
 * 
 */
public class Exercise03 {

	static class Node {
		int address;
		int value;
		int nextAddress;

		public Node(int address, int value, int nextAddress) {
			this.address = address;
			this.value = value;
			this.nextAddress = nextAddress;
		}

		public Node(String string) {
			String[] items = string.split("\\s+");
			this.address = Integer.valueOf(items[0]);
			this.value = Integer.valueOf(items[1]);
			this.nextAddress = Integer.valueOf(items[2]);
		}

		@Override
		public String toString() {
			return "Node [address=" + address + ", value=" + value
					+ ", nextAddress=" + nextAddress + "]";
		}

	}

	public static void main(String[] args) {
		HashMap<Integer, Node> linkedList = new HashMap<>();// {address:Node}

		Scanner scanner = new Scanner(
				"00100 6 3 00000 4 99999 00100 1 12309 68237 6 -1 33218 3 00000 99999 5 68237 12309 2 33218");
		// Scanner scanner = new Scanner(System.in);
		int headAddress = scanner.nextInt();
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		for (int i = 0; i < n; i++) {
			try {
				int address = scanner.nextInt();
				int value = scanner.nextInt();
				int nextAddress = scanner.nextInt();
				Node node = new Node(address, value, nextAddress);
				linkedList.put(node.address, node);
			} catch (Exception e) {
				break;
			}
		}
		scanner.close();

		print(linkedList, headAddress);
		System.out.println();

		boolean flag = true;

		if (n > 1 && k > 0) {
			int cur = headAddress;
			int smallHead = -1;
			int smallEnd = -1;
			int last = -1;

			for (int i = 0; i < n; i++) {
				if (cur == -1) {
					break;
				}

				if (i % k == 0) {
					smallHead = cur;
					cur = linkedList.get(cur).nextAddress;
				} else if (i % k == k - 1) {
					smallEnd = cur;
					cur = linkedList.get(cur).nextAddress;
					linkedList.get(smallEnd).nextAddress = -1;
					reverse(linkedList, smallHead);

					if (last != -1) {
						linkedList.get(last).nextAddress = smallEnd;
					}
					linkedList.get(smallHead).nextAddress = cur;
					last = smallHead;

					if (flag) {
						headAddress = smallEnd;
						flag = false;
					}
				} else {
					cur = linkedList.get(cur).nextAddress;
				}
			}
		}
		print(linkedList, headAddress);

	}

	private static int reverse(HashMap<Integer, Node> linkedList,
			int headAddress) {
		if (linkedList.get(headAddress) == null) {
			return -1;
		}
		Node cur = linkedList.get(headAddress);
		int nextAddress = -1;
		int preAddress = -1;

		while (cur != null) {
			nextAddress = cur.nextAddress;
			cur.nextAddress = preAddress;
			preAddress = cur.address;
			cur = linkedList.get(nextAddress);
		}
		return preAddress;
	}

	private static void print(HashMap<Integer, Node> linkedList, int headAddress) {
		if (linkedList == null) {
			return;
		}
		int cur = headAddress;
		while (cur != -1) {
			Node node = linkedList.get(cur);
			System.out.println(String.format("%5d %d %5d", node.address,
					node.value, node.nextAddress));
			cur = node.nextAddress;
		}
	}
}
