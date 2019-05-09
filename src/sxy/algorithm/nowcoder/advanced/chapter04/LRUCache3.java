package sxy.algorithm.nowcoder.advanced.chapter04;

import java.util.HashMap;
import java.util.Set;

/**
 * LRU缓存，使用Map和双向链表实现
 * 
 * 思路：Map<Key,Node>能够以O(1)复杂度找到相应Key的Node，Node存在双向链表里
 * 
 * @author Kevin
 * 
 */
public class LRUCache3<K, V> {

	private HashMap<K, Node<K, V>> keyNodeMap;
	private NodeDoubleLinkedList<K, V> nodeList;
	private int capacity;// 缓存大小

	public LRUCache3(int capacity) {
		if (capacity < 1) {
			throw new RuntimeException("should be more than 0.");
		}
		this.keyNodeMap = new HashMap<K, Node<K, V>>();
		this.nodeList = new NodeDoubleLinkedList<K, V>();
		this.capacity = capacity;
	}

	//时间复杂度为O(1)
	public V get(K key) {
		if (this.keyNodeMap.containsKey(key)) {
			Node<K, V> res = this.keyNodeMap.get(key);
			this.nodeList.moveNodeToTail(res);
			return res.value;
		}
		return null;
	}
	
	//时间复杂度为O(1)
	public void put(K key, V value) {
		if (this.keyNodeMap.containsKey(key)) {
			Node<K, V> node = this.keyNodeMap.get(key);
			node.value = value;
			this.nodeList.moveNodeToTail(node);
		} else {
			Node<K, V> newNode = new Node<K, V>(key, value);
			this.keyNodeMap.put(key, newNode);
			this.nodeList.addNode(newNode);
			if (this.keyNodeMap.size() == this.capacity + 1) {
				this.removeMostUnusedCache();
			}
		}
	}

	public Set<K> keySet() {
		return this.keyNodeMap.keySet();
	}

	private void removeMostUnusedCache() {
		this.keyNodeMap.remove(this.nodeList.removeHead().key);
	}

	private static class Node<K, V> {
		public K key;
		public V value;
		public Node<K, V> last;
		public Node<K, V> next;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private static class NodeDoubleLinkedList<K, V> {
		private Node<K, V> head;
		private Node<K, V> tail;

		public NodeDoubleLinkedList() {
			this.head = null;
			this.tail = null;
		}

		// 新加Node，加到尾部
		public void addNode(Node<K, V> newNode) {
			if (newNode == null) {
				return;
			}
			if (this.head == null) {
				this.head = newNode;
				this.tail = newNode;
			} else {
				this.tail.next = newNode;
				newNode.last = this.tail;
				this.tail = newNode;
			}
		}

		// 将中间的Node拿出来放在尾部
		public void moveNodeToTail(Node<K, V> node) {
			if (this.tail == node) {
				return;
			}
			if (this.head == node) {
				this.head = node.next;
				this.head.last = null;
			} else {
				node.last.next = node.next;
				node.next.last = node.last;
			}
			node.last = this.tail;
			node.next = null;
			this.tail.next = node;
			this.tail = node;
		}

		// 超了的时候把头部干掉
		public Node<K, V> removeHead() {
			if (this.head == null) {
				return null;
			}
			Node<K, V> res = this.head;
			if (this.head == this.tail) {
				this.head = null;
				this.tail = null;
			} else {
				this.head = res.next;
				res.next = null;
				this.head.last = null;
			}
			return res;
		}

	}

	public static void main(String[] args) {
		LRUCache3<String, String> cache = new LRUCache3<String, String>(5);
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
