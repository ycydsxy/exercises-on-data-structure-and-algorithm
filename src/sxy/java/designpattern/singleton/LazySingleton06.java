package sxy.java.designpattern.singleton;

/**
 * 懒汉式的单例模式，使用枚举
 * 
 * 优点：在使用的时候再初始化，实现了懒加载；线程安全；效率较高
 * 
 * @author Kevin
 * 
 */
public enum LazySingleton06 {
	INSTANCE {
		@Override
		public void whateverMethod() {

		}
	};

	public int whateverField = 0;

	public abstract void whateverMethod();
}
