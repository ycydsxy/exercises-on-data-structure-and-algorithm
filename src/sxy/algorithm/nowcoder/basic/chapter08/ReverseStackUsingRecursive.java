package sxy.algorithm.nowcoder.basic.chapter08;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数。
 * 
 * 解法：从逆序的主函数先递归实现，先取走n个元素的栈的栈底元素，逆序该栈，再压回该元素。取走栈底元素这个方法需要再次递归实现，首先弹出栈顶元素，
 * 再取走栈底元素，再压之前弹出的元素。（此题考查如何分解成规模更小的子问题）
 * 
 * @author Kevin
 * 
 */
public class ReverseStackUsingRecursive {

	// 逆序一个栈
	public static void reverse(Stack<Integer> stack) {
		if (stack == null || stack.size() <= 1) {
			return;
		}

		int last = getAndRemoveLastElement(stack);
		reverse(stack);
		stack.push(last);
	}

	// 返回栈底的元素且移除
	private static int getAndRemoveLastElement(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			throw new IllegalStateException();
		} else if (stack.size() == 1) {
			return stack.pop();
		}

		int top = stack.pop();
		int last = getAndRemoveLastElement(stack);
		stack.push(top);// 再放回去是维持这个函数的意义，只是返回栈底元素并移除，其他元素无任何变化
		return last;
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}
	}
}
