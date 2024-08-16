package blog.wstx.class17;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Code03_PrintAllPermutations
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 所有的全排列
 **/
public class Code03_PrintAllPermutations {
    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        ArrayList<Character> rest = new ArrayList<Character>();
        for (char cha : str) {
            rest.add(cha);
        }
        String path = "";
        process1(rest, path, ans);
        return ans;
    }
    public static void process1(List<Character> list,String path,List<String> ans){
        if(list.isEmpty()){
            ans.add(path);
        }else{
            int N = list.size();
            for (int i = 0; i < N; i++) {
                char cur = list.get(i);
                list.remove(i);
                process1(list,path+cur,ans);
                list.add(i,cur); // 还原现场
            }
        }
    }

    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process2(str, 0, ans);
        return ans;
    }
    public static void process2(char[] str,int i,List<String> ans){
        if(i == str.length){
            ans.add(String.valueOf(str));
        }else{
            for (int j = i; j < str.length; j++) {
                swap(str,i,j);
                process2(str,i+1,ans);
                swap(str,i,j); // 恢复现场
            }
        }
    }
    public static void swap(char[] chars,int i,int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    public static List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process3(str, 0, ans);
        return ans;
    }
    public static void process3(char[] str,int i,List<String> ans){
        if(i == str.length){
            ans.add(String.valueOf(str));
        }else{
            boolean[] visited = new boolean[256];
            for (int j = i; j < str.length; j++) {
                if(!visited[j]){
                    visited[str[i]] = true;
                    swap(str,i,j);
                    process3(str,i+1,ans);
                    swap(str,i,j);// 恢复现场
                }
            }
        }
    }
    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutation2(s);
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans3 = permutation3(s);
        for (String str : ans3) {
            System.out.println(str);
        }

    }

}
