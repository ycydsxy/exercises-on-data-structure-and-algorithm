package sxy.algorithm.nowcoder.basic.chapter08;

import java.math.BigInteger;

/**
 * n为非负整数(n<=100)，计算n!.
 * 
 * 解法：虽然很简单，但是要注意：long类型在21!就溢出了！
 * 
 * @author Kevin
 * 
 */
public class Factorial {

	public static void main(String[] args) {
		int n = 80;
		long start = System.nanoTime();
		long res1 = calculate1(n);
		long end = System.nanoTime();
		System.out.println(res1);
		System.out.println(end - start);

		start = System.nanoTime();
		long res2 = calculate2(n);
		end = System.nanoTime();

		System.out.println(res2);
		System.out.println(end - start);

		start = System.nanoTime();
		BigInteger res3 = calculate3(n);
		end = System.nanoTime();

		System.out.println(res3);
		System.out.println(end - start);

		start = System.nanoTime();
		calculate4(n);
		end = System.nanoTime();

		System.out.println(end - start);

	}

	// 阶乘的定义，递归调用（n大于20必定溢出）
	public static long calculate1(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		} else if (n == 0) {
			return 1L;
		} else if (n == 1) {
			return 1L;
		}
		return calculate1(n - 1) * n;
	}

	// 阶乘的循环写法（n大于20必定溢出）
	public static long calculate2(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		} else if (n == 0) {
			return 1L;
		} else if (n == 1) {
			return 1L;
		}

		long res = 1;
		for (int i = n; i > 1; i--) {
			res *= i;
		}

		return res;
	}

	// 使用循环，利用BigInteger，不会溢出
	public static BigInteger calculate3(int n) {
		BigInteger res = BigInteger.valueOf(1);

		if (n < 0) {
			throw new IllegalArgumentException();
		} else if (n == 0) {
			return res;
		} else if (n == 1) {
			return res;
		}

		for (int i = n; i > 1; i--) {
			res = res.multiply(BigInteger.valueOf(i));
		}

		return res;
	}

	// 使用数组存储每一位数字，自己进位
	public static void calculate4(int n) {
		int num[] = new int[1000];
		int i, j;
		if (n == 1 || n == 0) {
			System.out.println(1);
		} else {
			int p, h;// p 存放当前结果的位数，h为进位；
			p = 1;
			h = 0;
			num[1] = 1;
			for (i = 2; i <= n; i++) {
				// 使得a[]的每位与i相乘
				for (j = 1; j <= p; j++) {
					num[j] = num[j] * i + h;
					h = num[j] / 10;
					num[j] = num[j] % 10;
				}
				// 表示向新的位置进位
				while (h > 0) {
					num[j] = h % 10;
					h = h / 10;
					j++;
				}
				p = j - 1;
			}
			StringBuilder sb = new StringBuilder();
			for (i = p; i >= 1; i--) {
				sb.append(num[i]);
			}
			System.out.println(sb);
		}
	}
}
