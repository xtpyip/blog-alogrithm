package blog.dddt.class04;

/**
 * @ClassName: Code07_InterleavingString
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定三个字符串s1、s2、s3，请你帮忙验证s3是否是由s1和s2交错组成的
 * https://leetcode.cn/problems/interleaving-string/
 **/
public class Code07_InterleavingString {
    // 思路一：暴力递归 超出时间限制 105 / 106 个通过的测试用例
    public static boolean isInterleave1(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        char[] char3 = s3.toCharArray();
        return process1(char1,char2,char3,0,0,0);
    }
    // 从i1~n1,i2~n2能否从左到右地拼出i3~n3字符串
    public static boolean process1(char[] char1,char[] char2,char[] char3,int i1,int i2,int i3){
        if (i3 == char3.length) {
            return true; // 可以拼出
        }
        if (i1 < char1.length && char1[i1] == char3[i3]) {
            // char1当前字符可拼出char3,尝试
            if(process1(char1, char2, char3, i1 + 1, i2, i3 + 1)) return true;
        }
        if (i2 < char2.length && char2[i2] == char3[i3]) {
            // char2当前字符可拼出char3,尝试
            if(process1(char1,char2,char3,i1,i2+1,i3+1)) return true;
        }
        return false;
    }
    // 思路二：记忆化搜索
    public static boolean isInterleave2(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        char[] char3 = s3.toCharArray();
        // 0代表默认未经历，1代表true,-1代表false
        int[][][] dp = new int[char1.length+1][char2.length+1][char3.length+1];
        return process2(char1,char2,char3,0,0,0,dp);
    }
    // 从i1~n1,i2~n2能否从左到右地拼出i3~n3字符串
    public static boolean process2(char[] char1,char[] char2,char[] char3,int i1,int i2,int i3,int[][][] dp){
        if (i3 == char3.length) {
            return true; // 可以拼出
        }
        if(dp[i1][i2][i3] == -1) return false;
        if(dp[i1][i2][i3] == 1) return true;
        if (i1 < char1.length && char1[i1] == char3[i3]) {
            // char1当前字符可拼出char3,尝试
            if(process2(char1, char2, char3, i1 + 1, i2, i3 + 1,dp)){
                dp[i1][i2][i3] = 1;
                return true;
            }
        }
        if (i2 < char2.length && char2[i2] == char3[i3]) {
            // char2当前字符可拼出char3,尝试
            if(process2(char1,char2,char3,i1,i2+1,i3+1,dp)) {
                dp[i1][i2][i3] = 1;
                return true;
            }
        }
        dp[i1][i2][i3] = -1;
        return false;
    }
    // 思路三：dp dp[i][j]表示0~i,0~j能否表示0~(i+j)的s3
    public static boolean isInterleave3(String str1,String str2,String str3){
        if(str1.length() + str2.length() != str3.length()) return false;
        int N1 = str1.length(),N2 = str2.length();
        boolean[][] dp = new boolean[N1+1][N2+1];
        dp[0][0] = true;
        for (int i = 1; i <= N1; i++) {
            if(str1.charAt(i-1) != str3.charAt(i-1)){
                break;
            }
            dp[i][0] = true;
        }
        for (int i = 1; i <= N2; i++) {
            if(str2.charAt(i-1) != str3.charAt(i-1)){
                break;
            }
            dp[0][i] = true;
        }
        for (int i = 1; i <= N1; i++) {
            for (int j = 1; j <= N2; j++) {
                if(dp[i-1][j] && str1.charAt(i-1) == str3.charAt(i+j-1)){
                    dp[i][j] = true;
                }else if(dp[i][j-1] && str2.charAt(j-1) == str3.charAt(i+j-1)){
                    dp[i][j]=true;
                }
            }
        }
        return dp[N1][N2];
    }


}
