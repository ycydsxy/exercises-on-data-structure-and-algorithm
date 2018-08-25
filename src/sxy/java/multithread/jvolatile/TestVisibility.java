package sxy.java.multithread.jvolatile;

public class TestVisibility {

	public static void main(String[] args) throws InterruptedException {
		Visibility vis = new Visibility();
		Thread th = new Thread(vis);
		th.start();
		Thread.sleep(10);
		vis.running = false;
		Thread.sleep(100);
		System.out.println(vis.i);
		System.out.println("程序停止");

		NoVisibility noVis = new NoVisibility();
		th = new Thread(noVis);
		th.start();
		Thread.sleep(10);
		noVis.running = false;
		Thread.sleep(100);
		System.out.println(noVis.i);
		System.out.println("程序未停止");

	}
}

class NoVisibility implements Runnable {
	boolean running = true;
	int i = 0;

	@Override
	public void run() {
		while (running) {
			i++;
		}
	}
}

class Visibility implements Runnable {
	volatile boolean running = true;
	int i = 0;

	@Override
	public void run() {
		while (running) {
			i++;
		}
	}
}
