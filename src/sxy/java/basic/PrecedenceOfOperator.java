package sxy.java.basic;

/**
 * ����������ȼ�
 * 
 * @author Kevin
 * 
 */
public class PrecedenceOfOperator {
	public static void main(String[] args) {
		int a = (1 << 2) - 1;
		int b = 1 << 2 - 1;
		System.out.println(a);
		System.out.println(b);
	}
}
