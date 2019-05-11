package sxy.algorithm.nowcoder.advanced.chapter04;

import java.util.List;

/**
 * 有一家公司，其组织架构可以简化为一个多叉树，父节点是子节点的直接领导，子节点是父节点的直接下属。公司即将举办年会，需要邀请员工参加，
 * 每个员工有一个自己的活跃值，假设邀请了员工A，则A的所有直接下属即使接到了邀请也不会参加，求年会当天所在人员的活跃度最大值是多少？
 * 
 * 思路：树形DP
 * 
 * @author Kevin
 * 
 */
public class MaxHappy {

	class Node {
		public int happy;
		public List<Node> nexts;
	}

	public int getMaxHappy(Node boss) {
		Info res = process(boss);
		return Math.max(res.bulai_maxHappy, res.lai_maxHappy);
	}

	private Info process(Node x) {
		if (x.nexts.isEmpty()) {
			return new Info(0, x.happy);
		}

		int bulai_maxHappy = 0;
		int lai_maxHappy = x.happy;
		for (Node next : x.nexts) {
			Info nextInfo = process(next);
			bulai_maxHappy += Math.max(nextInfo.bulai_maxHappy,
					nextInfo.lai_maxHappy);
			lai_maxHappy += nextInfo.bulai_maxHappy;
		}

		return new Info(bulai_maxHappy, lai_maxHappy);
	}

	class Info {
		public int bulai_maxHappy;
		public int lai_maxHappy;

		public Info(int bulai_maxHappy, int lai_maxHappy) {
			super();
			this.bulai_maxHappy = bulai_maxHappy;
			this.lai_maxHappy = lai_maxHappy;
		}

	}
}
