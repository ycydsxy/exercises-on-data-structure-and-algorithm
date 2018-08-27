package sxy.algorithm.nowcoder.basic.chapter08;

/**
 * 假设有货币面值的数组arr，其中每个面值的货币数量均无限，给你一个目标钱数aim用这些面值进行找零，求解一共有多少种找零的方法。
 * 
 * @author Kevin
 * 
 */
public class ChangeProblem {

	public static int getSolutionNum1(int[] arr, int aim) {

		return process(0, aim, arr);
	}

	// 返回任意使用index位置以后的arr数组中的面值，将rest找零的方法数
	private static int process(int index, int rest, int[] arr) {
		if (index == arr.length) {
			return rest == 0 ? 1 : 0;
		}

		int res = 0;
		int k = 0;// index的货币用几张
		while (arr[index] * k <= rest) {
			res += process(index + 1, rest - arr[index] * k, arr);
			k++;
		}

		return res;
	}

	// O(aim^2*N)
	public static int getSolutionNum2(int[] arr, int aim) {
		int[][] dp = new int[aim + 1][arr.length + 1];
		dp[0][arr.length] = 1;

		for (int j = arr.length - 1; j >= 0; j--) {
			for (int i = 0; i <= aim; i++) {
				int k = 0;
				int res = 0;
				while (i - arr[j] * k >= 0) {
					res += dp[i - arr[j] * k][j + 1];
					k++;
				}
				dp[i][j] = res;
			}
		}

		return dp[aim][0];

	}

	// O(aim*N)
	public static int getSolutionNum3(int[] arr, int aim) {
		int[][] dp = new int[aim + 1][arr.length + 1];
		dp[0][arr.length] = 1;

		for (int j = arr.length - 1; j >= 0; j--) {
			for (int i = 0; i <= aim; i++) {
				int res = i - arr[j] >= 0 ? dp[i - arr[j]][j] : 0;
				dp[i][j] = dp[i][j + 1] + res;
			}
		}
		return dp[aim][0];
	}

	public static void main(String[] args) {
		int[] arr = { 10, 5, 2, 1 };
		int aim = 10;
		System.out.println(getSolutionNum1(arr, aim));
		System.out.println(getSolutionNum2(arr, aim));
		System.out.println(getSolutionNum3(arr, aim));
	}
}
