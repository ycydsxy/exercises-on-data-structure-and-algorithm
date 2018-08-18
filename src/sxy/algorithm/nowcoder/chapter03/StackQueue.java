package sxy.algorithm.nowcoder.chapter03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用栈实现队列结构
 * 
 * 解法：使用两个栈即可
 * 
 * @author Kevin
 * 
 */
public class StackQueue {
	Stack<Integer> inStack = new Stack<>();
	Stack<Integer> outStack = new Stack<>();
	int size = 0;

	public Integer poll() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		checkAndProcess();
		int res = outStack.pop();
		size--;
		return res;
	}

	public Integer peek() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		checkAndProcess();
		return outStack.peek();
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	public void offer(Integer x) {
		inStack.push(x);
		size++;
	}

	private void checkAndProcess() {
		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
			}
		}
	}

	public static void main(String[] args) {
		StackQueue queue = new StackQueue();

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
