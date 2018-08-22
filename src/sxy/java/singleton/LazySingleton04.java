package sxy.java.singleton;

/**
 * ����ʽ�ĵ���ģʽ��ʹ��ͬ��������˫���ж�
 * 
 * �ŵ㣺��ʹ�õ�ʱ���ٳ�ʼ����ʵ���������أ��̰߳�ȫ��Ч�ʽϸ�
 * 
 * @author Kevin
 * 
 */
public class LazySingleton04 {

	private LazySingleton04 singleton;

	private LazySingleton04() {

	}

	public LazySingleton04 getInstance() {
		if (singleton == null) {
			synchronized (LazySingleton04.class) {
				if (singleton == null) {
					singleton = new LazySingleton04();
				}
			}
		}
		return singleton;
	}
}
