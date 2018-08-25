package sxy.algorithm.nowcoder.basic.chapter01and02;

/**
 * 逆序对问题 在一个数组中，左边的数如果比右边的数大，则折两个数构成一个逆序对，请打印所有逆序对。
 * 
 * 解法：使用归并排序的思想，在合并的时候打印
 * 
 * @author Kevin Su
 * 
 */
public class ReversePair {

	public static final int MAX_SIZE = 15;
	public static final int MAX_NUMBER = 100;

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			int[] arr = getRandomArray();
			print(arr);
			print(getReversePair(arr, 0, arr.length - 1));
			print("");
		}

	}

	public static String getReversePair(int[] arr, int start, int end) {
		if (start >= end) {
			return "";
		}

		String reversePair = "";
		int mid = start + ((end - start) >> 1);
		reversePair = getReversePair(arr, start, mid)
				+ getReversePair(arr, mid + 1, end)
				+ getReversePairInMergeProcess(arr, start, mid, end);
		return reversePair;
	}

	private static String getReversePairInMergeProcess(int[] arr, int start,
			int mid, int end) {
		int[] temp = new int[arr.length];
		String reversePair = "";

		int k = start;
		int i = start;
		int j = mid + 1;

		while (i <= mid && j <= end) {
			if (arr[i] > arr[j]) {// 怎么改进？？
				for (int p = i; p <= mid; p++) {
					reversePair += arr[p] + "," + arr[j] + " ";
				}
			}
			temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
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

		return reversePair;

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
}
