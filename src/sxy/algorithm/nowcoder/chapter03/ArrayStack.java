package sxy.algorithm.nowcoder.chapter03;

import java.util.Stack;

/**
 * 用数组实现固定大小的栈
 * 
 * @author Kevin
 * 
 */
public class ArrayStack {
	int[] arr;
	int top;

	public ArrayStack() {
		new ArrayStack(10);
	}

	public ArrayStack(int arrLength) {
		if (arrLength < 0) {
			throw new IllegalArgumentException();
		}
		this.arr = new int[arrLength];
		this.top = -1;
	}

	public Integer pop() {
		if (isEmpty()) {
			return null;
		}

		return arr[top--];
	}

	public Integer peek() {
		if (isEmpty()) {
			return null;
		}
		return arr[top];
	}

	public boolean isEmpty() {
		if (top < 0) {
			return true;
		}
		return false;
	}

	public void push(Integer x) {
		if (top == arr.length - 1) {
			throw new IndexOutOfBoundsException();
		}
		arr[++top] = x;
	}

	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack(5);

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
