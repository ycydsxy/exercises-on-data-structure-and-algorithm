package sxy.job.bytedance.interview;

import java.util.Scanner;

/**
 * 假设有编号从1到n的n首歌，现在需要随机一个歌单出来，使得每首歌出现且只出现一次，且在任意位置出现的概率相同.
 * 
 * @author Kevin
 * 
 */
public class RandomSong {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		s.close();
		int[] oriSongs = new int[n];
		for (int i = 0; i < oriSongs.length; i++) {
			oriSongs[i] = i;
		}
		printArray(oriSongs);
		getRandomSongs(oriSongs);
		printArray(oriSongs);
	}

	private static void getRandomSongs(int[] oriSongs) {
		if (oriSongs.length < 1) {
			throw new IllegalArgumentException();
		}
		int size = oriSongs.length;

		for (int i = 0; i < oriSongs.length; i++) {
			int index = (int) (Math.random() * size--);
			swap(oriSongs, index, size);
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static void printArray(int[] arr) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < arr.length - 1; i++) {
			sb.append(arr[i]).append(" ,");
		}
		sb.append(arr[arr.length - 1]).append("]");
		System.out.println(sb);
	}
}
