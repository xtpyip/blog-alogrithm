package blog.wstx.class27;

import blog.wstx.class12.TreeNode;

import java.util.ArrayList;
import java.util.List;

// 测试链接 : https://leetcode.cn/problems/subtree-of-another-tree/
public class Code02_TreeEqual {
    public static boolean isSubtree(TreeNode big, TreeNode small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }

        List<String> bList = preSerial(big);
        List<String> sList = preSerial(small);
        String[] bs = new String[bList.size()];
        String[] ss = new String[sList.size()];
        for (int i = 0; i < bList.size(); i++) {
            bs[i] = bList.get(i) == null ? "null" : bList.get(i);
        }
        for (int i = 0; i < sList.size(); i++) {
            ss[i] = sList.get(i) == null ? "null" : sList.get(i);
        }
        int index = getIndexOf(bs,ss);
        return index != -1;
    }

    public static ArrayList<String> preSerial(TreeNode head) {
        ArrayList<String> ans = new ArrayList<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(TreeNode head, ArrayList<String> ans) {
        if (head == null) {
            ans.add(null);
            return;
        };
        ans.add(String.valueOf(head.val));
        pres(head.left, ans);
        pres(head.right, ans);
    }

    public static int getIndexOf(String[] str1, String[] str2) {
        if (str1.length < str2.length) return -1;
        int[] next = getNextArray(str2);
        int x = 0, y = 0;
        while (x < str1.length && y < str2.length) {
            if (str1[x].equals(str2[y])) {
                x++;
                y++;
            } else if (next[y] == -1) { // y = 0
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    public static int[] getNextArray(String[] ms) {
        if (ms.length == 1) return new int[]{-1};
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int cnt = 0, i = 2;
        while (i < next.length) {
            if (ms[i - 1].equals(ms[cnt])) {
                next[i++] = ++cnt;
            } else if (cnt > 0) {
                cnt = next[cnt];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }


}
