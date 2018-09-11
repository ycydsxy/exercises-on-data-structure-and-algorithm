package sxy.algorithm.nowcoder.basic.chapter06;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 宽度优先遍历
 * 
 * @author Kevin
 * 
 */
public class BFS {
	/**
	 * 从某一个节点出发作宽度优先遍历
	 * 
	 * @param node
	 */
	public static void broadFirstSearch(Node node) {
		if (node == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		HashSet<Node> map = new HashSet<>();// 防止节点重复进队列
		queue.add(node);
		map.add(node);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
			for (Node next : cur.nexts) {
				if (!map.contains(next)) {
					map.add(next);
					queue.add(next);
				}
			}
		}
	}
}
