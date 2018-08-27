package sxy.algorithm.nowcoder.basic.chapter08;

import static java.lang.Math.*;

/**
 * 多重背包问题
 * 
 * 给定两个数组w和v，两个数组长度相等，w[i]表示第i件商品的 重量，v[i]表示第i件商品的价值，c[i]表示第i件商品的数量。
 * 再给定一个整数bag，要求 你挑选商品的重量加起来一定不能超 过bag，返回满足这个条件 下，你能获得的最大价值
 * 
 * @author Kevin
 * 
 */
public class KnapsackProblem3 {
	public static int maxValue1(int[] w, int[] v, int[] c, int bag) {
		return process(0, bag, w, v, c);
	}

	// 剩余背包重量为restWeight时，从index开始随意选择的最大价值，并返回
	private static int process(int index, int restWeight, int[] w, int[] v,
			int[] c) {

		if (restWeight < 0) {
			return Integer.MIN_VALUE;
		}

		if (index == w.length) {
			return 0;
		}

		int max = Integer.MIN_VALUE;
		int k = 0;
		while (true) {
			if (restWeight - w[index] * k < 0 || k > c[index]) {
				break;
			}
			max = max(max,
					process(index + 1, restWeight - w[index] * k, w, v, c)
							+ v[index] * k);
			k++;
		}

		return max;
	}

	public static int maxValue2(int[] w, int[] v, int[] c, int bag) {
		int[][] dp = new int[bag + 1][w.length + 1];

		for (int j = w.length - 1; j >= 0; j--) {
			for (int i = 0; i <= bag; i++) {
				int max = Integer.MIN_VALUE;
				int k = 0;
				while (i - w[j] * k >= 0 && k <= c[j]) {
					max = max(max, dp[i - w[j] * k][j + 1] + v[j] * k);
					dp[i][j] = max;
					k++;
				}

			}
		}
		return dp[bag][0];
	}

	public static int maxValue3(int[] w, int[] v, int[] c, int bag) {
		int[][] dp = new int[bag + 1][w.length + 1];

		for (int j = w.length - 1; j >= 0; j--) {
			for (int i = 0; i <= bag; i++) {
				if (i - w[j] * (c[j] + 1) >= 0
						&& dp[i - w[j]][j] == dp[i - w[j] * (c[j] + 1)][j + 1]
								+ v[j] * c[j]) {// 前一个最大值取得的地方在现在的位置已经不能取了，遍历一遍才能取最大值
					int max = Integer.MIN_VALUE;
					int k = 0;
					while (i - w[j] * k >= 0 && k <= c[j]) {
						max = max(max, dp[i - w[j] * k][j + 1] + v[j] * k);
						dp[i][j] = max;
						k++;
					}
				} else {
					int lastMax = i - w[j] >= 0 ? dp[i - w[j]][j] + v[j]
							: Integer.MIN_VALUE;
					dp[i][j] = max(lastMax, dp[i][j + 1]);
				}

			}
		}

		return dp[bag][0];
	}

	public static void main(String[] args) {
		int[] w = { 3, 2, 4, 7 };
		int[] v = { 5, 6, 3, 10 };
		int[] c = { 2, 2, 3, 4 };
		int bag = 11;
		System.out.println(maxValue1(w, v, c, bag));
		System.out.println(maxValue2(w, v, c, bag));
		System.out.println(maxValue3(w, v, c, bag));
	}
}
