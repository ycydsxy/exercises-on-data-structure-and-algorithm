package sxy.algorithm.nowcoder.basic.chapter06;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * Dijkstra算法(最短路径算法)
 * 
 * 条件：有向图，无负权重
 * 
 * 思路：从head开始，依次求最短路径连接的节点的子节点。
 * 
 * @author Kevin
 * 
 */
public class Dijkstra {

	public static HashMap<Node, Integer> dijkstra1(Node head) {
		HashMap<Node, Integer> distanceMap = new HashMap<>();
		distanceMap.put(head, 0);// head到head的距离是0
		HashSet<Node> selectedNodes = new HashSet<>();// 需要标记节点的距离是否已经最终确定

		Node minNode = getMinDistanceAndUnselectedNode(distanceMap,
				selectedNodes);
		while (minNode != null) {
			int distance = distanceMap.get(minNode);
			for (Edge edge : minNode.edges) { // 对每一个子节点加上边的权值
				Node toNode = edge.to;
				if (!distanceMap.containsKey(toNode)) {// 没有就往里面加
					distanceMap.put(toNode, distance + edge.weight);
				}

				// 有的话就两者比较取其短
				distanceMap.put(
						toNode,
						Math.min(distanceMap.get(toNode), distance
								+ edge.weight));

			}
			selectedNodes.add(minNode); // 标记该节点已经计算完毕
			minNode = getMinDistanceAndUnselectedNode(distanceMap,
					selectedNodes);
		}
		return distanceMap;
	}

	// 获取目前没有被标记的节点中距离最短的节点，需要每次遍历，有优化的空间
	private static Node getMinDistanceAndUnselectedNode(
			HashMap<Node, Integer> distanceMap, HashSet<Node> touchedNodes) {
		Node minNode = null;
		int minDistance = Integer.MAX_VALUE;
		for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
			Node node = entry.getKey();
			int distance = entry.getValue();
			if (!touchedNodes.contains(node) && distance < minDistance) {
				minNode = node;
				minDistance = distance;
			}
		}
		return minNode;
	}

	public static class NodeRecord {
		public Node node;
		public int distance;

		public NodeRecord(Node node, int distance) {
			this.node = node;
			this.distance = distance;
		}
	}

	public static class NodeHeap {
		private Node[] nodes;
		private HashMap<Node, Integer> heapIndexMap;
		private HashMap<Node, Integer> distanceMap;
		private int size;

		public NodeHeap(int size) {
			nodes = new Node[size];
			heapIndexMap = new HashMap<>();
			distanceMap = new HashMap<>();
			this.size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public void addOrUpdateOrIgnore(Node node, int distance) {
			if (inHeap(node)) {
				distanceMap
						.put(node, Math.min(distanceMap.get(node), distance));
				insertHeapify(node, heapIndexMap.get(node));
			}
			if (!isEntered(node)) {
				nodes[size] = node;
				heapIndexMap.put(node, size);
				distanceMap.put(node, distance);
				insertHeapify(node, size++);
			}
		}

		public NodeRecord pop() {
			NodeRecord nodeRecord = new NodeRecord(nodes[0],
					distanceMap.get(nodes[0]));
			swap(0, size - 1);
			heapIndexMap.put(nodes[size - 1], -1);
			distanceMap.remove(nodes[size - 1]);
			nodes[size - 1] = null;
			heapify(0, --size);
			return nodeRecord;
		}

		private void insertHeapify(Node node, int index) {
			while (distanceMap.get(nodes[index]) < distanceMap
					.get(nodes[(index - 1) / 2])) {
				swap(index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		private void heapify(int index, int size) {
			int left = index * 2 + 1;
			while (left < size) {
				int smallest = left + 1 < size
						&& distanceMap.get(nodes[left + 1]) < distanceMap
								.get(nodes[left]) ? left + 1 : left;
				smallest = distanceMap.get(nodes[smallest]) < distanceMap
						.get(nodes[index]) ? smallest : index;
				if (smallest == index) {
					break;
				}
				swap(smallest, index);
				index = smallest;
				left = index * 2 + 1;
			}
		}

		private boolean isEntered(Node node) {
			return heapIndexMap.containsKey(node);
		}

		private boolean inHeap(Node node) {
			return isEntered(node) && heapIndexMap.get(node) != -1;
		}

		private void swap(int index1, int index2) {
			heapIndexMap.put(nodes[index1], index2);
			heapIndexMap.put(nodes[index2], index1);
			Node tmp = nodes[index1];
			nodes[index1] = nodes[index2];
			nodes[index2] = tmp;
		}
	}

	public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
		NodeHeap nodeHeap = new NodeHeap(size);
		nodeHeap.addOrUpdateOrIgnore(head, 0);
		HashMap<Node, Integer> result = new HashMap<>();
		while (!nodeHeap.isEmpty()) {
			NodeRecord record = nodeHeap.pop();
			Node cur = record.node;
			int distance = record.distance;
			for (Edge edge : cur.edges) {
				nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
			}
			result.put(cur, distance);
		}
		return result;
	}

}
