package sxy.java.designpattern.singleton;

/**
 * 懒汉式的单例模式，使用同步方法
 * 
 * 优点：在使用的时候再初始化，实现了懒加载；线程安全
 * 
 * 缺点：效率低下，多个线程同时获取时需要排队
 * 
 * @author Kevin
 * 
 */
public class LazySingleton02 {

	private LazySingleton02 singleton;

	private LazySingleton02() {

	}

	public synchronized LazySingleton02 getInstance() {
		if (singleton == null) {
			singleton = new LazySingleton02();
		}
		return singleton;
	}
}
