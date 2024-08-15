package blog.wstx.class12;

import blog.wstx.class10.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Code02_IsBST
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 是否为搜索二叉树
 **/
public class Code02_IsBST {


    public static boolean isValidBST1(Node head){
        if(head == null) return true;
        List<Integer> list = new ArrayList<>();
        in(head,list);
        int pre = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Integer cur = list.get(i);
            if(cur > pre){
                pre = cur;
            }else{
                return false;
            }
        }
        return true;
    }
    public static void in(Node cur,List<Integer> list){
        if(cur == null) return;
        in(cur.left,list);
        list.add(cur.value);
        in(cur.right,list);
    }

    public static boolean isValidBST2(Node head){
        if(head == null) return true;
        return process(head).isBST;
    }
    static class Info{
        public int min;
        public int max;
        public boolean isBST;
        public Info(){}
        public Info(int i,int a,boolean bst){min=i;max=a;isBST=bst;}
    }
    public static Info process(Node cur){
        if(cur == null) return null;
        Info lInfo = process(cur.left);
        Info rInfo = process(cur.right);
        int max,min;
        boolean isBST = false;
        if(lInfo == null && rInfo == null) return new Info(cur.value,cur.value,true);
        if(lInfo != null && rInfo != null){
            isBST = cur.value > lInfo.max && cur.value < rInfo.min && lInfo.isBST && rInfo.isBST;
            max = Math.max(lInfo.max, rInfo.max);
            min = Math.min(lInfo.min, rInfo.min);
        }else{
            if(lInfo != null){ // rInfo = null
                isBST = cur.value > lInfo.max && lInfo.isBST;
                min = lInfo.min;
                max = cur.value;
            }else{ // lInfo = null
                isBST = cur.value < rInfo.min && rInfo.isBST;
                min = cur.value;
                max = rInfo.max;
            }
        }
        return new Info(min,max,isBST);
    }
    /**
     *     public static boolean isValidBST(TreeNode head){
     *         if(head == null) return true;
     *         List<Integer> list = new ArrayList<>();
     *         in(head,list);
     *         int pre = list.get(0);
     *         for (int i = 1; i < list.size(); i++) {
     *             Integer cur = list.get(i);
     *             if(cur > pre){
     *                 pre = cur;
     *             }else{
     *                 return false;
     *             }
     *         }
     *         return true;
     *     }
     *     public static void in(TreeNode cur,List<Integer> list){
     *         if(cur == null) return;
     *         in(cur.left,list);
     *         list.add(cur.val);
     *         in(cur.right,list);
     *     }
     */

    /**
     * public static boolean isValidBST(TreeNode head){
     *         if(head == null) return true;
     *         return process(head).isBST;
     *     }
     *     static class Info{
     *         public int min;
     *         public int max;
     *         public boolean isBST;
     *         public Info(){}
     *         public Info(int i,int a,boolean bst){min=i;max=a;isBST=bst;}
     *     }
     *     public static Info process(TreeNode cur){
     *         if(cur == null) return null;
     *         Info lInfo = process(cur.left);
     *         Info rInfo = process(cur.right);
     *         int max,min;
     *         boolean isBST = false;
     *         if(lInfo == null && rInfo == null) return new Info(cur.val,cur.val,true);
     *         if(lInfo != null && rInfo != null){
     *             isBST = cur.val > lInfo.max && cur.val < rInfo.min && lInfo.isBST && rInfo.isBST;
     *             max = Math.max(lInfo.max, rInfo.max);
     *             min = Math.min(lInfo.min, rInfo.min);
     *         }else{
     *             if(lInfo != null){ // rInfo = null
     *                 isBST = cur.val > lInfo.max && lInfo.isBST;
     *                 min = lInfo.min;
     *                 max = cur.val;
     *             }else{ // lInfo = null
     *                 isBST = cur.val < rInfo.min && rInfo.isBST;
     *                 min = cur.val;
     *                 max = rInfo.max;
     *             }
     *         }
     *         return new Info(min,max,isBST);
     *     }
     */

}
