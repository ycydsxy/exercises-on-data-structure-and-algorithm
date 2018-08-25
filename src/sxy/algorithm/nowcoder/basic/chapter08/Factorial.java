package sxy.algorithm.nowcoder.basic.chapter08;

/**
 * 计算n!.
 * 
 * @author Kevin
 * 
 */
public class Factorial {

	public static void main(String[] args) {
		long start = System.nanoTime();
		long res1 = calculate1(20);
		long end = System.nanoTime();
		System.out.println(res1);
		System.out.println(end - start);

		start = System.nanoTime();
		long res2 = calculate2(20);
		end = System.nanoTime();

		System.out.println(res2);
		System.out.println(end - start);

	}

	private static long calculate1(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		} else if (n == 0) {
			return 0L;
		} else if (n == 1) {
			return 1L;
		}
		return calculate1(n - 1) * n;
	}

	private static long calculate2(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		} else if (n == 0) {
			return 0L;
		} else if (n == 1) {
			return 1L;
		}

		long res = 1;
		for (int i = n; i > 1; i--) {
			res *= i;
		}

		return res;
	}
}
