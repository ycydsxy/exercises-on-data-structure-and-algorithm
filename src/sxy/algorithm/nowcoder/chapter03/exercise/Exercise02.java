package sxy.algorithm.nowcoder.chapter03.exercise;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * ���ӣ�https://www.nowcoder.com/questionTerminal/9be0172896bd43948f8a32fb954e1be1
 * ��Դ��ţ����
 * 
 * ��εõ�һ���������е���λ����������������ж�����������ֵ����ô��λ������������ֵ����֮��λ���м����ֵ��������������ж���ż������ֵ��
 * ��ô��λ������������ֵ����֮���м���������ƽ��ֵ������ʹ��Insert()������ȡ��������ʹ��GetMedian()������ȡ��ǰ��ȡ���ݵ���λ����
 * 
 * @author Kevin
 * 
 */
public class Exercise02 {

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
