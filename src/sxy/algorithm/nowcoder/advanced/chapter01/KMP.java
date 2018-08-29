package sxy.algorithm.nowcoder.advanced.chapter01;

/**
 * KMP算法
 * 
 * 给定两个字符串s和m，求s是否被m包含，包含则返回s中m的起始下标，否则返回-1。
 * 
 * @author Kevin
 * 
 */
public class KMP {

	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] str1 = s.toCharArray();
		char[] str2 = m.toCharArray();
		int x = 0;
		int y = 0;
		int[] next = getNextArray(str2);// 获取m的next数组

		while (x < str1.length && y < str2.length) {
			if (str1[x] == str2[y]) {
				x++;
				y++;
			} else if (y == 0) {// 潜台词是str1[x]!=str2[y]，且y已经回到了0
				x++;
			} else {// y向前跳
				y = next[y];
			}
		}

		return y == str2.length ? x - y : -1;
	}

	// 返回一个数组，该数组中index为i的元素的含义为,m串中m[i]之前的子串中最长的前缀和后缀相等的数目
	private static int[] getNextArray(char[] m) {
		if (m == null) {
			return null;
		}
		if (m.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[m.length];
		next[0] = -1;

		int i = 2;
		int cn = 0;// 表示i-1位置的next数组的值（也表示需要和i-1位置比较的位置）， next[1]=0为初始值

		while (i < next.length) {
			if (m[i - 1] == m[cn]) {// 两者相同
				next[i++] = ++cn;// next[i]=next[i-1]+1;i++;cn++;的简写
			} else if (cn == 0) {// 两者不同，且cn再不能往前跳了，已经到0位置了
				next[i++] = 0;
			} else {// 两者不同，则cn往前跳，进入下一轮比较
				cn = next[cn];
			}
		}

		return next;
	}

	public static void main(String[] args) {
		String str = "abcabcababacccababaccc";
		String match = "ababa";
		System.out.println(getIndexOf(str, match));
	}

}
