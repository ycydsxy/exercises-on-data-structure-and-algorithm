package sxy.algorithm.nowcoder.advanced.chapter05;

/**
 * 蓄水池抽样问题
 * 
 * 有一个源不停地向外吐出标号为1,2,3,...的球，你有一个容量为k的袋子，设计一个算法，使得每吐出一个球后，已吐出的球在袋子里的概率都是相同的.
 * 
 * 思路：设现在已经吐到了第n号球，如果n<=k，则直接进袋子就完了.如果n>k，则以k/n的概率让其进袋子，并随便丢弃袋子中原有的球。
 * 
 * 用途：总体规模很大或者不知道总体规模的等概率采样.
 * 
 * @author ZuoChengyun & Kevin
 * 
 */
public class ReservoirSampling {

	// 等概率返回1~max中的一个
	private static int rand(int max) {
		return (int) (Math.random() * max) + 1;
	}

	// 袋子的大小为k，球的数量是n，返回袋子中的球的编号
	public static int[] getKRand(int k, int n) {
		if (n < 1 || k < 1) {
			return null;
		}

		int[] res = new int[Math.min(k, n)];
		for (int i = 1; i <= res.length; i++) {// 袋子还没满就进袋子
			res[i - 1] = i;
		}

		for (int i = k + 1; i <= n; i++) {// 袋子满了
			if (rand(i) <= k) {// 以k/i的概率进袋子
				res[rand(k) - 1] = i;// 等概率替换原来袋子里的球
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int k = 5;
		int n = 20;
		int testTime = 50000;
		int[] res = new int[n];
		for (int i = 0; i < testTime; i++) {
			int[] arr = getKRand(k, n);
			for (int j = 0; j < arr.length; j++) {
				res[arr[j] - 1]++;
			}
		}

		printArray(res);
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
