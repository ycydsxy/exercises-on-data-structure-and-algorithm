package sxy.algorithm.nowcoder.advanced.chapter02;

import java.util.LinkedList;

public class MaxNumInWindow {

	public static class UpdateDeque {
		public LinkedList<Integer> deque;
		public int[] arr;
		public int L;
		public int R;

		public UpdateDeque(int[] arr) {
			super();
			this.arr = arr;
			this.deque = new LinkedList<>();
			this.L = 0;
			this.R = 0;
		}

		public Integer getMaxNumInWindow() {
			if (deque.isEmpty()) {
				return null;
			}
			return arr[deque.peekFirst()];
		}

		public void slideR() {
			if (R == arr.length) {
				return;
			}
			R++;
			int x = arr[R - 1];
			while (!deque.isEmpty() && arr[deque.peekLast()] <= x) {// 非空则从尾部倒出
				deque.pollLast();
			}
			deque.offerLast(R - 1);
		}

		public void slideL() {
			if (L == R || L == arr.length) {
				return;
			}
			// 能到这里的潜台词是，deque中肯定非空
			L++;
			if (deque.peekFirst() < L) {
				deque.pollFirst();
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { 5, 4, 2, 3, 6 };
		UpdateDeque updateDeque = new UpdateDeque(arr);
		updateDeque.slideL();// null
		System.out.println(updateDeque.getMaxNumInWindow());
		updateDeque.slideR();// 5
		updateDeque.slideR();// 5,4
		System.out.println(updateDeque.getMaxNumInWindow());
		updateDeque.slideL();// 4
		System.out.println(updateDeque.getMaxNumInWindow());
		updateDeque.slideR();// 4,2
		updateDeque.slideR();// 4,2,3
		updateDeque.slideR();// 4,2,3,6
		System.out.println(updateDeque.getMaxNumInWindow());
		updateDeque.slideR();// 4,2,3,6
		System.out.println(updateDeque.getMaxNumInWindow());
		updateDeque.slideL();// 2,3,6
		updateDeque.slideL();// 3,6
		updateDeque.slideL();// 6
		System.out.println(updateDeque.getMaxNumInWindow());
		updateDeque.slideL();// null
		System.out.println(updateDeque.getMaxNumInWindow());
		System.out.println(updateDeque.L + updateDeque.R);
	}

}
