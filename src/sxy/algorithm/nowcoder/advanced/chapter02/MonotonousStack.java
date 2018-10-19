package sxy.algorithm.nowcoder.advanced.chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈
 * 
 * 设一个整型数组arr，对于arr中的每一个数，求其左边及右边比它小且离它距离最近的位置。要求时间复杂度为O(N)。
 * 
 * 解法：如果对每个数都遍历左右，则需要O(N^2)。使用单调栈能做到O(N)，注意处理有重复值的情况。
 * 
 * @author Kevin
 * 
 */
public class MonotonousStack {

	private static class Result {
		public int[] leftArr;// leftArr[i]表示arr[i]左边比它小的最近的数的下标
		public int[] rightArr;// rightArr[i]表示arr[i]右边比它小的最近的数的下标

		@Override
		public String toString() {
			return Arrays.toString(leftArr) + "\r\n"
					+ Arrays.toString(rightArr);
		}

		public Result(int[] arr) {
			leftArr = new int[arr.length];
			rightArr = new int[arr.length];
			for (int i = 0; i < leftArr.length; i++) {
				leftArr[i] = -1;
				rightArr[i] = -1;
			}
		}
	}

	// 暴力解。O(N^2)
	public static Result getResult1(int[] arr) {
		Result res = new Result(arr);
		for (int i = 0; i < arr.length; i++) {
			for (int j = i - 1; j >= 0; j--) {// 遍历左边
				if (arr[j] < arr[i]) {
					res.leftArr[i] = j;
					break;
				}
			}
			for (int j = i + 1; j < arr.length; j++) {// 遍历右边
				if (arr[j] < arr[i]) {
					res.rightArr[i] = j;
					break;
				}
			}
		}
		return res;
	}

	// 单调栈。使用小链表处理重复值。O(N)
	public static Result getResult2(int[] arr) {
		Result res = new Result(arr);
		Stack<Node> mStack = new Stack<>();// 单调栈，从栈底到栈顶由小到大

		for (int i = 0; i < arr.length; i++) {
			Node node = new Node(i);
			while (!mStack.isEmpty() && arr[mStack.peek().index] > arr[i]) {// 和单调栈规则不符，往外弹并结算
				Node cur = mStack.pop();
				while (cur != null) {// 有重复值
					res.rightArr[cur.index] = i;
					if (!mStack.isEmpty()) {
						res.leftArr[cur.index] = mStack.peek().index;
					}
					cur = cur.next;
				}
			}
			// 规则相符，可以往里放了
			if (!mStack.isEmpty() && arr[mStack.peek().index] == arr[i]) {// 处理相等，则压在一起
				Node cur = mStack.pop();
				cur = reverseList(cur);
				node.next = cur;
			}
			mStack.push(node);
		}

		// 结算单调栈里剩下的元素
		while (!mStack.isEmpty()) {
			Node cur = mStack.pop();
			while (cur != null) {// 有重复值
				if (!mStack.isEmpty()) {
					res.leftArr[cur.index] = mStack.peek().index;
				}
				cur = cur.next;
			}

		}

		return res;
	}

	static class Node {
		int index;
		Node next;

		public Node(int index) {
			super();
			this.index = index;
		}
	}

	private static Node reverseList(Node head) {
		Node pre = null;
		Node next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	// 单调栈。List处理重复值。O(N)
	public static Result getResult3(int[] arr) {
		Result res = new Result(arr);
		Stack<List<Integer>> mStack = new Stack<>();// 单调栈，从栈底到栈顶由小到大

		for (int i = 0; i < arr.length; i++) {
			while (!mStack.isEmpty() && arr[mStack.peek().get(0)] > arr[i]) {// 和单调栈规则不符，往外弹并结算
				List<Integer> cur = mStack.pop();
				for (Integer k : cur) {// 处理重复值
					res.rightArr[k] = i;
					if (!mStack.isEmpty()) {
						res.leftArr[k] = mStack.peek().get(0);
					}
				}
			}

			// 规则相符，可以往里放了
			if (!mStack.isEmpty() && arr[mStack.peek().get(0)] == arr[i]) {// 处理相等，则压在一起
				List<Integer> cur = mStack.peek();
				cur.add(i);
				Collections.reverse(cur);
			} else {
				List<Integer> cur = new ArrayList<Integer>();
				cur.add(i);
				mStack.push(cur);
			}
		}

		// 结算单调栈里剩下的元素
		while (!mStack.isEmpty()) {
			List<Integer> cur = mStack.pop();
			for (Integer k : cur) {// 处理重复值
				if (!mStack.isEmpty()) {
					res.leftArr[k] = mStack.peek().get(0);
				}
			}

		}

		return res;
	}

	private static int[] getRandomArray(int len) {
		if (len < 0) {
			return null;
		}
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * 10);
		}
		return arr;
	}

	public static void main(String[] args) {
		boolean flag = true;
		for (int i = 0; i < 1000; i++) {
			int[] arr = getRandomArray(20);
			Result res1 = getResult1(arr);
			Result res2 = getResult3(arr);
			if (!Arrays.equals(res1.leftArr, res2.leftArr)
					|| !Arrays.equals(res1.rightArr, res2.rightArr)) {
				flag = false;
			}
		}

		System.out.println(flag ? "succeed!" : "fuck!");

	}
}
