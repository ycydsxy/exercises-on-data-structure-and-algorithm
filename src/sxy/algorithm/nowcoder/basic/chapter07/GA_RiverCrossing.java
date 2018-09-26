package sxy.algorithm.nowcoder.basic.chapter07;

/**
 * 过河问题
 * 
 * 有n个人想过河，每个人过河都有自己的过河时间an，但只有一只小船，最多只能装2个人，每一次过河，过河时间为用时最多的那人过河时间，如果还有人没有过河，
 * 过去的人需要有一人把船送回来。问n人过河最少要多少时间。
 * 
 * 思路：
 * 
 * a) n=1时，用时t1
 * 
 * b) n=2时，用时t2
 * 
 * c) n=3时，用时t3+t1+t2
 * 
 * d) n=4时，贪心策略是将3、4最快运过去，然后问题退化为n=2。(如何将3、4最快运过去则要比较t2+t1+t4+t2和t4+t1+t3+t1)【注：
 * 这里考虑的时候还有一种情况是最快将4运过去，然后退化到n=3，但这种情形已经被包含】
 * 
 * 后面的以此类推即可.
 * 
 * @author Kevin
 * 
 */
public class GA_RiverCrossing {

	public static int getMinCrossingTime(int[] times) {
		int res = 0;
		int index = times.length - 1;

		while (index > 2) {
			res += Math.min(2 * times[1] + times[0] + times[index], 2
					* times[0] + times[index - 1] + times[index]);
			index -= 2;
		}

		switch (index) {
		case 0:
			res += times[0];
			break;
		case 1:
			res += times[1];
			break;
		case 2:
			res += times[0] + times[1] + times[2];
			break;
		default:
			throw new IllegalStateException();
		}
		return res;
	}

	public static void main(String[] args) {
		int[] times = { 1, 2, 5, 10 };
		System.out.println(getMinCrossingTime(times));
	}
}
