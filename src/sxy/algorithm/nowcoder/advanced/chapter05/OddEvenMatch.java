package sxy.algorithm.nowcoder.advanced.chapter05;

/**
 * 给你一个整型数组，要求调整这个数组，使得偶数下标上放的是偶数或者奇数下标上放的是奇数.
 * 
 * 思路：没什么算法，纯coding技巧.
 * 
 * @author Kevin
 * 
 */
public class OddEvenMatch {

	public static void adjust(int[] arr) {
		if (arr == null || arr.length < 1) {
			throw new IllegalArgumentException();
		} else if (arr.length == 1) {
			if ((arr[0] & 1) == 1) {// 只有一个数，且是奇数，无法调整
				throw new RuntimeException();
			} else {
				return;
			}
		}

		int even = 0;
		int odd = 1;
		while (even < arr.length || odd < arr.length) {
			if ((arr[arr.length - 1] & 1) == 0) {
				swap(arr, arr.length - 1, even);
				even += 2;
			} else {
				swap(arr, arr.length - 1, odd);
				odd += 2;
			}
		}
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
