package sxy.algorithm.nowcoder.advanced.chapter04;

/**
 * 子数组的最大累加和问题
 * 
 * 给定一个整数数组arr（可能有负值），返回子数组的最大累加和.要求时间复杂度为O(N)，额外空间复杂度为O(1).
 * 
 * 思路：动态规划，dp[i]是以i位置结尾的子数组的最大累加和.可以空间压缩.
 * 
 * 拓展问题： 矩阵中，子矩阵的最大累加和问题
 * 
 * @author Kevin
 * 
 */

public class SubArraySum2 {

	// dp，O(N)
	public static int getMaxSum1(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}

		int dp = arr[0];
		int res = dp;
		for (int i = 1; i < arr.length; i++) {
			if (dp > 0) {
				dp += arr[i];
			} else {
				dp = arr[i];
			}
			res = Math.max(res, dp);
		}

		return res;
	}

	// 暴力解，O(N^2)
	public static int getMaxSum2(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int res = Integer.MIN_VALUE;
		int[] help = new int[arr.length];// 前缀累加和数组
		help[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			help[i] = help[i - 1] + arr[i];
		}

		for (int l = 0; l < arr.length; l++) {
			for (int r = l; r < arr.length; r++) {
				res = Math.max(res, help[r] - help[l] + arr[l]);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 20;
		int maxValue = 50;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int res = getMaxSum1(arr);
			int comp = getMaxSum2(arr);
			if (res != comp) {
				succeed = false;
				printArray(arr);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

	// for test
	public static int[] generateRandomArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
		}
		return res;
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
