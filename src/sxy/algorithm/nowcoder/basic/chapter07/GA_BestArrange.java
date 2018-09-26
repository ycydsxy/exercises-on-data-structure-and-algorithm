package sxy.algorithm.nowcoder.basic.chapter07;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 安排时间问题
 * 
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目 的宣讲。 给你每一个项目开始的时间和结束的时间(给你一个数组，里面
 * 是一个个具体的项目)，你来安排宣讲的日程，要求会 议室进行 的宣讲的场次最多。返回这个最多的宣讲场次。
 * 
 * 思路：贪心策略为，每次选择结束时间最早的进行安排。见https://blog.csdn.net/qq_32400847/article/details/51336300.
 * 
 * @author Kevin
 * 
 */
public class GA_BestArrange {

	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}

	public static int bestArrange(Program[] programs, int start) {
		Arrays.sort(programs, new ProgramComparator());
		int result = 0;
		for (int i = 0; i < programs.length; i++) {
			if (start <= programs[i].start) {
				result++;
				start = programs[i].end;
			}
		}
		return result;
	}
}
