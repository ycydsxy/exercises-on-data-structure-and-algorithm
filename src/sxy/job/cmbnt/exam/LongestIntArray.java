package sxy.job.cmbnt.exam;

import java.util.Scanner;

/**
 * 给定一个正整数n，找到一串连续的正整数组，使得其加和等于n。请输出所有的满足条件的正整数组，如果没有，请输出 NULL.
 * 
 * @author Kevin
 * 
 */
public class LongestIntArray {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		s.close();

		int left = 1;
		int right = 2;
		boolean flag = false;

		while (left < right && right <= (n + 1) / 2) {
			if (getSum(left, right) < n) {
				right++;
			} else if (getSum(left, right) > n) {
				left++;
			} else {
				flag = true;
				printArray(left, right);
				right++;
			}
		}

		if (!flag) {
			System.out.println("NULL");
		}
	}

	private static int getSum(int left, int right) {
		return (left + right) * (right - left + 1) / 2;
	}

	private static void printArray(int left, int right) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = left; i < right; i++) {
			sb.append(i).append(",");
		}
		sb.append(right).append("]");
		System.out.println(sb.toString());
	}
}
