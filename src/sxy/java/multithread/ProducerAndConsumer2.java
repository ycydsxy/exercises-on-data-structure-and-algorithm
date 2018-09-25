package sxy.java.multithread;

import java.util.LinkedList;

/**
 * 生产者、消费者模式，使用wait和notify
 * 
 * @author Kevin
 * 
 */
public class ProducerAndConsumer2 {

	public static void main(String[] args) {
		MyQueue<Integer> queue = new MyQueue<>(10);
		new Producer(queue).start();

		new Consumer(queue).start();
		new Consumer(queue).start();

	}

	/**
	 * 加一个队列的最大长度
	 * 
	 * @author Kevin
	 * 
	 * @param <T>
	 */
	private static class MyQueue<T> extends LinkedList<T> {

		private static final long serialVersionUID = -3399265913425540294L;
		public int MAX_SIZE;

		public MyQueue(int MAX_SIZE) {
			super();
			this.MAX_SIZE = MAX_SIZE;
		}

		public boolean isFull() {
			return this.size() >= this.MAX_SIZE;
		}
	}

	private static class Producer extends Thread {

		private MyQueue<Integer> queue;

		public Producer(MyQueue<Integer> queue) {
			super();
			this.queue = queue;
		}

		@Override
		public void run() {
			while (true) {
				try {

					synchronized (queue) {
						while (queue.isFull()) {// 使用while是因为可能被唤醒后队列仍是满的，此时应该再次进入等待状态
							System.out.println(String.format("生产者 %s发现当前队列满",
									this.getName()));
							System.out.println(String.format("生产者 %s开始等待",
									this.getName()));
							queue.wait();
							System.out.println(String.format("生产者 %s停止等待",
									this.getName()));
						}
						queue.add(1);
						System.out.println(String.format(
								"生产者%s生产一条任务，当前队列长度为%d", this.getName(),
								queue.size()));
						queue.notifyAll();
					}
					Thread.sleep((long) (Math.random() * 500));

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class Consumer extends Thread {

		private MyQueue<Integer> queue;

		public Consumer(MyQueue<Integer> queue) {
			super();
			this.queue = queue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					synchronized (queue) {

						while (queue.isEmpty()) {
							System.out.println(String.format("消费者 %s发现当前队列为空",
									this.getName()));
							System.out.println(String.format("消费者 %s开始等待",
									this.getName()));
							queue.wait();
							System.out.println(String.format("消费者 %s停止等待",
									this.getName()));
						}
						queue.poll();
						System.out.println(String.format(
								"消费者%s消费一条任务，当前队列长度为%d", this.getName(),
								queue.size()));
						queue.notifyAll();
					}
					Thread.sleep((long) (Math.random() * 1000));

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}