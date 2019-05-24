package sxy.algorithm.nowcoder.advanced.chapter05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 回文对问题（https://leetcode.com/problems/palindrome-pairs/）
 * 
 * 给定一个不重复的字符串数组，任意拿出两个串拼接，看能否为回文串，求其中能拼成回文串的所有的组合.
 * 
 * Input: ["abcd","dcba","lls","s","sssll"]
 * 
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * 
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * 
 * 思路：暴力解，一共能拼O(N^2)个串，查每个串是否为回文串.另可以，针对某一个单词，查其各个前缀是否回文串，如果是的话在原数组中查一下其后缀的逆序有没有
 * ，如果有则可以拼接成回文串；后缀同理.可以做到O(N).加速查询前缀是否回文串，可以用Manacher算法.
 * 
 * @author Kevin
 * 
 */
public class PalindromePair {

	public static List<List<Integer>> palindromePairs(String[] words) {
		if (words == null || words.length < 1) {
			return new ArrayList<List<Integer>>();
		}
		HashMap<Integer, Set<Integer>> resMap = new HashMap<>();

		HashMap<String, Integer> wordMap = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			wordMap.put(words[i], i);
		}

		for (int i = 0; i < words.length; i++) {
			String word = words[i];

			// 下面分别处理前缀和后缀子串，涵盖了整串和空串
			int[] pArr = getPArr(word); // 回文半径数组，以O(M)时间复杂度得到，M为word的长度

			for (int k = 0; k < pArr.length; k++) {
				// 前缀是回文
				if (k - pArr[k] == -1) {
					int index = (k + pArr[k]) >> 1;
					Integer match = wordMap.get(reverse(word.substring(index)));
					addRecord(resMap, match, i);// addRecord()方法中会判断i和j是否为空、是否相同，不满足则不会加入！！
				}

				// 后缀是回文
				if (k + pArr[k] == pArr.length) {
					int index = (k - pArr[k] + 1) >> 1;
					Integer match = wordMap.get(reverse(word
							.substring(0, index)));
					addRecord(resMap, i, match);
				}
			}
		}

		List<List<Integer>> res = new ArrayList<>();
		for (Entry<Integer, Set<Integer>> entry : resMap.entrySet()) {
			Integer i = entry.getKey();
			for (Integer j : entry.getValue()) {
				List<Integer> tmp = new ArrayList<>();
				tmp.add(i);
				tmp.add(j);
				res.add(tmp);
			}
		}

