package sxy.algorithm.nowcoder.chapter03;

/**
 * 寻找极小值点 给定给一数组，寻找其一个极小值的下标。极小值点下标若为i，则有a[i-1],a[i+1]均大于a[i](若越过边界，则该约束忽略)。
 * 要求时间复杂度小于O (N)。
 * 
 * 解法：。时间复杂度小于O(N)，故不能遍历，可采用二分法。【注：有排他性质的问题，可以考虑使用二分法解决】
 * 
 * @author Kevin
 * 
 */
public class FindExtremePoint {
	public static int findExtremePoint(int[] arr) {
		// 不存在
		if (arr == null || arr.length == 0) {
			return -1; // no exist
		}
		// 两端也算作极值点
		if (arr.length == 1 || arr[0] < arr[1]) {
			return 0;
		}
		if (arr[arr.length - 1] < arr[arr.length - 2]) {
			return arr.length - 1;
		}

		// 极值点若不在两端，其中left比左点低，right比右点低，每一步做完均能保持这个性质
		int left = 1;
		int right = arr.length - 2;
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (arr[mid] > arr[mid - 1]) {
				right = mid - 1;
			} else if (arr[mid] > arr[mid + 1]) {
				left = mid + 1;
			} else {
				return mid;// 满足这个条件的肯定是
			}
		}

		// 循环结束还没找到，此时left=right而指向同一个点，而由于其中left比左点低，right比右点低，故这个点就是
		return left;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 7, 6, 5, 3, 4, 6, 7, 8, 9 };
		printArray(arr);
		int index = findExtremePoint(arr);
		System.out.println("index: " + index + ", value: " + arr[index]);

		int[] arr2 = { 1, 2, 3, 4, 5 };
		printArray(arr2);
		int index2 = findExtremePoint(arr2);
		System.out.println("index: " + index2 + ", value: " + arr2[index2]);

		int[] arr3 = { 5, 4, 3, 2, 1 };
		printArray(arr3);
		int index3 = findExtremePoint(arr3);
		System.out.println("index: " + index3 + ", value: " + arr3[index3]);

		int[] arr4 = { 4, 4, 4, 4, 4 };
		printArray(arr4);
		int index4 = findExtremePoint(arr4);
		System.out.println("index: " + index4 + ", value: " + arr4[index4]);

	}
}
