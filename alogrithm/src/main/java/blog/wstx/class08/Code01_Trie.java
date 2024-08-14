package blog.wstx.class08;

/**
 * @ClassName: Code01_Trie
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 前缀树
 **/
// https://leetcode.cn/problems/implement-trie-ii-prefix-tree/
public class Code01_Trie {
    class Trie { // 前缀树-所有的字符串数据放入路径中

        class Node { // 节点
            public int pass; // 通过此路径的数量
            public int end; // 以此节点结束的数量
            public Node[] nexts; // 路径值

            public Node() { // 初始化
                pass = 0;
                end = 0;
                nexts = new Node[26];
            }
        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if(word == null) return;
            char[] chars = word.toCharArray();
            Node p = root;
            p.pass++;
            int index;
            for (char cur : chars) {
                index = cur - 'a';
                if(p.nexts[index] == null){
                    p.nexts[index] = new Node();
                }
                p = p.nexts[index];
                p.pass++;
            }
            p.end++;
        }

        public void erase(String word) {
            if(countWordsStartingWith(word) != 0){
                char[] chars = word.toCharArray();
                Node node = root;
                node.pass--;
                int index;
                for (char cur : chars) {
                    index = cur - 'a';
                    if(--node.nexts[index].pass == 0){
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        public int countWordsEqualTo(String word) {
            if(word == null) return 0;
            char[] chars = word.toCharArray();
            Node node = root;
            int index;
            for (char cur : chars) {
                index = cur - 'a';
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        public int countWordsStartingWith(String pre) {
            if(pre == null) return 0;
            char[] chars = pre.toCharArray();
            Node node = root;
            int index;
            for (char cur : chars) {
                index = cur - 'a';
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }
}
