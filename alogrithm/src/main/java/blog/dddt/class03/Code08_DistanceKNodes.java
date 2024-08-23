package blog.dddt.class03;

import blog.wstx.class10.Node;

import java.util.*;

/**
 * @ClassName: Code08_DistanceKNodes
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定三个参数，二叉树的头节点head，树上某个节点target，正数K。
 * 从target开始，可以向上走或者向下走，返回与target的距离是K的所有节点
 **/
public class Code08_DistanceKNodes {
    // 思路：由当前节点可向上向下进行宽度优先获取，直到第K层结束
    public static List<Node> distanceKNodes(Node root, Node target, int K) {
        HashMap<Node, Node> parents = new HashMap<>();
        parents.put(root,null);
        fill(root,parents);
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        visited.add(target);
        queue.add(target);
        ArrayList<Node> ans = new ArrayList<>();
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if(level == K){
                    ans.add(cur);
                }
                // 向上
                if(parents.get(cur) != null && !visited.contains(parents.get(cur))){
                    queue.add(parents.get(cur));
                    visited.add(parents.get(cur));
                }
                // 向下
                if(cur.left != null && !visited.contains(cur.left)){
                    visited.add(cur.left);
                    queue.add(cur.left);
                }
                if(cur.right != null && !visited.contains(cur.right)){
                    queue.add(cur.right);
                    visited.add(cur.right);
                }
            }
            level++;
            if(level > K){
                break;
            }
        }
        return ans;
    }
    public static void fill(Node cur, Map<Node,Node> parents){
        if(cur == null) return;
        // 当前节点不为null，则设置当前节点的所有子节点的父
        if(cur.left != null){
            parents.put(cur.left,cur);
            fill(cur.left,parents);
        }
        if(cur.right != null){
            parents.put(cur.right,cur);
            fill(cur.right,parents);
        }
    }
    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans = distanceKNodes(root, target, K);
        for (Node o1 : ans) {
            System.out.println(o1.value);
        }

    }

}

