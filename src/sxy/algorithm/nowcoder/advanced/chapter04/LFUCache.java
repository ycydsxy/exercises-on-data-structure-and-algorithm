package sxy.algorithm.nowcoder.advanced.chapter04;

import java.util.HashMap;

/**
 * LFU（Least Frequently Used）缓存，其get和set方法时间复杂度均是O(1).
 * 
 * LFU和LRU的区别在于，LFU会记录缓存里目前所有内容的操作次数，满时先丢弃最少操作的，操作相同时丢弃最早操作的.
 * 使用双向链表连接的桶以及双向链表连接的内部节点实现， 同样操作次数的节点放在一个桶里
 * ，桶是按顺序排列的但是并非一定是自然数，因为如果桶内没有元素了需要删掉这个桶.最终删除的元素是最左边的桶的头节点.由于要求时间复杂度是O
 * (1)，需要一个快速找到节点的办法，即需要一个Map<K,Node<K,V>>，另外还需要一个快速找到节点所在桶的办法 ，故还需要一个Map.
 * 
 * @author ZuoChengyun & Kevin
 * 
 * @param <K>
 * @param <V>
 */
public class LFUCache<K, V> {

	class Node {
		public K key;
		public V value;
		public Integer times;
		public Node up;
		public Node down;

		public Node(K key, V value, int times) {
			this.key = key;
			this.value = value;
			this.times = times;
		}
	}

	class NodeBucket {
		public Node head;
		public Node tail;
		public NodeBucket last;
		public NodeBucket next;

		// 不接受空桶
		public NodeBucket(Node node) {
			head = node;
			tail = node;
		}

		public void addNodeFromHead(Node newHead) {
			newHead.down = head;
			head.up = newHead;
			head = newHead;
		}

		public boolean isEmpty() {
			return head == null;
		}

		// 需要保证node在桶中
		public void deleteNode(Node node) {
			if (head == tail) {// 只有一个节点
				head = null;
				tail = null;
			} else {// 不只一个节点
				if (node == head) {
					head = node.down;
					head.up = null;
				} else if (node == tail) {
					tail = node.up;
					tail.down = null;
				} else {
					node.up.down = node.down;
					node.down.up = node.up;
				}
			}
			node.up = null;
			node.down = null;
		}
	}

	private int capacity;// 总限制
	private int size;
	private HashMap<K, Node> keyNodeMap;
	private HashMap<Node, NodeBucket> nodeBucketMap;
	private NodeBucket headBucket;// 头桶，即使用频率最低的桶

	public LFUCache(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.keyNodeMap = new HashMap<>();
		this.nodeBucketMap = new HashMap<>();
		headBucket = null;
	}

	public void set(K key, V value) {
		if (keyNodeMap.containsKey(key)) {// 更新
			Node node = keyNodeMap.get(key);
			node.value = value;
			node.times++;
			NodeBucket curNodeBucket = nodeBucketMap.get(node);
			move(node, curNodeBucket); // node从老桶出，进次数+1的桶，有则直接进，无则新建
		} else {// 新建
			if (size == capacity) {// 内存不够用，干掉一个节点
				Node node = headBucket.tail;
				headBucket.deleteNode(node);
				modifyBucket(headBucket);
				keyNodeMap.remove(node.key);
				nodeBucketMap.remove(node);
				size--;
			}

			// 内存够用
			Node node = new Node(key, value, 1);
			if (headBucket == null) {// 没有头桶
				headBucket = new NodeBucket(node);
			} else {
				if (headBucket.head.times.equals(node.times)) {// 头桶操作数为1
					headBucket.addNodeFromHead(node);
				} else {// 头桶操作数不是1
					NodeBucket newList = new NodeBucket(node);
					newList.next = headBucket;
					headBucket.last = newList;
					headBucket = newList;
				}
			}
			keyNodeMap.put(key, node);
			nodeBucketMap.put(node, headBucket);
			size++;
		}
	}

	private void move(Node node, NodeBucket oldNodeBucket) {
		oldNodeBucket.deleteNode(node);
		NodeBucket preList = modifyBucket(oldNodeBucket) ? oldNodeBucket.last
				: oldNodeBucket;
		NodeBucket nextList = oldNodeBucket.next;
		if (nextList == null) {
			NodeBucket newList = new NodeBucket(node);
			if (preList != null) {
				preList.next = newList;
			}
			newList.last = preList;
			if (headBucket == null) {
				headBucket = newList;
			}
			nodeBucketMap.put(node, newList);
		} else {
			if (nextList.head.times.equals(node.times)) {
				nextList.addNodeFromHead(node);
				nodeBucketMap.put(node, nextList);
			} else {
				NodeBucket newList = new NodeBucket(node);
				if (preList != null) {
					preList.next = newList;
				}
				newList.last = preList;
				newList.next = nextList;
				nextList.last = newList;
				if (headBucket == nextList) {
					headBucket = newList;
				}
				nodeBucketMap.put(node, newList);
			}
		}
	}

	// return whether delete this bucket
	//当前桶为空则删掉，若删掉的是头桶则换头桶
	private boolean modifyBucket(NodeBucket nodeBucket) {
		if (nodeBucket.isEmpty()) {
			if (headBucket == nodeBucket) {
				headBucket = nodeBucket.next;
				if (headBucket != null) {
					headBucket.last = null;
				}
			} else {
				nodeBucket.last.next = nodeBucket.next;
				if (nodeBucket.next != null) {
					nodeBucket.next.last = nodeBucket.last;
				}
			}
			return true;
		}
		return false;
	}

	public V get(K key) {
		if (!keyNodeMap.containsKey(key)) {
			return null;
		}
		Node node = keyNodeMap.get(key);
		node.times++;
		NodeBucket curNodeBucket = nodeBucketMap.get(node);
		move(node, curNodeBucket);
		return node.value;
	}

}
