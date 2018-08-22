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
public class StarvingSingleton01 {

	private static final StarvingSingleton01 singleton = new StarvingSingleton01();

	private StarvingSingleton01() {

	}

	public StarvingSingleton01 getInstance() {
		return singleton;
	}
}
