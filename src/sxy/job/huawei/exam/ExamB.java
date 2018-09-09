package sxy.job.huawei.exam;

import static java.lang.Math.max;

import java.util.Stack;

/**
 * 最长有效括号长度
 * 
 * 输入一组带括号的字符串，判断中最长的合法括号的数量(合法是指其中没有其他字符，如(()))的合法括号长度为4,(()())的合法括号长度为6)
 * 
 * 输入: (((a))((
 * 
 * 输出: 0
 * 
 * @author Kevin
 * 
 */
public class ExamB {

	public static int getMaxLength1(String str) {
		String[] subStrings = str.split("[^\\(\\)]");// 分成子串，最长合法括号不可能跨过除了'('和')'之外的字符
		int max = 0;
		for (String subString : subStrings) {
			max = max(max, getBasicMaxLength(subString));
		}
		return max;
	}

	// 使用栈来做，前提是字符串中仅包含'('和')'
	private static int getBasicMaxLength(String str) {
		int max = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.empty()) {
					stack.push(i);
				} else {
					max = Math.max(max, i - stack.peek());
				}
			}
		}
		return max;

	}

	// 暴力递归
	public static int getMaxLength2(String str) {
		int max = 0;
		for (int i = 0; i < str.length(); i++) {
			max = max(max, process(i, str.toCharArray()));
		}
		return max;
	}

	// 返回以index位置结尾的最长有效括号的长度
	private static int process(int index, char[] s) {
		if (index < 1 || s[index] != ')') {// 在0位置肯定返回0，该位置不是')'返回0
			return 0;
		}

		int res = 0;
		if (s[index - 1] == '(') {// 该位置是)且前一个位置时(，可以直接和前面连起来
			res = process(index - 2, s) + 2;
		} else if (s[index - 1] == ')') { // 该位置是)且前一个位置时)，可以看前面一个位置的最长有效括号之前的字符，如果是(就能连起来
			int index2 = index - 1 - process(index - 1, s);
			if (index2 >= 0 && s[index2] == '(') {
				res = process(index - 1, s) + process(index2 - 1, s) + 2;
			}
		}
		return res;
	}

	// 动态规划
	public static int getMaxLength3(String str) {
		char[] s = str.toCharArray();
		int[] dp = new int[s.length];
		int max = 0;
		for (int i = 1; i < s.length; i++) {// 所有其他情况dp[i]=0
			if (s[i] == ')') {
				if (s[i - 1] == '(') {
					dp[i] = i - 2 >= 0 ? dp[i - 2] + 2 : 2;
				} else if (s[i - 1] == ')') {
					int j = i - 1 - dp[i - 1];
					if (j >= 0 && s[j] == '(') {
						dp[i] = j - 1 >= 0 ? dp[i - 1] + dp[j - 1] + 2
								: dp[i - 1] + 2;
					}
				}
			}
			max = max(max, dp[i]);
		}
		return max;
	}

	public static void main(String[] args) {
		String input = "(())a";
		System.out.println(getMaxLength1(input));
		System.out.println(getMaxLength2(input));
		System.out.println(getMaxLength3(input));
		System.out.println();

		input = "a()()()";
		System.out.println(getMaxLength1(input));
		System.out.println(getMaxLength2(input));
		System.out.println(getMaxLength3(input));
		System.out.println();

		input = "(()(()()a)";
		System.out.println(getMaxLength1(input));
		System.out.println(getMaxLength2(input));
		System.out.println(getMaxLength3(input));
		System.out.println();

		input = "()(())";
		System.out.println(getMaxLength1(input));
		System.out.println(getMaxLength2(input));
		System.out.println(getMaxLength3(input));
		System.out.println();
	}

}
