package sxy.algorithm.nowcoder.basic.chapter05;

/**
 * 岛问题
 * 
 * 给一个二维整数矩阵（只含0和1）m，求其中有多少个岛。m中相连的1属于同一个岛，其中相连是指横或纵方向上能够连通，不含斜方向。
 * 
 * @author Kevin
 * 
 */
public class Islands {

	public static int countIslands(int[][] m) {
		if (m == null || m[0] == null) {
			return 0;
		}
		int N = m.length;
		int M = m[0].length;
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (m[i][j] == 1) { // 该点没有被标记过，可以计数
					res++;
					infect(m, i, j, N, M);// 将与之相连的点都标记为已统计
				}
			}
		}
		return res;
	}

	// 递归函数含义：把与(i,j)相连的所有点都标记上
	private static void infect(int[][] m, int i, int j, int N, int M) {
		if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {
			// 如果越界则返回
			// 如果已经被标记过则返回
			return;
		}
		m[i][j] = 2;// 将该节点标记
		infect(m, i + 1, j, N, M);
		infect(m, i - 1, j, N, M);
		infect(m, i, j + 1, N, M);
		infect(m, i, j - 1, N, M);
	}

	public static void main(String[] args) {
		int[][] m1 = { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 1, 0, 1, 1, 1, 0 }, 
				{ 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				{ 0, 1, 1, 0, 1, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 1, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				};
		System.out.println(countIslands(m1));

		int[][] m2 = { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 1, 1, 1, 1, 1, 0 }, 
				{ 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				{ 0, 1, 1, 0, 0, 0, 1, 1, 0 }, 
				{ 0, 0, 0, 0, 0, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 1, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				};
		System.out.println(countIslands(m2));

	}

}
