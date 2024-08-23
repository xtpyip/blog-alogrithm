package blog.dddt.class03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @ClassName: Code01_LongestSubstringWithoutRepeatingCharacters
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 求一个字符串中，最长无重复字符子串长度
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 **/
public class Code01_LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        ArrayList<Character> characters = null;
        int l = 0,r = 0;
        int n = s.length();
        int ans = 0;
        while (r < n) {
            while (r < n && !map.containsKey(s.charAt(r))){
                map.put(s.charAt(r),r);
                r++;
            }
            ans = Math.max(ans,r - l);
            characters = new ArrayList<>();
            while (r < n && l <= map.get(s.charAt(r))){
                characters.add(s.charAt(l++));
            }
            for (int i = 0; i < characters.size(); i++) {
                map.remove(characters.get(i));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
