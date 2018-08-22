package sxy.java.singleton;

/**
 * ����ʽ�ĵ���ģʽ��ʹ��ͬ�������
 * 
 * �ŵ㣺��ʹ�õ�ʱ���ٳ�ʼ����ʵ���������أ�
 * 
 * ȱ�㣺�̲߳���ȫ
 * 
 * @author Kevin
 * 
 */
public class LazySingleton03 {

	private LazySingleton03 singleton;

	private LazySingleton03() {

	}

	public LazySingleton03 getInstance() {
		if (singleton == null) {
			synchronized (LazySingleton03.class) {
				singleton = new LazySingleton03();
			}
		}
		return singleton;
	}
}
