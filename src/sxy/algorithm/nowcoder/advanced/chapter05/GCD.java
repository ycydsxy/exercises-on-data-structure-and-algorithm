package sxy.algorithm.nowcoder.advanced.chapter05;

/**
 * 求两个数a和b的最大公约数
 * 
 * @author Kevin
 * 
 */
public class GCD {

	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

}
