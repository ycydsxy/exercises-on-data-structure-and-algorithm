package sxy.algorithm.nowcoder.basic.chapter06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 拓扑排序
 * 
 * 条件：无环有向图，每个节点出现且只出现一次，保证后面的节点没有通向前面节点的路
 * 
 * @author Kevin
 * 
 */
public class TopologySort {

	public static List<Node> sortedTopology(Graph graph) {
		HashMap<Node, Integer> inMap = new HashMap<>();// 入度的Map，方便查和更新节点的入度
		Queue<Node> zeroInQueue = new LinkedList<>();
		for (Node node : graph.nodes.values()) {// 将所有节点加入入度Map中
			inMap.put(node, node.in);
			if (node.in == 0) {// 将所有入度为0的节点加入Queue中
				zeroInQueue.add(node);
			}
		}

		List<Node> result = new ArrayList<>();

		while (!zeroInQueue.isEmpty()) {
			Node cur = zeroInQueue.poll();
			result.add(cur);
			for (Node next : cur.nexts) {
				inMap.put(next, inMap.get(next) - 1);// 所有相邻节点的入度减1
				if (inMap.get(next) == 0) {// 重复将所有入度为0的节点加入Queue中
					zeroInQueue.add(next);
				}
			}
		}
		return result;
	}
}
