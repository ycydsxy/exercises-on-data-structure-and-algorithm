package sxy.algorithm.nowcoder.advanced.chapter04;

/**
 * 回文最小分割数
 * 
 * 给定一个字符串str，请把str切割，保证每一部分都是回文串，求最小的分割数.
 * 
 * @author Kevin
 * 
 */
public class PalindromeMinCut {

	public static int minCut1(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		char[] chars = str.toCharArray();

		return process(chars, 0) - 1;
	}

	// 从index出发，str[index...]切出的都是回文的子串最少个数
	public static int process(char[] chars, int index) {
		if (index == chars.length) {
			return 0;
		}

		if (index == chars.length - 1) {
			return 1;
		}

		int res = Integer.MAX_VALUE;
		for (int i = index; i < chars.length; i++) {
			if (isValid(chars, index, i)) {
				res = Math.min(res, process(chars, i + 1) + 1);
			}
		}

		return res;
	}

	// 是否为回文
	private static boolean isValid(char[] chars, int L, int R) {
		boolean flag = true;
		while (R > L) {
			if (chars[L++] != chars[R--]) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	// 时间复杂度O(N^2)
	public static int minCut2(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		char[] chas = str.toCharArray();
		int len = chas.length;
		int[] dp = new int[len + 1];
		dp[len] = -1;
		boolean[][] p = new boolean[len][len];// 预处理表，是否是回文
		for (int i = len - 1; i >= 0; i--) {
			dp[i] = Integer.MAX_VALUE;
			for (int j = i; j < len; j++) {
				if (chas[i] == chas[j] && (j - i < 2 || p[i + 1][j - 1])) {
					p[i][j] = true;
					dp[i] = Math.min(dp[i], dp[j + 1] + 1);
				}
			}
		}
		return dp[0];
	}

	// for test
	public static String getRandomStringOnlyAToD(int len) {
		int range = 'D' - 'A' + 1;
		char[] charArr = new char[(int) (Math.random() * (len + 1))];
		for (int i = 0; i != charArr.length; i++) {
			charArr[i] = (char) ((int) (Math.random() * range) + 'A');
		}
		return String.valueOf(charArr);
	}

	public static void main(String[] args) {
		int maxLen = 10;
		int testTimes = 500000;
		String str = null;
		boolean succeed = true;
		for (int i = 0; i != testTimes; i++) {
			str = getRandomStringOnlyAToD(maxLen);
			int res = minCut1(str);
			int comp = minCut2(str);
			if (res != comp) {
				succeed = false;
				System.out.println(str);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}

		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

	}
}
