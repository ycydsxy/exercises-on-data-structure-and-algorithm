package sxy.algorithm.nowcoder.advanced.chapter05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 出现次数问题
 * 
 * 一个整型数组中，若一个数字出现次数大于一半，打印这个数，若没有则不打印.要求时间复杂度为O(N)，额外空间复杂度O(1).
 * 
 * 思路：要么有一个，要么没有。故每次去掉两个不同的数，到最后留下的数验一下是不是出现次数大于一半，是的话就是答案.（如何用coding实现这个事是难点）
 * 
 * 拓展：一个整型数组中，打印出现次数大于N/K次的所有数，要求时间复杂度为O(N*K)，额外空间复杂度为O(K).
 * 
 * 思路：最多有K-1个.仿照原问题，每次干掉K个不同的数.
 * 
 * @author Kevin
 *  */

public class FindKMajority {

	// 时间复杂度O(N)，额外空间复杂度O(1)
	public static Integer getHalfMajor1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return null;
		}

		int cand = 0;// 候选人
		int HP = 0;// 血量，为0代表无候选人
		for (int i = 0; i < arr.length; i++) {
			if (HP == 0) {
				cand = arr[i];
				HP++;
			} else if (arr[i] == cand) {
				HP++;
			} else {
				HP--;// 干掉当前数和候选数
			}
		}

		if (HP == 0) {// 没有这个数
			return null;
		}
		HP = 0;
		for (int i = 0; i < arr.length; i++) {// 重新验一遍
			if (arr[i] == cand) {
				HP++;
			}
		}

		if (HP > arr.length / 2) {// 出现次数大于N/2
			return cand;
		} else {// 不是
			return null;
		}
	}

	// 时间复杂度O(N)，额外空间复杂度O(N)
	public static Integer getHalfMajor2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return null;
		}

		HashMap<Integer, Integer> map = new HashMap<>();
		int max = 0;
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				int count = map.get(arr[i]) + 1;
				max = Math.max(max, count);
				res = arr[i];
				map.put(arr[i], count);
			} else {
				map.put(arr[i], 1);
			}
		}

		if (max > arr.length / 2) {
			return res;
		} else {
			return null;
		}
	}

	// 时间复杂度O(N*K)，额外空间复杂度O(K)
	public static List<Integer> getKMajor1(int[] arr, int k) {
		if (arr == null || arr.length < 2 || k < 2) {
			return null;
		}

		HashMap<Integer, Integer> cands = new HashMap<>();// (cand,HP)表，此处这个表的大小是k-1
		for (int i = 0; i < arr.length; i++) {
			if (cands.containsKey(arr[i])) {
				cands.put(arr[i], cands.get(arr[i]) + 1);
			} else {
				if (cands.size() == k - 1) {// 表满了
					Iterator<Map.Entry<Integer, Integer>> iterator = cands
							.entrySet().iterator();// 这里因为要在遍历过程中删元素，不能用for循环，否则会报CocurrentModificationException
					while (iterator.hasNext()) {
						Entry<Integer, Integer> entry = iterator.next();
						if (entry.getValue() == 1) {// 减了就为0，干掉
							iterator.remove();
						} else {// 掉一点血
							entry.setValue(entry.getValue() - 1);
						}
					}
				} else {
					cands.put(arr[i], 1);
				}
			}
		}

		List<Integer> res = new ArrayList<>();
		for (Entry<Integer, Integer> entry : cands.entrySet()) {
			if (entry.getValue() > arr.length / k) {
				res.add(entry.getKey());
			}
		}

		return res;
	}

	public static void main(String[] args) {
		int testTime = 50000;
		int maxSize = 50;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			Integer res = getHalfMajor1(arr);
			Integer comp = getHalfMajor2(arr);
			if (res != comp) {
				succeed = false;
				printArray(arr);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
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
