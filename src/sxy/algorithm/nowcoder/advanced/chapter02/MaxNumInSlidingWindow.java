package sxy.algorithm.nowcoder.advanced.chapter02;

import java.util.LinkedList;

/**
 * 设计一个窗口最大（小）值的更新结构
 * 
 * 有一个窗口，求在左右边界滑动过程中，窗口的最大（小）值，要求获取当前窗口的最大（小）值的时间复杂度是O(1).
 * 
 * 解法：单调双端队列，窗口中的每个值至多进队列一次，出队列一次，故窗口划过N个数时，更新代价是O(N)，故平均下来的时间复杂度是O(1)的。
 * 
 * @author Kevin
 * 
 */
public class MaxNumInSlidingWindow {

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
			while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[R - 1]) {// 非空则从尾部倒出
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
