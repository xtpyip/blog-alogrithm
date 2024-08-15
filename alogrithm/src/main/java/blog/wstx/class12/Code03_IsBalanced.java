package blog.wstx.class12;

import blog.wstx.class10.Node;

/**
 * @ClassName: Code03_IsBalanced
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 是否为平衡二叉树
 **/
public class Code03_IsBalanced {
    public static boolean isBalanced1(Node head){
        if(head == null) return true;
        boolean[] ans = new boolean[]{true};
        process(head,ans);
        return ans[0];
    }
    public static int process(Node cur,boolean[] ans){
        if(!ans[0] || cur == null) return -1;
        int lProcess = process(cur.left, ans);
        int rProcess = process(cur.right, ans);
        if(Math.abs(lProcess - rProcess) > 1){
            ans[0] = false;
        }
        return Math.max(lProcess,rProcess) + 1;
    }

    public static class Info{
        public int height;
        public boolean isBalanced;
        public Info(){}
        public Info(int h,boolean b){height=h;isBalanced=b;}
    }
    public static boolean isBalanced2(Node head){
        if(head == null) return true;
        return getInfo(head).isBalanced;
    }
    public static Info getInfo(Node cur){
        if(cur == null) return new Info(0,true);
        Info lInfo = getInfo(cur.left);
        Info rInfo = getInfo(cur.right);
        boolean b = Math.abs(lInfo.height - rInfo.height) <= 1 && lInfo.isBalanced && rInfo.isBalanced;
        int height = Math.max(lInfo.height, rInfo.height) + 1;
        return new Info(height,b);
    }


    /**
     * public static boolean isBalanced(TreeNode head){
     *         if(head == null) return true;
     *         boolean[] ans = new boolean[]{true};
     *         process(head,ans);
     *         return ans[0];
     *     }
     *     public static int process(TreeNode cur,boolean[] ans){
     *         if(!ans[0] || cur == null) return -1;
     *         int lProcess = process(cur.left, ans);
     *         int rProcess = process(cur.right, ans);
     *         if(Math.abs(lProcess - rProcess) > 1){
     *             ans[0] = false;
     *         }
     *         return Math.max(lProcess,rProcess) + 1;
     *     }
     *
     *
     *
     *     public static class Info{
     *         public int height;
     *         public boolean isBalanced;
     *         public Info(){}
     *         public Info(int h,boolean b){height=h;isBalanced=b;}
     *     }
     *     public static boolean isBalanced(TreeNode head){
     *         if(head == null) return true;
     *         return getInfo(head).isBalanced;
     *     }
     *     public static Info getInfo(TreeNode cur){
     *         if(cur == null) return new Info(0,true);
     *         Info lInfo = getInfo(cur.left);
     *         Info rInfo = getInfo(cur.right);
     *         boolean b = Math.abs(lInfo.height - rInfo.height) <= 1 && lInfo.isBalanced && rInfo.isBalanced;
     *         int height = Math.max(lInfo.height, rInfo.height) + 1;
     *         return new Info(height,b);
     *     }
     */
}
