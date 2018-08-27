package sxy.algorithm.nowcoder.basic.chapter08;

import static java.lang.Math.*;

/**
 * 完全背包问题
 * 
 * 给定两个数组w和v，两个数组长度相等，w[i]表示第i件商品的 重量，v[i]表示第i件商品的价值，每件物品有无数个。 再给定一个整数bag，要求
 * 你挑选商品的重量加起来一定不能超 过bag，返回满足这个条件 下，你能获得的最大价值
 * 
 * @author Kevin
 * 
 */
public class KnapsackProblem2 {
	public static int maxValue1(int[] w, int[] v, int bag) {
		return process(0, bag, w, v);
	}

	// 剩余背包重量为restWeight时，从index开始随意选择的最大价值，并返回
	private static int process(int index, int restWeight, int[] w, int[] v) {

		if (restWeight < 0) {
			return Integer.MIN_VALUE;
		}

		if (index == w.length) {
			return 0;
		}

		int max = Integer.MIN_VALUE;
		int i = 0;
		while (true) {
			if (restWeight - w[index] * i < 0) {
				break;
			}
			max = max(max, process(index + 1, restWeight - w[index] * i, w, v)
					+ v[index] * i);
			i++;
		}

		return max;
	}

	public static int maxValue2(int[] w, int[] v, int bag) {
		int[][] dp = new int[bag + 1][w.length + 1];

		for (int j = w.length - 1; j >= 0; j--) {
			for (int i = 0; i <= bag; i++) {
				int max = Integer.MIN_VALUE;
				int k = 0;
				while (true) {
					if (i - w[j] * k < 0) {
						break;
					}
					max = max(max, dp[i - w[j] * k][j + 1] + v[j] * k);
					dp[i][j] = max;
					k++;
				}

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
