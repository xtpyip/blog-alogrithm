package blog.wstx.class03;

/**
 * @ClassName: Code03_DeleteGivenValue
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 给你一个链表的头节点 `head` 和一个整数 `val` ，请你删除链表中所有满足 `Node.val == val` 的节点，并返回 **新的头节点** 。
 **/
public class Code03_DeleteGivenValue {
    public SingleNode removeElements(SingleNode head, int val) {
        SingleNode newHead = new SingleNode();
        newHead.next = head;
        SingleNode p = newHead;
        while (p.next != null) {
            if(p.next.val == val){
                p.next = p.next.next;
            }else{
                p = p.next;
            }
        }
        return newHead.next;
    }
}
