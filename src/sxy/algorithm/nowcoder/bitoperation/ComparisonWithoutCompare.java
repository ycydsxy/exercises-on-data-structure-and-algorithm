package sxy.algorithm.nowcoder.bitoperation;

/**
 * 不用比较，找出a和b中的较大的数
 * 
 * @author Kevin
 * 
 */
public class ComparisonWithoutCompare {

	// 基础代码，但可能溢出
	public static int getBigger1(int a, int b) {
		int c = a - b;// a和b符号不同可能溢出
		int x = (c >> 31) & 1;
		int y = x ^ 1;
		return y * a + x * b;
	}

	public static int getBigger2(int a, int b) {
		int c = a - b;
		int sa = (a >> 31) & 1;
		int sb = (b >> 31) & 1;
		int sc = (c >> 31) & 1;
		int ab = sa ^ sb;// a和b符号相同为0，不同为1
		int x = ((ab ^ 1) & sc) | (ab & sa);// 返回b的条件：同号且c负，或异号且a为负
		int y = x ^ 1;
		return y * a + x * b;
	}

	public static void main(String[] args) {
		int a = -16;
		int b = 1;
		System.out.println(getBigger1(a, b));
		System.out.println(getBigger2(a, b));
		a = 2147483647;
		b = -2147480000;
		System.out.println(getBigger1(a, b));
		System.out.println(getBigger2(a, b));
	}
}
