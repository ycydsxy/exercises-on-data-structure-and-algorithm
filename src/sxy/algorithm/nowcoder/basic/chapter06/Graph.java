package sxy.algorithm.nowcoder.basic.chapter06;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ZuoChengyun
 * 
 */
public class Graph {
	public HashMap<Integer, Node> nodes;
	public HashSet<Edge> edges;

	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
