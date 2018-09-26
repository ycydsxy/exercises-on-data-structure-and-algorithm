package sxy.job.bytedance.exam;

import static java.lang.Math.*;

/**
 * 类似背包问题
 * 
 * 小a和小b玩一个游戏，有n张卡牌，每张上面有两个正整数x,y。 取一张牌时，个人积分增加x，团队积分增加y。
 * 求小a，小b各取若干张牌，使得他们的个人积分相等。
 * 
 * 输入： 第一行一个整数n。 接下来n行，每行两个整数x,y，用空格隔开。
 * 
 * 输出： 一行一个整数表示小a的积分和小b的积分相等的时候，团队积分的最大值。
 * 
 * 输入示例：
 * 
 * 4
 * 
 * 3 1
 * 
 * 2 2
 * 
 * 1 4
 * 
 * 1 4
 * 
 * 输出示例：
 * 
 * 10
 * 
 * 其他： 对于100%的数据，0<n<=100，1<x<=1e3，0<y<=1e6。
 * 
 * @author Kevin
 * 
 */
public class ExamC {

	// 暴力递归解
	public static int getMaxGroupScore1(int[] x, int[] y) {
		return process(0, 0, x, y);
	}

	// 返回从index位置开始发牌，且发牌前两人个人积分相差distance时所获得的最大团体分数
	private static int process(int index, int distance, int[] x, int[] y) {
		if (index == x.length) {
			return distance == 0 ? 0 : Integer.MIN_VALUE;
		}

		int res1 = process(index + 1, distance, x, y);// 这张牌不拿
		int res2 = process(index + 1, distance + x[index], x, y) + y[index];// 这张牌拿给小a
		int res3 = process(index + 1, distance - x[index], x, y) + y[index];// 这张牌拿给小b

		return max(max(res1, res2), res3);
	}

	// dp
	public static int getMaxGroupScore2(int[] x, int[] y) {
		int md = 0; // maxDistance两者相差的最大个人积分：所有的牌都归小a或者小b，拿完后两人个人积分相差最多
		for (int i = 0; i < x.length; i++) {
			md += x[i];
		}

		int[][] dp = new int[x.length + 1][2 * md + 1];// 横坐标从0到x.length，纵坐标从-md到+md(下面可能会涉及纵坐标的转换)

		for (int j = 0; j < 2 * md + 1; j++) {
			if (j != md) {
				dp[x.length][j] = Integer.MIN_VALUE;
			}
		}

		for (int i = x.length - 1; i >= 0; i--) {
			for (int j = 0; j < 2 * md + 1; j++) {
				dp[i][j] = dp[i + 1][j];
				if (j - x[i] >= 0) {
					dp[i][j] = max(dp[i][j], dp[i + 1][j - x[i]] + y[i]);
				}
				if (j + x[i] < 2 * md + 1) {
					dp[i][j] = max(dp[i][j], dp[i + 1][j + x[i]] + y[i]);
				}
			}
		}

		return dp[0][md];
	}

	public static void main(String[] args) {
		int[] x = { 3, 2, 1, 1 };// 个人积分
		int[] y = { 1, 2, 4, 4 };// 团队积分
		System.out.println(getMaxGroupScore1(x, y));
		System.out.println(getMaxGroupScore2(x, y));

	}

}
