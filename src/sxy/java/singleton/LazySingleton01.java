package sxy.java.singleton;

/**
 * ����ʽ�ĵ���ģʽ
 * 
 * �ŵ㣺��ʹ�õ�ʱ���ٳ�ʼ����ʵ����������
 * 
 * ȱ�㣺�̲߳���ȫ�����ܳ��ֶ��ʵ��
 * 
 * @author Kevin
 * 
 */
public class LazySingleton01 {

	private LazySingleton01 singleton;

	private LazySingleton01() {

	}

	public LazySingleton01 getInstance() {
		if (singleton == null) {
			singleton = new LazySingleton01();
		}
		return singleton;
	}
}
