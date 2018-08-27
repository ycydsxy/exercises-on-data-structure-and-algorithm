package sxy.algorithm.nowcoder.basic.chapter08;

/**
 * 有1,2,...n位置，p表示其中的起始位置，m表示目标位置，k表示走的步数，问从p出发，走k步到达m，共有多少种走法。n,p,m,k均是正整数，p<=n
 * ,m<=n。
 * 
 * @author Kevin
 * 
 */
public class PathNum {

	public static int getPathNum1(int n, int p, int m, int k) {
		return process(p, k, m, n);
	}

	// 返回从cur位置出发，走rest步，到达m的走法
	public static int process(int cur, int rest, int m, int n) {
		if (rest == 0) {
			return cur == m ? 1 : 0;
		}

		if (cur == 1) {
			return process(2, rest - 1, m, n);
		} else if (cur == n) {
			return process(n - 1, rest - 1, m, n);
		}

		return process(cur + 1, rest - 1, m, n)
				+ process(cur - 1, rest - 1, m, n);
	}

	public static int getPathNum2(int n, int p, int m, int k) {
		int[][] dp = new int[k + 1][n + 2];
		dp[0][m] = 1;
		for (int i = 1; i <= k; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
			}
		}
		return dp[k][p];
	}

	public static void main(String[] args) {
		int n = 5;
		int p = 2;
		int m = 3;
		int k = 3;
		System.out.println(getPathNum1(n, p, m, k));
		System.out.println(getPathNum2(n, p, m, k));
	}

}
