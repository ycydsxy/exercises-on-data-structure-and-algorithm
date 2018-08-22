package sxy.java.singleton;

/**
 * 懒汉式的单例模式，使用静态内部类（jvm帮助实现懒加载）
 * 
 * 优点：在使用的时候再初始化，实现了懒加载；线程安全；效率较高
 * 
 * @author Kevin
 * 
 */
public class LazySingleton05 {

	private static class Helper {
		public static LazySingleton05 singleton = new LazySingleton05();
	}

	private LazySingleton05() {

	}

	public LazySingleton05 getInstance() {
		return Helper.singleton;
	}
}
