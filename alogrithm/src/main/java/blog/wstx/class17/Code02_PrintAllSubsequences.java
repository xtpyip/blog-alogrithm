package blog.wstx.class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: Code02_PrintAllSubsquences
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 子序列集合
 **/
public class Code02_PrintAllSubsequences {
    // s -> "abc" ->
    public static List<String> subs(String s) {
        char[] strs = s.toCharArray();
        String path = "";
        ArrayList<String> ans = new ArrayList<>();
        process1(strs,0,path,ans);
        return ans;
    }
    public static void process1(char[] str,int i,String path,List<String> ans){
        if(i == str.length){
            ans.add(path);
            return;
        }
        process1(str,i+1,path,ans);
        process1(str,i+1,path+str[i],ans);
    }

    public static List<String> subsNoRepeat(String s) { // 不剪枝
        char[] strs = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(strs, 0, set, path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }
    public static void process2(char[] strs, int i, Set<String> set,String path){
        if(i == strs.length){
            set.add(path);
            return;
        }
        process2(strs,i+1,set,path);
        process2(strs,i+1,set,path+strs[i]);
    }

    public static void main(String[] args) {
        String test = "acccc";
        List<String> ans1 = subs(test);
        List<String> ans2 = subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");

    }

}
