package sxy.algorithm.nowcoder.basic.chapter08;

import java.util.HashSet;
import java.util.Set;

/**
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 * 
 * 解法：考查分解规模更小的子问题和递归实现。分解为当前下标字符和后面所有字符的全排列之间的排列问题。不能重复，所以利用HashSet。
 * 
 * @author Kevin
 * 
 */
public class PrintPermutations {

	public static void main(String[] args) {
		System.out.println(getPermutations(0, "abbc"));
	}

	// 获得从index位置开始的全部排列
	private static Set<String> getPermutations(int index, String s) {
		Set<String> set = new HashSet<>();

		if (index == s.length() - 1) {
			set.add(s.charAt(index) + "");
			return set;
		}

		Set<String> subPermutations = getPermutations(index + 1, s);

		for (String item : subPermutations) {
			char[] charArray = item.toCharArray();
			for (int i = -1; i < charArray.length; i++) {
				set.add(String.valueOf(insertIntoArray(charArray,
						s.charAt(index), i)));
			}
		}

		return set;
	}

	/**
	 * 将x插入到arr的index位后面，并返回一个新的char[]
	 * 
	 * @param arr
	 * @param x
	 * @param index
	 * @return
	 */
	private static char[] insertIntoArray(char[] arr, char x, int index) {
		char[] newArr = new char[arr.length + 1];
		for (int i = 0; i < newArr.length; i++) {
			if (i == index + 1) {
				newArr[i] = x;
			} else if (i <= index) {
				newArr[i] = arr[i];
			} else {
				newArr[i] = arr[i - 1];
			}
		}
		return newArr;
	}
}
