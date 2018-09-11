package sxy.algorithm.nowcoder.basic.chapter06;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树
 * 
 * 条件：无向图
 * 
 * 思路：以边为单位考虑。从权值最小的边开始选，未成环则要，成环则丢弃。因为需要判断是否成环，故使用并查集来辅助。
 * 
 * @author Kevin
 * 
 */
public class MSTKruskal {

	public static Set<Edge> kruskalMST(Graph graph) {
		UnionFind unionFind = new UnionFind();
		unionFind.makeSets(graph.nodes.values());// 所有节点加入并查集
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(// 小根堆，用于找最小边
				new Comparator<Edge>() {
					@Override
					public int compare(Edge o1, Edge o2) {
						return o1.weight - o2.weight;
					}
				});
		for (Edge edge : graph.edges) {
			priorityQueue.add(edge);
		}

		Set<Edge> result = new HashSet<>();
		while (!priorityQueue.isEmpty()) {
			Edge edge = priorityQueue.poll();
			if (!unionFind.isSameSet(edge.from, edge.to)) {// 两边的节点没有相连，则加入该边，且连接两边的节点
				result.add(edge);
				unionFind.union(edge.from, edge.to);
			}
		}
		return result;
	}

	// 并查集结构
	public static class UnionFind {
		private HashMap<Node, Node> fatherMap;
		private HashMap<Node, Integer> rankMap;

		public UnionFind() {
			fatherMap = new HashMap<Node, Node>();
			rankMap = new HashMap<Node, Integer>();
		}

		private Node findFather(Node n) {
			Node father = fatherMap.get(n);
			if (father != n) {
				father = findFather(father);
			}
			fatherMap.put(n, father);
			return father;
		}

		public void makeSets(Collection<Node> nodes) {
			fatherMap.clear();
			rankMap.clear();
			for (Node node : nodes) {
				fatherMap.put(node, node);
				rankMap.put(node, 1);
			}
		}

		public boolean isSameSet(Node a, Node b) {
			return findFather(a) == findFather(b);
		}

		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aFather = findFather(a);
			Node bFather = findFather(b);
			if (aFather != bFather) {
				int aFrank = rankMap.get(aFather);
				int bFrank = rankMap.get(bFather);
				if (aFrank <= bFrank) {
					fatherMap.put(aFather, bFather);
					rankMap.put(bFather, aFrank + bFrank);
				} else {
					fatherMap.put(bFather, aFather);
					rankMap.put(aFather, aFrank + bFrank);
				}
			}
		}
	}
}
