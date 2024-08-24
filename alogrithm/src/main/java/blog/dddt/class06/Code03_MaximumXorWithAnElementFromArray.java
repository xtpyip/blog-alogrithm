package blog.dddt.class06;

/**
 * @ClassName: Code03_MaximumXorWithAnElementFromArray
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定一个非负整数组成的数组nums。另有一个查询数组queries，其中queries[i]=[xi, mi]
 * 第i个查询的答案是xi和任何nums数组中不超过mi的元素按位异或（XOR）得到的最大值
 * 换句话说，答案是max(nums[j] XOR xi)，其中所有j均满足nums[j]<= mi。如果nums中的所有元素都大于mi，最终答案就是-1
 * 返回一个整数数组answer作为查询的答案，其中answer.length==queries.length且answer[i]是第i个查询的答案
 * Leetcode题目：https://leetcode.com/problems/maximum-xor-with-an-element-from-array/
 **/
public class Code03_MaximumXorWithAnElementFromArray {
    // 前缀树实现，路径值还是有2个，0与1。
    // 添加一个节点值，此节点记录最小经过此节点的值
    public int[] maximizeXor(int[] nums, int[][] queries) {
        XorTrie trie = new XorTrie();
        for (int i = 0; i < nums.length; i++) {
            trie.add(nums[i]);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = trie.maxXor(queries[i][0],queries[i][1]);
        }
        return ans;
    }
    public class Node{
        public Node[] nexts = new Node[2];
        public Integer minValue = Integer.MAX_VALUE;
    }
    public class XorTrie{
        private Node head = new Node();
        public void add(int newNum){
            Node cur = head;
            head.minValue = Math.min(head.minValue,newNum);
            for (int i = 30; i >= 0; i--) {
                int path = (newNum >> i) & 1;
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
                cur.minValue = Math.min(cur.minValue,newNum);
            }
        }
        public Integer maxXor(int x,int m){
            Node cur = head;
            if(cur.minValue > m) {
                return -1;
            }
            int ans = 0;
            for (int i = 30; i >= 0; i--) {
                // 当前通路
                int path = (x >> i) & 1;
                int best = path ^ 1;
                // best可能不存在，也有可能best的minValue>num
                best = cur.nexts[best] == null || cur.nexts[best].minValue > m ?
                        (best ^ 1) : best;
                ans |= (path ^ best) << i;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Code03_MaximumXorWithAnElementFromArray f = new Code03_MaximumXorWithAnElementFromArray();
        int[] ans = f.maximizeXor(new int[]{0, 1, 2, 3, 4},
                new int[][]{
                        {3, 1},
                        {1, 3},
                        {5, 6}
                });
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
}
