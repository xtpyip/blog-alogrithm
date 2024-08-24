package blog.dddt.class05;

import blog.wstx.class12.TreeNode;
import sun.reflect.generics.tree.Tree;

/**
 * @ClassName: Code01_ConstructBinarySearchTreeFromPreorderTraversal
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 前序遍历构造二叉搜索树
 * https://leetcode.cn/problems/construct-binary-search-tree-from-preorder-traversal/description/
 **/
public class Code01_ConstructBinarySearchTreeFromPreorderTraversal {

    // 8 5 1 7 10 12
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder == null || preorder.length < 1) return null;
        return process(preorder,0,preorder.length-1);
    }
    public TreeNode process(int[] preorder,int L,int R){
        if(L > R) return null;
        if(L == R) return new TreeNode(preorder[L]);
        TreeNode root = new TreeNode(preorder[L]);
        int index = L+1;
        while (index <= R && preorder[index] < preorder[L]){
            index++;
        }
        root.left = process(preorder,L+1,index-1);
        root.right = process(preorder,index,R);
        return root;
    }
}
