package sxy.java.multithread;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerAndConsumer1 {
	private final int MAX_LEN = 10;
	private Queue<Integer> queue = new LinkedList<Integer>();

	class Producer extends Thread {
		@Override
		public void run() {
			produce();
		}

		private void produce() {
			while (true) {
				synchronized (queue) {
					if (queue.size() == MAX_LEN) {
						System.out.println("当前队列满");
						try {
							queue.notify();
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					queue.add(1);
					System.out.println("生产者生产一条任务，当前队列长度为" + queue.size());

					if (Math.random() < 0.4) {// 用概率控制生产速度，通知消费者消费
						try {
							queue.notify();
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class Consumer extends Thread {
		@Override
		public void run() {
			consume();
		}

		private void consume() {
			while (true) {
				synchronized (queue) {
					if (queue.size() == 0) {
						System.out.println("当前队列为空");
						try {
							queue.notify();
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					queue.poll();
					System.out.println("消费者消费一条任务，当前队列长度为" + queue.size());
					if (Math.random() < 0.5) {
						try {
							queue.notify();
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		ProducerAndConsumer1 pc = new ProducerAndConsumer1();
		Producer producer = pc.new Producer();
		Consumer consumer = pc.new Consumer();
		producer.start();
		consumer.start();
	}
}