		return res;
	}

	// 暴力解
	public static List<List<Integer>> palindromePairs2(String[] words) {
		if (words == null || words.length < 1) {
			return new ArrayList<List<Integer>>();
		}
		List<List<Integer>> res = new ArrayList<>();

		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				if (i == j) {
					continue;
				}
				String cat = words[i] + words[j];
				if (maxLcpsLength(cat) == cat.length()) {
					ArrayList<Integer> tmp = new ArrayList<>();
					tmp.add(i);
					tmp.add(j);
					res.add(tmp);
				}

			}

		}

		return res;
	}

	// 参见Manacher算法
	private static int maxLcpsLength(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] charArr = getDoubleCharArr(str);
		int[] pArr = new int[charArr.length];
		int C = -1;
		int R = -1;
		int max = 1;
		for (int i = 0; i != charArr.length; i++) {
			pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;

			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}

			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			max = Math.max(max, pArr[i]);
		}
		return max - 1;
	}

	// 参见Manacher算法
	private static int[] getPArr(String str) {
		char[] charArr = getDoubleCharArr(str);
		int[] pArr = new int[charArr.length];
		int C = -1;
		int R = -1;
		for (int i = 0; i != charArr.length; i++) {
			pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
		}
		return pArr;
	}

	private static char[] getDoubleCharArr(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}

	private static String reverse(String word) {
		char[] chs = word.toCharArray();
		int l = 0;
		int r = chs.length - 1;
		while (l < r) {
			char tmp = chs[l];
			chs[l++] = chs[r];
			chs[r--] = tmp;
		}

		return String.valueOf(chs);
	}

	private static void addRecord(HashMap<Integer, Set<Integer>> resMap,
			Integer i, Integer j) {
		if (i == null || j == null || i.intValue() == j.intValue()) {// 这地方注意，比较两个Integer的值不能直接i==j
			return;
		}

		Set<Integer> tmp = resMap.get(i);

		if (tmp == null) {
			tmp = new HashSet<>();
		}
		tmp.add(j);
		resMap.put(i, tmp);
	}

	public static void main(String[] args) {
		String[] words = { "abcd", "dcba", "lls", "s", "sssll" };
		System.out.println(palindromePairs2(words));
		System.out.println(palindromePairs(words));
		words = new String[] { "a", "" };
		System.out.println(palindromePairs2(words));
		System.out.println(palindromePairs(words));
		words = new String[] { "abba", "bba" };
		System.out.println(palindromePairs2(words));
		System.out.println(palindromePairs(words));
		words = new String[] { "ajdhjaefgjaebadg", "bjjdac", "ebhc",
				"jghbcfdifacffjajeb", "ebgiabbbeajh", "jcffagcijagjdd",
				"cgcdcbhdbedhh", "eibgeiaghdehgdbgighe", "agajidfchfebdfcgj",
				"fjgaajfchhijgdd", "beibeiafjjbcf", "hiiafi", "haiiijfhedchfj",
				"bcdhehg", "cdcjfdadcciceebdecff", "jhdjabebcieabd",
				"cgbdbfaihegdifgchf", "jidjdedffedbdaii",
				"ccdiahaceiihecafecj", "bahhc", "dcgfbg", "abjjcbadgccibgcebc",
				"aiicgeca", "adbjaecaeafgbdfehg", "affj", "cjbe", "e", "chif",
				"cjij", "dge", "hafdifafifefgjd", "cahbeiiicdb",
				"ghifhddaggjfghjfhjc", "hcdaebaiebbhbgeihfec",
				"idhfceicdehchj", "jjfcce", "fgjidcbijbgaj", "habdbfh",
				"ghdjbheggib", "i", "icbfih", "dfb", "aajhbahgidb",
				"fgfdbgjhdde", "ajcaiggfifafgech", "ejdcbdeadhcfcbhcgh", "bh",
				"d", "eeigjafgffcdiibdgg", "fhhhgbddhibfb",
				"dgehidgjedehgjiicibe", "dibagciedhjjeiibeihg",
				"jffhbehbhdighe", "fa", "hiaebaabh", "aajde",
				"ehifhadchfgcgjaadd", "ahgdjfedcfjc", "hadcbaijfa", "ifegdd",
				"fchdfighceehhed", "achdhaidbhiie", "jhcdfafjhdjg", "ide",
				"ffgfec", "jdjaeii", "gfajdjbdjifdhgj", "ajed", "jhfcb",
				"chhbejjejie", "gbfd", "afbffejcciaafgcacf", "ijfd", "dg",
				"dibdjhcfiea", "gbcdjacjjeeaibaahcj", "igcadcahdcajejjhbdj",
				"agfdhbfeagbbbaijfgbe", "ajifaceefedgjaeja",
				"jhebhabehebffdfih", "icheeehdfjh", "g", "hf", "cijbdefgdjcaj",
				"fjegffdhcjfhefjb", "jihedcbf", "hcghgadaffbhd", "fjbaabffeaf",
				"ffjfbdcafdde", "hebgbhfdd", "aagf", "jhbcid", "aejgjdhbchije",
				"hicjeddbhjijc", "bi", "jh", "ccebbcdiajafgj", "caf",
				"cjhiafe", "iccjgacbeb", "gdjjcgbbgacighd", "achbaiejjgi",
				"aej", "agigfhdfjhfjicfjahh", "jjhhceihcd",
				"jhihjggdajibeeifijbc", "hbdhijijhgbei", "bddfcageiijcbcjgfga",
				"jdeejgbeiaebb", "hffj", "cj", "jhgjeaiidebjafegcic", "fhjbdb",
				"jagffbchb", "deh", "aghbfb", "gdijdjihbebbfhd", "fhii",
				"cjajdaifbfjee", "dij", "bffihiadibhjfibgeb",
				"fbehgdccjhffihafhef", "gidechad", "fbfic", "bgecggbj", "be",
				"bedhcgdhijgiigbibab", "fh", "ee", "f" };
		System.out.println(palindromePairs2(words));
		System.out.println(palindromePairs(words));
	}
}
