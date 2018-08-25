package sxy.algorithm.nowcoder.basic.chapter04;

import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/f74c7506538b44399f2849eba2f050b5
 * 来源：牛客网
 * 
 * 由正整数1，2，3……组成了一颗完全二叉树。我们已知这个二叉树的最后一个结点是n。现在的问题是，结点m所在的子树中一共包括多少个结点。
 * 
 * 输入描述:
 * 
 * 输入数据包括多行，每行给出一组测试数据，包括两个整数m，n (1 <= m <= n <= 1000000000)。
 * 
 * 
 * 输出描述:
 * 
 * 对于每一组测试数据，输出一行，该行包含一个整数，给出结点m所在子树中包括的结点的数目。
 * 
 * 示例
 * 
 * 输入
 * 
 * 3 12
 * 
 * 输出
 * 
 * 4
 * 
 * @author Kevin
 * 
 */
public class CBTSubtreeNodeNum {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int m = s.nextInt();
		int n = s.nextInt();
		s.close();
		System.out.print(getSubtreeNum(m, n));
	}

	private static int getSubtreeNum(int m, int n) {
		if (m > n) {
			return 0;
		}

		int nodeNum = 0;
		int cur = m;
		while (cur <= n) {
			nodeNum++;
			int left = 2 * cur;
			int right = 2 * cur + 1;

			int leftHeight = getHeight(left, n);
			int rightHeight = getHeight(right, n);

			if (leftHeight == rightHeight) {// 左树是满的
				nodeNum += (1 << leftHeight) - 1;
				cur = right;
			} else {// 右树是满的
				nodeNum += (1 << rightHeight) - 1;
				cur = left;
			}
		}
		return nodeNum;
	}

	private static int getHeight(int x, int n) {
		int height = 0;
		while (x <= n) {
			height++;
			x *= 2;
		}
		return height;
	}
}
