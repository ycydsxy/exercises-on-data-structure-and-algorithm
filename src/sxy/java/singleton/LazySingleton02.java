package sxy.java.singleton;

/**
 * ����ʽ�ĵ���ģʽ��ʹ��ͬ������
 * 
 * �ŵ㣺��ʹ�õ�ʱ���ٳ�ʼ����ʵ���������أ��̰߳�ȫ
 * 
 * ȱ�㣺Ч�ʵ��£�����߳�ͬʱ��ȡʱ��Ҫ�Ŷ�
 * 
 * @author Kevin
 * 
 */
public class LazySingleton02 {

	private LazySingleton02 singleton;

	private LazySingleton02() {

	}

	public synchronized LazySingleton02 getInstance() {
		if (singleton == null) {
			singleton = new LazySingleton02();
		}
		return singleton;
	}
}
