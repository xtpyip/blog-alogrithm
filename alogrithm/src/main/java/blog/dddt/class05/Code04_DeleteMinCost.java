package blog.dddt.class05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Code04_DeleteMinCost
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
 * 比如 s1 = "abcde"，s2 = "axbc"，s2删掉'x'即可，返回1
 **/
public class Code04_DeleteMinCost {
    // 解法一
    // 求出str2所有的子序列，然后按照长度排序，长度大的排在前面。
    // 然后考察哪个子序列字符串和s1的某个子串相等(KMP)，答案就出来了。
    // 分析：
    // 因为题目原本的样本数据中，有特别说明s2的长度很小。所以这么做也没有太大问题，也几乎不会超时。
    // 但是如果某一次考试给定的s2长度远大于s1，这么做就不合适了。
    public static int deleteMinCost1(String s1,String s2){
        ArrayList<String> lists = new ArrayList<>();
        process(s2,0,"",lists);
        lists.sort((a,b)->{return b.length() - a.length();});
        for (int i = 0; i < lists.size(); i++) {
            String cur = lists.get(i);
            if(s1.contains(cur)){// 存在子串
                return s2.length() - cur.length();
            }
        }
        return s2.length();
    }
    public static void process(String s, int index, String path, List<String> ans){
        if(index == s.length()){
            ans.add(path);
            return;
        }
        process(s,index+1,path,ans);
        process(s,index+1,path+s.charAt(index),ans);
    }
    // 使用了编辑距离
    public static int deleteMinCost2(String s1,String s2){
        if(s1.length() == 0 || s2.length() == 0) return s2.length();
        int ans = Integer.MAX_VALUE;
        char[] str2 = s2.toCharArray();
        for (int start = 0; start < s1.length(); start++) {
            for (int end = start+1; end <= s1.length(); end++) {
                ans = Math.min(ans,distance(str2,s1.substring(start,end).toCharArray()));
            }
        }

        return ans == Integer.MAX_VALUE ? s2.length() : ans;
    }
    public static int distance(char[] x, char[] y) {
        if (x.length < y.length) {
            return Integer.MAX_VALUE;
        }
        int N = x.length;
        int M = y.length;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        // dp[i][j]表示前缀长度
        for (int i = 1; i <= N; i++) {
            dp[i][0] = i;
        }
        for (int xlen = 1; xlen <= N; xlen++) {
            for (int ylen = 1; ylen <= Math.min(M, xlen); ylen++) {
                if (dp[xlen - 1][ylen] != Integer.MAX_VALUE) {
                    dp[xlen][ylen] = dp[xlen - 1][ylen] + 1;
                }
                if (x[xlen - 1] == y[ylen - 1] && dp[xlen - 1][ylen - 1] != Integer.MAX_VALUE) {
                    dp[xlen][ylen] = Math.min(dp[xlen][ylen], dp[xlen - 1][ylen - 1]);
                }
            }
        }
        return dp[N][M];
    }
    public static int minCostX(String s1,String s2){
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        // dp[i][j] 的含义 :
        // s2中前缀i长度的字符串，至少删除多少个字符可以变成 s1中前缀j长度字符串的 后缀串
        int[][] dp = new int[c2.length + 1][c1.length + 1];
        // s2前缀0长度，删掉0个字符，可以变成s1前缀任意长度字符串的 后缀串
        // 所以dp[0][....] = 0，所以省略了
        for (int i = 1; i <= c2.length; i++) {
            dp[i][0] = i;
            for (int j = 1; j <= c1.length; j++) {
                // 如果 c2[i-1] == c1[j-1]
                // 可能性1 要么 c2[i-1] 和 c1[j-1] 进行匹配
                // 问题回到 dp[i-1][j-1]
                // 可能性2 要么 c2[i-1] 和 c1[j-1] 不进行匹配
                // s2[0...i] 依然删除 s2[i] 问题回到 dp[i-1][j] + 1
                // dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j] + 1);
                // 实际上
                // 只需要考虑可能性1
                // 例
                // abc 去匹配 abc.....c
                // 删掉 .....c 和 删掉 c..... 是一个意思
                if(c2[i-1] == c1[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    // 如果 c2[i-1] != c1[j-1]
                    // c2[i-1] 和 c1[j-1] 不进行匹配
                    // s2[0...i] 删除 s2[i] 问题回到 dp[i-1][j] + 1
                    dp[i][j] = dp[i-1][j] + 1;
                }
            }
        }
        int ans = dp[c2.length][0];
        for (int j = 1; j <= c1.length; j++) {
            ans = Math.min(ans,dp[c2.length][j]);
        }
        return ans;
    }

    // 为了测试
    public static String generateRandomString(int l, int v) {
        int len = (int) (Math.random() * l);
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(str);
    }

    // 为了测试
    public static void main(String[] args) {
        int str1Len = 200;
        int str2Len = 100;
        int v = 5;
        int testTime = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            String str1 = generateRandomString(str1Len, v);
            String str2 = generateRandomString(str2Len, v);
//            int ans3 = deleteMinCost1(str1, str2);
            // 同学提供的解法，最优解
            int ans3 = deleteMinCost2(str1, str2);
            int ansX = minCostX(str1, str2);
            if (ans3 != ansX) {
                System.out.println("出错了!");
                System.out.println(str1);
                System.out.println(str2);
                System.out.println(ans3);
                System.out.println(ansX);
                break;
            }else{
                System.out.println("完成"+(i+1));
            }
        }
        System.out.println("测试结束");
    }
}
