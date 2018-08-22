package sxy.algorithm.exams.huawei;

import java.util.Scanner;

/**
 * ����һ���ַ���������������Ӵ�(���ȳ��򷵻���ǰ����Ǹ�)����������ַ������䳤��.
 * 
 * ����:
 * ATCGATCGLOWELAD
 * 
 * ���:
 * ATCG 4
 * 
 * ע��Ŀǰ�Ľⷨ����ͨ�����еĲ���������ԭ�������.
 * 
 * @author Kevin
 *
 */
public class ExamC {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();

		int max = 0;
		String result = "";

		int first = 0;// ��ʼλ��
		int count = 0;
		for (int p1 = 1; p1 < input.length(); p1++) {
			for (int p2 = 0; p2 < input.length() - p1; p2++) {
				if (input.charAt(p2) == input.charAt(p1 + p2)) {
					count++;
				} else {
					count = 0;
				}
				if (count > max) {
					max = count;
					first = p2 - count + 1;
				}
			}
		}

		if (max > 0) {
			result = input.substring(first, first + max);
		}

		System.out.println(result + " " + max);

	}
}
