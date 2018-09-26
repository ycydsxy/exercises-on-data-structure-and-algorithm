package sxy.algorithm.nowcoder.basic.chapter07;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 最低字典序
 * 
 * 给定一个字符串类型的数组strs，找到一种拼接方式，使得把所有字符串拼起来之后形成的字符串具有最低的字典序。
 * 
 * 思路：先从最简单的两个字符串来看，拼接a和b，若a+b>b+a则拼接后为b+a，反之亦然。贪心策略为每次选择按上述规则最小的两个进行拼接。
 * 
 * @author Kevin
 * 
 */
public class GA_LowestLexicography {

	public static class MyComparator implements Comparator<String> {
		@Override
		public int compare(String a, String b) {
			return (a + b).compareTo(b + a);
		}
	}

	public static String lowestString(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		Arrays.sort(strs, new MyComparator());
		String res = "";
		for (int i = 0; i < strs.length; i++) {
			res += strs[i];
		}
		return res;
	}

	public static void main(String[] args) {
		String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
		System.out.println(lowestString(strs1));

		String[] strs2 = { "ba", "b" };
		System.out.println(lowestString(strs2));

	}

}
