package sxy.algorithm.nowcoder.advanced.chapter04;

/**
 * 水量面积
 * 
 * 给定一直方图数组Arr，其中全为非负整数，将其看作容器，求最大能装水的水量面积。
 * 
 * 思路：
 * 
 * 求每个i位置以上能放多少水即可，i位置的水量为Math.min(左边最大高度，右边最大高度)-arr[i]，负数则说明放不了水，将每个位置的水量加起来即可
 * 。
 * 
 * @author Kevin
 * 
 */

public class WaterArea {

	public static int getWaterArea(int[] arr) {
		if (arr == null || arr.length <= 2) {
			return 0;
		}

		int ans = 0;
		int l = 0;
		int r = arr.length - 1;
		int lMax = -1;
		int rMax = -1;

		while (l <= r) {
			int tempMin = Math.min(lMax, rMax);
			if (tempMin == lMax) {// 左边是短板
				ans += tempMin - arr[l] < 0 ? 0 : tempMin - arr[l];
				lMax = Math.max(lMax, arr[l]);
				l++;
			} else {// 右边是短板
				ans += tempMin - arr[r] < 0 ? 0 : tempMin - arr[r];
				rMax = Math.max(rMax, arr[r]);
				r--;
			}
		}

		return ans;
	}

	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
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

	public static void main(String[] args) {
		int testTime = 5;
		int maxSize = 7;
		int maxValue = 30;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int res = getWaterArea(arr);
			printArray(arr);
			System.out.println(res);
		}

	}

}
