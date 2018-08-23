package sxy.java.designpattern.singleton;

/**
 * 饿汉式的单例模式，使用静态代码块进行初始化
 * 
 * 优点：线程安全
 * 
 * 缺点：饿汉式，如果不用则占用内存
 * 
 * @author Kevin
 * 
 */
public class StarvingSingleton02 {

	private static final StarvingSingleton02 singleton;

	static {
		singleton = new StarvingSingleton02();
	}

	private StarvingSingleton02() {

	}

	public StarvingSingleton02 getInstance() {
		return singleton;
	}
}
