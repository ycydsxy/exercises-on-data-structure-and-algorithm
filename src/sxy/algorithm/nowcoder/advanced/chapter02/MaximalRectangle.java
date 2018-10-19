package sxy.algorithm.nowcoder.advanced.chapter02;

import java.util.Stack;

/**
 * 最大子矩阵的大小
 * 
 * 给定一个整型矩阵map，其中的值只有0和1两种，求其中全是1的所有矩形区 域中，最大的矩形区域为1的数量。
 * 
 * 例如：
 * 
 * 1 1 1 0
 * 
 * 其中，最大的矩形区域有3个1，所以返回3。
 * 
 * 再如：
 * 
 * 1 0 1 1
 * 
 * 1 1 1 1
 * 
 * 1 1 1 0
 * 
 * 其中，最大的矩形区域有6个1，所以返回6。
 * 
 * 思路：
 * 
 * 1) M*N的矩阵的子矩阵的规模是O(M^2*N^2)，全部遍历性能太差;
 * 
 * 2) 按行来分解问题，分解为以第i行为底的长方形的最大面积，遍历矩阵中的行，可得总的最大面积。
 * 
 * @author Kevin
 * 
 */
public class MaximalRectangle {

	// 利用直方图数组中的最大长方形面积算法。O(M*N)
	public static int maxRecSize(int[][] map) {
		if (map == null || map.length == 0 || map[0].length == 0) {
			return 0;
		}
		int maxArea = 0;
		int[] height = new int[map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				height[j] = map[i][j] == 0 ? 0 : height[j] + 1;// 以该行为底的直方图数组
			}
			maxArea = Math.max(maxRecFromBottom(height), maxArea);
		}

		return maxArea;
	}

	// 算法原型：求直方图数组中的最大长方形面积。height是直方图数组，没有负数，可能有重复值。O(N)
	public static int maxRecFromBottom(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int maxArea = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {// 注意小于等于，小于时肯定正确结算，等于时弹出当前这个等于位置可能算错，但是最后那个肯定会算对
				int j = stack.pop();
				int k = stack.isEmpty() ? -1 : stack.peek();
				int curArea = (i - k - 1) * height[j];
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int j = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek();
			int curArea = (height.length - k - 1) * height[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}

	public static void main(String[] args) {
		int[][] map = { { 1, 0, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 0 }, };
		System.out.println(maxRecSize(map));
	}

}
