package sxy.algorithm.nowcoder.basic.chapter08;

/**
 * 汉诺塔问题
 * 
 * 打印n层汉诺塔从最左边移动到最右边的全部过程
 * 
 * 解法：考查分解规模更小的子问题和递归实现。
 * 
 * @author Kevin
 * 
 */
public class Hanoi {

	public static void main(String[] args) {
		int n = 3;
		process(n, "左", "右", "中");
	}

	// 将编号为n的盘子（含）上所有的盘子通过other从from移动到to，打印其中所有的过程
	private static void process(int n, String from, String to, String other) {
		if (n == 0) {
			return;
		}
		process(n - 1, from, other, to);
		System.out.println(String.format("将 %d 从 %s 移动到 %s", n, from, to));
		process(n - 1, other, to, from);
	}

}
