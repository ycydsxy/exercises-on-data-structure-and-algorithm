package sxy.java.basic.jfinal;

/**
 * final引用的不可变性
 * 
 * @author Kevin
 * 
 */
public class ForCycle {

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i <= 10; i++) {
			final int j = i; // 关键是这一句代码，将 i 转化为 j，这样j 还是final类型的参与线程
			Thread aa = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
						System.out.println(j);
					} catch (Exception e) {

					}
				}
			});
			aa.start();
			aa.join();
		}

		System.out.println("ALL DONE!");
	}
}