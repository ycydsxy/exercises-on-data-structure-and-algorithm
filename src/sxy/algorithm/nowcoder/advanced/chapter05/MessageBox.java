package sxy.algorithm.nowcoder.advanced.chapter05;

import java.util.HashMap;

/**
 * 消息盒子
 * 
 * 在网络中从A到B发信息包，每个包中有一个编号（自然数）和内容，B收到的包的编号不一定是顺序的，B最开始期望收到编号为1的包，
 * B在接收包时检查是不是当前期望的包，如果是的话就打印从该编号开始连续的已接受的包的内容，如果不是就缓存着.设包都不会
 * 丢失，且B的缓存足够大，设计一种结构来实现上述过程.
 * 
 * @author ZuoChengyun & Kevin
 * 
 */
public class MessageBox {

	class Node {
		int num;
		String content;
		Node next;

		public Node(int num, String content) {
			super();
			this.num = num;
			this.content = content;
		}

	}

	private HashMap<Integer, Node> headMap = new HashMap<>();
	private HashMap<Integer, Node> tailMap = new HashMap<>();
	private int lastPrint = 0;

	public void receive(int num, String content) {
		if (num < 1) {
			return;
		}

		Node node = new Node(num, content);
		headMap.put(num, node);
		tailMap.put(num, node);
		if (headMap.containsKey(num - 1)) {
			tailMap.get(num - 1).next = node;
			headMap.remove(num);
			tailMap.remove(num - 1);
		}

		if (headMap.containsKey(num + 1)) {
			node.next = headMap.get(num + 1);
			headMap.remove(num + 1);
			tailMap.remove(num);
		}

		if (headMap.containsKey(lastPrint + 1)) {
			print();
		}
	}

	private void print() {
		Node cur = headMap.get(++lastPrint);
		headMap.remove(lastPrint);
		while (cur != null) {
			System.out.print(cur.content+" ");
			cur = cur.next;
			lastPrint++;
		}
		tailMap.remove(--lastPrint);
		System.out.println();
	}

	public static void main(String[] args) {
		MessageBox box = new MessageBox();
		// 'I have a dream!'
		box.receive(5, "!");
		box.receive(2, "have");
		box.receive(1, "I");
		box.receive(4, "dream");
		box.receive(3, "a");
	}
}
