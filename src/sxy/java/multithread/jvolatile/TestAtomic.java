package sxy.java.multithread.jvolatile;

/**
 * volatile关键字不能保证原子性
 * 
 * @author Kevin
 * 
 */
public class TestAtomic {
	public static volatile int inc = 0;

	public static int test() {
		inc = 0;
		for (int i = 0; i < 10; i++) {
			new Thread() {
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						inc++;
					}

				};
			}.start();
		}

		while (Thread.activeCount() > 1) {// 保证所有线程都跑完
			Thread.yield();
		}

		return inc;
	}

	public static void main(String[] args) {
		int expectedResult = 10000;
		boolean flag = true;
		for (int i = 0; i < 100; i++) {
			int result = test();
			if (result != expectedResult) {
				flag = false;
				break;
			}
		}

		if (flag) {
			System.out.println("succeeded!");
		} else {
			System.out.println("fuck!");
			System.out.println(inc);
		}
	}
}
