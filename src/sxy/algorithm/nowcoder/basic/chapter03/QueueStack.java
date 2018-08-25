package sxy.algorithm.nowcoder.basic.chapter03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用队列实现栈结构
 * 
 * 解法：使用两个队列即可
 * 
 * @author Kevin
 * 
 */
public class QueueStack {
	Queue<Integer> inQueue = new LinkedList<>();
	Queue<Integer> outQueue = new LinkedList<>();
	int size = 0;

	public Integer pop() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		checkAndProcess();
		int res = outQueue.poll();
		size--;
		return res;
	}

	public Integer peek() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		checkAndProcess();

		int res = outQueue.poll();
		inQueue.offer(res);
		return res;
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	public void push(Integer x) {
		inQueue.offer(x);
		size++;
	}

	private void checkAndProcess() {
		while (inQueue.size() > 1) {
			outQueue.offer(inQueue.poll());
		}
		Queue<Integer> temp = inQueue;
		inQueue = outQueue;
		outQueue = temp;
	}

	public static void main(String[] args) {
		QueueStack stack = new QueueStack();

		stack.push(1);
		stack.push(2);
		stack.push(3);
		print(stack.pop());
		stack.push(4);
		print(stack.peek());
		stack.push(5);
		stack.push(6);
		print("");
		while (!stack.isEmpty()) {
			print(stack.pop());
		}
		print("");

		Stack<Integer> stack2 = new Stack<>();
		stack2.push(1);
		stack2.push(2);
		stack2.push(3);
		print(stack2.pop());
		stack2.push(4);
		print(stack2.peek());
		stack2.push(5);
		stack2.push(6);
		print("");
		while (!stack2.isEmpty()) {
			print(stack2.pop());
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
