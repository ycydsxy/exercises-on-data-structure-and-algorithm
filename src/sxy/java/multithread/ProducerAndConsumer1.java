package sxy.java.multithread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者、消费者模式，使用阻塞队列
 * 
 * @author Kevin
 * 
 */
public class ProducerAndConsumer1 {

	public static void main(String[] args) {
		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

		new Producer(blockingQueue).start();
		new Consumer(blockingQueue).start();
		new Consumer(blockingQueue).start();
	}

	private static class Producer extends Thread {

		private BlockingQueue<Integer> blockingQueue;

		public Producer(BlockingQueue<Integer> blockingQueue) {
			super();
			this.blockingQueue = blockingQueue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					blockingQueue.put(1);
					System.out.println(String.format("生产者%s生产一条任务，当前队列长度为%d",
							this.getName(), blockingQueue.size()));
					Thread.sleep((long) (Math.random() * 500));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private static class Consumer extends Thread {
		private BlockingQueue<Integer> blockingQueue;

		public Consumer(BlockingQueue<Integer> blockingQueue) {
			super();
			this.blockingQueue = blockingQueue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					blockingQueue.take();
					System.out.println(String.format("消费者%s消费一条任务，当前队列长度为%d",
							this.getName(), blockingQueue.size()));
					Thread.sleep((long) (Math.random() * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
