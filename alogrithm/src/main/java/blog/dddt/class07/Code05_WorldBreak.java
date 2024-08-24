package blog.dddt.class07;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: Code05_WorldBreak
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 假设所有字符都是小写字母，大字符串是str，arr是去重的单词表
 * 每个单词都不是空字符串且可以使用任意次。使用arr中的单词有多少种拼接str的方式，返回方法数。
 **/
public class Code05_WorldBreak {
    // 注意，题目为拼接
    public static int ways1(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        HashSet<String> set = new HashSet<>();
        for (String s : arr) {
            set.add(s);
        }
        return process1(str,0,set);
    }
    // index~str.length有多少种方法拼成
    public static int process1(String str, int index, Set<String> set){
        if(index == str.length()) return 1;
        int ans = 0;
        for (int end = index;end < str.length();end++){
            String subStr = str.substring(index, end+1);// 前闭后开
            if(set.contains(subStr)){
                // 可以去往下面
                ans += process1(str,end+1,set);
            }
        }
        return ans;
    }

    public static int ways2(String str,String[] arr){

        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        HashSet<String> set = new HashSet<>();
        for (String s : arr) {
            set.add(s);
        }
        int N = str.length();
        int[] dp = new int[N + 1];
        // dp[i]表示str[i...]有由arr拼几种拼法
        dp[N] = 1; // 什么都不选拼成空串只有一种
        for (int i = N - 1; i >= 0; i--) {
            for (int end = i; end < N; end++) {
                if(set.contains(str.substring(i,end+1))){
                    dp[i] += dp[end+1];
                }
            }

        }
        return dp[0];
    }

    public static class Node{
        boolean isEnd;
        Node[] nexts = new Node[26];
        public Node(boolean flag){isEnd = flag;}
    }

    // 生成前缀树
    public static class Trie{
        private Node head = new Node(false);
        public void insert(String str){
            char[] chars = str.toCharArray();
            Node cur = head;
            for (int i = 0; i < chars.length; i++) {
                int path = (chars[i] - 'a');
                if(cur.nexts[path] == null){
                    cur.nexts[path] = new Node(false);
                }
                cur = cur.nexts[path];
            }
            cur.isEnd = true;
        }
    }
    public static int ways3(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        Trie trie = new Trie();
        for (String s : arr) {
            trie.insert(s);
        }
        return process3(str.toCharArray(),0,trie.head);
    }
    public static int process3(char[] chars,int index,Node node){
        if(index == chars.length) return 1;
        int ways = 0;
        Node cur = node;
        for (int end = index; end < chars.length; end++) {
            int path = chars[end] - 'a';
            if(cur.nexts[path] == null){
                break;
            }
            cur = cur.nexts[path];
            if(cur.isEnd){
                ways += process3(chars,end+1,node);
            }
        }
        return ways;
    }

    public static int ways4(String str,String[] arr){
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        Node head = new Node(false);
        for (String s : arr) {
            char[] chars = s.toCharArray();
            Node node = head;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if(node.nexts[index] == null){
                    node.nexts[index] = new Node(false);
                }
                node = node.nexts[index];
            }
            node.isEnd = true;
        }
        char[] strs = str.toCharArray();
        int N = strs.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            Node cur = head;
            for (int end = i; end < N; end++) {
                int path = strs[end] - 'a';
                if(cur.nexts[path] == null){
                    break;
                }
                cur = cur.nexts[path];
                if(cur.isEnd){
                    dp[i] += dp[end+1];
                }
            }
        }
        return dp[0];
    }


    // 以下的逻辑都是为了测试
    public static class RandomSample {
        public String str;
        public String[] arr;

        public RandomSample(String s, String[] a) {
            str = s;
            arr = a;
        }
    }

    // 随机样本产生器
    public static RandomSample generateRandomSample(char[] candidates, int num, int len, int joint) {
        String[] seeds = randomSeeds(candidates, num, len);
        HashSet<String> set = new HashSet<>();
        for (String str : seeds) {
            set.add(str);
        }
        String[] arr = new String[set.size()];
        int index = 0;
        for (String str : set) {
            arr[index++] = str;
        }
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < joint; i++) {
            all.append(arr[(int) (Math.random() * arr.length)]);
        }
        return new RandomSample(all.toString(), arr);
    }

    public static String[] randomSeeds(char[] candidates, int num, int len) {
        String[] arr = new String[(int) (Math.random() * num) + 1];
        for (int i = 0; i < arr.length; i++) {
            char[] str = new char[(int) (Math.random() * len) + 1];
            for (int j = 0; j < str.length; j++) {
                str[j] = candidates[(int) (Math.random() * candidates.length)];
            }
            arr[i] = String.valueOf(str);
        }
        return arr;
    }

    public static void main(String[] args) {
        char[] candidates = { 'a', 'b' };
        int num = 20;
        int len = 4;
        int joint = 5;
        int testTimes = 30000;
        boolean testResult = true;
        for (int i = 0; i < testTimes; i++) {
            RandomSample sample = generateRandomSample(candidates, num, len, joint);
            int ans1 = ways1(sample.str, sample.arr);
            int ans2 = ways2(sample.str, sample.arr);
            int ans3 = ways3(sample.str, sample.arr);
            int ans4 = ways4(sample.str, sample.arr);
            if (ans1 != ans2 || ans3 != ans4 || ans2 != ans4) {
                testResult = false;
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                break;
            }
        }
        System.out.println(testTimes + "次随机测试是否通过：" + testResult);
    }

}

