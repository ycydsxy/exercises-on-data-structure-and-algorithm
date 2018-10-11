package sxy.job.bytedance.interview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 学校的名次
 * 
 * 共有A、B、C、D、E五所学校，对于他们的排名情况，现已知D学校不可能为第二或者第三名，其他的名次各个学校都有传言，其中A学校中传E学校是第1名，
 * B学校中传它是第2名 * ，C学校中传A学校是最后一名，D学校中传C学校不是第一名，E学校中传D学校是第一名。这些传言中只有真实名次为第一和第二的
 * 两个学校的判断是准确的， 请输出所有可能的学校的名次。
 * 
 * @author Kevin
 * 
 */
public class SchoolRange {

	public static void main(String[] args) {
		Set<int[]> possibleConditions = getPossibleCondtions();
		List<int[]> results = new ArrayList<>();

		for (int[] condition : possibleConditions) {
			boolean[] judgements = new boolean[5];
			judgements[0] = condition[4] == 1;
			judgements[1] = condition[1] == 2;
			judgements[2] = condition[0] == 5;
			judgements[3] = condition[2] != 1;
			judgements[4] = condition[3] == 1;

			boolean flag = true;
			for (int i = 0; i < condition.length; i++) {
				if (condition[i] == 1 || condition[i] == 2) {
					if (!judgements[i]) {
						flag = false;
					}
				} else {
					if (judgements[i]) {
						flag = false;
					}
				}
			}

			if (flag) {
				results.add(condition);
			}
		}
		System.out.println(results.size());
	}

	private static Set<int[]> getPossibleCondtions() {
		int[] item = { 1, 2, 3, 4, 5 };
		Set<int[]> res = getPermutations(0, item);
		return res;
	}

	// 获得从index位置开始的全部排列
	private static Set<int[]> getPermutations(int index, int[] s) {
		Set<int[]> set = new HashSet<>();

		if (index == s.length - 1) {
			set.add(new int[] { s[index] });
			return set;
		}

		Set<int[]> subPermutations = getPermutations(index + 1, s);

		for (int[] item : subPermutations) {
			for (int i = -1; i < item.length; i++) {
				set.add(insertIntoArray(item, s[index], i));
			}
		}

		return set;
	}

	/**
	 * 将x插入到arr的index位后面，并返回一个新的char[]
	 * 
	 * @param arr
	 * @param x
	 * @param index
	 * @return
	 */
	private static int[] insertIntoArray(int[] arr, int x, int index) {
		int[] newArr = new int[arr.length + 1];
		for (int i = 0; i < newArr.length; i++) {
			if (i == index + 1) {
				newArr[i] = x;
			} else if (i <= index) {
				newArr[i] = arr[i];
			} else {
				newArr[i] = arr[i - 1];
			}
		}
		return newArr;
	}

}
