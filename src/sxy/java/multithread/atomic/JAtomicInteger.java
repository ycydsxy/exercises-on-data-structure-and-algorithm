package sxy.java.multithread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * atomic类可以保证线程安全
 * 
 * @author Kevin
 * 
 */
public class JAtomicInteger implements Runnable {

	private static AtomicInteger atomicInteger = new AtomicInteger(0);
	private static int normalInt = 0;

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			atomicInteger.getAndIncrement();
			normalInt++;
		}
	}

	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < 10; i++) {
			new Thread(new JAtomicInteger()).start();
		}

		while (Thread.activeCount() > 1) {// 保证所有线程都跑完
			Thread.yield();
		}

		System.out.println("atomicInteger:" + atomicInteger.get());
		System.out.println("normalInt:" + normalInt);
	}
}
