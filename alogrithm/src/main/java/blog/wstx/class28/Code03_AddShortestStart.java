package blog.wstx.class28;

/**
 * @ClassName: Code03_AddShortestStart
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 最短回文串
 * https://leetcode.cn/problems/shortest-palindrome/description/
 **/
public class Code03_AddShortestStart {
    public static String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int C = -1;
        int R = -1;
        int maxContainsStart = -1; // 左边界在-1的最长半径
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2*C - i],R - i) : 1;
            while (i - pArr[i] >= 0 && i + pArr[i] < str.length){
                if(str[i - pArr[i]] == str[i + pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }
            if(i + pArr[i] > R){
                C = i;
                R = i + pArr[i];
            }
            if(2 * C  == R - 1){
                maxContainsStart = Math.max(maxContainsStart,pArr[i]);
            }
        }
        char[] res = new char[s.length() - (maxContainsStart - 1)];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = str[ ((maxContainsStart - 1)*2 + i*2 + 1)];
        }

        return String.valueOf(res)+s;
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }
    // "aacecaaa" -> "#a#a#c#e#c#a#a#a#" 8

    public static void main(String[] args) {
        System.out.println(shortestPalindrome("aacecaaa"));
    }

}
