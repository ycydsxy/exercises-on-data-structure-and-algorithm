package sxy.algorithm.nowcoder.basic.chapter06;

import java.util.ArrayList;

/**
 * @author ZuoChengyun
 * 
 */
public class Node {
	public int value;
	public int in;
	public int out;
	public ArrayList<Node> nexts;
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
