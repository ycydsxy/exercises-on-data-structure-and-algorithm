package sxy.algorithm.nowcoder.advanced.chapter05;

import java.util.HashMap;

/**
 * 子数组的最大累加和问题
 * 
 * 给你一个整数数组arr（可能有负值）和一个整数aim。求累加和等于aim的最长子数组的长度.
 * 
 * 思路：准备一张哈希表，key为之前求过的累加和，value为该累加和最先出现的位置.
 * 
 * 拓展:给你一个只有1,2,3的数组，求1和3个数相等的最长的子数组长度.
 * 
 * 思路：把1变成-1，把2变成0，把3变成1，求累加和等于0的最长的子数组长度.
 * 
 * @author Kevin
 * 
 */

public class SubArraySum2 {

	// O(N)
	public static int maxLength(int[] arr, int aim) {
		if (arr == null || arr.length < 1) {
			return 0;
		}

		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		int res = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (map.containsKey(sum - aim)) {
				res = Math.max(res, i - map.get(sum - aim));
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}

		return res;
	}
}
