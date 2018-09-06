package sxy.algorithm.basic.os;

import java.util.ArrayList;
import java.util.List;

/**
 * LRU缓存，使用单链表实现
 * 
 * @author Kevin
 * 
 * @param <K>
 * @param <V>
 */
public class LRUCache2<K, V> {

	private Node<K, V> head;// 头结点
	private int cacheSize;// 缓存大小
	private int size;// 当前大小

	private class Node<A, B> {
		A key;
		B value;
		Node<A, B> next;

		public Node(A key, B value) {
			super();
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + "]";
		}
	}

	public LRUCache2(int cacheSize) {
		if (cacheSize < 1) {
			throw new IllegalArgumentException("cacheSize<1 !");
		}
		this.cacheSize = cacheSize;
	}

	public void put(K key, V value) {
		if (key == null) {// 默认key不能为null
			throw new IllegalArgumentException("key is null !");
		}
		// 找是否在已在缓存中
		Node<K, V> node = this.findNode(key);
		if (node != null) {// 找到了就更新
			node.value = value;
			this.moveNode(node);
		} else {// 否则在最前面增加，并干掉超过容量的节点
			node = new Node<K, V>(key, value);
			node.next = head;
			head = node;
			size++;
			this.cleanup();
		}

	}

	public V get(K key) {
		Node<K, V> node = this.findNode(key);
		if (node == null) {
			return null;
		}
		this.moveNode(node);
		return node.value;
	}

	public List<K> keySet() {
		List<K> keySet = new ArrayList<>();
		Node<K, V> cur = head;
		while (cur != null) {
			keySet.add(cur.key);
			cur = cur.next;
		}
		return keySet;
	}

	private Node<K, V> findNode(K key) {
		Node<K, V> cur = head;
		while (cur != null && !cur.key.equals(key)) {
			cur = cur.next;
		}
		return cur;
	}

	/**
	 * 将node节点移到链表头部
	 * 
	 * @param node
	 */
	private void moveNode(Node<K, V> node) {
		if (node == null) {
			throw new IllegalStateException();
		}

		if (node == head) {
			return;
		}

		Node<K, V> next = node.next;
		node.next = head;
		head = node;

		Node<K, V> pre = head;
		while (pre.next != head) {
			pre = pre.next;
		}
		pre.next = next;
	}

	private void cleanup() {
		if (this.size > this.cacheSize) {
			System.out.println("cleanup!");
			Node<K, V> cur = head;
			for (int i = 0; i < cacheSize - 1; i++) {
				cur = cur.next;
			}
			cur.next = null;
		}
	}

	// 测试
	public static void main(String[] args) {
		LRUCache2<String, String> cache = new LRUCache2<String, String>(5);
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
