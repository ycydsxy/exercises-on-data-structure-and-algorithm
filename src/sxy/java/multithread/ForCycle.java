package sxy.java.multithread;

import java.util.ArrayList;
import java.util.List;

public class ForCycle {

	public static void main(String[] args) throws InterruptedException {
		List<Thread> threadList = new ArrayList<>();
		for (int i = 0; i <= 10; i++) {
			final int j = i; // 关键是这一句代码，将 i 转化为 j，这样j 还是final类型的参与线程
			Thread aa = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep((long) (3000 * Math.random()));
						System.out.println(j);
					} catch (Exception e) {

					}
				}
			});
			threadList.add(aa);
		}

		for (Thread bb : threadList) {
			bb.start();
			bb.join();
		}

		System.out.println("ALL DONE!");
	}

}