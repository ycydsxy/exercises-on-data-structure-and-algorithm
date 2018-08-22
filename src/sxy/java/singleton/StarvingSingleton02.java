package sxy.java.singleton;

/**
 * ����ʽ�ĵ���ģʽ��ʹ�þ�̬�������г�ʼ��
 * 
 * �ŵ㣺�̰߳�ȫ
 * 
 * ȱ�㣺����ʽ�����������ռ���ڴ�
 * 
 * @author Kevin
 * 
 */
public class StarvingSingleton02 {

	private static final StarvingSingleton02 singleton;

	static {
		singleton = new StarvingSingleton02();
	}

	private StarvingSingleton02() {

	}

	public StarvingSingleton02 getInstance() {
		return singleton;
	}
}
