package sxy.java.multithread.jvolatile;

public class TestVisibility implements Runnable {
	volatile boolean  running = true;
	int i = 6;

	@Override
	public void run() {
		while (running) {
			i++;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TestVisibility task = new TestVisibility();
		Thread th = new Thread(task);
		th.start();
		Thread.sleep(10);
		task.running = false;
		Thread.sleep(100);
		System.out.println(task.i);
		System.out.println("程序停止");

	}
}
