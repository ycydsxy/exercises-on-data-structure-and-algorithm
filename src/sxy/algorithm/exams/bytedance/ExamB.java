package sxy.algorithm.exams.bytedance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 区间合并 ，输入多个区间，将这些区间进行合并
 * 
 * 输入:
 * 2 
 * 1,5;2,6; 
 * 7,8;
 * 
 * 输出:
 * 1,6;7,8
 * 
 * @author Kevin
 * 
 */
public class ExamB {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = Integer.parseInt(sc.nextLine());
		List<Interval> intervals = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			String[] groups = sc.nextLine().split(";");
			for (String group : groups) {
				String[] items = group.split(",");
				intervals.add(new Interval(items[0], items[1]));
			}
		}
		sc.close();

		List<Interval> result = merge(intervals);
		StringBuilder sb = new StringBuilder();
		for (Interval item : result) {
			sb.append(item.start);
			sb.append(",");
			sb.append(item.end);
			sb.append(";");
		}
		String sbString = sb.toString();
		String resultString = sbString.substring(0, sbString.length() - 1);
		System.out.println(resultString);
	}

	private static List<Interval> merge(List<Interval> intervals) {
		List<Interval> result = new LinkedList<>();

		if (intervals == null || intervals.size() < 1) {
			return result;
		}

		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval arg0, Interval arg1) {
				return arg0.start - arg1.start;
			}
		});

		Interval pre = null;

		for (Interval i : intervals) {
			if (pre == null || pre.end < i.start) {
				result.add(i);
				pre = i;
			} else if (pre.end < i.end) {
				pre.end = i.end;
			}
		}

		return result;
	}

}

class Interval {
	int start;
	int end;

	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public Interval(String start, String end) {
		this.start = Integer.parseInt(start);
		this.end = Integer.parseInt(end);
	}

	@Override
	public String toString() {
		return "Interval [start=" + start + ", end=" + end + "]";
	}

}
