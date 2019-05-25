package sxy.algorithm.nowcoder.advanced.chapter05;

/**
 * 旋变字符串问题（https://leetcode.com/problems/scramble-string/）
 * 
 * 思路：暴力递归（4个参数）→暴力递归（3个参数）→三维DP
 * 
 * @author Kevin
 * 
 */
public class ScrambleString {

	// 三维动态规划
	public static boolean isScramble(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() != s2.length()) {
			return false;
		}

		if (s1.equals(s2)) {
			return true;
		}

		int n = s1.length();
		char[] chs1 = s1.toCharArray();
		char[] chs2 = s2.toCharArray();
		boolean[][][] dp = new boolean[n][n][n + 1];// (l1,l2,size)

		// base case
		for (int l1 = 0; l1 < n; l1++) {
			for (int l2 = 0; l2 < n; l2++) {
				dp[l1][l2][1] = chs1[l1] == chs2[l2];
			}
		}

		for (int size = 2; size <= n; size++) {
			for (int l1 = 0; l1 <= n - size; l1++) {
				for (int l2 = 0; l2 <= n - size; l2++) {

					for (int part = 1; part < size; part++) {
						if ((dp[l1][l2][part] && dp[l1 + part][l2 + part][size
								- part])
								|| (dp[l1][l2 + size - part][part] && dp[l1
										+ part][l2][size - part])) {
							dp[l1][l2][size] = true;
							break;
						}
					}

				}
			}
		}

		return dp[0][0][n];
	}

	// 暴力递归
	public static boolean isScramble2(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() != s2.length()) {
			return false;
		}

		if (s1.equals(s2)) {
			return true;
		}

		return process(s1.toCharArray(), s2.toCharArray(), 0, s1.length() - 1,
				0, s2.length() - 1);
	}

	private static boolean process(char[] s1, char[] s2, int l1, int r1,
			int l2, int r2) {
		if (l1 == r1) {
			return s1[l1] == s2[l2];
		}

		for (int i = 0; i < r1 - l1; i++) {
			if ((process(s1, s2, l1, l1 + i, l2, l2 + i) && process(s1, s2, l1
					+ i + 1, r1, l2 + i + 1, r2))
					|| (process(s1, s2, l1, l1 + i, r2 - i, r2) && process(s1,
							s2, l1 + i + 1, r1, l2, r2 - i - 1))) {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		System.out.println(isScramble2("great", "rgeat"));
		System.out.println(isScramble("great", "rgeat"));
		System.out.println(isScramble2("ccabcbabcbabbbbcbb",
				"bbbbabccccbbbabcba"));
		System.out.println(isScramble("ccabcbabcbabbbbcbb",
				"bbbbabccccbbbabcba"));
	}
}
