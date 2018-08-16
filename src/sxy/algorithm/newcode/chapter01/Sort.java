package sxy.algorithm.newcode.chapter01;

import java.util.Arrays;

/**
 * 各种排序算法
 * 
 * @author Kevin Su
 * 
 */
public class Sort {

	public static final int MAX_SIZE = 20;
	public static final int MAX_NUMBER = 1000;

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			int[] arr = getRandomArray();
			print("original:\t", arr);
			print("comparator:\t", comparator(arr));
			print("bubbleSort:\t", bubbleSort(arr));
			print("selectionSort:\t", selectionSort(arr));
			print("insertSort:\t", insertSort(arr));
			print("mergeSort:\t", mergeSort(arr));
			print("quickSort:\t", quickSort(arr));
			print("heapSort:\t", heapSort(arr));
			print("");
		}

	}

	public static int[] comparator(int[] array) {
		int[] arr = array.clone();
		Arrays.sort(arr);
		return arr;
	}

	public static int[] bubbleSort(int[] array) {
		int[] arr = array.clone();
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}
		return arr;
	}

	public static int[] selectionSort(int[] array) {
		int[] arr = array.clone();
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			for (int j = i; j < arr.length; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			swap(arr, minIndex, i);
		}
		return arr;
	}

	public static int[] insertSort(int[] array) {
		int[] arr = array.clone();
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					swap(arr, j, j - 1);
				}
			}
		}
		return arr;
	}

	public static int[] mergeSort(int[] array) {
		int[] arr = array.clone();
		return mergeSort(arr, 0, arr.length - 1);
	}

	public static int[] quickSort(int[] array) {
		int[] arr = array.clone();
		return quickSort(arr, 0, arr.length - 1);
	}

	public static int[] heapSort(int[] array) {
		int[] arr = array.clone();

		// use heap insert
		// for (int i = 0; i < arr.length; i++) {
		// heapInsert(arr, i);
		// }

		// use heapify
		int lastLayer = (int) (Math.log(arr.length) / Math.log(2));
		for (int i = lastLayer - 1; i >= 0; i--) {
			for (int j = (int) (Math.pow(2, i) - 1); j < (int) (Math.pow(2,
					i + 1) - 1); j++) {
				heapify(arr, j, arr.length);
			}
		}

		int size = arr.length;
		while (size > 0) {
			swap(arr, 0, --size);
			heapify(arr, 0, size);
		}
		return arr;
	}

	public static int[] bucketSort(int[] array) {
		int[] arr = array.clone();
		// TODO: 计数排序，从最小到最大依次给桶，放到桶里再遍历
		return arr;
	}

	public static int[] RadixSort(int[] array) {
		int[] arr = array.clone();
		// TODO: 基数排序，依次比较各位上的数
		return arr;
	}

	private static int[] getRandomArray() {
		int size = (int) (Math.random() * MAX_SIZE) + 1;
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = (int) (Math.random() * MAX_NUMBER);
		}
		return arr;
	}

	private static void print(Object o, boolean nextLine) {
		String result = o.toString();
		if (o instanceof int[]) {
			int[] arr = (int[]) o;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]).append(" ");
			}
			result = sb.toString();
		}
		if (nextLine) {
			System.out.println(result);
		} else {
			System.out.print(result);
		}
	}

	private static void print(Object o) {
		print(o, true);
	}

	private static void print(Object... objects) {
		for (Object o : objects) {
			print(o, false);
			print(" ", false);
		}
		System.out.println();
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	private static int[] mergeSort(int[] arr, int start, int end) {
		if (start >= end)
			return arr;

		int mid = start + ((end - start) >> 1);
		mergeSort(arr, start, mid);
		mergeSort(arr, mid + 1, end);
		return merge(arr, start, mid, end);
	}

	private static int[] merge(int[] arr, int start, int mid, int end) {
		int[] temp = new int[arr.length];

		int k = start;
		int i = start;
		int j = mid + 1;

		while (i <= mid && j <= end) {
			temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
		}

		while (i <= mid) {
			temp[k++] = arr[i++];
		}

		while (j <= end) {
			temp[k++] = arr[j++];
		}

		for (int a = start; a <= end; a++) {
			arr[a] = temp[a];
		}

		return arr;
	}

	private static int[] quickSort(int[] arr, int start, int end) {
		if (end <= start) {
			return arr;
		}

		swap(arr, end, (int) (Math.random() * (end - start + 1)) + start);
		int[] result = partition(arr, start, end);
		quickSort(arr, start, result[0]);
		quickSort(arr, result[1], end);
		return arr;
	}

	private static int[] partition(int[] arr, int start, int end) {
		int less = start - 1;
		int more = end + 1;
		int index = start;
		int flag = arr[end];

		while (index < more) {
			if (arr[index] < flag) {
				swap(arr, index++, ++less);
			} else if (arr[index] > flag) {
				swap(arr, index, --more);
			} else {
				index++;
			}
		}
		return new int[] { less, more };
	}

	private static int[] heapInsert(int[] arr, int i) {
		while (arr[i] > arr[(i - 1) / 2]) {
			swap(arr, i, (i - 1) / 2);
			i = (i - 1) / 2;
		}
		return arr;
	}

	private static int[] heapify(int[] arr, int i, int size) {
		int left = 2 * i + 1;
		while (left < size) {
			int right = left + 1;
			int largest = (right < size && arr[right] > arr[left]) ? right
					: left;
			largest = arr[i] > arr[largest] ? i : largest;
			if (largest == i) {
				break;
			}
			swap(arr, i, largest);
			i = largest;
			left = 2 * i + 1;
		}
		return arr;
	}
}
