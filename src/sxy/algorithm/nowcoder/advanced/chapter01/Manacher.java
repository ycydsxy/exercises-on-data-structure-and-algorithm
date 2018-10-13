package sxy.algorithm.nowcoder.advanced.chapter01;

/**
 * 求一个字符串中的最长回文子串的长度.
 * 
 * @author Kevin
 * 
 */
public class Manacher {

	// 极度暴力解。对str的子串依次判断是否为回文串，若是则更新最大回文长度。O(N^3)=N*1+(N-1)*2+...+[N-(N-1)]*N
	public static int maxLcpsLength1(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}

		int res = 0;
		char[] charArr = str.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			for (int j = i; j < charArr.length; j++) {
				if (isPalindrome(charArr, i, j)) {
					res = Math.max(res, j - i + 1);
				}
			}
		}
		return res;
	}

	// 判断从begin到end区域是否是回文的，前提为end>=begin。
	private static boolean isPalindrome(char[] charArr, int begin, int end) {
		boolean flag = true;
		while (end > begin) {
			if (charArr[begin++] != charArr[end--]) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	// 暴力解。中心移位，往外扩，移动中心之前更新最大长度。O(N^2)=(0+1+2+3+...+N)*2
	public static int maxLcpsLength2(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}

		int res = 0;
		char[] doubleCharArr = getDoubleCharArr(str);// 处理奇数和偶数往外扩不一致的问题
		for (int center = 0; center < doubleCharArr.length; center++) {
			int left = center - 1;
			int right = center + 1;
			int temp = 1;
			while (left >= 0 && right <= doubleCharArr.length - 1) {
				if (doubleCharArr[left--] == doubleCharArr[right++]) {
					temp += 2;
				} else {
					break;
				}
			}
			res = Math.max(res, temp / 2);
		}
		return res;
	}

	// 将str转化为字符数组，并在每个字符之间添加#(为了处理奇数和偶数往外扩不一致的问题)
	private static char[] getDoubleCharArr(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}

	// Manacher算法。O(N)
	// 当地回文中心i从开始扫，则有以下情况：
	// 1) 若i在R外，则暴力往外扩(无优化)-->推高R
	// 2) 若i在R内部，则潜台词是i肯定在C和R之间。设i'是i关于C的镜像点，则i'肯定在L和C之间，且i'的回文区域之前已经求得。
	// 2a) 若i'的回文区域左边界未越过L，潜台词是该区域完全包含在[L,R]内部，则i的回文半径就是i'的回文半径；
	// 2b) 若i'的回文区域左边界越过了L，则i的回文半径是i到R；
	// 2c) 若i'的回文区域左边界和L重合，则i的回文半径至少是i到R，继续暴力扩；
	public static int maxLcpsLength3(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] charArr = getDoubleCharArr(str);// 处理奇数和偶数往外扩不一致的问题
		int[] pArr = new int[charArr.length];// 回文半径数组，pArr[i]表示i位置的回文半径
		int C = -1;// 整体最右回文右边界所对应的中心
		int R = -1;// 整体最右回文右边界
		int max = 1;
		for (int i = 0; i != charArr.length; i++) {// i为当前回文中心
			pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;
			// 若i>=R则命中1)规则，需要暴力扩，当前位置回文半径至少是1
			// 若i<R则命中2)规则，这里是将3条合并起来写

			// 对于1)和2c)情况，还需要暴力扩
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			// i位置的回文半径已求得，看是否更新整体回文右边界和中心
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			max = Math.max(max, pArr[i]);
		}
		return max - 1;// 从处理后串的回文半径到原字符串的最大回文长度
	}

	public static void main(String[] args) {
		String str1 = "abc1234321ab";
		System.out.println(maxLcpsLength1(str1));
		System.out.println(maxLcpsLength2(str1));
		System.out.println(maxLcpsLength3(str1));
		String str2 = "abc12344321cbad";
		System.out.println(maxLcpsLength1(str2));
		System.out.println(maxLcpsLength2(str2));
		System.out.println(maxLcpsLength3(str2));
	}

}
