package sxy.algorithm.exams.huawei;

import java.util.Scanner;
import java.util.Stack;

/**
 * 输入一组带括号的字符串，判断中最长的合法括号的数量(合法是指其中没有其他字符，如(()))的合法括号长度为4)
 * 
 * 输入:
 * (((a))((
 * 
 * 输出:
 * 0
 * 
 * @author Kevin
 *
 */
public class ExamB {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] input = sc.nextLine().toCharArray();
		sc.close();

		Stack<Integer> stack = new Stack<>();
		int max = 0;
		for (int i = 0; i < input.length; i++) {
			if (input[i] == ')' && !stack.isEmpty()
					&& input[stack.peek()] == '(') {
				stack.pop();
				if (stack.isEmpty()) {
					max = i + 1;
				} else {
					if (i - stack.peek() > max) {
						max = i - stack.peek();
					}
				}

			} else {
				stack.push(i);
			}
		}

		System.out.println(max);
	}
}
