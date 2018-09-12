package sxy.java.multithread.jvolatile;

/**
 * volatile关键字可以保证可见性
 * 
 * @author Kevin
 * 
 */
public class TestVisibility {

	public static void main(String[] args) throws InterruptedException {
		Task1 task1 = new Task1();
		new Thread(task1).start();
		Thread.sleep(10);
		task1.running = false;
		System.out.println("已改变running值.");
		Thread.sleep(100);
		System.out.println("----------------");

		Task2 task2 = new Task2();
		new Thread(task2).start();
		Thread.sleep(10);
		task2.running = false;
		System.out.println("已改变running值.");
		Thread.sleep(100);

	}
}

class Task1 implements Runnable {
	volatile boolean running = true;
	int i = 0;

	@Override
	public void run() {
		System.out.println(getClass().getSimpleName() + " is running!");
		while (running) {
			i++;
		}
		System.out.println(getClass().getSimpleName() + " is stopped!");
	}
}

class Task2 implements Runnable {
	boolean running = true;
	int i = 0;

	@Override
	public void run() {
		System.out.println(getClass().getSimpleName() + " is running!");
		while (running) {
			i++;
		}
		System.out.println(getClass().getSimpleName() + " is stopped!");
	}
}
