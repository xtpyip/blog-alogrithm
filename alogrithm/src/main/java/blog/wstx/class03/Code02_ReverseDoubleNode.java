package blog.wstx.class03;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Code01_ReverseDoubleNode
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 反转单链表
 */
public class Code02_ReverseDoubleNode {


    public static DoubleNode reverseList(DoubleNode head) {
        DoubleNode newHead = new DoubleNode();
        DoubleNode p = head,next;
        while (p != null) {
            DoubleNode cur = p;
            next = p.next;
            p.next = null;
            if (next != null) {
                next.pre = null;
            }
            cur.next = newHead.next;
            if (newHead.next != null) {
                newHead.next.pre = cur;
            }
            newHead.next = cur;
            p = next;
        }
        return newHead.next;
    }
    public static DoubleNode testReverseDoubleList(DoubleNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<DoubleNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        DoubleNode pre = list.get(0);
        int N = list.size();
        for (int i = 1; i < N; i++) {
            DoubleNode cur = list.get(i);
            cur.pre = null;
            cur.next = pre;
            pre.pre = cur;
            pre = cur;
        }
        return list.get(N - 1);
    }

    // for test
    public static DoubleNode generateRandomDoubleList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
        DoubleNode pre = head;
        while (size != 0) {
            DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
            pre.next = cur;
            cur.pre = pre;
            pre = cur;
            size--;
        }
        return head;
    }

    // for test
    public static List<Integer> getLinkedListOriginOrder(DoubleNode head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.val);
            head = head.next;
        }
        return ans;
    }
    // for test
    public static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.val);
            head = head.next;
        }
        return ans;
    }

    // for test
    public static boolean checkDoubleListReverse(List<Integer> origin, DoubleNode head) {
        DoubleNode end = null;
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.val)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        for (int i = 0; i < origin.size(); i++) {
            if (!origin.get(i).equals(end.val)) {
                return false;
            }
            end = end.pre;
        }
        return true;
    }

    // for test
    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 100000;
        System.out.println("test begin!");
        for (int i = 0; i < testTime; i++) {


            DoubleNode node3 = generateRandomDoubleList(len, value);
            List<Integer> list3 = getDoubleListOriginOrder(node3);
            node3 = reverseList(node3);
            if (!checkDoubleListReverse(list3, node3)) {
                System.out.println("Oops3!");
            }

            DoubleNode node4 = generateRandomDoubleList(len, value);
            List<Integer> list4 = getDoubleListOriginOrder(node4);
            node4 = testReverseDoubleList(node4);
            if (!checkDoubleListReverse(list4, node4)) {
                System.out.println("Oops4!");
            }

        }
        System.out.println("test finish!");

    }






}
