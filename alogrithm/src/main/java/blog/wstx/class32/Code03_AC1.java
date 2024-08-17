package blog.wstx.class32;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Code03_AC1 {

	public static class Node {
		public int end; // 有多少个字符串以该节点结尾
		public Node fail;
		public Node[] nexts;

		public Node() {
			end = 0;
			fail = null;
			nexts = new Node[26];
		}
	}

	public static class ACAutomation {
		private Node root;

		public ACAutomation() {
			root = new Node();
		}

		// 你有多少个匹配串，就调用多少次insert
		public void insert(String s) {
			char[] str = s.toCharArray();
			Node cur = root;
			int index = 0;
			for (int i = 0; i < str.length; i++) {
				index = str[i] - 'a';
				if (cur.nexts[index] == null) {
					cur.nexts[index] = new Node();
				}
				cur = cur.nexts[index];
			}
			cur.end++;
		}

		public void build() {
			// 宽度优先遍历
			Queue<Node> queue = new LinkedList<>();
			queue.add(root);
			Node cur = null;
			Node cfail = null;
			while (!queue.isEmpty()){
				 cur = queue.poll(); // 每弹出一个，要把它的所有的子节点的fail指针挂好
				for (int i = 0; i < 26; i++) {
					if(cur.nexts[i] != null){ // 找当前孩子的fail指向
						cur.nexts[i].fail = root; // 如果找的到更新，找不到就是root
						cfail = cur.fail; // 从父去找其fail指针
						while (cfail != null){
							if(cfail.nexts[i] != null){ // 存在cfail的指针
								cur.nexts[i].fail = cfail.nexts[i];
								break;
							}
							cfail = cfail.fail;
						}
						queue.add(cur.nexts[i]);
					}
				}
			}
		}

		public int containNum(String content) {
			char[] chars = content.toCharArray();
			Node cur = root;
			int ans = 0;
			int index = 0;
			Node follow = null;
			for (int i = 0; i < chars.length; i++) {
				index = chars[i] - 'a';
				while (cur.nexts[index] == null && cur != root) {
					cur = cur.fail; // 没有当前的通路
				}
				cur = cur.nexts[index] != null ? cur.nexts[index] : root;
				follow = cur; // 要么走到了root,要么还有内容
				while (follow != null){
					if(follow.end == -1){
						break;
					}
					{  // 具体业务
						ans += follow.end;
						follow.end = -1;
					}
					follow = follow.fail;
				}

			}

			return ans;
		}

	}

	public static void main(String[] args) {
		ACAutomation ac = new ACAutomation();
		ac.insert("dhe");
		ac.insert("he");
		ac.insert("c");
		ac.build();
		System.out.println(ac.containNum("cdhe"));
	}

}
