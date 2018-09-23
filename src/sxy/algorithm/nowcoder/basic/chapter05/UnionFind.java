package sxy.algorithm.nowcoder.basic.chapter05;

import java.util.HashMap;
import java.util.List;

/**
 * 并查集结构
 * 
 * 在该结构中有如下两个功能：
 * 
 * a) makeSets(List<Node> nodes)： 将nodes初始化为并查集
 * 
 * b) isSameSet(Node a, Node b)：看a和b是否连通
 * 
 * c) union(Node a, Node b)：将a和b连通
 * 
 * @author ZuoChengyun
 * 
 */
public class UnionFind {

	public static class Node {
		// whatever you like
	}

	public static class UnionFindSet {
		public HashMap<Node, Node> fatherMap;
		public HashMap<Node, Integer> sizeMap;

		public UnionFindSet() {
			fatherMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
		}

		public void makeSets(List<Node> nodes) {
			fatherMap.clear();
			sizeMap.clear();
			for (Node node : nodes) {
				fatherMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		private Node findHead(Node node) {
			Node father = fatherMap.get(node);
			if (father != node) {
				father = findHead(father);// 最多挂两级，故再找一次能到顶
			}
			fatherMap.put(node, father);
			return father;
		}

		public boolean isSameSet(Node a, Node b) {
			return findHead(a) == findHead(b);
		}

		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aHead = findHead(a);
			Node bHead = findHead(b);
			if (aHead != bHead) { // 源不同才需要合并
				int aSetSize = sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				if (aSetSize <= bSetSize) { // 小的往大的身上挂
					fatherMap.put(aHead, bHead);
					sizeMap.put(bHead, aSetSize + bSetSize);
				} else {
					fatherMap.put(bHead, aHead);
					sizeMap.put(aHead, aSetSize + bSetSize);
				}
			}
		}

	}

	public static void main(String[] args) {

	}

}
