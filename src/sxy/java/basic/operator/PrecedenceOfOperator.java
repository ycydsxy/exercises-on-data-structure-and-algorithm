package sxy.java.basic.operator;

/**
 * 运算符的优先级
 * 
 * @author Kevin
 * 
 */
public class PrecedenceOfOperator {
	
	public static void main(String[] args) {
		//移位运算符
		int a = (1 << 2) - 1;
		int b = 1 << 2 - 1;
		System.out.println(a);
		System.out.println(b);

		//三目运算符
		String list = "aaa";
		System.out.println("prefix " + list == null ? 0 : list + " postfix");
		System.out.println("prefix " + (list == null ? 0 : list) + " postfix");
		list = null;
		System.out.println("prefix " + list == null ? 0 : list + " postfix");
		System.out.println("prefix " + (list == null ? 0 : list) + " postfix");
	}
}
