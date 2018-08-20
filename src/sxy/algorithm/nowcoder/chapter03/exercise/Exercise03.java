package sxy.algorithm.nowcoder.chapter03.exercise;

import java.util.*;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/5f44c42fd21a42b8aeb889ab83b17ad0
 * 来源：牛客网
 * 
 * 给定一个常数K以及一个单链表L，请编写程序将L中每K个结点反转。例如：给定L为1→2→3→4→5→6，K为3，则输出应该为
 * 3→2→1→6→5→4；如果K为4，则输出应该为4→3→2→1→5→6，即最后不到K个元素不反转。
 * 
 * 输入描述:
 * 
 * 每个输入包含1个测试用例。每个测试用例第1行给出第1个结点的地址、结点总个数正整数N(<= 105)、以及正整数K(<=N)，即要求反转的
 * 子链结点的个数。结点的地址是5位非负整数，NULL地址用-1表示。
 * 
 * 接下来有N行，每行格式为：
 * 
 * Address Data Next
 * 
 * 其中Address是结点地址，Data是该结点保存的整数数据，Next是下一结点的地址。
 * 
 * 输出描述:
 * 
 * 对每个测试用例，顺序输出反转后的链表，其上每个结点占一行，格式与输入相同。 示例1 输入
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
 * 输出
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
