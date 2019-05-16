package sxy.algorithm.nowcoder.advanced.chapter05;

import java.util.HashMap;

/**
 * 设计一个哈希表结构，使得其具有传统哈希表的put、get功能外，还有一个setAll功能，
 * 其可以传入一个value使得所有Entry的value都变为这个值.要求所有操作的时间复杂度为O(1).
 * 
 * 思路：setAll时候不能一个一个地put，时间复杂度会变为O(N).可以通过盖时间戳的方式来做.
 * 
 * @author Kevin
 * 
 */
public class SetAllHashMap<K, V> {

	class Node {
		V value;
		int time;

		public Node(V value, int time) {
			super();
			this.value = value;
			this.time = time;
		}

	}

	private int currentTime;
	private HashMap<K, Node> map;
	private Node allNode;

	public SetAllHashMap() {
		this.currentTime = 0;
		this.map = new HashMap<>();
		this.allNode = new Node(null, -1);
	}

	public void setAll(V arg1) {
		this.allNode.value = arg1;
		this.allNode.time = currentTime++;
	}

	public V get(Object arg0) {
		Node node = map.get(arg0);
		if (node == null) {
			return null;
		}

		if (node.time < allNode.time) {
			node = allNode;
		}
		return node.value;
	}

	public V put(K arg0, V arg1) {
		Node node = map.put(arg0, new Node(arg1, currentTime++));// 无重复返回null，重复返回上一个值
		if (node == null) {
			return null;
		}

		if (node.time < allNode.time) {
			node = allNode;
		}
		return node.value;
	}

	public V remove(Object arg0) {
		Node node = map.remove(arg0);
		if (node == null) {
			return null;
		}

		if (node.time < allNode.time) {
			node = allNode;
		}
		return node.value;
	}

	public static void main(String[] args) {
		SetAllHashMap<Integer, Integer> map = new SetAllHashMap<>();
		System.out.println(map.put(5, 5));// null
		System.out.println(map.put(3, 3));// null
		System.out.println(map.put(5, 6));// 5
		System.out.println(map.put(7, 7));// null
		System.out.println(map.get(5));// 6
		System.out.println(map.get(4));// null
		System.out.println(map.remove(4));// null
		System.out.println(map.remove(3));// 3
		map.setAll(1);
		System.out.println(map.put(3, 3));// null
		System.out.println(map.put(5, 6));// 1
		System.out.println(map.get(7));// 1
		System.out.println(map.get(4));// null
		System.out.println(map.remove(3));// 3
	}
}
