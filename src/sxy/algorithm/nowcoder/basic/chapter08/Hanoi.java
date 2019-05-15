package sxy.algorithm.nowcoder.basic.chapter08;

/**
 * 汉诺塔问题
 * 
 * 1.标准汉诺塔：打印n层汉诺塔从最左边移动到最右边的全部过程，或返回总步数
 * 
 * 2.拓展汉诺塔：给一个长度为n-1的数组（只含1,2,3，分别代表左、中、右杆）表示目前n个圆盘的状态，问这个状态是1问题里的第几步？非法状态返回-1.
 * 
 * @author Kevin
 * 
 */
public class Hanoi {

	public static void main(String[] args) {
		int n = 2;
		System.out.println(process(n, 0, 2, 1));// 标准汉诺塔

		int[] arr = { 2, 2, 2 };
		System.out.println(processExtend(arr, arr.length - 1, 0, 2, 1));// 拓展汉诺塔
	}

	// 将编号为n的盘子（含）上所有的盘子通过other从from移动到to，打印其中所有的过程，返回所用步数
	private static int process(int n, int from, int to, int other) {
		if (n == -1) {
			return 0;
		}
		int res = 1;
		res += process(n - 1, from, other, to);
		System.out.println(String.format("将%d号盘从%d杆移动到%d杆", n, from, to));
		res += process(n - 1, other, to, from);
		return res;
	}

	// 给定一个状态arr，求arr是将把0~i号盘子从from挪到to中的第几步
	private static int processExtend(int[] arr, int i, int from, int to,
			int other) {
		if (i == -1) {// 0个盘子
			return 0;
		}

		if (arr[i] != from && arr[i] != to) {// 最后的盘子在other号上，不可能
			return -1;
		} else if (arr[i] == from) {// 最后的盘子在from上
			return processExtend(arr, i - 1, from, other, to);
		} else {// 只差将0~i-1号盘子从other上挪到to上
			int res = (1 << i) - 1;// 将0~i-1个盘子从from挪到other上的步数，汉诺塔的公式
			res++;// 将最后一个盘子从from挪到to需要1步
			int rest = processExtend(arr, i - 1, other, to, from);
			if (rest == -1) {
				return -1;
			}
			return res + rest;
		}
	}
}
