package sxy.java.singleton;

/**
 * ����ʽ�ĵ���ģʽ��ʹ��ö��
 * 
 * �ŵ㣺��ʹ�õ�ʱ���ٳ�ʼ����ʵ���������أ��̰߳�ȫ��Ч�ʽϸ�
 * 
 * @author Kevin
 * 
 */
public enum LazySingleton06 {
	INSTANCE {
		@Override
		public void whateverMethod() {

		}
	};

	public int whateverField = 0;

	public abstract void whateverMethod();
}
