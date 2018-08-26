package sxy.algorithm.nowcoder.basic.chapter08;

import static java.lang.Math.*;

/**
 * 0/1背包问题
 * 
 * 给定两个数组w和v，两个数组长度相等，w[i]表示第i件商品的 重量，v[i]表示第i件商品的价值。 再给定一个整数bag，要求
 * 你挑选商品的重量加起来一定不能超 过bag，返回满足这个条件 下，你能获得的最大价值
 * 
 * @author Kevin
 * 
 */
public class KnapsackProblem1 {
	public static int maxValue1(int[] w, int[] v, int bag) {
		return process(0, bag, w, v);
	}

	// 剩余背包重量为restWeight时，从index开始随意选择的最大价值，并返回
	private static int process(int index, int restWeight, int[] w, int[] v) {
		if (index == w.length - 1) {// 最后一个东西
			if (w[index] > restWeight) {
				return 0;
			} else {
				return v[index];
			}
		}

		return max(process(index + 1, restWeight, w, v),
				process(index + 1, restWeight - w[index], w, v) + v[index]);
	}

	public static int maxValue2(int[] w, int[] v, int bag) {
		int[][] dp = new int[bag + 1][w.length];
		for (int i = w[w.length - 1]; i <= bag; i++) {
			dp[i][w.length - 1] = v[w.length - 1];
		}

		for (int j = w.length - 2; j >= 0; j--) {
			for (int i = 1; i <= bag; i++) {
				int temp = i - w[j] < 0 ? Integer.MIN_VALUE
						: dp[i - w[j]][j + 1] + v[j];
				dp[i][j] = max(dp[i][j + 1], temp);
			}
		}
		return dp[bag][0];
	}

	public static void main(String[] args) {
		int[] w = { 3, 2, 4, 7 };
		int[] v = { 5, 6, 3, 19 };
		int bag = 11;
		System.out.println(maxValue1(w, v, bag));
		System.out.println(maxValue2(w, v, bag));
	}
}
