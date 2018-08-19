package sxy.algorithm.nowcoder.chapter02.exercise;

import java.util.Scanner;

/**
 * ���ӣ�https://www.nowcoder.com/questionTerminal/6ffdd7e4197c403e88c6a8aa3e7a332a
 * ��Դ��ţ����
 * 
 * ����һ��������n,��n!(���׳�)ĩβ�ж��ٸ�0�� ����: n = 10; n! = 3628800,���Դ�Ϊ2 ��������:
 * 
 * ����Ϊһ�У�n(1 �� n �� 1000)
 * 
 * �������:
 * 
 * ���һ������,����Ŀ����
 * 
 * �ⷨ����ʵ���Ƕ�n��n-1��...1��Щ���ֽ���ʽ�����ж��ٸ�2��5����2������̫�࣬���ù��ˣ�ֻ�ÿ��ж��ٸ�5��
 * 
 * @author Kevin
 * 
 */
public class Exercise02 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		s.close();
		int k5 = 0;
		for (int i = n; i >= 1; i--) {
			int temp = i;
			while (true) {
				int p = temp / 5;
				int q = temp - 5 * p;
				if (q == 0) {
					k5++;
					temp = p;
				} else {
					break;
				}
			}
		}

		System.out.println(k5);

	}

}
