package sxy.algorithm.exams.huawei;

import java.util.Scanner;

/**
 * 输入一个字符串，找其最长公共子串(若等长则返回在前面的那个)，并输出该字符串和其长度.
 * 
 * 输入:
 * ATCGATCGLOWELAD
 * 
 * 输出:
 * ATCG 4
 * 
 * 注：目前的解法不能通过所有的测试样例，原因待查明.
 * 
 * @author Kevin
 *
 */
public class ExamC {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();

		int max = 0;
		String result = "";

		int first = 0;// 起始位置
		int count = 0;
		for (int p1 = 1; p1 < input.length(); p1++) {
			for (int p2 = 0; p2 < input.length() - p1; p2++) {
				if (input.charAt(p2) == input.charAt(p1 + p2)) {
					count++;
				} else {
					count = 0;
				}
				if (count > max) {
					max = count;
					first = p2 - count + 1;
				}
			}
		}

		if (max > 0) {
			result = input.substring(first, first + max);
		}

		System.out.println(result + " " + max);

	}
}
