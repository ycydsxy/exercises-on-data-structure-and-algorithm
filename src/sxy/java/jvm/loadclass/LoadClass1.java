package sxy.java.jvm.loadclass;

/**
 * loadClass()和forName()的区别
 * 
 * @author Kevin
 * 
 */
public class LoadClass1 {

	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Class clazz = classLoader.loadClass("sxy.java.jvm.loadclass.LoadClass1DummyClass");
		System.out.print("Test");
		clazz.forName("sxy.java.jvm.loadclass.LoadClass1DummyClass");
	}

}

class LoadClass1DummyClass {
	static {
		System.out.print("A");
	}
}
