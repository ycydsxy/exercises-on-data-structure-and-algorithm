package sxy.algorithm.nowcoder.advanced.chapter02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 子数组中最小值和累加和乘积的最大值
 * 
 * 设一个整型数组arr中都是正数，对每一个子数组arr[i...j]，其最小值与累加和的乘积为min(arr[i...j])*(arr[i]+arr[i
 * +1]+...+arr [j])，求所有子数组中该乘积的最大值。要求时间复杂度为O(N)。
 * 
 * 思路：
 * 
 * a) 长度为N的子数组的数量级为O(N^2)，故不能枚举所有的子数组；
 * 
 * b) 子数组arr[i...j]累加和的问题可以变成前缀和的问题， 即sum(arr[i...j])=sum(arr[0...j])-sum(arr[0
 * ... i-1])，得到这个前缀和数组的代价是O(N)。
 * 
 * c) 对于数组中的一个位置i，若分别找到离它左边和右边比它小的最近的位置，则找到了以i位置为最小值的子数组中的乘积最大值（正数数组，扩的越大累加和越大）。
 * 这个可以用单调栈实现，平均下来的代价是O(1)。遍历数组，则可得到最大乘积。
 * 
 * @author Kevin
 * 
 */
public class MaxProduct {

	// 暴力解。O(N^2)
	public static int getMaxProduct1(int[] arr) {
		int[] sumArr = getSumArr(arr);
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			int min = arr[i];
			for (int j = i; j < arr.length; j++) {
				min = Math.min(min, arr[j]);
				int sum = i > 0 ? sumArr[j] - sumArr[i - 1] : sumArr[j];
				res = Math.max(res, min * sum);
			}
		}
		return res;
	}

	// 获得前缀和数组
	private static int[] getSumArr(int[] arr) {
		int[] sumArr = new int[arr.length];
		sumArr[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			sumArr[i] += sumArr[i - 1] + arr[i];
		}
		return sumArr;
	}

	// 使用单调栈。O(N)
	public static int getMaxProduct2(int[] arr) {
		int[] sumArr = getSumArr(arr);
		int[] leftArr = new int[arr.length];
		int[] rightArr = new int[arr.length];
		for (int i = 0; i < leftArr.length; i++) {
			leftArr[i] = -1;
			rightArr[i] = arr.length;
		}

		Stack<List<Integer>> mStack = new Stack<>();// 单调栈，从栈底到栈顶由小到大

		for (int i = 0; i < arr.length; i++) {
			while (!mStack.isEmpty() && arr[mStack.peek().get(0)] > arr[i]) {// 和单调栈规则不符，往外弹并结算
				List<Integer> cur = mStack.pop();
				for (Integer k : cur) {// 处理重复值
					rightArr[k] = i;
					if (!mStack.isEmpty()) {
						leftArr[k] = mStack.peek().get(0);
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
					leftArr[k] = mStack.peek().get(0);
				}
			}

		}

		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			int left = leftArr[i] + 1;
			int right = rightArr[i] - 1;
			int sum = left > 0 ? sumArr[right] - sumArr[left - 1]
					: sumArr[right];
			res = Math.max(res, sum * arr[i]);
		}

		return res;
	}

	// 使用单调栈，且简化写法，栈顶元素和当前元素相等时也直接弹出结算。O(N)
	public static int getMaxProduct3(int[] arr) {
		int res = 0;
		int[] sumArr = getSumArr(arr);

		Stack<Integer> mStack = new Stack<>();

		for (int i = 0; i < arr.length; i++) {
			while (!mStack.isEmpty() && arr[mStack.peek()] >= arr[i]) {// 当前数比栈顶大时结算栈顶元素，不仅如此等于时也结算（虽然相等情况会有结算不正确的情况，但是最后的那个一定结算正确）
				Integer cur = mStack.pop();
				Integer left = mStack.isEmpty() ? 0 : mStack.peek() + 1;
				Integer right = i - 1;
				int sum = left > 0 ? sumArr[right] - sumArr[left - 1]
						: sumArr[right];
				res = Math.max(res, sum * arr[cur]);
			}
			mStack.push(i);
		}

		// 结算单调栈里剩下的元素
		while (!mStack.isEmpty()) {
			Integer cur = mStack.pop();
			Integer left = mStack.isEmpty() ? 0 : mStack.peek() + 1;
			Integer right = arr.length - 1;// 右边已经到头了
			int sum = left > 0 ? sumArr[right] - sumArr[left - 1]
					: sumArr[right];
			res = Math.max(res, sum * arr[cur]);
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
		for (int i = 0; i < 10000; i++) {
			int[] arr = getRandomArray(20);
			int res1 = getMaxProduct1(arr);
			int res2 = getMaxProduct2(arr);
			int res3 = getMaxProduct3(arr);
			if (res1 != res2 || res1 != res3) {
				flag = false;
			}
		}

		System.out.println(flag ? "succeed!" : "fuck!");
	}
}
