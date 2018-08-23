package sxy.java.multithread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class JAtomicInteger {

	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				// 调用AtomicInteger的getAndIncement返回的是增加之前的值
				System.out.println(atomicInteger.getAndIncrement());
			}
		};
		for (int i = 0; i < 5; i++) {
			new Thread(task).start();
		}
		Thread.sleep(1000);
		System.out.println("result:" + atomicInteger.get());
	}
}
