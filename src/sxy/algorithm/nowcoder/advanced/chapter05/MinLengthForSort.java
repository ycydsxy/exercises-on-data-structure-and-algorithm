package sxy.algorithm.nowcoder.advanced.chapter05;

/**
 * 需要排序的最短子数组长度
 * 
 * 给你一个整型数组arr，其中一定存在某子数组，其排序后arr变为有序的，求该子数组的最小长度.要求时间复杂度O(N)，额外空间复杂度O(1).
 * 
 * 思路：关注最后哪些数是不用动的.由于是排一个子数组，故而最后不用动的数都在左右两边，找到这两个边界即可.例如，从左到右遍历一遍，记录最后一个满足max(0
 * ~i)>arr[i]的i值，即再右边的数都是可以不动的.故而i就是所求最短子数组的右边界.左边界同理.
 * 
 * @author Kevin
 * 
 */
public class MinLengthForSort {

	public static int getMinLengthForSort(int[] arr) {
		if (arr == null || arr.length < 1) {
			return -1;
		}

		int R = -1;
		int L = -1;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < max) {
				R = i;
			}
			max = Math.max(max, arr[i]);
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] > min) {
				L = i;
			}
			min = Math.min(min, arr[i]);
		}

		return R - L + 1;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 3, 4, 6, 5, 2, 9 };
		System.out.println(getMinLengthForSort(arr));
	}
}
