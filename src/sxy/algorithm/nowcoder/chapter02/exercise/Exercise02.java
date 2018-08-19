package sxy.algorithm.nowcoder.chapter02.exercise;

import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/6ffdd7e4197c403e88c6a8aa3e7a332a
 * 来源：牛客网
 * 
 * 输入一个正整数n,求n!(即阶乘)末尾有多少个0？ 比如: n = 10; n! = 3628800,所以答案为2 输入描述:
 * 
 * 输入为一行，n(1 ≤ n ≤ 1000)
 * 
 * 输出描述:
 * 
 * 输出一个整数,即题目所求
 * 
 * 解法：其实就是对n，n-1，...1这些数分解因式，看有多少个2和5。（2的数量太多，不用管了，只用看有多少个5）
 * 
 * @author Kevin
 * 
 */
public class Exercise02 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		s.close();
		int k5 = 0;
		for (int i = n; i >= 1; i--) {
			int temp = i;
			while (true) {
				int p = temp / 5;
				int q = temp - 5 * p;
				if (q == 0) {
					k5++;
					temp = p;
				} else {
					break;
				}
			}
		}

		System.out.println(k5);

	}

}
