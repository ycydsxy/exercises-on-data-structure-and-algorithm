package sxy.algorithm.basic.os;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU缓存，使用LinkedHashMap实现
 * 
 * https://www.cnblogs.com/dailidong/p/7571178.html
 * 
 * @author Internet
 * 
 * @param <K>
 * @param <V>
 */
public class LRUCache1<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 2139479237910324L;

	// 缓存大小
	private int cacheSize;

	public LRUCache1(int cacheSize) {
		super(16, 0.75f, true); // 第三个参数true是关键
		this.cacheSize = cacheSize;
	}

	/**
	 * 缓存是否已满
	 */
	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		boolean r = this.size() > cacheSize;
		if (r) {
			System.out.println("清除缓存key：" + eldest.getKey());
		}
		return r;
	}

	// 测试
	public static void main(String[] args) {
		LRUCache1<String, String> cache = new LRUCache1<String, String>(5);
		cache.put("1", "1");
		cache.put("2", "2");
		cache.put("3", "3");
		cache.put("4", "4");
		cache.put("5", "5");

		System.out.println("初始：");
		System.out.println(cache.keySet());
		System.out.println("访问3：");
		cache.get("3");
		System.out.println(cache.keySet());
		System.out.println("访问2：");
		cache.get("2");
		System.out.println(cache.keySet());
		System.out.println("增加数据6,7：");
		cache.put("6", "6");
		cache.put("7", "7");
		System.out.println(cache.keySet());
	}
}
