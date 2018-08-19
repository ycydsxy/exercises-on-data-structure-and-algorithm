package sxy.algorithm.nowcoder.chapter03.exercise;

import java.util.*;

/**
 * ���ӣ�https://www.nowcoder.com/questionTerminal/6235a76b1e404f748f7c820583125c50
 * ?orderByHotValue=1&mutiTagIds=582&page=1&onlyReference=true ��Դ��ţ����
 * 
 * �мҶ���������ֻ����è�͹��������������������������������������ʽ����һ��Ϊֱ���������ж�������������������ģ��ڶ���Ϊѡ�������Ķ������ͣ�è�򹷣���
 * ���������ֶ�������������������ġ� ����һ����������int[][2]
 * ope(C++��Ϊvector<vector<int>>)���������¼�������һ��Ԫ��Ϊ1 ���������
 * ����������������ڶ���Ԫ��Ϊ����ı�ţ�������������������è ������һ��Ԫ��Ϊ2�������������������ڶ���Ԫ����Ϊ0�����ȡ��һ��������ʽ����Ϊ1 ��
 * ��ָ������������Ϊ-1��ָ������è���밴˳�򷵻����������С������ֲ��Ϸ��Ĳ�������û�п��Է�������Ҫ��Ķ������������������ԡ�
 * 
 * ���������� [[1,1],[1,-1],[2,0],[2,-1]]
 * 
 * ���أ�[1,-1]
 * 
 * @author Kevin
 * 
 */
public class Exercise01 {

	Queue<Info> dogQueue = new LinkedList<Info>();
	Queue<Info> catQueue = new LinkedList<Info>();
	public static int timestamp = 0;

	class Info {
		public int value;
		public int timestamp;

		public Info(int value, int timestamp) {
			this.value = value;
			this.timestamp = timestamp;
		}
	}

	private void addAnimal(int value) {
		if (value == 0) {
			return;
		}

		Info animal = new Info(value, ++timestamp);
		if (value > 0) {
			dogQueue.offer(animal);
		} else {
			catQueue.offer(animal);
		}
	}

	private Integer getAnimal(int code) {
		if (code < -1 || code > 1) {
			return null;
		}

		if (code == 1) {// ������
			if (!dogQueue.isEmpty()) {
				return dogQueue.poll().value;
			} else {
				return null;
			}
		} else if (code == -1) {// ����è
			if (!catQueue.isEmpty()) {
				return catQueue.poll().value;
			} else {
				return null;
			}
		} else {// ��������Ķ���
			Info animal1 = null;
			Info animal2 = null;
			if (!dogQueue.isEmpty()) {
				animal1 = dogQueue.peek();
			}

			if (!catQueue.isEmpty()) {
				animal2 = catQueue.peek();
			}

			Queue<Info> activeQueue = null;
			if (animal1 != null && animal2 != null) {
				activeQueue = animal1.timestamp < animal2.timestamp ? dogQueue
						: catQueue;
				return activeQueue.poll().value;
			} else if (animal1 == null && animal2 == null) {
				return null;
			} else {
				activeQueue = animal1 != null ? dogQueue : catQueue;
				return activeQueue.poll().value;
			}

		}

	}

	public ArrayList<Integer> asylum(int[][] ope) {
		ArrayList<Integer> res = new ArrayList<>();
		for (int[] item : ope) {
			if (item[0] == 1) {// ������
				this.addAnimal(item[1]);
			} else if (item[0] == 2) {// ������
				Integer value = this.getAnimal(item[1]);
				if (value != null) {
					res.add(value);
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String inputString = "1,-3,1,-6,1,10,1,3,2,0,1,19,2,-1,1,-81,1,36,2,0,2,1,1,66,2,0,1,-13,2,0,2,-1,2,0,1,29,2,1,2,1,2,1,1,56,1,-99,2,-1,2,-1,1,79,1,-25,1,-6,1,63,1,48,1,-40,1,56,2,1,1,28,1,78,1,20,1,18,1,20,1,-92,1,87,2,0,1,34,2,-1,1,96,1,38,2,0,2,-1,1,17,1,13,1,3,1,-26,2,0,2,0,2,-1,2,1,2,0,1,-78,1,57,1,71,1,-11,2,-1,1,-28,1,-28,1,-87,1,-86,1,-9,1,50,2,1,2,0,1,65,1,-98,1,-54,2,0,2,-1,1,84,1,-72,1,-42,1,77,1,-61,1,-61,1,-11,1,94,2,1,1,93,2,-1,2,-1,1,43,2,-1,1,-72,2,-1,1,-31,1,-41,1,-85,1,-2,2,0,1,94,1,80,1,-86,1,-83,1,-20,1,49,1,-47,1,46,1,34,2,1,2,0,1,-41,2,1,2,-1,1,-44,1,100,1,-85,1,-25,1,-8,1,-69,1,13,1,82,2,1,1,-41,1,-44,1,22,1,-72,1,-16,1,-11,1,65,1,-66,1,25,1,-31,1,-63,2,1,1,86,1,2,1,6,1,-42,1,-9,1,76,1,54,2,0,2,1";
		List<Integer> list = new ArrayList<>();
		for (String item : inputString.split(",")) {
			list.add(Integer.parseInt(item));
		}
		int[][] input = new int[list.size() >> 1][2];
		for (int i = 0; i < list.size() >> 1; i++) {
			input[i][0] = list.get(2 * i);
			input[i][1] = list.get(2 * i + 1);
		}

		List<Integer> res = new Exercise01().asylum(input);
		System.out.println(res);
		System.out.println("[-3, -6, 10, 3, 19, -81, -13, 36, 66, 29, -99, 56, 79, -25, -6, -40, 63, 48, -92, 56, 28, -26, 78, 20, 18, -78, 20, -11, -28, -28, -87, 87, 34, 96, 38, -86, 17, 13, 3, 57] ");

	}
}
