package sxy.algorithm.nowcoder.advanced.chapter02;

import java.util.LinkedList;

/**
 * 最大值减去最小值小于或等于num的子数组数量
 * 
 * 给定数组arr和整数num，共返回有多少个子数组满足如下情况： max(arr[i..j]) - min(arr[i..j]) <= num
 * max(arr[i..j])表示子数组arr[i..j]中的最大值，min(arr[i..j])表 示子数组arr[i..j]中的最小值。
 * 
 * 要求：如果数组长度为N，请实现时间复杂度为O(N)的解法。
 * 
 * 思路：
 * 
 * 1) O(N)的解法已经是指标的极限，没有最优了（必须要看完数组中所有的数才能出答案）；
 * 
 * 2) 长度为N的子数组的数量级为O(N^2)，故不能枚举所有的子数组；
 * 
 * 3) 注意以下关系：如果一个子数组达标，则内部的子数组都达标；如果一个子数组达标，则以它为子数组的数组均不达标；
 * 
 * 解法：利用窗口内最大值、最小值的更新结构。从0开始扩r窗口，直到[l,r]不达标，结算以0为左的子数组。然后扩l,重复上述过程。当r到达数组边界，停止即可
 * ，并结算这部分。
 * 
 * @author Kevin
 * 
 */
public class SubArrayNum {

	public static int getNum(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		LinkedList<Integer> qmin = new LinkedList<Integer>();
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int i = 0;
		int j = 0;
		int res = 0;
		while (i < arr.length) {
			while (j < arr.length) {// 这层循环说的是在L不动的情况下，R最远扩到哪儿
				while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
					qmin.pollLast();
				}
				qmin.addLast(j);
				while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
					qmax.pollLast();
				}
				qmax.addLast(j);
				if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
					break;
				}
				j++;
			}
			if (qmin.peekFirst() == i) {
				qmin.pollFirst();
			}
			if (qmax.peekFirst() == i) {
				qmax.pollFirst();
			}
			res += j - i;
			i++;
		}
		return res;
	}

	// for test
	public static int[] getRandomArray(int len) {
		if (len < 0) {
			return null;
		}
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * 10);
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[] arr = getRandomArray(30);
		int num = 5;
		printArray(arr);
		System.out.println(getNum(arr, num));

	}

}
