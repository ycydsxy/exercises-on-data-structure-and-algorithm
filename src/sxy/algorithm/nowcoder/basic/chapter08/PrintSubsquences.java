package sxy.algorithm.nowcoder.basic.chapter08;

import java.util.HashSet;
import java.util.Set;

/**
 * 打印一个字符串的全部子序列，包括空字符串
 * 
 * @author Kevin
 * 
 */
public class PrintSubsquences {

	public static void main(String[] args) {
		System.out.println(getSubsquences(0, "abbc"));
	}

	// 返回从index位置开始的子序列
	private static Set<String> getSubsquences(int index, String s) {
		Set<String> set = new HashSet<>();

		if (index == s.length()) {
			set.add("");
			return set;
		}

		for (String item : getSubsquences(index + 1, s)) {
			set.add(item);
			set.add(s.charAt(index) + item);
		}
		return set;
	}
}
