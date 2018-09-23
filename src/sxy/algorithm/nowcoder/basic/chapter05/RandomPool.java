package sxy.algorithm.nowcoder.basic.chapter05;

import java.util.HashMap;

/**
 * 设计RandomPool结构   
 * 
 * 设计一种结构，在该结构中有如下三个功能:
 * 
 * a) insert(key)：将某个key加入到该结构，做到不重复加入;
 * 
 * b) delete(key)：将原本在结构中的某个key移除;
 * 
 * c) getRandom()：等概率随机返回结构中的任何一个key。
 * 
 * 要求insert、delete和getRandom方法的时间复杂度都是O(1)。
 * 
 * 
 * @author Kevin
 * 
 */
public class RandomPool<T> {
	private int size;
	private HashMap<Integer, T> map1;
	private HashMap<T, Integer> map2;

	public RandomPool() {
		size = 0;
		map1 = new HashMap<>();
		map2 = new HashMap<>();
	}

	public void insert(T key) {
		if (!map2.containsKey(key)) {// 不能重复加入
			map1.put(size, key);
			map2.put(key, size++);
		}

	}

	public void delete(T key) {
		if (map2.containsKey(key)) {// 有才能删除
			Integer deleteIndex = map2.get(key);
			Integer lastIndex = --size;
			T lastKey = map1.get(lastIndex);
			map1.put(deleteIndex, lastKey);
			map2.put(lastKey, deleteIndex);
			map1.remove(lastIndex);
			map2.remove(lastKey);
		}
	}

	public T getRandom() {
		if (size == 0) {
			return null;
		}

		Integer randomIndex = (int) (Math.random() * size);
		return map1.get(randomIndex);
	}

	public static void main(String[] args) {
		RandomPool<String> pool = new RandomPool<String>();
		pool.insert("zuo");
		pool.insert("cheng");
		pool.insert("yun");
		pool.insert("su");

		pool.delete("cheng");

		HashMap<String, Integer> countMap = new HashMap<>();
		countMap.put("zuo", 0);
		countMap.put("cheng", 0);
		countMap.put("yun", 0);
		countMap.put("su", 0);

		for (int i = 0; i <= 12000; i++) {
			String key = pool.getRandom();
			countMap.put(key, countMap.get(key) + 1);
		}

		System.out.println(countMap);

	}
}
