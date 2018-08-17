package sxy.algorithm.nowcoder.chapter02;

import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/ac61207721a34b74b06597fe6eb67c52
 * 来源：牛客网
 * 
 * 给定一个十进制数M，以及需要转换的进制数N。将十进制数M转化为N进制数 输入描述:
 * 
 * 输入为一行，M(32位整数)、N(2 ≤ N ≤ 16)，以空格隔开。
 * 
 * 
 * 输出描述:
 * 
 * 为每个测试实例输出转换后的数，每个输出占一行。如果N大于9，则对应的数字规则参考16进制（比如，10用A表示，等等）
 * 
 * @author Kevin
 * 
 */
public class Exercise01 {
	public static void main(String[] args) {
		String numbers = "0123456789ABCDEF";
		Scanner s = new Scanner(System.in);
		int m = s.nextInt();
		int n = s.nextInt();
		boolean flag = m < 0 ? true : false;
		m = flag ? -m : m;
		s.close();
		StringBuilder result = new StringBuilder();
		while (true) {
			int p = m / n;
			int q = m - n * p;
			result.append(numbers.charAt(q));
			if (p == 0) {
				break;
			}
			m = p;
		}
		String output = result.reverse().toString();
		output = flag ? "-" + output : output;
		System.out.println(output);
	}

}
