package sxy.algorithm.nowcoder.chapter03;

/**
 * Ѱ�Ҽ�Сֵ�� ������һ���飬Ѱ����һ����Сֵ���±ꡣ��Сֵ���±���Ϊi������a[i-1],a[i+1]������a[i](��Խ���߽磬���Լ������)��
 * Ҫ��ʱ�临�Ӷ�С��O (N)��
 * 
 * �ⷨ����ʱ�临�Ӷ�С��O(N)���ʲ��ܱ������ɲ��ö��ַ�����ע�����������ʵ����⣬���Կ���ʹ�ö��ַ������
 * 
 * @author Kevin
 * 
 */
public class FindExtremePoint {
	public static int findExtremePoint(int[] arr) {
		// ������
		if (arr == null || arr.length == 0) {
			return -1; // no exist
		}
		// ����Ҳ������ֵ��
		if (arr.length == 1 || arr[0] < arr[1]) {
			return 0;
		}
		if (arr[arr.length - 1] < arr[arr.length - 2]) {
			return arr.length - 1;
		}

		// ��ֵ�����������ˣ�����left�����ͣ�right���ҵ�ͣ�ÿһ��������ܱ����������
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
				return mid;// ������������Ŀ϶���
			}
		}

		// ѭ��������û�ҵ�����ʱleft=right��ָ��ͬһ���㣬����������left�����ͣ�right���ҵ�ͣ�����������
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
