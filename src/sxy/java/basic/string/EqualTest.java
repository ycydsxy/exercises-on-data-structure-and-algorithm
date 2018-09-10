package sxy.java.basic.string;

public class EqualTest {

	public static void main(String[] args) {
		String s1 = "accp";
		String s2 = "accp";
		String s3 = new String(s1);
		String s4 = new String("accp");

		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(s1 == s3);
		System.out.println(s1.equals(s3));
		System.out.println(s1 == s4);
	}

}
