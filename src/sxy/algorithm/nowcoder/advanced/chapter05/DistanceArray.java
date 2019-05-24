package sxy.algorithm.nowcoder.advanced.chapter05;

/**
 * 城市到首都的距离
 * 
 * 给你一个整数数组arr表示一张有向图，arr[i]表示下标为i的城市指向下标为arr[i]的城市，该数组中只有一个首都（arr[i]=i）
 * 且每个城市都必定有路径到首都，请在额外空间O(1)的限制下，返回距首都距离的统计数组dis，即dis[i]表示距离首都i的城市的个数.
 * 
 * 思路：关键在于额外空间限制为O(1)，所以不能申请额外空间dis，而是将arr该写最后返回.分两步走，第一步将arr变为i下标的城市距离首都的距离数组，
 * 第二步将这个距离数组变成要求的统计数组.这道题目考查coding技巧，即如何在不用额外空间的情况下进行数组的变换，关键是可以利用负数在同一个数
 * 组中表达不同的含义.
 * 
 * @author Kevin
 * 
 */
public class DistanceArray {

	public static int[] getDistanceArray(int[] cityMap) {
		if (cityMap == null || cityMap.length < 1) {
			return cityMap;
		}
		cityToDistances(cityMap);
		distancesToNum(cityMap);

		return cityMap;
	}

	public static void cityToDistances(int[] cityMap) {
		int capital = -1;

		for (int i = 0; i < cityMap.length; i++) {
			if (cityMap[i] < 0) {// 已经变过就略过
				continue;
			}

			if (cityMap[i] == i) {// 是首都也略过
				capital = i;
				continue;
			}

			// 顺着走
			int cur = i;
			int last = i;
			while (cur != cityMap[cur] && cityMap[cur] > 0) {// cur不是首都且cur没到已经统计过的位置
				int next = cityMap[cur];
				cityMap[cur] = last;
				last = cur;
				cur = next;
			}

			// 出来后cur在首都或者在已经统计过的位置
			// 返回走并统计
			int distance = cur == cityMap[cur] ? 0 : -cityMap[cur];
			cur = last;
			while (cur != cityMap[cur]) {// 往回走并计数
				int next = cityMap[cur];
				cityMap[cur] = -(++distance);// 用负数表示距离
				cur = next;
			}

			cityMap[i] = -(++distance);
		}

		cityMap[capital] = 0;

	}

	// 下标循环怼
	public static void distancesToNum(int[] distances) {
		for (int i = 0; i < distances.length; i++) {
			if (distances[i] >= 0) {// 说明该位置已经计算过，为0时就认为是统计过的，0距离有且仅有首都一个，先不用管
				continue;
			}
			int cur = -distances[i];
			distances[i] = 0;// 外循环时到该位置时，记数为0

			while (distances[cur] < 0) {
				int next = -distances[cur];
				distances[cur] = 1;
				cur = next;
			}
			// 出来时cur位置必定是被统计过的
			distances[cur]++;
		}

		distances[0] = 1;
	}

	// test
	public static int[] getDistanceArray2(int[] cityMap) {
		if (cityMap == null || cityMap.length < 1) {
			return cityMap;
		}

		int[] distances = new int[cityMap.length];
		for (int i = 0; i < cityMap.length; i++) {
			int distance = 0;
			int cur = i;
			while (cityMap[cur] != cur) {
				distance++;
				cur = cityMap[cur];
			}
			distances[i] = distance;
		}

		int[] res = new int[cityMap.length];
		for (int i = 0; i < distances.length; i++) {
			res[distances[i]]++;
		}

		return res;
	}

	public static void main(String[] args) {
		int[] cityMap = { 3, 2, 2, 4, 2, 1 };
		printArray(getDistanceArray2(cityMap));
		printArray(getDistanceArray(cityMap));

		cityMap = new int[] { 1, 7, 1, 2, 7, 8, 5, 8, 8 };
		printArray(getDistanceArray2(cityMap));
		printArray(getDistanceArray(cityMap));

	}

	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}
