package sxy.algorithm.nowcoder.basic.chapter03;

/**
 * 转圈打印矩阵 给定一个整型矩阵matrix，请按照转圈的方式打印它。额外空间复杂度为O(1)。
 * 
 * 解法：有整体思维，其实就是打印框
 * 
 * @author Kevin
 * 
 */
public class PrintMatrixInSpiralOrder {
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		printMatrix(matrix);
		printMatrixInSpiralOrder(matrix);

		System.out.println();
		int[][] matrix2 = { { 1, 2, 3, 4 } };
		printMatrix(matrix2);
		printMatrixInSpiralOrder(matrix2);

	}

	public static void printMatrixInSpiralOrder(int[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) {
			return;
		}
		int rMax = m.length - 1;
		int cMax = m[0].length - 1;
		for (int r1 = 0, c1 = 0, r2 = rMax, c2 = cMax; r1 <= r2 && c1 <= c2; r1++, c1++, r2--, c2--) {
			printBorder(m, r1, c1, r2, c2);
		}
	}

	private static void printBorder(int[][] m, int r1, int c1, int r2, int c2) {
		if (r1 == r2) {
			for (int i = c1; i <= c2; i++) {
				System.out.print(m[r1][i] + " ");
			}
		} else if (c1 == c2) {
			for (int i = r1; i <= r2; i++) {
				System.out.print(m[i][c2] + " ");
			}
		} else {
			for (int i = c1; i < c2; i++) {
				System.out.print(m[r1][i] + " ");
			}
			for (int i = r1; i < r2; i++) {
				System.out.print(m[i][c2] + " ");
			}

			for (int i = c2; i > c1; i--) {
				System.out.print(m[r2][i] + " ");
			}

			for (int i = r2; i > r1; i--) {
				System.out.print(m[i][c1] + " ");
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
