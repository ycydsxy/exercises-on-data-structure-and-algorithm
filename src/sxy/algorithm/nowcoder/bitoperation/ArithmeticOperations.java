package sxy.algorithm.nowcoder.bitoperation;

/**
 * 使用位运算实现整数的四则运算
 * 
 * @author Kevin
 * 
 */
public class ArithmeticOperations {

	public static int add(int a, int b) {
		if ((a & b) == 0) {
			return a | b;
		}
		return add(a ^ b, (a & b) << 1);
	}

	public static int minus(int a, int b) {
		return add(a, add(~b, 1));
	}

	public static int multiply(int a, int b) {
		int res = 0;
		int size = 0;// b的位数
		for (; size < Integer.SIZE; size++) {
			if (b >> size == 0) {
				break;
			}
		}
		for (int i = 0; i < size; i++) {
			if (((b >> i) & 1) == 1) {
				res = add(res, a << i);
			}
		}
		return res;
	}

	public static int divide(int a, int b) {
		int res = 0;
		int a0 = a;
		int b0 = b;
		
		if (a0 >>> 31 == 1) {
			a = add(~a0, 1);
		}

		if (b0 >>> 31 == 1) {
			b = add(~b0, 1);
		}

		int sizeA = 0;// a的位数
		for (; sizeA < Integer.SIZE; sizeA++) {
			if (a >> sizeA == 0) {
				break;
			}
		}

		int sizeB = 0;// b的位数
		for (; sizeB < Integer.SIZE; sizeB++) {
			if (b >> sizeB == 0) {
				break;
			}
		}

		for (int i = sizeA-sizeB; i >= 0; i--) {
			int cur = b << i;
			if (cur <= a) {
				a = minus(a, cur);
				res |= 1 << i;
			}
		}
		
		if(((a0 >>> 31)^(b0 >>> 31))==0){//同号
			return res;
		}else {
			return add(~res, 1);
		}
	}

	public static void main(String[] args) {
		int testTime = 500000;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray();
			// 检验加
			int res = add(arr[0], arr[1]);
			int comp = arr[0] + arr[1];
			if (res != comp) {
				succeed = false;
				System.out.println(arr[0]);
				System.out.println(arr[1]);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
			// 检验减
			res = minus(arr[0], arr[1]);
			comp = arr[0] - arr[1];
			if (res != comp) {
				succeed = false;
				System.out.println(arr[0]);
				System.out.println(arr[1]);
				System.out.println(res);
				System.out.println(comp);
				break;
			}

			// 检验乘
			res = multiply(arr[0], arr[1]);
			comp = arr[0] * arr[1];
			if (res != comp) {
				succeed = false;
				System.out.println(arr[0]);
				System.out.println(arr[1]);
				System.out.println(res);
				System.out.println(comp);
				break;
			}

			// 检验除
			res = divide(arr[0], arr[1]);
			comp = arr[0]/ arr[1];
			if (res != comp) {
				succeed = false;
				System.out.println(arr[0]);
				System.out.println(arr[1]);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

	// for test
	public static int[] generateRandomArray() {
		int[] arr = new int[2];
		long interval = (long) Integer.MAX_VALUE - (long) Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * interval + Integer.MIN_VALUE);
		}
		return arr;
	}

}
