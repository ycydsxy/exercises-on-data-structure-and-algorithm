package sxy.job.tencent.exam;

import java.util.Scanner;

/**
 * 设有x首长度为a的歌曲，y首长度为b的歌曲（每首歌名都不一样），请从其中挑选出若干歌曲，使得加起来长度为k。其中，x、a、y、b、k均为整数，
 * 问一共有多少种找法？(结果模1000000007)
 * 
 * @author Kevin
 * 
 */
public class ExamA {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int a = sc.nextInt();
		int x = sc.nextInt();
		int b = sc.nextInt();
		int y = sc.nextInt();
		sc.close();
		int res = 0;

		for (int i = 0; i <= k / a && i <= a; i++) {
			int j = (k - i * a) / b;
			if (k != i * a + j * b || j > y) {
				continue;
			} else {
				res += (C(i, x) * C(j, y)) % 1000000007;
			}
		}

		System.out.println(res % 1000000007);
	}

	public static int A(int up, int bellow) {
		int result = 1;
		for (int i = up; i > 0; i--) {
			result *= bellow;
			bellow--;
		}
		return result;
	}

	public static int C(int up, int below) {
		int helf = below / 2;

		if (up > helf) {
			up = below - up;
		}
		int denominator = A(up, up);
		int numerator = A(up, below);
		return numerator / denominator;
	}
}
