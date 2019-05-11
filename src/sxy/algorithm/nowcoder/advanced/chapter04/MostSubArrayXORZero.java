package sxy.algorithm.nowcoder.advanced.chapter04;

import java.util.HashMap;

/**
 * 异或和为0的最多子数组
 * 
 * 给定一个数组arr，你可以任意把arr分成很多不相容的子数组，你的目的是：分出来的子数组中，异或和为0的子数组最多.请返回：分出来的子数组中，
 * 异或和为0的子数组最多是多少？其中，数组的异或和定义为：数组中所有的数异或起来，得到的结果叫做数组的异或和，比如数组{3,2,1}的异或和是，3^2^1
 * = 0.
 * 
 * 思路：子数组联想到动态规划，以每个位置结尾的子数组如何如何.求0~i范围内异或和为0的最多子数组，两种情况i所在的子数组异或和为0或者不为0.dp[i]=
 * max(dp[i-1],dp[j-1]+1)，其中j位置是第二种情况最后一个子数组的起始位置，即从i位置开始往前异或第一次为0的位置。
 * 
 * @author ZuoChengyun & Kevin
 * 
 */
public class MostSubArrayXORZero {

	// 时间复杂度O(N)的动态规划
	public static int mostXOR(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		int[] dp = new int[arr.length];// dp[i]的含义为0~i的范围上，最多划分出几个异或和为0的子数组
		HashMap<Integer, Integer> map = new HashMap<>();// 前綴的异或结果集，异或和为key的上一次位置为value，用于快速找j位置
		int xor_all = 0;// 0~i的前缀异或和
		map.put(0, -1);// 边界处理

		if (arr[0] == 0) {
			dp[0] = 1;
			map.put(0, 0);
		}

		for (int i = 1; i < arr.length; i++) {
			xor_all ^= arr[i];
			if (map.containsKey(xor_all)) {
				int pre = map.get(xor_all);// pre+1就是j位置
				dp[i] = pre == -1 ? 1 : (dp[pre] + 1);// 可能性二
			}
			dp[i] = Math.max(dp[i - 1], dp[i]);
			map.put(xor_all, i);
		}
		return dp[arr.length - 1];
	}

	// 时间复杂度O(N^2)的动态规划，用于验证
	public static int comparator(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		int[] dp = new int[arr.length];// dp[i]的含义为0~i的范围上，最多划分出几个异或和为0的子数组
		dp[0] = arr[0] == 0 ? 1 : 0;

		for (int i = 1; i < arr.length; i++) {
			for (int k = 0; k < i; k++) {
				dp[i] = Math.max(dp[i], isXorZero(arr, k + 1, i) ? dp[k] + 1
						: dp[k]);
			}
		}

		return dp[arr.length - 1];
	}

	private static boolean isXorZero(int[] arr, int start, int end) {
		int xor = 0;
		for (int i = start; i <= end; i++) {
			xor ^= arr[i];
		}
		return xor == 0;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
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

	// for test
	public static void main(String[] args) {
		int testTime = 10000;
		int maxSize = 300;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int res = mostXOR(arr);
			int comp = comparator(arr);
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

}
