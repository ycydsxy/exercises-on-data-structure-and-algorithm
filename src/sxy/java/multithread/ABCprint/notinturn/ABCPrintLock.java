package sxy.java.multithread.ABCprint.notinturn;

import java.util.concurrent.locks.*;

public class ABCPrintLock implements Runnable{
	private static Lock lock = new ReentrantLock();
	public static Integer count = 0;
	public String name;

	public ABCPrintLock(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			
			try {
				lock.lock();
				switch (count % 3) {
				case 0:
					System.out.println(this.name + ": A");
					count++;
					break;
				case 1:
					System.out.println(this.name + ": B");
					count++;
					break;
				case 2:
					System.out.println(this.name + ": C");
					count++;
					break;
				}

			} catch (Exception e) {

			} finally {
				lock.unlock();
			}

		}
	}

	public static void main(String[] args) throws InterruptedException {
		new Thread(new ABCPrintLock("T1")).start();
		new Thread(new ABCPrintLock("T2")).start();
		new Thread(new ABCPrintLock("T3")).start();
		new Thread(new ABCPrintLock("T4")).start();
	}

}