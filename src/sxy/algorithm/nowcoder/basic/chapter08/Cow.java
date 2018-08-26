package sxy.algorithm.nowcoder.basic.chapter08;

/**
 * 母牛每年生一只母牛，新出生的母牛成长三年后也能每年生一只 母牛，假设不会死。求N年后，母牛的数量。
 * 
 * 解法：类似斐波那契数列问题。第N年的数量就是去年的数量（老牛没死）加上三年前的数量（三年前的牛可以生幼牛）
 * 
 * 进阶：如果每只母牛只能活10年（假设死的那年是生了再死），求N年后，母牛的数量。
 * 
 * 解法：10年之内和不死的情形一样；10年之后的牛的数量=【n-1】年的牛的数量（已有）+【n-3】年牛的数量（新生，而且因为要死的牛也生了）-【n-10】
 * 年的牛的数量（当年要死的牛）
 * 
 * @author Kevin
 * 
 */
public class Cow {

	public static void main(String[] args) {
		System.out.println(cowNumNoDeath(11));
		System.out.println(cowNumDeath(11));
	}

	// 返回过了第n年的牛的数量（不会死）
	private static int cowNumNoDeath(int n) {
		if (n < 0) {
			return 0;
		}

		if (n <= 3) {
			return n;
		}
		return cowNumNoDeath(n - 1) + cowNumNoDeath(n - 3);
	}

	// 返回过了第n年的牛的数量（会死，且是生了再死）
	private static int cowNumDeath(int n) {
		if (n < 0) {
			return 0;
		}

		if (n <= 3) {
			return n;
		}

		if (n < 10) {// 小于10年牛不会死，和不会死的情形一样
			return cowNumDeath(n - 1) + cowNumDeath(n - 3);
		}

		return cowNumDeath(n - 1) + cowNumDeath(n - 3) - cowNumDeath(n - 10);// 大于10年，在不会死的基础上先减去那年死的牛（因为是生了再死，故只减一遍）

	}

}
