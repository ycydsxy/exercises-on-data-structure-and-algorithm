package sxy.algorithm.nowcoder.advanced.chapter04;

/**
 * 最长公共子串
 * 
 * 经典动态规划，dp[i][j]表示str1[0...i]和str2[0...j]的最长公共子串的大小，且该公共子串以
 * str1的i位置和str2的j位置结尾 ，最终最长公共子串的大小为max(dp[i][j])
 * 
 * @author Zuochengyun & Kevin
 * 
 */

public class LCSubstring {

	public static String lcst1(String str1, String str2) {
		if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
			return "";
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int[][] dp = getdp(chs1, chs2);
		int end = 0;
		int max = 0;
		for (int i = 0; i < chs1.length; i++) {
			for (int j = 0; j < chs2.length; j++) {
				if (dp[i][j] > max) {
					end = i;
					max = dp[i][j];
				}
			}
		}
		return str1.substring(end - max + 1, end + 1);
	}

	public static int[][] getdp(char[] str1, char[] str2) {
		int[][] dp = new int[str1.length][str2.length];
		for (int i = 0; i < str1.length; i++) {
			if (str1[i] == str2[0]) {
				dp[i][0] = 1;
			}
		}
		for (int j = 1; j < str2.length; j++) {
			if (str1[0] == str2[j]) {
				dp[0][j] = 1;
			}
		}
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				if (str1[i] == str2[j]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
			}
		}
		return dp;
	}

	// 额外空间复杂度O(1)，先从最右上开始算dp表，因为只依赖对角线上，故只用有限变量即可
	public static String lcst2(String str1, String str2) {
		if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
			return "";
		}
		char[] chs1 = str1.toCharArray();// 可以省掉，只是方便编程
		char[] chs2 = str2.toCharArray();
		int row = 0;// 开始在最右上
		int col = chs2.length - 1;// 开始在最右上
		int max = 0;// 全局最大值，答案
		int end = 0;// 出现最大值时，str1中的位置
		while (row < chs1.length) {
			int i = row;
			int j = col;
			int len = 0;
			while (i < chs1.length && j < chs2.length) {
				if (chs1[i] != chs2[j]) {
					len = 0;
				} else {
					len++;
				}
				if (len > max) {
					end = i;
					max = len;
				}
				// 走对角线，望最右下角走
				i++;
				j++;
			}
			if (col > 0) {
				col--;
			} else {
				row++;
			}
		}
		return str1.substring(end - max + 1, end + 1);
	}

	public static void main(String[] args) {
		String str1 = "ABC1234567DEFG";
		String str2 = "HIJKL1234567MNOP";
		System.out.println(lcst1(str1, str2));
		System.out.println(lcst2(str1, str2));
		// 可以利用最长公共子串计算最长回文子串
		str1 = "abc1234321ab";
		System.out.println(lcst1(str1, new StringBuilder(str1).reverse()
				.toString()));
		str1 = "abc12344321cbad";
		System.out.println(lcst1(str1, new StringBuilder(str1).reverse()
				.toString()));
	}

}