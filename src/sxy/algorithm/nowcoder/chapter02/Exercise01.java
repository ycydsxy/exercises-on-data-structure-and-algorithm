package sxy.algorithm.nowcoder.chapter02;

import java.util.Scanner;

/**
 * ���ӣ�https://www.nowcoder.com/questionTerminal/ac61207721a34b74b06597fe6eb67c52
 * ��Դ��ţ����
 * 
 * ����һ��ʮ������M���Լ���Ҫת���Ľ�����N����ʮ������Mת��ΪN������ ��������:
 * 
 * ����Ϊһ�У�M(32λ����)��N(2 �� N �� 16)���Կո������
 * 
 * 
 * �������:
 * 
 * Ϊÿ������ʵ�����ת���������ÿ�����ռһ�С����N����9�����Ӧ�����ֹ���ο�16���ƣ����磬10��A��ʾ���ȵȣ�
 * 
 * @author Kevin
 * 
 */
public class Exercise01 {
	public static void main(String[] args) {
		String numbers = "0123456789ABCDEF";
		Scanner s = new Scanner(System.in);
		int m = s.nextInt();
		int n = s.nextInt();
		boolean flag = m < 0 ? true : false;
		m = flag ? -m : m;
		s.close();
		StringBuilder result = new StringBuilder();
		while (true) {
			int p = m / n;
			int q = m - n * p;
			result.append(numbers.charAt(q));
			if (p == 0) {
				break;
			}
			m = p;
		}
		String output = result.reverse().toString();
		output = flag ? "-" + output : output;
		System.out.println(output);
	}

}
