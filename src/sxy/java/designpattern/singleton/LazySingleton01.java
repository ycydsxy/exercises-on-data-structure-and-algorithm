package sxy.java.designpattern.singleton;

/**
 * 懒汉式的单例模式
 * 
 * 优点：在使用的时候再初始化，实现了懒加载
 * 
 * 缺点：线程不安全，可能出现多个实例
 * 
 * @author Kevin
 * 
 */
public class LazySingleton01 {

	private LazySingleton01 singleton;

	private LazySingleton01() {

	}

	public LazySingleton01 getInstance() {
		if (singleton == null) {
			singleton = new LazySingleton01();
		}
		return singleton;
	}
}
