package sxy.algorithm.nowcoder.advanced.chapter05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 加油站问题
 * 
 * （基础问题见https://leetcode.com/problems/gas-station/）
 * 
 * 有N个加油站连成环形，给定两个长度为N的正整数数组gas和cost，其中gas表示加油站总共的油，cost表示从当前加油站到下一个加油站所需要的油.
 * 你开车从某一个站出发 （起始时油箱为空），加了油后向下一个加油站进发，问以哪些加油站作为起点能够使得你可以绕一圈后回到该加油站？返回能回到的
 * 加油站的下标，如果一个都没有则返回 -1.
 * 
 * 
 * 思路：每到一个地方都加油，然后耗油去下一个地方，故而可以使用一个数组help表示从当前加油站到下一加油站的所耗的净油量，即help[i]=cost[i]-
 * gas[i]. 暴力解为从每个地方出发，都累加这个help数组，在回到该点的过程中累加和没有出现负数，则说明可以回到该加油站.
 * 
 * @author Kevin
 * 
 */
public class GasStation {

	// 暴力解，时间复杂度O(N^2)，额外空间复杂度O(N)
	public static List<Integer> getValidIndexs1(int[] gas, int[] cost) {
		if (gas == null || cost == null || gas.length != cost.length
				|| gas.length < 1) {
			throw new IllegalArgumentException();
		}

		List<Integer> list = new ArrayList<>();
		int[] help = new int[gas.length * 2];// 两个数组连成环，只需要考虑0~gas.length-1的范围即可
		for (int i = 0; i < help.length; i++) {
			help[i] = gas[i % gas.length] - cost[i % gas.length];
		}

		for (int i = 0; i < gas.length; i++) {// i是起始下标
			int sum = 0;
			int step = 0;
			for (; step < gas.length; step++) {
				sum += help[i + step];
				if (sum < 0) {
					break;
				}
			}

			if (step == gas.length) {
				list.add(i);
			}
		}

		if (list.isEmpty()) {
			list.add(-1);
		}

		return list;
	}

	// 利用窗口内最大最小值更新结构优化，时间复杂度O(N)，额外空间复杂度O(N)
	public static List<Integer> getValidIndexs2(int[] gas, int[] cost) {
		if (gas == null || cost == null || gas.length != cost.length
				|| gas.length < 1) {
			throw new IllegalArgumentException();
		}

		List<Integer> list = new ArrayList<>();
		int[] help = new int[gas.length * 2];// 两个数组连成环，只需要考虑0~gas.length-1的范围即可
		for (int i = 0; i < help.length; i++) {
			help[i] = gas[i % gas.length] - cost[i % gas.length];
		}

		int[] sums = new int[gas.length * 2];// help数组的前缀和数组
		int sum = 0;
		for (int i = 0; i < sums.length; i++) {
			sum += help[i];
			sums[i] = sum;
		}

		UpdateDeque queue = new UpdateDeque(sums);
		for (int i = 0; i < gas.length; i++) {// 最开始窗口里有gas.length个数
			queue.slideR();
		}

		for (int i = 0; i < gas.length; i++) {// i是起始下标
			int cirterion = queue.getMinNumInWindow() - sums[i] + help[i];// getMinNumInWindow()平均时间复杂度O(1)
			queue.slideL();
			queue.slideR();
			if (cirterion >= 0) {
				list.add(i);
			}
		}

		if (list.isEmpty()) {
			list.add(-1);
		}

		return list;
	}

	public static class UpdateDeque {
		public LinkedList<Integer> deque;
		public int[] arr;
		public int L;
		public int R;

		public UpdateDeque(int[] arr) {
			super();
			this.arr = arr;
			this.deque = new LinkedList<>();
			this.L = 0;
			this.R = 0;
		}

		public Integer getMinNumInWindow() {
			if (deque.isEmpty()) {
				return null;
			}
			return arr[deque.peekFirst()];
		}

		public void slideR() {
			if (R == arr.length) {
				return;
			}
			R++;
			while (!deque.isEmpty() && arr[deque.peekLast()] >= arr[R - 1]) {
				deque.pollLast();
			}
			deque.offerLast(R - 1);
		}

