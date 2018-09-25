package sxy.algorithm.nowcoder.basic.chapter07;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * IPO问题
 * 
 * 输入：参数1 参数2 参数3 参数4
 * 
 * a) 正数数组costs，costs[i]表示i号项目的花费
 * 
 * b) 正数数组profits，profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * 
 * c) 正数k，k表示你不能并行、只能串行的最多做k个项目
 * 
 * d) 正数m， m表示你初始的资金
 * 
 * 输出： 你最后获得的最大钱数。
 * 
 * 说明：你每做完一个项目，马上获得的收益，可以支持你去做下 一个 项目。此题为LeetCode502问题。
 * 
 * @author Kevin
 * 
 */
public class GA_IPO {
	private static class Project {
		public int cost;
		public int profit;

		public Project(int cost, int profit) {
			this.cost = cost;
			this.profit = profit;
		}
	}

	public int findMaximizedCapital(int[] costs, int[] profits, int k, int m) {
		List<Project> projects = new LinkedList<>();// 需要频繁删除

		for (int i = 0; i < costs.length; i++) {
			projects.add(new Project(costs[i], profits[i]));
		}

		Collections.sort(projects, new Comparator<Project>() {
			@Override
			public int compare(Project arg0, Project arg1) {
				return arg1.profit - arg0.profit;
			}
		});

		int currentMoney = m;

		for (int i = 0; i < k; i++) {
			if (projects.isEmpty()) {
				break;
			}
			int index = 0;
			Project goodProject = projects.get(index);// 拿利润最大的项目出来看能不能做

			while (goodProject.cost > currentMoney) {// 不够钱做就找下面的，直到能做为止
				if (++index >= projects.size()) {// index越界了，说明遗留的项目一个都做不了
					return currentMoney;
				}
				goodProject = projects.get(index);
			}

			// 如果能做
			currentMoney += goodProject.profit;
			projects.remove(index);// 项目做完了舍弃掉
		}

		return currentMoney;
	}
}
