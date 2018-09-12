package sxy.java.basic.jfinal;

/**
 * final变量和普通变量的区别
 * 
 * 注意：当final变量是基本数据类型以及String类型时，如果在编译期间能知道它的确切值，则编译器会把它当做编译期常量使用.
 * 
 * @author Kevin
 * 
 */
public class Test1 {
	public static void main(String[] args) {
		String a = "hello2";
		String a1 = "hello" + 2;// 编译期直接优化

		final String b1 = "hello"; // final变量，已确定值，当做常量处理
		String b2 = "hello"; // 普通变量

		String a2 = b1 + 2;
		String a3 = b2 + 2;

		System.out.println((a == a1));
		System.out.println((a == a2));
		System.out.println((a == a3));

	}
}
