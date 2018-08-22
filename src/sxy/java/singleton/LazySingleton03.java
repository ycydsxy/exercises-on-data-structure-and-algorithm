package sxy.java.singleton;

/**
 * 懒汉式的单例模式，使用同步代码块
 * 
 * 优点：在使用的时候再初始化，实现了懒加载；
 * 
 * 缺点：线程不安全
 * 
 * @author Kevin
 * 
 */
public class LazySingleton03 {

	private LazySingleton03 singleton;

	private LazySingleton03() {

	}

	public LazySingleton03 getInstance() {
		if (singleton == null) {
			synchronized (LazySingleton03.class) {
				singleton = new LazySingleton03();
			}
		}
		return singleton;
	}
}
