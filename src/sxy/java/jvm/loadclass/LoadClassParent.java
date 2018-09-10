package sxy.java.jvm.loadclass;

public class LoadClassParent {
	public static void main(String[] args) {
		System.out.println("测试代码开始");
		new Child();
		System.out.println("测试代码结束");
	}
}

class Parent {
	static {
		System.out.println("父类：静态代码块");
	}

	{
		System.out.println("父类：构造代码块");
	}

	private static String staticStringInParent = initStaticStringInParent();

	private String stringInParent = initStringInParent();

	public Parent() {
		System.out.println("父类：构造方法");
	}

	private static String initStaticStringInParent() {
		System.out.println("父类：静态方法，被静态成员变量赋值调用。");
		return "initStaticStringInParent";
	}

	private String initStringInParent() {
		System.out.println("父类：普通成员方法，被普通成员变量赋值调用。");
		return "initStringInParent";
	}
}

class Child extends Parent {
	private String stringInChild = initStringInChild();
	private static String staticStringInChild = initStaticStringInChild();

	{
		System.out.println("子类：构造代码块");
	}

	static {
		System.out.println("子类：静态代码块");
	}

	public Child() {
		System.out.println("子类：构造方法");
	}

	private static String initStaticStringInChild() {
		System.out.println("子类：静态方法，被静态成员变量赋值调用。");
		return "initStaticStringInChild";
	}

	private String initStringInChild() {
		System.out.println("子类：普通成员方法，被普通成员变量赋值调用。");
		return "initStringInChild";
	}

}
