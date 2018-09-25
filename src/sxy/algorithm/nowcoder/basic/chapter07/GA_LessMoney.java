package sxy.algorithm.nowcoder.basic.chapter07;

import java.util.PriorityQueue;

/**
 * 切分金条问题
 * 
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。比如 长度为20的 金条，不管切成长度多大的两半，都要花费20个铜 板。一群人想整分整块金
 * 条，怎么分最省铜板？ 输入一个数组，返回分割的最小代价。
 * 
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为 10+20+30=60. 金条要分成10,20,30三个部分。 如果， 先把长
 * 度60的金条分成10和50，花费60 再把长度50的金条分成20和30， 花费50 一共花费110铜板。 但是如果，
 * 先把长度60的金条分成30和30，花费60 再把长度30 金条分成10和20，花费30 一共花费90铜板。
 * 
 * 思路：本题可以转化为这样一个问题，即用n个长度分别为a1、a2、...、
 * an的金条如何组合成一根大金条，其中组合的过程对应原题中分割的过程，故每次组合的时候会产生花费 ，要使得花费总和最小。
 * 
 * 贪心策略如下，即每次组合使得该组合在目前的情形下花费最少
 * ，即每次取其中两个最小的(如ap和aq)进行组合，并将ap+aq放入原数组中(因为这次组合又会产生一个小金条)。类似哈夫曼树。
 * 
 * @author Kevin
 * 
 */
public class GA_LessMoney {

	public static int lessMoney(int[] arr) {
		PriorityQueue<Integer> pQ = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) {
			pQ.add(arr[i]);
		}
		int sum = 0;
		int cur = 0;
		while (pQ.size() > 1) {// pQ最后会变成一个数，即整个金条的长度
			cur = pQ.poll() + pQ.poll();
			sum += cur;
			pQ.add(cur);
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 109, 110 };
		System.out.println(lessMoney(arr));
	}

}
