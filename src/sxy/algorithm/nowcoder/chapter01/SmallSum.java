package sxy.algorithm.nowcoder.chapter01;

/**
 * 小和问题 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组 的小和
 * 
 * 解法：使用归并排序的思想，在合并的时候计算小和
 * 
 * @author Kevin Su
 * 
 */
public class SmallSum {

	public static final int MAX_SIZE = 15;
	public static final int MAX_NUMBER = 100;

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			int[] arr = getRandomArray();
			print(arr);
			print(getSmallSum(arr, 0, arr.length - 1));
			print("");
		}

	}

	public static int getSmallSum(int[] arr, int start, int end) {
		if (start >= end)
			return 0;

		int result = 0;
		int mid = start + ((end - start) >> 1);
		result = getSmallSum(arr, start, mid) + getSmallSum(arr, mid + 1, end)
				+ getSmallSumInMergeProcess(arr, start, mid, end);
		return result;
	}

	private static int getSmallSumInMergeProcess(int[] arr, int start, int mid,
			int end) {
		int result = 0;
		int[] temp = new int[arr.length];

		int k = start;
		int i = start;
		int j = mid + 1;

		while (i <= mid && j <= end) {
			result += arr[i] < arr[j] ? arr[i] * (end - j + 1) : 0;
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

		return result;
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
