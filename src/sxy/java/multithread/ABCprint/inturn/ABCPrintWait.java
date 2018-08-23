package sxy.java.multithread.ABCprint.inturn;

public class ABCPrintWait implements Runnable {
	private String self;
	private String next;
	private volatile static int count = 0;
	private static final int MAX_COUNT = 10;

	public ABCPrintWait(String self, String next) {
		this.self = self;
		this.next = next;
	}

	@Override
	public void run() {
		try {
			while (true) {
				synchronized (self) {
					if (count >= MAX_COUNT) {// 这个地方不仅要使得本线程结束，需要让其他wait的线程结束
						synchronized (next) {
							next.notify();
						}
						break;
					}
					System.out.println(self);
					count++;
					synchronized (next) {
						next.notify();
					}
					self.wait();
				}
				Thread.sleep(500);
			}
			System.out.println(self + " exit.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		String a = "A";
		String b = "B";
		String c = "C";

		new Thread(new ABCPrintWait(a, b)).start();
		Thread.sleep(500);
		new Thread(new ABCPrintWait(b, c)).start();
		Thread.sleep(500);
		new Thread(new ABCPrintWait(c, a)).start();
	}
}
