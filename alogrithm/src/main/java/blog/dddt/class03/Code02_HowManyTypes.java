package blog.dddt.class03;

import java.util.HashSet;

/**
 * @ClassName: Code02_HowManyTypes
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 只由小写字母（a~z）组成的一批字符串，都放在字符类型的数组String[] arr中，
 * 如果其中某两个字符串所含有的字符种类完全一样
 * 就将两个字符串算作一类，比如baacbba和bac就算作一类，返回arr中有多少类
 * 
 **/
public class Code02_HowManyTypes {
    // 思路一：将所有的字符串按照顺序放入map的key中
    public static int kinds1(String[] arr){
        // 如baacbba加工为abc，存入key中
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(doIt(arr[i]));
        }
        return set.size();
    }
    public static String doIt(String str){
        int[] count = new int[26];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - 'a']++;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if(count[i] != 0){
                builder.append((char)(i + 'a'));
            }
        }
        return builder.toString();
    }
    // 思路二：使用位图
    public static int kinds2(String[] arr){
        // 如baacbba加工为abc，存入key中
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(doItWithBitMap(arr[i]));
        }
        return set.size();
    }
    public static int doItWithBitMap(String str){
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';
            ans = ans | (1 << index);
        }
        return ans;
    }
    // for test
    public static String[] getRandomStringArray(int possibilities, int strMaxSize, int arrMaxSize) {
        String[] ans = new String[(int) (Math.random() * arrMaxSize) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = getRandomString(possibilities, strMaxSize);
        }
        return ans;
    }

    // for test
    public static String getRandomString(int possibilities, int strMaxSize) {
        char[] ans = new char[(int) (Math.random() * strMaxSize) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strMaxSize = 10;
        int arrMaxSize = 100;
        int testTimes = 500000;
        System.out.println("test begin, test time : " + testTimes);
        for (int i = 0; i < testTimes; i++) {
            String[] arr = getRandomStringArray(possibilities, strMaxSize, arrMaxSize);
            int ans1 = kinds1(arr);
            int ans2 = kinds2(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");

    }
}
