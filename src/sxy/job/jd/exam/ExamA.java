package sxy.job.jd.exam;

import java.util.Scanner;

/**
 * 现有n个物品，每个物品有三个参数 ai , bi , ci ，定义i物品不合格品的依据是 : 若存在物品 j , 且aj>ai , bj>bi ,
 * cj>ci，则称i物品为不合格品。 现给出n个物品的a,b,c参数，请你求出不合格品的数量。
 * 
 * 输入：
 * n
 * a1 b1 c1
 * a2 b2 c2
 * ...
 * an bn cn
 * 
 * 输出：
 * k
 * 
 * @author Kevin
 * 
 */
public class ExamA {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		int[] c = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = s.nextInt();
			b[i] = s.nextInt();
			c[i] = s.nextInt();
		}
		s.close();

		boolean[] notValid = new boolean[n]; // 不合格为true，合格为false，默认合格

		int[] dp = new int[n];
		dp[0] = 0;

		for (int k = 1; k < n; k++) {
			dp[k] = dp[k - 1];

			// k位置合不合格
			for (int i = 0; i < k; i++) {
				if (a[i] > a[k] && b[i] > b[k] && c[i] > c[k]) {
					dp[k]++;
					notValid[k] = true;
					break;
				}
			}

			// k-1（含）之前的有没有从合格变为不合格的
			for (int i = 0; i < k; i++) {
				if (notValid[i]) {
					if (a[k] > a[i] && b[k] > b[i] && c[k] > c[i]) {
						dp[k]++;
						notValid[i] = true;
					}
				}
			}
		}

		System.out.println(dp[n - 1]);
	}

}
