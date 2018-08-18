package sxy.algorithm.nowcoder.chapter03;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。pop、push、getMin操作的时间复杂度都是O(1)。
 * 
 * 解法：O(1)的复杂度需要保留最小值的轨迹，即在原有栈的基础上再开一个栈
 * 
 * @author Kevin
 * 
 */
public class MinStack extends Stack<Integer> {

	private static final long serialVersionUID = 9187707583804764599L;

	private Stack<Integer> minStack;

	public MinStack() {
		minStack = new Stack<>();
	}

	public Integer getMin() {
		if (minStack.isEmpty()) {
			return null;
		}
		return minStack.peek();
	}

	@Override
	public synchronized Integer pop() {
		minStack.pop();
		return super.pop();
	}

	@Override
	public Integer push(Integer arg0) {
		if (this.isEmpty()) {
			minStack.push(arg0);
		} else {
			minStack.push(Math.min(arg0, minStack.peek()));
		}
		return super.push(arg0);
	}

	public static void main(String[] args) {
		MinStack stack = new MinStack();

		stack.push(7);
		stack.push(3);
		stack.push(2);
		print("min:", stack.getMin());
		print("pop:", stack.pop());
		stack.push(4);
		print("peek:", stack.peek());
		stack.push(5);
		print("min:", stack.getMin());
		stack.push(6);
		print("");
		while (!stack.isEmpty()) {
			print(stack.pop());
			print("min:", stack.getMin());
		}
		print("");
	}

	private static void print(Object o, boolean nextLine) {
		String result = "";
		if (null == o) {
			result = "null";
		} else {
			result = o.toString();
			if (o instanceof int[]) {
				int[] arr = (int[]) o;
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < arr.length; i++) {
					sb.append(arr[i]).append(" ");
				}
				result = sb.toString();
			}
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
