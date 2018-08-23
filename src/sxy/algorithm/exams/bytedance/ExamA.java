package sxy.algorithm.exams.bytedance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 岛问题
 * 
 * @author Kevin
 *
 */
public class ExamA {
	public static final int SEARCHED_BEFORE = -1;
	public static int m = 1;
	public static int n = 1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] line1 = sc.nextLine().split(",");
		m = Integer.parseInt(line1[0]);
		n = Integer.parseInt(line1[1]);
		int[][] matrix = new int[m][n];// 观众矩阵
		for (int i = 0; i < m; i++) {
			String[] currentLine = sc.nextLine().split(",");
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(currentLine[j]);
			}
		}
		sc.close();

		List<Integer> resultList = new ArrayList<>();
		
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(matrix[i][j]==1){
					int currentNodeNum=findAdjecentNum(matrix,i,j);
					resultList.add(currentNodeNum);
				}else{
					continue;
				}
			}
		}
		Collections.sort(resultList);
		int listSize=resultList.size();
		System.out.println(listSize+","+resultList.get(listSize-1));

	}

	private static int findAdjecentNum(int[][] matrix, int x, int y) {
		if (!isValidBoundary(x, y)) {
			return 0;
		}
		if (matrix[x][y] == 0 || matrix[x][y] == SEARCHED_BEFORE) {
			return 0;
		}
		int result = 1;
		matrix[x][y] = SEARCHED_BEFORE;
		result+=findAdjecentNum(matrix,x-1,y+1);
		result+=findAdjecentNum(matrix,x-1,y);
		result+=findAdjecentNum(matrix,x-1,y-1);
		result+=findAdjecentNum(matrix,x,y+1);
		result+=findAdjecentNum(matrix,x,y-1);
		result+=findAdjecentNum(matrix,x+1,y+1);
		result+=findAdjecentNum(matrix,x+1,y);
		result+=findAdjecentNum(matrix,x+1,y-1);

		return result;
	}

	private static boolean isValidBoundary(int x, int y) {
		boolean flag = true;
		if (x < 0 || x >= m) {
			flag = false;
		}
		if (y < 0 || y >= n) {
			flag = false;
		}
		return flag;
	}
}
