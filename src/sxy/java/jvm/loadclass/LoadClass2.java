package sxy.java.jvm.loadclass;

public class LoadClass2 {
	public static void main(String[] args) {
		System.out.println(LoadClass2DummyClass1.s);
		System.out.println();
		System.out.println(LoadClass2DummyClass2.s);
		System.out.println();
		System.out.println(LoadClass2DummyClass3.s);
	}
}

class LoadClass2DummyClass1 {
	public static final String s = new String("OK");

	static {
		System.out.print("JD");
	}
}

class LoadClass2DummyClass2 {

	public static final String s = "OK";

	static {
		System.out.print("JD");
	}
}

class LoadClass2DummyClass3 {

	public static String s = "OK";

	static {
		System.out.print("JD");
	}

}
