package sxy.java.multithread.ABCprint.notinturn;

public class ABCPrintSychronize implements Runnable{
	public static Integer count = 0;
	public String name;

	public ABCPrintSychronize(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (count) {
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
			}
			
		}
	}
	public static void main(String[] args) {
		new Thread(new ABCPrintSychronize("T1")).start();
		new Thread(new ABCPrintSychronize("T2")).start();
		new Thread(new ABCPrintSychronize("T3")).start();
		new Thread(new ABCPrintSychronize("T4")).start();
	}

}

