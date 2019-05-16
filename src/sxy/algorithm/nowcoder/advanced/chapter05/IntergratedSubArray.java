package sxy.algorithm.nowcoder.advanced.chapter05;

import java.util.HashSet;

/**
 * 可整合子数组最大长度
 * 
 * 规定可整合数组的定义为，排序后从前到后依次递增1.给你一个整型数组arr，求其可整合的子数组最大长度.
 * 
 * 思路：
 * 
 * 1.极度暴力解.子数组个数O(N^2)，排序O(Nlog(N))，再挨个扫一下，看是否递增1，O(N)，故总共O(N^4log(N)).
 * 
 * 2.这题的问题在于这个可整合标准耗时太多，可以将其该写为：最大值-最小值=最右下标-最左下标，这样就好做多了。
 * 
 * @author ZuoChengyun & Kevin
 * 
 */
public class IntergratedSubArray {

	public static int getMaxIntergratedSubArrayLength(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}

		int len = 0;
		int max = 0;
		int min = 0;
		HashSet<Integer> set = new HashSet<>();// 有无重复值
		for (int L = 0; L < arr.length; L++) {
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			for (int R = L; R < arr.length; R++) {
				if (set.contains(arr[R])) {
					break;
				}
				set.add(arr[R]);
				max = Math.max(max, arr[R]);
				min = Math.min(min, arr[R]);
				if (max - min == R - L) {
					len = Math.max(len, R - L + 1);
				}
			}
			set.clear();
		}
		return len;
	}
}
