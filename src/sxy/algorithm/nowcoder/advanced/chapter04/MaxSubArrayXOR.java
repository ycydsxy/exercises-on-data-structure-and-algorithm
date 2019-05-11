package sxy.algorithm.nowcoder.advanced.chapter04;

/**
 * 子数组中最大的异或和
 * 
 * 给定一个数组arr，你可以任意把arr分成很多不相容的子数组，求分出来的子数组中，最大的异或和是多少？其中，数组的异或和定义为：数组中所有的数异或起来，
 * 得到的结果叫做数组的异或和，比如数组{3,2,1}的异或和是，3^2^1 = 0.要求时间复杂度为O(N).
 * 
 * 思路：
 * 
 * 1.O(N^2)的算法比较好想：用下标s和e来表示子数组，一共只有O(N^2)个。遍历每个子数组求该子数组的异或和即可得到结果，
 * 遍历过程中使用前缀异或和数组help来加速该过程，即xor(s~e)=help[e]^help[s-1]，其中help[i]=xor(0~i).
 * 
 * 2.上述过程也可理解为，求出以i位置结尾的子数组的最大异或和，然后i从0到N-1遍历。求以i结尾的子数组的最大异或和是将0~i、1~i、...、i-1~
 * i所有的异或和都求出来，故而需要O(i)的时间。若求以i结尾的子数组的最大异或和能加速到O(1)的时间， 则整个算法的速度即为O(N)。
 * 
 * 3.求以i结尾的子数组的最大异或和问题可变形为求help[i]^help[s-1]的最大值，s从0开始遍历到i，help[-1]=0。
 * 即一确定数和某数的异或结果最大
 * ，可采用贪心算法：二进制下，使得异或出来的符号位为0（正数），高位尽量为1。利用前缀树可以实现快速查找，且整数一共就32位，
 * 故而这种查找的时间复杂度为O(1)。
 * 
 * @author ZuoChengyun & Kevin
 * 
 */
public class MaxSubArrayXOR {
	public static class Node {
		public Node[] nexts = new Node[2];// 只有两条路，二进制0和1，nexts[0]==null则表示没有走向0的路
	}

	public static class NumTrie {
		public Node head = new Node();

		// 把num的二进制信息由高到低加入前缀树
		public void add(int num) {
			Node cur = head;
			for (int move = 31; move >= 0; move--) {// 整数是32位的
				int path = ((num >> move) & 1);
				if (cur.nexts[path] == null) {
					cur.nexts[path] = new Node();
				}
				cur = cur.nexts[path];
			}
		}

		// 之前的前缀异或和都有，选出和num异或之后，可能的最大值返回
		public int maxXor(int num) {
			Node cur = head;
			int res = 0;
			for (int move = 31; move >= 0; move--) {// 整数是32位的
				int path = (num >> move) & 1;// num的当前位
				int best = move == 31 ? path : (path ^ 1);// 期待选的路
				best = cur.nexts[best] != null ? best : (best ^ 1);// 实际选的路
				res |= (path ^ best) << move;// 设置答案的每一位
				cur = cur.nexts[best];// 继续往下走
			}
			return res;
		}

	}

	// 使用前缀树，O(N)
	public static int maxXorSubarray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int prefixXOR = 0;// 前缀异或和help[i]
		NumTrie numTrie = new NumTrie();// numTrie前缀树里记录着0,0~0,0~1,...,0~i-1范围全部的前缀异或和
		numTrie.add(0);// help[-1]=0;
		for (int i = 0; i < arr.length; i++) {
			prefixXOR ^= arr[i];
			max = Math.max(max, numTrie.maxXor(prefixXOR));// maxXor方法求以i结尾的子数组的最大异或和
			numTrie.add(prefixXOR);
		}
		return max;
	}

	// 暴力解，O(N^2)
	public static int comparator(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int eor = 0;
			for (int j = i; j < arr.length; j++) {
				eor ^= arr[j];
				max = Math.max(max, eor);
			}
		}
		return max;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random())
					- (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 30;
		int maxValue = 50;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int res = maxXorSubarray(arr);
			int comp = comparator(arr);
			if (res != comp) {
				succeed = false;
				printArray(arr);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
