package sxy.algorithm.nowcoder.basic.chapter01and02;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/9be0172896bd43948f8a32fb954e1be1
 * 来源：牛客网
 * 
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
 * 那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 * 
 * @author Kevin
 * 
 */
public class MadianQuick {

	private PriorityQueue<Integer> less = new PriorityQueue<Integer>(
			new Comparator<Integer>() {
				@Override
				public int compare(Integer arg0, Integer arg1) {
					return arg1.compareTo(arg0);
				}
			});
	private PriorityQueue<Integer> more = new PriorityQueue<Integer>();

	public void Insert(Integer num) {
		less.offer(num);
		if (Math.abs(less.size() - more.size()) > 1) {
			more.offer(less.poll());
		}
	}

	public Double GetMedian() {
		if (less.isEmpty()) {
			return null;
		}
		if (more.isEmpty()) {
			return (double) less.peek();
		}

		if (less.size() == more.size()) {
			return ((double) (less.peek() + more.peek())) / 2;
		} else {
			return Math.min((double) less.peek(), (double) more.peek());
		}
	}
}
