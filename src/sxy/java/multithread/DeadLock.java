package sxy.java.multithread;

/**
 * 死锁的一个例子
 * 
 * @author Kevin
 *
 */
public class DeadLock {
	public static Object a = new Object();
	public static Object b = new Object();

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("task1 is trying to get a...");
				synchronized (DeadLock.a) {
					System.out.println("task1 got a!");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("task1 is trying to get b...");
					synchronized (DeadLock.b) {
						System.out.println("task1 got b!");
					}
				}

			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("task2 is trying to get b...");
				synchronized (DeadLock.b) {
					System.out.println("task2 got b!");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("task2 is trying to get a...");
					synchronized (DeadLock.a) {
						System.out.println("task2 got a!");
					}

				}

			}
		}).start();
	}
}
