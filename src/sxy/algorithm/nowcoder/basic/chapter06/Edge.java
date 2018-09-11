package sxy.algorithm.nowcoder.basic.chapter06;

/**
 * @author ZuoChengyun
 *
 */
public class Edge {
	public int weight;
	public Node from;
	public Node to;

	public Edge(int weight, Node from, Node to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}

}
