package sxy.algorithm.nowcoder.advanced.chapter05;

/**
 * 糖果问题
 * 
 * 一群孩子做游戏，现在请你根据得分来分发糖果，要求如下： 1.每个孩子至少分到一个糖果； 2.任意两个相邻的孩子之间，得分较多的孩子必须多拿糖果；
 * 
 * 给定一个数组arr代表每个孩子的分数，请返回最少需要的糖果数目.
 * 
 * 思路：即是数组中找极大值和极小值的问题，极小值位置分1个糖果，然后两边依次增加，极大值位置取其两边极小值位置累加过来的糖果的较大值即可.
 * 具体的做法是准备两个数组a和b，分别代表从左边上坡和从右边上坡所需要的最少糖果，然后最后的糖果分配就是这两个数组对应位置的最大值.
 * 
 * 
 * 拓展：如果再加一条规则，任意两个相邻的孩子若得分一样，则其糖果数必须相同.求原问题的答案.
 * 
 * 思路：遇到平坡的时候，直接复制即可.
 * 
 * @author Kevin
 * 
 */
public class CandyProblem {

	// 时间复杂度O(N)，额外空间复杂度O(N)
	public static int candy(int[] ratings) {
		if (ratings == null || ratings.length < 1) {
			return 0;
		}

		int res = 0;
		int[] a = new int[ratings.length];
		int[] b = new int[ratings.length];

		a[0] = 1;
		b[0] = 1;
		a[ratings.length - 1] = 1;
		b[ratings.length - 1] = 1;

		int tmp = 1;
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				tmp++;
			} else {
				tmp = 1;
			}
			a[i] = tmp;
		}

		tmp = 1;
		for (int i = ratings.length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1]) {
				tmp++;
			} else {
				tmp = 1;
			}
			b[i] = tmp;
		}

		for (int i = 0; i < ratings.length; i++) {
			res += a[i] > b[i] ? a[i] : b[i];
		}

		return res;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 0, 2 };
		System.out.println(candy(arr));
	}
}
