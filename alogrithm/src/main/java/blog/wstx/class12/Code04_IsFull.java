package blog.wstx.class12;

import blog.wstx.class10.Node;

/**
 * @ClassName: Code04_IsFull
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 是否为满二叉树
 **/
public class Code04_IsFull {

    public static boolean isFull1(Node head){
        if(head == null) return true;
        Info1 info = process1(head);
        return (1 << (info.height)) - 1 == info.nodes;
    }
    public static class Info1 {
        public int height;
        public int nodes;

        public Info1(int h, int n) {
            height = h;
            nodes = n;
        }
    }
    public static Info1 process1(Node head){
        if(head == null) return new Info1(0,0);
        Info1 lInfo = process1(head.left);
        Info1 rInfo = process1(head.right);
        return new Info1(Math.max(lInfo.height,rInfo.height)+1, lInfo.nodes+ rInfo.nodes + 1);
    }

    public static boolean isFull2(Node head){
        if(head == null) return true;
        Info2 info = process2(head);
        return info.isFull;
    }
    public static class Info2 {
        public int height;
        public boolean isFull;

        public Info2(int h, boolean n) {
            height = h;
            isFull = n;
        }
    }
    public static Info2 process2(Node head){
        if(head == null) return new Info2(0,true);
        Info2 lInfo = process2(head.left);
        Info2 rInfo = process2(head.right);
        return new Info2(Math.max(lInfo.height,rInfo.height)+1, lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height);
    }
    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }

}

