package sxy.java.singleton;

/**
 * ����ʽ�ĵ���ģʽ��ʹ�þ�̬�ڲ��ࣨjvm����ʵ�������أ�
 * 
 * �ŵ㣺��ʹ�õ�ʱ���ٳ�ʼ����ʵ���������أ��̰߳�ȫ��Ч�ʽϸ�
 * 
 * @author Kevin
 * 
 */
public class LazySingleton05 {

	private static class Helper {
		public static LazySingleton05 singleton = new LazySingleton05();
	}

	private LazySingleton05() {

	}

	public LazySingleton05 getInstance() {
		return Helper.singleton;
	}
}
