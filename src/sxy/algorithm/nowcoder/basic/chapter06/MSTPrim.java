package sxy.algorithm.nowcoder.basic.chapter06;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树
 * 
 * 条件：无向图
 * 
 * 思路：以节点为单位考虑。加入一个节点后，
 * 
 * @author Kevin
 * 
 */
public class MSTPrim {

	public static Set<Edge> primMST(Graph graph) {
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(
				new Comparator<Edge>() {
					@Override
					public int compare(Edge o1, Edge o2) {
						return o1.weight - o2.weight;
					}
				});

		Set<Node> set = new HashSet<>();// 表示节点之间的连接情况，逐步往外扩
		Set<Edge> result = new HashSet<>();

		for (Node node : graph.nodes.values()) {// 有多个森林有用，假如只有一个的话，for循环进入后一次就能把最小生成树搞定
			if (!set.contains(node)) {// fromNode未被收入
				set.add(node);
				for (Edge edge : node.edges) {// 所有的边加入小根堆
					priorityQueue.add(edge);
				}
				while (!priorityQueue.isEmpty()) {// 拿权值最小的边，对出节点们重复上述过程
					Edge edge = priorityQueue.poll();
					Node toNode = edge.to;
					if (!set.contains(toNode)) {// toNode未被收入，此时这条最小边是有效的
						set.add(toNode);
						result.add(edge);
						for (Edge nextEdge : toNode.edges) {
							priorityQueue.add(nextEdge);
						}
					}
				}
			}
		}
		return result;
	}

}
