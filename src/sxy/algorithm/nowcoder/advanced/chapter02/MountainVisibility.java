package sxy.algorithm.nowcoder.advanced.chapter02;

import java.util.Scanner;
import java.util.Stack;

/**
 * 可见山峰对问题
 * 
 * 给一个正整数组arr，每个元素代表一座山峰的高度，山峰成环状排列，求有多少对山峰能够相互看见。山峰的可见性如下：1) 相邻的山峰相互可见；2)
 * 不相邻的山峰的路径（顺/逆时针均可）中的山峰高度均小于这俩山峰中较小的山峰高度，则这俩山峰相互可见；3) 其他均相互不可见。
 * 
 * 思路：
 * 
 * 1) 为了不重复统计，找的过程中始终用小数的姿态去找大数，这样遍历下来不会产生重复值。
 * 
 * 2) 如果arr中没有重复值，则可直接算出结果是 max(2n-3,0) 对，代价是O(1)。2n-3=2*(n-2)+1。
 * 
 * 3) 数组中有重复值，使用单调栈，从最大值开始入栈(最大值打底，不可能在遍历时出来)，弹出时结算山峰对(注意内部的山峰对)。遍历完成后，在清算栈元素时，
 * 倒数第二条数据之上的元素和遍历时一样清算， 倒数第二条数据和倒数第一条数据需要不同考虑 。
 * 
 * @author Kevin
 * 
 */
public class MountainVisibility {

	private static class Pair {
		public int value;
		public int count;

		public Pair(int value) {
			this.value = value;
			this.count = 1;
		}
	}

	public static long communications(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		long res = 0L;
		int size = arr.length;
		int maxIndex = 0;
		for (int i = 0; i < size; i++) {
			maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
		}
		int value = arr[maxIndex];
		Stack<Pair> stack = new Stack<Pair>();// 单调栈
		stack.push(new Pair(value));// 最大值打底

		int index = nextIndex(size, maxIndex);
		while (index != maxIndex) {// 开始遍历
			value = arr[index];
			while (!stack.isEmpty() && stack.peek().value < value) {// 弹并结算
				int count = stack.pop().count;
				res += getInternalSum(count) + count * 2;
			}
			if (!stack.isEmpty() && stack.peek().value == value) {// 相等则直接加数量
				stack.peek().count++;
			} else {// 否则往里push
				stack.push(new Pair(value));
			}
			index = nextIndex(size, index);
		}

		// 清算栈里剩余元素
		while (stack.size() > 2) {// 倒数第二和倒数第一之上的元素清算和遍历时一样
			int count = stack.pop().count;
			res += getInternalSum(count) + count * 2;
		}
		if (stack.size() == 2) {// 倒数第二条清算
			int count = stack.pop().count;
			res += (stack.peek().count == 1 ? count : 2 * count)
					+ getInternalSum(count);
		}
		res += getInternalSum(stack.pop().count);// 倒数第一条清算

		return res;
	}

	// 环状遍历时，当前位置时是i，求下一个位置
	private static int nextIndex(int size, int i) {
		return i < (size - 1) ? (i + 1) : 0;
	}

	// 内部产生多少对
	private static long getInternalSum(int n) {
		return n == 1L ? 0L : (long) n * (long) (n - 1) / 2L;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int size = in.nextInt();
			int[] arr = new int[size];
			for (int i = 0; i < size; i++) {
				arr[i] = in.nextInt();
			}
			System.out.println(communications(arr));
		}
		in.close();
	}
}