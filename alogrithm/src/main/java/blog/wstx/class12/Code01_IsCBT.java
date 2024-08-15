package blog.wstx.class12;

import blog.wstx.class10.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: Code01_IsCBT
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 判断当前树是否为完全二叉树
 **/
public class Code01_IsCBT {

    public static boolean isCBT1(Node head){
        // 使用队列添加每一层
        if(head == null) return true;
        Queue<Node> queue = new LinkedList<>();
        return addQueueAndCheck(head,queue);
    }

    public static boolean addQueueAndCheck(Node head,Queue<Node> queue){
        queue.add(head);
        boolean isExistNull = false;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if(isExistNull && cur != null){
                return false;
            }
            if(cur == null){
                isExistNull = true;
                continue;
            }
            queue.add(cur.left);
            queue.add(cur.right);
        }
        return true;
    }

    static class Info{
        public boolean isFull;
        public boolean isCBT;
        public int height;
        public Info(boolean full, boolean cbt, int h) {
            isFull = full;
            isCBT = cbt;
            height = h;
        }
    }

    public static boolean isCBT2(Node head){
        if(head == null) return true;
        return process(head).isCBT;
    }
    public static Info process(Node head){
        if(head == null) return new Info(true,true,0);
        Info lInfo = process(head.left);
        Info rInfo = process(head.right);
        boolean isFull,isCBT = false;
        isFull = lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height;
        int height = Math.max(lInfo.height, rInfo.height) + 1;
        if(isFull){
            isCBT = true;
        }else{
            if(lInfo.isCBT && rInfo.isCBT){
                if(lInfo.isCBT && rInfo.isFull && lInfo.height == rInfo.height + 1){
                    isCBT = true;
                }
                if(lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height+1){
                    isCBT = true;
                }
                if(lInfo.isFull && rInfo.isCBT && lInfo.height == rInfo.height){
                    isCBT = true;
                }
            }
        }
        return new Info(isFull,isCBT,height);
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
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
