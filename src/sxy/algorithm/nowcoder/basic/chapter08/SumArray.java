package sxy.algorithm.nowcoder.basic.chapter08;

/**
 * 给你一个数组arr，和一个整数aim。如果可以任意选择arr中的 数字，能不能累加得到aim，返回true或者false
 * 
 * @author Kevin
 * 
 */
public class SumArray {

	public static boolean canSum1(int[] arr, int aim) {
		if (arr == null || aim < 0) {
			return false;
		}
		return process(0, aim, arr);

	}

	// index位置到之后的数字可以任意使用，累加得到aim，返回可能性
	private static boolean process(int index, int aim, int[] arr) {
		if (index == arr.length) {
			return aim == 0;
		}

		return process(index + 1, aim, arr)
				|| process(index + 1, aim - arr[index], arr);
	}

	public static boolean canSum2(int[] arr, int aim) {
		if (arr == null || aim < 0) {
			return false;
		}

		boolean[][] dp = new boolean[aim + 1][arr.length + 1];
		for (int j = 0; j < dp[0].length; j++) {
			dp[0][j] = true;
		}

		for (int i = 1; i < dp.length; i++) {
			for (int j = dp[0].length - 2; j >= 0; j--) {
				if (i - arr[j] >= 0) {
					dp[i][j] = dp[i][j + 1] || dp[i - arr[j]][j + 1];
				} else {
					dp[i][j] = dp[i][j + 1];
				}
			}
		}

		return dp[aim][0];
	}

	public static void main(String[] args) {
		int[] arr = { 2, 4, 5, 7 };
		int aim = 9;
		System.out.println(canSum1(arr, aim));
		System.out.println(canSum2(arr, aim));

		aim = 10;
		System.out.println(canSum1(arr, aim));
		System.out.println(canSum2(arr, aim));

	}
}
