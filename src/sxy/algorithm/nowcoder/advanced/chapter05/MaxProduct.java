package sxy.algorithm.nowcoder.advanced.chapter05;

/**
 * 
 * 给你一个double类型的数组，求该数组中子数组的累乘积最大值.
 * 
 * 思路：动态规划，以i位置结尾的子数组的累乘积最大值有三种情况：
 * 
 * a) 自身
 * 
 * b) i-1位置结尾的最大累乘积乘以arr[i]
 * 
 * c) i-1位置结尾的最小累乘积乘以arr[i]
 * 
 * 可以利用空间压缩，额外空间复杂度到O(1).
 * 
 * @author ZuoChengyun & Kevin
 * 
 */
public class MaxProduct {

	public static double maxProduct(double[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}

		double max = arr[0];
		double min = arr[0];
		double res = arr[0];
		double p1 = 0;
		double p2 = 0;
		double p3 = 0;
		for (int i = 1; i < arr.length; i++) {
			p1 = arr[i];
			p2 = arr[i] * max;
			p3 = arr[i] * min;
			max = Math.max(Math.max(p1, p2), p3);
			min = Math.min(Math.min(p1, p2), p3);
			res = Math.max(res, max);
		}
		return res;
	}

}
