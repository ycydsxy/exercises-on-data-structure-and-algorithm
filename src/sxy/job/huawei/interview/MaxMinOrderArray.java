package sxy.job.huawei.interview;

/**
 * 已知一有序正整数数组，按第一个数最大第二个数最小，第二个数次大，第三个数次小的规则进行排序，返回最终排序完成后的结果。要求额外空间复杂度为O(1)。
 * 
 * @author Kevin
 * 
 */
public class MaxMinOrderArray {

	// O(N^2)
	public static int[] maxMinOrderSort1(int[] array) {
		int[] arr = array.clone();
		for (int i = 0; i < arr.length; i++) {
			if ((i & 1) == 0) {// 偶数则交换最后一个，奇数则直接过
				swap(arr, i, arr.length - 1);

				// 将i位置后面的维持升序排列
				int temp = arr[arr.length - 1];
				for (int j = arr.length - 1; j >= i + 2; j--) {
					arr[j] = arr[j - 1];
				}
				if (i + 1 < arr.length) {
					arr[i + 1] = temp;
				}
			}
		}
		return arr;
	}

	// 能做到小于O(N^2)吗？
	public static int[] maxMinOrderSort2(int[] array) {
		int[] arr = array.clone();
		// TODO: 更优的算法
		return arr;
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void main(String[] args) {
		int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8 };
		print(maxMinOrderSort1(arr1));
		print(maxMinOrderSort2(arr1));
		int[] arr2 = { 1, 2, 3, 4, 5, 6, 7 };
		print(maxMinOrderSort1(arr2));
		print(maxMinOrderSort2(arr2));

	}

	private static void print(Object o) {
		String result = o.toString();
		if (o instanceof int[]) {
			int[] arr = (int[]) o;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]).append(" ");
			}
			result = sb.toString();
		}

		System.out.println(result);
	}
}
