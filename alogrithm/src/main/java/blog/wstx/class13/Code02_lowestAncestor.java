package blog.wstx.class13;

import blog.wstx.class10.Node;
import blog.wstx.class12.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: Code02_lowestAncestor
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 最低公共祖先
 **/
// https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/
public class Code02_lowestAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestor1(root,p,q);
    }
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(p == q) return p;
        Map<TreeNode, TreeNode> map = new HashMap<>();
        process(root,map,null);
        return getLowestNode(p,q,map);
    }
    public static void process(TreeNode cur,Map<TreeNode, TreeNode> map,TreeNode pre){
        if(cur == null) return;
        map.put(cur,pre);
        process(cur.left,map,cur);
        process(cur.right,map,cur);
    }
    public static TreeNode getLowestNode(TreeNode p,TreeNode q,Map<TreeNode,TreeNode> map){
        Set<TreeNode> exists = new HashSet<>();
        TreeNode cur = p;
        while (cur != null){
            exists.add(cur);
            cur = map.get(cur);
        }
        cur = q;
        while (!exists.contains(cur)){
            cur = map.get(cur);
        }
        return cur;
    }
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        return process(root,p,q).lowestNode;
    }
    static class Info{
        public boolean isExistP;
        public boolean isExistQ;
        public TreeNode lowestNode;
        public Info(){}
        public Info(boolean p,boolean q,TreeNode lowestNode){isExistP=p;isExistQ=q;this.lowestNode=lowestNode;}
    }
    public static Info process(TreeNode cur,TreeNode p,TreeNode q){
        if(cur == null) return new Info(false,false,null);
        Info lInfo = process(cur.left, p, q);
        Info rInfo = process(cur.right, p, q);
        if(lInfo.lowestNode != null || rInfo.lowestNode != null){
            return lInfo.lowestNode != null ? lInfo : rInfo;
        }
        boolean isP = lInfo.isExistP || rInfo.isExistP || p == cur, isQ = lInfo.isExistQ || rInfo.isExistQ || q == cur;
        TreeNode lowestNode = null;
        if(isQ && isP) return new Info(true,true,cur);
        else return new Info(isP,isQ,lowestNode);
    }
}
