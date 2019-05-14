package sxy.algorithm.nowcoder.advanced.chapter04;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 两个有序数组间相加和的TOP K问题
 * 
 * 给定两个有序数组arr1和arr2，再给定一个整数k，返回来自 arr1和arr2的两个数相加和最大的前k个，两个数必须
 * 分别来自两个数组.要求时间复杂度达到O(klogk).
 * 
 * 思路：使用大根堆来排序，使用哈希表来标记是否进过堆.从两有序数组最后的位置入手，先进堆。然后每次弹出后，都放入弹出位置的左、上的位置。
 * 
 * 
 * @author Kevin
 * 
 */
public class TopKSum {

	public static class HeapNode implements Comparable<HeapNode> {
		public int row;
		public int col;
		public int value;

		public HeapNode(int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}

		@Override
		public int compareTo(HeapNode arg0) {
			return arg0.value - this.value;
		}

	}

	public static int[] topKSum(int[] a1, int[] a2, int topK) {
		if (a1 == null || a2 == null || topK < 1) {
			return null;
		}
		topK = Math.min(topK, a1.length * a2.length);
		PriorityQueue<HeapNode> heap = new PriorityQueue<>();
		heap.add(new HeapNode(a1.length - 1, a2.length - 1, a1[a1.length - 1]
				+ a2[a2.length - 1]));
		HashSet<String> positionSet = new HashSet<String>();
		positionSet.add(String.valueOf(a1.length - 1) + "_"
				+ String.valueOf(a2.length - 1));
		int[] res = new int[topK];
		int resIndex = 0;
		while (resIndex != topK) {
			HeapNode node = heap.poll();
			res[resIndex++] = node.value;
			if (node.row != 0) {
				String upKey = String.valueOf(node.row - 1) + "_"
						+ String.valueOf(node.col);
				if (!positionSet.contains(upKey)) {
					positionSet.add(upKey);
					heap.add(new HeapNode(node.row - 1, node.col,
							a1[node.row - 1] + a2[node.col]));
				}
			}

			if (node.col != 0) {
				String upKey = String.valueOf(node.row) + "_"
						+ String.valueOf(node.col - 1);
				if (!positionSet.contains(upKey)) {
					positionSet.add(upKey);
					heap.add(new HeapNode(node.row, node.col - 1, a1[node.row]
							+ a2[node.col - 1]));
				}
			}

		}
		return res;
	}

	// For test, this method is inefficient but absolutely right
	public static int[] topKSumTest(int[] arr1, int[] arr2, int topK) {
		int[] all = new int[arr1.length * arr2.length];
		int index = 0;
		for (int i = 0; i != arr1.length; i++) {
			for (int j = 0; j != arr2.length; j++) {
				all[index++] = arr1[i] + arr2[j];
			}
		}
		Arrays.sort(all);
		int[] res = new int[Math.min(topK, all.length)];
		index = all.length - 1;
		for (int i = 0; i != res.length; i++) {
			res[i] = all[index--];
		}
		return res;
	}

	public static int[] generateRandomSortArray(int len) {
		int[] res = new int[len];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * 50000) + 1;
		}
		Arrays.sort(res);
		return res;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static boolean isEqual(int[] arr1, int[] arr2) {
		if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i != arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int a1Len = 5000;
		int a2Len = 4000;
		int k = 2000;
		int[] arr1 = generateRandomSortArray(a1Len);
		int[] arr2 = generateRandomSortArray(a2Len);
		long start = System.currentTimeMillis();
		int[] res = topKSum(arr1, arr2, k);
		long end = System.currentTimeMillis();
		System.out.println(end - start + " ms");

		start = System.currentTimeMillis();
		int[] absolutelyRight = topKSumTest(arr1, arr2, k);
		end = System.currentTimeMillis();
		System.out.println(end - start + " ms");

		System.out.println(isEqual(res, absolutelyRight));

	}

}
