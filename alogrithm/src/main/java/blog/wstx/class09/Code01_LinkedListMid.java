package blog.wstx.class09;

import blog.wstx.class03.SingleNode;

import java.util.ArrayList;

/**
 * @ClassName: LinkedListMid
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 求中点位置
 **/
public class Code01_LinkedListMid {

//    1)输入链表头节点，奇数长度返回中点，偶数长度返回上中点 midOrUpMidNode
    public static SingleNode midOrUpMidNode(SingleNode head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        SingleNode fast = head;
        SingleNode slow = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

//    2)输入链表头节点，奇数长度返回中点，偶数长度返回下中点 midOrDownMidNode
    public static SingleNode midOrDownMidNode(SingleNode head){
        if(head == null || head.next == null) return head;
        // 两个及以上节点
        SingleNode fast = head.next;
        SingleNode slow = head.next;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
//  3)输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个 midOrUpMidPreNode
    // 1 2 3 -> 1
    // 1 2 3 4 -> 1
    public static SingleNode midOrUpMidPreNode(SingleNode head){
        if(head == null || head.next == null) return null;
        SingleNode fast = head.next;
        SingleNode slow = head;
        while (fast.next != null && fast.next.next != null && fast.next.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
//    4)输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个 midOrDownMidPreNode
    // 1 -> null
    // 1 2 -> 1
    // 1 2 3 -> 1
    // 1 2 3 4 -> 2
    // 1 2 3 4 5 -> 2
    public static SingleNode midOrDownMidPreNode(SingleNode head){
        if(head == null || head.next == null) return null;
        SingleNode fast = head.next;
        SingleNode slow = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    public static SingleNode right1(SingleNode head) {
        if (head == null) {
            return null;
        }
        SingleNode cur = head;
        ArrayList<SingleNode> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 1) / 2);
    }

    public static SingleNode right2(SingleNode head) {
        if (head == null) {
            return null;
        }
        SingleNode cur = head;
        ArrayList<SingleNode> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2);
    }

    public static SingleNode right3(SingleNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        SingleNode cur = head;
        ArrayList<SingleNode> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 3) / 2);
    }

    public static SingleNode right4(SingleNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        SingleNode cur = head;
        ArrayList<SingleNode> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 2) / 2);
    }

    public static void main(String[] args) {
        SingleNode test = null;
        test = new SingleNode(0);
        test.next = new SingleNode(1);
        test.next.next = new SingleNode(2);
        test.next.next.next = new SingleNode(3);
        test.next.next.next.next = new SingleNode(4);
        test.next.next.next.next.next = new SingleNode(5);
        test.next.next.next.next.next.next = new SingleNode(6);
        test.next.next.next.next.next.next.next = new SingleNode(7);
        test.next.next.next.next.next.next.next.next = new SingleNode(8);

        SingleNode ans1 = null;
        SingleNode ans2 = null;

        ans1 = midOrUpMidNode(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        ans1 = midOrDownMidNode(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        ans1 = midOrUpMidPreNode(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        ans1 = midOrDownMidPreNode(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        test.next.next.next.next.next.next.next.next.next = new SingleNode(9);

        System.out.println("======================================");
        ans1 = midOrUpMidNode(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        ans1 = midOrDownMidNode(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        ans1 = midOrUpMidPreNode(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        ans1 = midOrDownMidPreNode(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

    }

}
