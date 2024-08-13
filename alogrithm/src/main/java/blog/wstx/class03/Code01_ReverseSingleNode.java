package blog.wstx.class03;

/**
 * @ClassName: Code01_ReverseSingleNode
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 反转单链表
 */
public class Code01_ReverseSingleNode {


    public SingleNode reverseList(SingleNode head) {
        SingleNode newHead = new SingleNode();
        SingleNode p = head,next = null;
        while (p != null) {
            SingleNode cur = p;
            next = p.next;
            p.next = null;
            cur.next = newHead.next;
            newHead.next = cur;
            p = next;
        }
        return newHead.next;
    }
}
