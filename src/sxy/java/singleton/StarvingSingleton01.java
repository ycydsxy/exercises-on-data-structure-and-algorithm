package sxy.java.singleton;

/**
 * 饿汉式的单例模式，使用静态常量进行初始化
 * 
 * 优点：线程安全
 * 
 * 缺点：饿汉式，如果不用则占用内存
 * 
 * @author Kevin
 * 
 */
public class StarvingSingleton01 {

	private static final StarvingSingleton01 singleton = new StarvingSingleton01();

	private StarvingSingleton01() {

	}

	public StarvingSingleton01 getInstance() {
		return singleton;
	}
}
