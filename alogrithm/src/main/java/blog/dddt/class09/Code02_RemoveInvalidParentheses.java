package blog.dddt.class09;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。返回所有可能的结果。答案可以按任意顺序返回
 * Leetcode题目：https://leetcode.cn/problems/remove-invalid-parentheses/
 */
public class Code02_RemoveInvalidParentheses {

    public static List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        process(s,ans,0,0,new char[]{'(',')'});
        return ans;
    }
    // modifyIndex <= checkIndex
    // 只查s[checkIndex....]的部分，因为之前的一定已经调整对了
    // 但是之前的部分是怎么调整对的，调整到了哪？就是modifyIndex
    // 比如：
    // ( ( ) ( ) ) ) ...
    // 0 1 2 3 4 5 6
    // 一开始当然checkIndex = 0，modifyIndex = 0
    // 当查到6的时候，发现不对了，
    // 然后可以去掉2位置、4位置的 )，都可以
    // 如果去掉2位置的 ), 那么下一步就是
    // ( ( ( ) ) ) ...
    // 0 1 2 3 4 5 6
    // checkIndex = 6 ，modifyIndex = 2
    // 如果去掉4位置的 ), 那么下一步就是
    // ( ( ) ( ) ) ...
    // 0 1 2 3 4 5 6
    // checkIndex = 6 ，modifyIndex = 4
    // 也就是说，
    // checkIndex和modifyIndex，分别表示查的开始 和 调的开始，之前的都不用管了  par  (  )
    public static void process(String s,List<String> ans,int checkIndex,int deleteIndex,char[] par){
        for(int count = 0,i = checkIndex;i<s.length();i++){
            if(s.charAt(i) == par[0]){
                count++;
            }
            if (s.charAt(i) == par[1]) {
                count--;
            }
            // check的数量
            if(count < 0){
                for (int j = deleteIndex; j <= i; ++j) {
                    if(s.charAt(j) == par[1] && (j == deleteIndex || s.charAt(j - 1) != par[1])){
                        process(s.substring(0, j) + s.substring(j + 1, s.length()),
                                ans,i,j,par); // 从i继续向下查
                    }
                }
                return;
            }
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            process(reversed, ans, 0, 0, new char[] { ')', '(' });
        } else {
            ans.add(reversed);
        }
    }


}
