package blog.dddt.class06;

/**
 * @ClassName: Code02_MaximumXorOfTwoNumbers
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 数组中所有数都异或起来的结果，叫做异或和。
 * 给定一个数组arr，想知道arr中哪两个数的异或结果最大，返回最大的异或结果
 * Leetcode题目：https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/
 **/
public class Code02_MaximumXorOfTwoNumbersInAnArray {
    public int findMaximumXOR(int[] nums) {
        XorTrie trie = new XorTrie();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            trie.add(nums[i]);
            ans = Math.max(ans,trie.maxXor(nums[i]));
        }
        return  ans;
    }
    public class Node{
        public Node[] nexts = new Node[2];
    }
    public class XorTrie{
        private Node head = new Node();
        public void add(int newNum){
            Node cur = head;
            for (int i = 31; i >= 0; i--) {
                int path = (newNum >> i) & 1;
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }
        public int maxXor(int num){
            Node cur = head;
            int ans = 0;
            for (int move = 31; move >= 0; move--) {
                // 当前通路
                int path = (num >> move) & 1;
                int best = move == 31 ? path : (path ^ 1); //最好通路
                // 最好通路是否存在,存在则是最好，不存在走另一条
                best = cur.nexts[best] != null ? best : (best ^ 1);
                ans |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }
}
