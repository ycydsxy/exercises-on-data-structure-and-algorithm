package sxy.algorithm.nowcoder.advanced.chapter01;

import java.util.Arrays;

/**
 * 给定一个长度为n的整型数组，找出其中最小的前k个数。
 * 
 * @author Kevin
 * 
 */
public class MinKNums {

	// 使用堆来做，先使用数组前k个元素建一个大顶堆，然后从k+1个元素开始，若小于根节点则替换后调整。时间复杂度O(N*logK)。
	public static int[] getMinKNumsByHeap(int[] arr, int k) {
		if (k < 1 || k > arr.length) {
			return arr;
		}
		int[] kHeap = new int[k];
		for (int i = 0; i != k; i++) {
			heapInsert(kHeap, arr[i], i);
		}
		for (int i = k; i != arr.length; i++) {
			if (arr[i] < kHeap[0]) {
				kHeap[0] = arr[i];
				heapify(kHeap, 0, k);
			}
		}
		return kHeap;
	}

	private static void heapInsert(int[] arr, int value, int index) {
		arr[index] = value;
		while (index != 0) {
			int parent = (index - 1) / 2;
			if (arr[parent] < arr[index]) {
				swap(arr, parent, index);
				index = parent;
			} else {
				break;
			}
		}
	}

	private static void heapify(int[] arr, int index, int heapSize) {
		int left = index * 2 + 1;
		int right = index * 2 + 2;
		int largest = index;
		while (left < heapSize) {
			if (arr[left] > arr[index]) {
				largest = left;
			}
			if (right < heapSize && arr[right] > arr[largest]) {
				largest = right;
			}
			if (largest != index) {
				swap(arr, largest, index);
			} else {
				break;
			}
			index = largest;
			left = index * 2 + 1;
			right = index * 2 + 2;
		}
	}

	// 使用partition过程来做，随机快排只走一边。各种情形综合后，时间复杂度的期望能够达到O(N)，已经很优秀了。
	public static int[] getMinKNumsByPartition(int[] arr, int k) {
		if (k < 1 || k > arr.length) {
			return arr;
		}

		int start = 0;
		int end = arr.length - 1;
		swap(arr, end, (int) (Math.random() * (end - start + 1)) + start);
		int[] range = partition(arr, 0, arr.length - 1, arr[arr.length - 1]);

		while (k <= range[0] || k >= range[1]) {// 没有命中等于区域
			if (k <= range[0]) {
				end = range[0] - 1;
			} else {
				start = range[1] + 1;
			}
			swap(arr, end, (int) (Math.random() * (end - start + 1)) + start);
			range = partition(arr, start, end, arr[end]);
		}

		return Arrays.copyOfRange(arr, 0, k);
	}

	private static int[] partition(int[] arr, int begin, int end, int pivotValue) {
		int small = begin - 1;
		int cur = begin;
		int big = end + 1;
		while (cur != big) {
			if (arr[cur] < pivotValue) {
				swap(arr, ++small, cur++);
			} else if (arr[cur] > pivotValue) {
				swap(arr, cur, --big);
			} else {
				cur++;
			}
		}
		int[] range = new int[2];
		range[0] = small + 1;
		range[1] = big - 1;
		return range;
	}

	// BFPRT算法，和上面partition算法流程的区别就在于选划分值。严格O(N)，非常优秀。
	// BFPRT算法的优点，partition随机可能打偏，导致淘汰的数据量少，而BFPRT每走一次（无论左右）至少能淘汰掉 N*3/10 的数据量
	public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
		if (k < 1 || k > arr.length) {
			return arr;
		}
		int minKth = getMinKthByBFPRT(arr, k);// 用BFPRT找到了第K小的数

		int[] res = new int[k];
		int index = 0;
		for (int i = 0; i != arr.length; i++) {
			if (arr[i] < minKth) {
				res[index++] = arr[i];
			}
		}
		for (; index != res.length; index++) {
			res[index] = minKth;
		}
		return res;
	}

	// BFPRT算法。T(N)=T(N/5)+T(N*7/10)+O(N) --> O(N)
	// 1) 分组，5个数一组。O(1)，只有逻辑对应
	// 2) 小组内排序，跨组不排序。O(N)=O(1)*N/5
	// 3) 每一组的中位数拿出来，组成一个新数组newArr(最后一组若为偶数个数，拿上中位数)，其长度为N/5。O(N)
	// 4) BFPRT(newArr,N/10)求中位数组中的上中位数，该数即为划分值。T(N/5)，递归。
	// 5) partition。O(N)
	// 6) 左右走一边即可。T(N*7/10)，递归。
	private static int getMinKthByBFPRT(int[] arr, int K) {
		int[] copyArr = copyArray(arr);
		return BFPRT(copyArr, 0, copyArr.length - 1, K - 1);
	}

	private static int[] copyArray(int[] arr) {
		int[] res = new int[arr.length];
		for (int i = 0; i != res.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// BFPRT主过程，在arr[begin...end]中求第i小的数(前提：begin<=i<=end)
	private static int BFPRT(int[] arr, int begin, int end, int i) {
		if (begin == end) {
			return arr[begin];
		}
		int pivot = medianOfMedians(arr, begin, end);
		int[] range = partition(arr, begin, end, pivot);
		if (i >= range[0] && i <= range[1]) {
			return arr[i];
		} else if (i < range[0]) {
			return BFPRT(arr, begin, range[0] - 1, i);
		} else {
			return BFPRT(arr, range[1] + 1, end, i);
		}
	}

	// BFPRT算法1到4步骤,最终取到划分值
	private static int medianOfMedians(int[] arr, int begin, int end) {
		int num = end - begin + 1;
		int offset = num % 5 == 0 ? 0 : 1;
		int[] mArr = new int[num / 5 + offset];
		for (int i = 0; i < mArr.length; i++) {
			int beginI = begin + i * 5;
			int endI = beginI + 4;
			mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
		}
		return BFPRT(mArr, 0, mArr.length - 1, mArr.length / 2);
	}

	private static int getMedian(int[] arr, int begin, int end) {
		insertionSort(arr, begin, end);
		int sum = end + begin;
		int mid = (sum / 2) + (sum % 2);
		return arr[mid];
	}

	private static void insertionSort(int[] arr, int begin, int end) {
		for (int i = begin + 1; i != end + 1; i++) {
			for (int j = i; j != begin; j--) {
				if (arr[j - 1] > arr[j]) {
					swap(arr, j - 1, j);
				} else {
					break;
				}
			}
		}
	}

	private static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

	private static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9 };
		// sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
		printArray(getMinKNumsByHeap(arr, 10));
		printArray(getMinKNumsByPartition(arr, 10));
		printArray(getMinKNumsByBFPRT(arr, 10));

	}

}
