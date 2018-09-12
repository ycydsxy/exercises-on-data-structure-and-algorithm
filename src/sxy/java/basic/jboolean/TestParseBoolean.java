package sxy.java.basic.jboolean;

/**
 * parseBoolean除了入参是"true"，其余全为false，不能用1、0进行转换
 * 
 * @author Kevin
 * 
 */
public class TestParseBoolean {

	public static void main(String[] args) {
		System.out.println(Boolean.parseBoolean("1"));
		System.out.println(Boolean.parseBoolean("0"));
		System.out.println(Boolean.parseBoolean("2"));
		System.out.println(Boolean.parseBoolean("true"));
		System.out.println(Boolean.parseBoolean("TRUE"));
		System.out.println(Boolean.parseBoolean("True"));
		System.out.println(Boolean.parseBoolean("false"));
	}

}
