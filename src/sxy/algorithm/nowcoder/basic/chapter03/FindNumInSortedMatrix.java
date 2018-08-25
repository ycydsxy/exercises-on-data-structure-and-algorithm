package sxy.algorithm.nowcoder.basic.chapter03;

/**
 * 有序矩阵找数 在一个行列分别排好序的矩阵（m*n)中，找某一数字x，要求时间复杂度为O(m+n)
 * 
 * @author Kevin
 * 
 */
public class FindNumInSortedMatrix {

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
				{ 10, 12, 13, 15, 16, 17, 18 },// 1
				{ 23, 24, 25, 26, 27, 28, 29 },// 2
				{ 44, 45, 46, 47, 48, 49, 50 },// 3
				{ 65, 66, 67, 68, 69, 70, 71 },// 4
				{ 96, 97, 98, 99, 100, 111, 122 },// 5
				{ 166, 176, 186, 187, 190, 195, 200 },// 6
				{ 233, 243, 321, 341, 356, 370, 380 } // 7
		};
		System.out.println(isContains(matrix, 90));
		System.out.println(isContains(matrix, 100));

	}

	private static boolean isContains(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}

		int maxRow = matrix.length - 1;
		int maxColumn = matrix[0].length - 1;

		int r = 0;
		int c = maxColumn;

		while (r <= maxRow && c >= 0) {
			if (matrix[r][c] == k) {
				return true;
			} else if (matrix[r][c] < k) {
				r++;
			} else {
				c--;
			}
		}
		return false;
	}
}
