package sxy.algorithm.nowcoder.advanced.chapter01;

/**
 * 最少添加字符串
 * 
 * 在字符串s后面添加字符串，要求使得添加后的字符串里含有两个原来的字符串（开头位置不同），如何添加可以使得添加串的字符数量最少？（返回添加的字符串）例如，“
 * abcabc”的最少添加字符串为“abc”。
 * 
 * 解法：KMP算法的变体，只用求next数组就可以了
 * 
 * @author Kevin
 * 
 */
public class ShortestHaveTwice {

	public static void main(String[] args) {
		String test1 = "a";
		System.out.println(answer(test1));

		String test2 = "aa";
		System.out.println(answer(test2));

		String test3 = "ab";
		System.out.println(answer(test3));

		String test4 = "abcdabcd";
		System.out.println(answer(test4));

		String test5 = "abracadabra";
		System.out.println(answer(test5));
	}

	public static String answer(String s) {
		int[] next = getNextArray(s.toCharArray());
		String res = s.substring(next[s.length()]);

		return res;
	}

	private static int[] getNextArray(char[] m) {
		if (m == null) {
			return null;
		}

		if (m.length == 1) {
			return new int[] { -1, 0 };
		}

		int[] next = new int[m.length + 1];

		int i = 2;
		int cn = 0;

		while (i <= m.length) {// 多求一位
			if (m[i - 1] == m[cn]) {
				next[i++] = ++cn;
			} else if (cn == 0) {
				next[i++] = 0;
			} else {
				cn = next[cn];
			}
		}

		return next;
	}

}