		public void slideL() {
			if (L == R || L == arr.length) {
				return;
			}
			L++;
			if (deque.peekFirst() < L) {
				deque.pollFirst();
			}
		}
	}

	// 时间复杂度O(N)，额外空间复杂度O(1)
	public static List<Integer> getValidIndexs3(int[] gas, int[] cost) {
		if (gas == null || cost == null || gas.length != cost.length
				|| gas.length < 1) {
			throw new IllegalArgumentException();
		}

		List<Integer> list = new ArrayList<>();
		int[] help = new int[gas.length];
		int init = -1;// 起始的考察点

		for (int i = 0; i < help.length; i++) {
			help[i] = gas[i % gas.length] - cost[i % gas.length];
			if (help[i] >= 0) {
				init = i;
			}
		}

		if (init == -1) {// 里面一个正数都没有，直接返回
			list.add(-1);
			return list;
		}

		// 里面有正数，从init位置开始找
		int end = nextIndex(init, help);// 连通区[init,end)
		int need = 0;// 当前点到连通区头部所需要的净油量
		int rest = 0;// 当前点走到连通区尾部还剩的油量
		int cur = init;// 从init开始考查

		while (true) {// 出本循环只有两种情况：end==init或者cur==end

			if (help[cur] < 0) {// 当前点是负的
				need += -help[cur];
			} else {// 当前点是正的，可能扩头部
				int tmp = help[cur] - need;
				need = tmp >= 0 ? 0 : -tmp;
				if (need == 0) {// 连通了
					rest += tmp;
					init = cur;
				}
			}
			if (cur == end) {
				break;
			}

			while (rest + help[end] >= 0) {// 扩尾部
				rest += help[end];
				end = nextIndex(end, help);
				if (end == cur) {
					break;
				}
			}

			if (end == init) {
				break;
			}

			cur = lastIndex(cur, help);
		}

		if (end == init) {// end==init的情况，直接扩成了一圈
			list.add(init);
			need = 0;
			cur = lastIndex(init, help);
			while (cur != end) {// 从init前一个点开始看能否到init，能到就可以转一圈
				if (help[cur] < 0) {// 当前点是负的
					need += -help[cur];
				} else {// 当前点是正的，可能扩头部
					int tmp = help[cur] - need;
					need = tmp >= 0 ? 0 : -tmp;
					if (need == 0) {// 连通了
						list.add(cur);
					}
				}
				cur = lastIndex(cur, help);
			}
		}

		// cur==end的情况没有点符合
		if (list.isEmpty()) {
			list.add(-1);
		}

		return list;
	}

	private static int nextIndex(int cur, int[] arr) {
		if (cur == arr.length - 1) {
			return 0;
		} else {
			return cur + 1;
		}
	}

	private static int lastIndex(int cur, int[] arr) {
		if (cur == 0) {
			return arr.length - 1;
		} else {
			return cur - 1;
		}
	}

	public static void main(String[] args) {
		int testTime = 50000;
		int maxSize = 30;
		int maxValue = 30;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[][] arr = generateRandomArrayEqualLength(maxSize, maxValue);
			List<Integer> res1 = getValidIndexs2(arr[0], arr[1]);
			List<Integer> res2 = getValidIndexs3(arr[0], arr[1]);
			List<Integer> comp = getValidIndexs1(arr[0], arr[1]);
			if (!isEqual(comp, res1) || !isEqual(comp, res2)) {
				succeed = false;
				printArray(arr[0]);
				printArray(arr[1]);
				System.out.println(res1);
				System.out.println(res2);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

	}

	private static boolean isEqual(List<Integer> a, List<Integer> b) {
		if (a.size() != b.size()) {
			return false;
		}

		for (int num : a) {
			if (!b.contains(num)) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static int[][] generateRandomArrayEqualLength(int maxSize,
			int maxValue) {
		int[][] arr = new int[2][(int) ((maxSize) * Math.random() + 1)];
		for (int i = 0; i < arr[0].length; i++) {
			arr[0][i] = (int) ((maxValue + 1) * Math.random());
			arr[1][i] = (int) ((maxValue + 1) * Math.random());
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
}
