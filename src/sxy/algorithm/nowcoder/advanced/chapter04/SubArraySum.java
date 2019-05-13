package sxy.algorithm.nowcoder.advanced.chapter04;

/**
 * 子数组累加和问题
 * 
 * 给你一个整数数组arr（可能有负值）和一个整数aim。求累加和小于等于aim的最长子数组的长度，要求时间复杂度O(N).
 * 
 * 思路：最优算法不严格纠结每个位置开头的答案，只关注有没有更长的可能，短的中间结果直接不求，故而右边界不回退，时间复杂度从O(N^2)降到O(N).
 * 
 * @author ZuoChengyun & Kevin
 * 
 */

public class SubArraySum {

	// 最优算法，时间复杂度O(N)
	public static int maxLengthAwesome(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] min_sums = new int[arr.length];// 以i位置开头的子数组的最小累加和
		int[] min_sums_ends = new int[arr.length];// 以i位置开头的子数组累加和最小时的右边界
		min_sums[arr.length - 1] = arr[arr.length - 1];
		min_sums_ends[arr.length - 1] = arr.length - 1;

		for (int i = arr.length - 2; i >= 0; i--) {
			if (min_sums[i + 1] < 0) {
				min_sums[i] = arr[i] + min_sums[i + 1];
				min_sums_ends[i] = min_sums_ends[i + 1];
			} else {// 无利可图，只包含自己
				min_sums[i] = arr[i];
				min_sums_ends[i] = i;
			}
		}

		int R = 0;// 已形成的窗口的下一个位置，有效范围为[L,R)
		int sum = 0;// 窗口[L,R-1]的累加和
		int res = 0;// 累加和<=aim的最长子数组长度
		for (int L = 0; L < arr.length; L++) {
			while (R < arr.length && sum + min_sums[R] <= k) {// 窗口能够包含进R时，窗口往右扩
				sum += min_sums[R];
				R = min_sums_ends[R] + 1;
			}
			// 当前L下，最长窗口已经形成，求长度
			res = Math.max(res, R - L);
			// 窗口还有则左边界右移，窗口中没东西了就另起炉灶
			sum -= R > L ? arr[L] : 0;
			R = Math.max(R, L + 1);
		}
		return res;
	}

	// 常规解法，O(N^2)
	public static int maxLength(int[] arr, int k) {
		int[] h = new int[arr.length + 1];
		int sum = 0;
		h[0] = sum;
		for (int i = 0; i != arr.length; i++) {
			sum += arr[i];
			h[i + 1] = Math.max(sum, h[i]);
		}
		sum = 0;
		int res = 0;
		int pre = 0;
		int len = 0;
		for (int i = 0; i != arr.length; i++) {
			sum += arr[i];
			pre = getLessIndex(h, sum - k);
			len = pre == -1 ? 0 : i - pre + 1;
			res = Math.max(res, len);
		}
		return res;
	}

	public static int getLessIndex(int[] arr, int num) {
		int low = 0;
		int high = arr.length - 1;
		int mid = 0;
		int res = -1;
		while (low <= high) {
			mid = (low + high) / 2;
			if (arr[mid] >= num) {
				res = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return res;
	}

	// for test
	public static int[] generateRandomArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
		}
		return res;
	}

	public static void main(String[] args) {
		boolean succeed = true;
		for (int i = 0; i < 1000000; i++) {
			int[] arr = generateRandomArray(10, 20);
			int k = (int) (Math.random() * 20) - 5;
			int res = maxLengthAwesome(arr, k);
			int comp = maxLength(arr, k);
			if (res != comp) {
				succeed = false;
				System.out.println(k);
				printArray(arr);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
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
