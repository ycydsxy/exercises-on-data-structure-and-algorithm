package sxy.java.singleton;

/**
 * 懒汉式的单例模式，使用同步代码块加双重判断
 * 
 * 优点：在使用的时候再初始化，实现了懒加载；线程安全；效率较高
 * 
 * @author Kevin
 * 
 */
public class LazySingleton04 {

	private LazySingleton04 singleton;

	private LazySingleton04() {

	}

	public LazySingleton04 getInstance() {
		if (singleton == null) {
			synchronized (LazySingleton04.class) {
				if (singleton == null) {
					singleton = new LazySingleton04();
				}
			}
		}
		return singleton;
	}
}
