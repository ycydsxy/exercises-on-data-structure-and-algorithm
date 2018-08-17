package sxy.algorithm.newcode.chapter01;

/**
 * 最大距离问题 给定一个数组，求如果排序之后，相邻两数的最大差值，要求时间复杂度O(N)，且要求不能用非基于比较的排序。
 * 
 * 解法：使用桶的思想
 * 
 * @author Kevin
 * 
 */
public class MaxGap {

	public static final int MAX_SIZE = 15;
	public static final int MAX_NUMBER = 100;

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			int[] arr = getRandomArray();
			print(arr);
			print(getMaxGap(arr));
			print("");
		}
	}

	public static int getMaxGap(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return 0;
		}

		// 找最小最大值
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int length = arr.length;
		for (int i = 0; i < length; i++) {
			min = arr[i] < min ? arr[i] : min;
			max = arr[i] > max ? arr[i] : max;
		}
		if (min == max) {
			return 0;
		}

		// 初始化桶（保证有一个空桶）
		boolean[] hasValues = new boolean[length + 1];
		int[] minValues = new int[length + 1];
		int[] maxValues = new int[length + 1];

		// 入桶
		for (int i = 0; i < length; i++) {
			int bid = findBucketIndex(arr[i], min, max, length);
			minValues[bid] = hasValues[bid] ? Math.min(minValues[bid], arr[i])
					: arr[i];
			maxValues[bid] = hasValues[bid] ? Math.max(maxValues[bid], arr[i])
					: arr[i];
			hasValues[bid] = true;
		}

		// 找相邻两桶中最大差值，取最大值即为所求
		int result = 0;
		int lastMax = maxValues[0];
		for (int i = 1; i <= length; i++) {
			if (hasValues[i]) {
				result = Math.max(result, minValues[i] - lastMax);
				lastMax = maxValues[i];
			}
		}

		return result;
	}

	private static int findBucketIndex(int num, int min, int max, int length) {
		return (num - min) * length / (max - min);
	}

	private static int[] getRandomArray() {
		int size = (int) (Math.random() * MAX_SIZE) + 1;
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = (int) (Math.random() * MAX_NUMBER);
		}
		return arr;
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
