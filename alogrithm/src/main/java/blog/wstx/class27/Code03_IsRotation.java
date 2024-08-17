package blog.wstx.class27;

/**
 * @ClassName: Code03_IsRotation
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 是否为旋转字符串
 *
 * https://leetcode.cn/problems/rotate-array/description/
 **/
public class Code03_IsRotation {
    public static boolean isRotation(String b,String a){
        if(b.length() != a.length()) return false;
        int n = b.length();
        char[] dChars = new char[n * 2];
        for (int i = 0; i < n; i++) {
            dChars[i] = b.charAt(i);
            dChars[n+i] = b.charAt(i);
        }
        char[] aChar = a.toCharArray();
        return indexOf(dChars,aChar) != -1;
    }
    public static int indexOf(char[] a,char[] b) {
        int x = 0, y = 0;
        int[] next = getNext(b);
        while (x < a.length && y < b.length) {
            if (a[x] == b[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == b.length ? x - y : -1;
    }
    public static int[] getNext(char[] b){
        if(b.length == 1) return new int[]{-1};
        int[] next = new int[b.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2,cnt = 0;
        while (i < b.length){
            if(b[cnt] == b[i-1]){
                next[i++] = ++cnt;
            }else if(cnt > 0){
                cnt = next[cnt];
            }else{
                next[i++] = 0;
            }
        }
        return next;
    }
    public static void main(String[] args) {
        String str1 = "yunzuocheng";
        String str2 = "zuochengyun";
        System.out.println(isRotation(str1, str2));

    }
}
