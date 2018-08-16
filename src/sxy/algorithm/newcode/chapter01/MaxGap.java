package sxy.algorithm.newcode.chapter01;

public class MaxGap {

	public static final int MAX_SIZE = 15;
	public static final int MAX_NUMBER = 100;

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			int[] arr = getRandomArray();
			print(arr);
			print(getMaxGap(arr));
			print("");
		}

	}

	private static int getMaxGap(int[] arr) {
		if(arr==null || arr.length<=1){
			return 0;
		}
		
		//找最小最大值
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			min=arr[i]<min?arr[i]:min;
			max=arr[i]>max?arr[i]:max;
		}

		//初始化桶（保证有一个空桶）
		int bucketNum=arr.length+1;
		boolean[] hasValues=new boolean[bucketNum];
		int[] minValues = new int[bucketNum];
		int[] maxValues = new int[bucketNum];
		
		
		
		return 0;
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
