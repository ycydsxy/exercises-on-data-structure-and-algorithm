package sxy.job.meituan.interview;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

/**
 * 美团面试题
 * 
 * 设有一组有序区间和一个x值，请找出x是否包含于这些区间的某一个区间中。
 * 
 * @author Kevin
 * 
 */
public class BinarySearchInterval {

	private static class Interval {
		public int start;
		public int end;

		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "[" + start + ", " + end + "]";
		}

	}

	// 遍历比较x是否在区间组intervals中
	public static boolean isInIntervals1(int x, List<Interval> intervals) {
		boolean flag = false;

		for (Interval interval : intervals) {
			if (x <= interval.end && x >= interval.start) {
				flag = true;
				break;
			}
		}

		return flag;

	}

	// 二分查找x是否在区间组intervals中
	public static boolean isInIntervals2(int x, List<Interval> intervals) {
		int s = 0;
		int e = intervals.size() - 1;
		while (e - s > 1) {
			int mid = s + ((e - s) >> 1);
			if (x < intervals.get(mid).start) {
				e = mid;
			} else if (x > intervals.get(mid).end) {
				s = mid;
			} else {
				return true;
			}
		}

		if (x <= intervals.get(s).end && x >= intervals.get(s).start) {
			return true;
		}

		if (x <= intervals.get(e).end && x >= intervals.get(e).start) {
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		int intervalNum = 10;
		int max = 1000;
		int min = 10;
		boolean flag = true;

		for (int i = 0; i < 100000; i++) {
			List<Interval> intervals = getRandomIntervals(intervalNum, min, max);
			int x = (int) (random() * (max - min) + min);
			boolean res1 = isInIntervals1(x, intervals);
			boolean res2 = isInIntervals2(x, intervals);

			if (res1 != res2) {
				System.out.println(String.format("%s is in %s", x, intervals));
				System.out.println(String.format("res1: %s", res1));
				flag = false;
				break;
			}
		}

		if (flag) {
			System.out.println("succeeded!");
		} else {
			System.out.println("fuck!");
		}

	}

	public static List<Interval> getRandomIntervals(int n, int min, int max) {
		List<Interval> intervals = new ArrayList<>();
		int length = (max - min) / n;
		int lastHigh = min;

		for (int i = 0; i < n; i++) {
			int low = (int) (lastHigh + random() * length);
			int high = (int) (low + random() * length);

			if (high < max) {
				intervals.add(new Interval(low, high));
				lastHigh = high;
			}
		}
		return intervals;

	}
}
