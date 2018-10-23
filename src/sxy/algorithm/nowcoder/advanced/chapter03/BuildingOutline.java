package sxy.algorithm.nowcoder.advanced.chapter03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/**
 * 大楼轮廓问题-Lintcode顶级难题
 * 
 * 给定一个N行3列二维数组，每一行表示有一座大楼，一共有N座 大楼。 所有大楼的底部都坐落在X轴上，每一行的三个值
 * (a,b,c)代表每座大楼的从(a,0)点开始，到 (b,0)点结束，高 度为c。 输入的数据可以保证a<b,且a，b，c均为正数。大楼之 间可以有重合。
 * 请输出整体的轮廓线。
 * 
 * 例子：给定一个二维数组 [ [1, 3, 3], [2, 4, 4], [5, 6, 1] ], 输出为轮廓线 [ [1, 2, 3], [2, 4,
 * 4], [5, 6, 1] ]
 * 
 * 解法：将数组变成操作（如[1,3,3]变成[1,3]和[3,-3]两个操作），使用有序表记录高度和高度的次数，并实时监测最大高度，
 * 最大高度发生了变化就出现了轮廓。
 * 
 * @author Kevin
 * 
 */
public class BuildingOutline {

	public static List<int[]> getBuildingOutline(int[][] buildings) {
		List<int[]> res = new ArrayList<>();
		List<Operation> operations = getOperations(buildings);
		if (operations.size() < 2) {
			return res;
		}

		TreeMap<Integer, Integer> map = new TreeMap<>();
		handleOperation(operations.get(0), map);
		int start = operations.get(0).position;
		int maxHeight = map.lastKey();

		for (int i = 1; i < operations.size(); i++) {
			Operation o = operations.get(i);
			handleOperation(o, map);
			if (map.isEmpty()) {
				res.add(new int[] { start, o.position, maxHeight });

				maxHeight = 0;
				start = o.position;
			} else if (maxHeight != map.lastKey()) {// 最大高度改变时候产生轮廓
				res.add(new int[] { start, o.position, maxHeight });

				maxHeight = map.lastKey();
				start = o.position;
			}

		}
		return res;
	}

	private static class Operation {
		int position;
		int increment;

		public Operation(int position, int increment) {
			super();
			this.position = position;
			this.increment = increment;
		}

	}

	private static List<Operation> getOperations(int[][] buildings) {
		List<Operation> list = new ArrayList<>(buildings.length * 2);
		for (int i = 0; i < buildings.length; i++) {
			list.add(new Operation(buildings[i][0], buildings[i][2]));
			list.add(new Operation(buildings[i][1], -buildings[i][2]));
		}
		Collections.sort(list, new Comparator<Operation>() {
			@Override
			public int compare(Operation o1, Operation o2) {
				if (o1.position == o2.position) {
					return o2.increment - o1.increment;// 保证正数在前面即可
				}
				return o1.position - o2.position;
			}
		});
		return list;
	}

	private static void handleOperation(Operation o,
			TreeMap<Integer, Integer> map) {
		if (o.increment > 0) {// 应该往里面放
			Integer key = o.increment;
			Integer value = map.get(key);
			if (value == null) {
				map.put(key, 1);
			} else {
				map.put(key, ++value);
			}
		} else {// 从中移除
			Integer key = -o.increment;
			Integer value = map.get(key);
			if (value == 1) {
				map.remove(key);
			} else {
				map.put(key, --value);
			}
		}
	}

	public static void main(String[] args) {
		int[][] buildings = { { 2, 9, 10 }, { 3, 7, 15 }, { 5, 12, 12 },
				{ 15, 20, 10 }, { 19, 24, 8 } };
		getBuildingOutline(buildings);
	}
}
