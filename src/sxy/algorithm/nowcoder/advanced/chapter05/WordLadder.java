package sxy.algorithm.nowcoder.advanced.chapter05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 字符梯子（https://leetcode.com/problems/word-ladder/）
 * 
 * 给两个单词beginWord和endWord，并给一个单词集wordList，希望从beginWord变成endWord，每次变一个字母，
 * 且每次变完的结果都在wordList中.问从beginWord变到endWord最少的步数是多少?
 * 
 * @author Kevin
 * 
 */
public class WordLadder {

	public static List<List<String>> findLadders(String beginWord,
			String endWord, List<String> wordList) {
		wordList.add(beginWord);
		// 生成邻居表
		HashMap<String, ArrayList<String>> nexts = getNexts(wordList);
		// 从start开始，进行宽度优先遍历，求每一个字符串到start的最短距离
		HashMap<String, Integer> distances = getDistances(beginWord, nexts);
		// 深度优先遍历收集答案
		LinkedList<String> pathList = new LinkedList<>();
		List<List<String>> res = new ArrayList<>();
		getShortestPaths(beginWord, endWord, nexts, distances, pathList, res);
		return res;

	}

	private static void getShortestPaths(String beginWord, String endWord,
			HashMap<String, ArrayList<String>> nexts,
			HashMap<String, Integer> distances, LinkedList<String> pathList,
			List<List<String>> res) {
		// TODO Auto-generated method stub

	}

	private static HashMap<String, Integer> getDistances(String beginWord,
			HashMap<String, ArrayList<String>> nexts) {
		// TODO Auto-generated method stub
		return null;
	}

	private static HashMap<String, ArrayList<String>> getNexts(
			List<String> wordList) {
		// TODO Auto-generated method stub
		return null;
	}

}
