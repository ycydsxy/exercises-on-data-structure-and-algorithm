package sxy.algorithm.nowcoder.advanced.chapter05;

/**
 * 丑数问题
 * 
 * 若一个正整数，其只有1、2、3和5的因子则其为丑数.给定一个正整数N，求第N个丑数.
 * 
 * 思路：暴力解就是验每一个数是不是丑数.更好的思路是抓住丑数只有2、3和5的因子，故而每一个丑数都是由前面丑数乘以2或者乘以3或者乘以5得到的.
 * 
 * @author Kevin
 * 
 */
public class UglyNumber {

	public static int getUglyNumber(int n) {
		int[] help = new int[n];
		help[0] = 1;
		int x = 0;// 2因子在0位置
		int y = 0;// 3因子在0位置
		int z = 0;// 5因子在0位置

		for (int index = 1; index < n; index++) {
			help[index] = Math.min(2 * help[x],
					Math.min(3 * help[y], 5 * help[z]));
			if (help[index] == 2 * help[x]) {
				x++;
			}
			if (help[index] == 3 * help[y]) {
				y++;
			}
			if (help[index] == 5 * help[z]) {
				z++;
			}
		}

		return help[n - 1];
	}

	public static void main(String[] args) {
		System.out.println(getUglyNumber(10));
	}
}
