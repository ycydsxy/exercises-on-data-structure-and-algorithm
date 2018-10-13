package sxy.algorithm.nowcoder.advanced.chapter01;

/**
 * 给一个字符串str，在后面添加字符，使其整体变为回文串，求满足要求的最短的字符串。
 * 
 * 解法：Manacher算法的变体，求包含最后一个字符的最长回文串，然后逆序打印前面的即可。
 * 
 * @author Kevin
 * 
 */
public class ShortestPalindrome {

	public static String answer(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char[] charArr = getDoubleCharArr(str);
		int[] pArr = new int[charArr.length];
		int C = -1;
		int R = -1;
		int maxContainsEnd = -1;
		for (int i = 0; i != charArr.length; i++) {
			pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}

			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}

			if (R == charArr.length) {
				maxContainsEnd = pArr[i];
				break;
			}
		}

		char[] res = new char[str.length() - (maxContainsEnd - 1)];
		for (int i = 0; i < res.length; i++) {
			res[res.length - 1 - i] = charArr[i * 2 + 1];
		}
		return String.valueOf(res);

	}

	private static char[] getDoubleCharArr(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}

	public static void main(String[] args) {
		String str = "abcd123321";
		System.out.println(answer(str));
		String str2 = "abcd";
		System.out.println(answer(str2));

	}

}
