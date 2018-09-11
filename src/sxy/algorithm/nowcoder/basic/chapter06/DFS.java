package sxy.algorithm.nowcoder.basic.chapter06;

import java.util.HashSet;
import java.util.Stack;

/**
 * 深度优先遍历
 * 
 * @author Kevin
 * 
 */
public class DFS {
	/**
	 * 从某一个节点出发作深度优先遍历
	 * 
	 * @param node
	 */
	public static void depthFirstSearch(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> statck = new Stack<>();
		HashSet<Node> map = new HashSet<>();// 防止节点重复进队列
		statck.add(node);
		map.add(node);
		while (!statck.isEmpty()) {
			Node cur = statck.pop();
			System.out.println(cur.value);
			for (Node next : cur.nexts) {
				if (!map.contains(next)) {
					map.add(next);
					statck.push(next);
				}
			}
		}
	}

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		n1.nexts.add(n2);
		n1.nexts.add(n3);
		n2.nexts.add(n3);
		n2.nexts.add(n4);
		n4.nexts.add(n5);
		depthFirstSearch(n1);
	}
}
