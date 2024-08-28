package blog.dddt.class10;

/**
 * https://leetcode.cn/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 * 给定一棵搜索二叉树头节点，转化成首尾相接的有序双向链表（节点都有两个方向的指针）
 */
public class Code04_BSTtoDoubleLinkedList {
    // 提交时不要提交这个类
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    // 提交下面的代码
    public static Node treeToDoublyList(Node head) {
        Help info = process(head);
        info.head.left = info.tail;
        info.tail.right = info.head;
        return info.head;
    }
    public static class Help{
        public static Node head;
        public static Node tail;
        public Help(Node h,Node t){head=h;tail=t;}
    }
    public static Help process(Node cur){
        if(cur == null) return new Help(null,null);
        Help l = process(cur.left);
        Help r = process(cur.right);
        if(l.tail != null){
            l.tail.right = cur;
        }
        cur.left = l.tail;
        cur.right = r.head;
        if(r.head != null){
            r.head.left = cur;
        }

        return new Help(l.head != null ? l.head : cur,
                        r.tail != null ? r.tail : cur);
    }

}
