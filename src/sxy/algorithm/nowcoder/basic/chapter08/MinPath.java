package sxy.algorithm.nowcoder.basic.chapter08;

import static java.lang.Math.*;

/**
 * 最小路径和
 * 
 * 给你一个二维数组，二维数组中的每个数都是正数，要求从左上 角走到右下角，每一步只能向右或者向下。沿途经过的数字要累加起来。返回最小的路径和。
 * 
 * 解法：从暴力递归到动态规划。递归子问题为从(i,j)到最右下角的最小路径和。
 * 
 * @author Kevin
 * 
 */
public class MinPath {

	// 暴力递归
	public static int minPath1(int[][] m) {
		return process(0, 0, m);
	}

	// 返回从(i,j)位置走到右下角的最小路径和
	private static int process(int i, int j, int[][] m) {
		int r = m.length - 1;// 右下角点的横坐标
		int c = m[0].length - 1;// 右下角点的纵坐标
		if (i == r && j == c) {// 就在右下角点上
			return m[i][j];
		}

		if (i == r) {
			return m[i][j] + process(i, j + 1, m);
		}

		if (j == c) {
			return m[i][j] + process(i + 1, j, m);
		}

		return m[i][j] + min(process(i + 1, j, m), process(i, j + 1, m));

	}

	// 动态规划
	public static int minPath2(int[][] m) {
		int r = m.length - 1;
		int c = m[0].length - 1;
		int[][] dp = new int[r + 1][c + 1];
		dp[r][c] = m[r][c];
		for (int i = r - 1; i >= 0; i--) {
			dp[i][c] = m[i][c] + dp[i + 1][c];
		}

		for (int j = c - 1; j >= 0; j--) {
			dp[r][j] = m[r][j] + dp[r][j + 1];
		}

		for (int i = r - 1; i >= 0; i--) {
			for (int j = c - 1; j >= 0; j--) {
				dp[i][j] = m[i][j] + min(dp[i + 1][j], dp[i][j + 1]);
			}
		}

		return dp[0][0];
	}

	public static void main(String[] args) {
		int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 },
				{ 8, 8, 4, 0 } };
		System.out.println(minPath1(m));
		System.out.println(minPath2(m));

		m = generateRandomMatrix(6, 7);
		System.out.println(minPath1(m));
		System.out.println(minPath2(m));
	}

	private static int[][] generateRandomMatrix(int rowSize, int colSize) {
		if (rowSize < 0 || colSize < 0) {
			return null;
		}
		int[][] result = new int[rowSize][colSize];
		for (int i = 0; i != result.length; i++) {
			for (int j = 0; j != result[0].length; j++) {
				result[i][j] = (int) (Math.random() * 10);
			}
		}
		return result;
	}
}
