package sxy.algorithm.nowcoder.basic.chapter03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用数组实现固定大小的队列
 * 
 * 解法：使用两个指针分别指向队首和队尾，两个都往前走，当到达数组边界时置0；用另一个变量size来判断是否为空和越界情况
 * 
 * @author Kevin
 * 
 */
public class ArrayQueue {
	int[] arr;
	int start;
	int end;
	int size;

	public ArrayQueue() {
		this(10);
	}

	public ArrayQueue(int arrLength) {
		if (arrLength < 0) {
			throw new IllegalArgumentException();
		}
		this.arr = new int[arrLength];
		this.start = 0;
		this.end = 0;
		this.size = 0;
	}

	public Integer poll() {
		if (isEmpty()) {
			return null;
		}
		int res = arr[start];
		start++;
		size--;
		if (start == arr.length) {
			start = 0;
		}
		return res;
	}

	public Integer peek() {
		if (isEmpty()) {
			return null;
		}
		return arr[start];
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	public void offer(Integer x) {
		if (size == arr.length) {
			throw new IndexOutOfBoundsException();
		}
		arr[end] = x;
		end++;
		if (end == arr.length) {
			end = 0;
		}
		size++;
	}

	public static void main(String[] args) {
		ArrayQueue queue = new ArrayQueue(5);

		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		print(queue.poll());
		queue.offer(4);
		print(queue.peek());
		queue.offer(5);
		queue.offer(6);
		print("");
		while (!queue.isEmpty()) {
			print(queue.poll());
		}
		print("");

		Queue<Integer> queue2 = new LinkedList<>();
		queue2.offer(1);
		queue2.offer(2);
		queue2.offer(3);
		print(queue2.poll());
		queue2.offer(4);
		print(queue2.peek());
		queue2.offer(5);
		queue2.offer(6);
		print("");
		while (!queue2.isEmpty()) {
			print(queue2.poll());
		}

	}

	private static void print(Object o, boolean nextLine) {
		String result = o.toString();
		if (o instanceof int[]) {
			int[] arr = (int[]) o;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]).append(" ");
			}
			result = sb.toString();
		}
		if (nextLine) {
			System.out.println(result);
		} else {
			System.out.print(result);
		}
	}

	private static void print(Object o) {
		print(o, true);
	}

	private static void print(Object... objects) {
		for (Object o : objects) {
			print(o, false);
			print(" ", false);
		}
		System.out.println();
	}

}
