package sxy.algorithm.exams.huawei;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 工厂1和工厂2哪个更便宜
 * 
 * @author Kevin
 *
 */
public class ExamA {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		double price = sc.nextDouble();
		sc.close();

		double money1 = price * n;// 选择1厂商的钱数
		if (n >= 3) {
			money1 *= 0.7;// 打7折
			money1 = getTrueMoney(money1);// 四舍五入
		}
		money1 += 10;// 运费
		if (money1 >= 50) {
			money1 -= 10;
		}

		double money2 = price * n;// 选择2厂商的钱数
		int bargin = ((int) money2 / 10) * 2;
		money2 -= bargin;
		money2 += 6;// 运费
		if (money2 >= 99) {
			money2 -= 6;
		}

		int res = 0;
		money1 = getTrueMoney(money1);
		money2 = getTrueMoney(money2);

		if (money1 > money2) {
			res = 2;
		} else if (money1 < money2) {
			res = 1;
		} else {
			res = 0;
		}

		// System.out.println(String.format("%f %f", money1, money2));
		System.out.println(res);
	}

	private static double getTrueMoney(double money) {
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.valueOf(df.format(money));
	}

}
