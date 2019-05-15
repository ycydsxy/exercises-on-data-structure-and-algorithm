package sxy.algorithm.nowcoder.advanced.chapter05;

import java.util.Arrays;

/**
 * 给定两个长度相等的有序数组，求两个数组中整体的上中位数
 * 
 * 思路：
 * 
 * 1.合并并排序，O(N)
 * 
 * 2.根据两个数组的上中位数大小有以下推论：a)若两上中位数相等，则总体的上中位数就是这个；b)两上中位数不等，则可以排除掉绝对不可能的数，
 * 剩下的数再求上中位数，便等于总体的上中位数（可以分情况讨论证明）.这样每次都可以砍掉一半的总量，故时间复杂度为O(log(N)).
 * 
 * 拓展：
 * 
 * 给定两个有序数组，长度分别为M和N，求两个数组整体第k小的数
 * 
 * 思路：
 * 
 * 1.合并并排序，O(K)
 * 
 * 2.使用上面求长度相等有序数组整体上中位数的结论，分三种情况讨论，k<=min(M,N)，k>max(M,N)，和min(M,N)<k<=max(M,N)
 * .时间复杂度为O(log(min(M, N))).
 * 
 * @author Kevin
 * 
 */
public class UpMedianInSortedArray {

	// O(log(N))
	public static int getUpMedianAll1(int[] arr1, int[] arr2) {
		if (arr1 == null || arr2 == null || arr1.length < 1 || arr2.length < 1
				|| arr1.length != arr2.length) {
			throw new IllegalArgumentException();
		}

		return process(arr1, arr2, 0, arr1.length - 1, 0, arr2.length - 1);
	}

	// r1-l1=r2-l2
	// 计算arr1从l1到r1位置与arr2从l2到r2位置合一起的上中位数
	private static int process(int[] arr1, int[] arr2, int l1, int r1, int l2,
			int r2) {
		if (l1 == r1 || l2 == r2) {// 只有一个数
			return Math.min(arr1[l1], arr2[l2]);
		}
		int mid1 = (l1 + r1) >> 1;
		int mid2 = (l2 + r2) >> 1;
		if (arr1[mid1] == arr2[mid2]) {// 中值相等直接返回
			return arr1[mid1];
		} else if (arr1[mid1] > arr2[mid2]) {// arr1的中值比arr2大
			if (((r1 - l1) & 1) == 1) {// 偶数长度，长度为r1-l1+1
				return process(arr1, arr2, l1, mid1, mid2 + 1, r2);
			} else {// 奇数长度
				if (arr2[mid2] >= arr1[mid1 - 1]) {
					return arr2[mid2];
				}
				return process(arr1, arr2, l1, mid1 - 1, mid2 + 1, r2);
			}
		} else {// arr1的中值比arr2小，反一下就行
			if (((r1 - l1) & 1) == 1) {// 偶数长度，长度为r1-l1+1
				return process(arr2, arr1, l2, mid2, mid1 + 1, r1);
			} else {// 奇数长度
				if (arr1[mid1] >= arr2[mid2 - 1]) {
					return arr1[mid1];
				}
				return process(arr2, arr1, l2, mid2 - 1, mid1 + 1, r1);
			}
		}
	}

	// O(N)
	public static int getUpMedianAll2(int[] arr1, int[] arr2) {
		if (arr1 == null || arr2 == null || arr1.length < 1 || arr2.length < 1
				|| arr1.length != arr2.length) {
			throw new IllegalArgumentException();
		}

		int[] all = new int[arr1.length + arr2.length];
		int c1 = 0;
		int c2 = 0;

		for (int i = 0; i < all.length; i++) {
			if (c1 < arr1.length && c2 < arr2.length) {
				all[i] = arr1[c1] <= arr2[c2] ? arr1[c1++] : arr2[c2++];
			} else if (c1 >= arr1.length) {
				all[i] = arr2[c2++];
			} else {
				all[i] = arr1[c1++];
			}
		}
		return all[arr1.length - 1];
	}

	// O(log(min(M,N)))
	public static int getKthNum1(int[] arr1, int[] arr2, int k) {
		if (arr1 == null || arr2 == null || arr1.length < 1 || arr2.length < 1
				|| k < 1 || k > arr1.length + arr2.length) {
			throw new IllegalArgumentException();
		}

		int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
		int[] shorts = longs == arr1 ? arr2 : arr1;
		int l = longs.length;
		int s = shorts.length;
		if (k <= s) {
			return process(shorts, longs, 0, k - 1, 0, k - 1);
		}

		if (k > l) {
			if (shorts[k - l - 1] >= longs[l - 1]) {
				return shorts[k - l - 1];
			}
			if (longs[k - s - 1] >= shorts[s - 1]) {
				return longs[k - s - 1];
			}
			return process(shorts, longs, k - l, s - 1, k - s, l - 1);
		}

		if (longs[k - s - 1] >= shorts[s - 1]) {
			return longs[k - s - 1];
		}

		return process(shorts, longs, 0, s - 1, k - s, k - 1);

	}

	// O(k)
	public static int getKthNum2(int[] arr1, int[] arr2, int k) {
		if (arr1 == null || arr2 == null || arr1.length < 1 || arr2.length < 1
				|| k < 1 || k > arr1.length + arr2.length) {
			throw new IllegalArgumentException();
		}

		int[] res = new int[k];
		int c1 = 0;
		int c2 = 0;
		for (int i = 0; i < res.length; i++) {
			if (c1 < arr1.length && c2 < arr2.length) {
				res[i] = arr1[c1] <= arr2[c2] ? arr1[c1++] : arr2[c2++];
			} else if (c1 >= arr1.length) {
				res[i] = arr2[c2++];
			} else {
				res[i] = arr1[c1++];
			}
		}

		return res[k - 1];
	}

	// for test
	public static void main(String[] args) {
		int testTime = 50000;
		int maxSize = 50;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[][] arr = generateRandomArrayEqualLength(maxSize, maxValue);
			Arrays.sort(arr[0]);
			Arrays.sort(arr[1]);
			int comp = getUpMedianAll2(arr[0], arr[1]);
			int res = getUpMedianAll1(arr[0], arr[1]);
			if (res != comp) {
				succeed = false;
				printArray(arr[0]);
				printArray(arr[1]);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = generateRandomArray(maxSize, maxValue);
			Arrays.sort(arr1);
			Arrays.sort(arr2);
			int k = (int) (Math.random() * (arr1.length + arr2.length) + 1);
			int comp = getKthNum1(arr1, arr2, k);
			int res = getKthNum2(arr1, arr2, k);
			if (res != comp) {
				succeed = false;
				System.out.println(k);
				printArray(arr1);
				printArray(arr2);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

	// for test
	public static int[][] generateRandomArrayEqualLength(int maxSize,
			int maxValue) {
		int[][] arr = new int[2][(int) ((maxSize) * Math.random() + 1)];
		for (int i = 0; i < arr[0].length; i++) {
			arr[0][i] = (int) ((maxValue + 1) * Math.random());
			arr[1][i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize) * Math.random() + 1)];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
