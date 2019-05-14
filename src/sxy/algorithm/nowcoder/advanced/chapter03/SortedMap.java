package sxy.algorithm.nowcoder.advanced.chapter03;

import java.util.TreeMap;

/**
 * 有序表
 * 
 * key按序组织，增删改查的代价为O(log(N))
 * 
 * 实现有：红黑树，AVL树，SB树，跳表，...
 * 
 * @author Kevin
 * 
 */
public class SortedMap {

	public static void main(String[] args) {
		TreeMap<Integer, Integer> map = new TreeMap<>();// 红黑树
		map.put(1, 1);
		map.put(3, 1);
		map.put(4, 1);
		map.remove(4);
		System.out.println(map.get(1));
		System.out.println(map.firstKey());
		System.out.println(map.lastKey());
		System.out.println(map.floorKey(2));
		System.out.println(map.ceilingKey(2));

	}
}
