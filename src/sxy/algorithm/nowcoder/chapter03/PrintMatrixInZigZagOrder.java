package sxy.algorithm.nowcoder.chapter03;

/**
 * 折线打印矩阵 给定一个整型矩阵matrix，请按照折线的方式打印它。额外空间复杂度为O(1)。
 * 
 * 解法：有整体思维，其实就是直线
 * 
 * @author Kevin
 * 
 */
public class PrintMatrixInZigZagOrder {
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		printMatrix(matrix);
		printMatrixInZigZagOrder(matrix);

		System.out.println();
		int[][] matrix2 = { { 1, 2, 3, 4 } };
		printMatrix(matrix2);
		printMatrixInZigZagOrder(matrix2);

	}

	public static void printMatrixInZigZagOrder(int[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) {
			return;
		}
		int rMax = m.length - 1;
		int cMax = m[0].length - 1;
		boolean order = true;

		for (int n = 0; n < rMax + cMax + 1; n++) {
			printLine(m, n, order);
			order = !order;
		}
	}

	private static void printLine(int[][] m, int n, boolean order) {
		int rMax = m.length - 1;
		int cMax = m[0].length - 1;

		int downR = Math.min(n, rMax);
		int downC = Math.max(n - rMax, 0);
		int upR = Math.max(n - cMax, 0);
		int upC = Math.min(n, cMax);
		if (order) {
			int r = downR;
			int c = downC;
			while (r >= upR && c <= upC) {
				System.out.print(m[r--][c++] + " ");
			}
		} else {
			int r = upR;
			int c = upC;
			while (r <= downR && c >= downC) {
				System.out.print(m[r++][c--] + " ");
			}
		}
	}

	public static void printMatrix(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}

}
