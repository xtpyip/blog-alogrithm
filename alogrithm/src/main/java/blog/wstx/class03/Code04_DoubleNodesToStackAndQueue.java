package blog.wstx.class03;

/**
 * @ClassName: Code04_DoubleNodesToStackAndQueue
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 双端节点实现栈与队列
 **/
public class Code04_DoubleNodesToStackAndQueue {

    public static class DoubleNode {
        int value;
        DoubleNode pre;
        DoubleNode next;

        public DoubleNode() {
        }

        public DoubleNode(int data) {
            this.value = data;
        }
    }

    static class MyStack {
        DoubleNode head = null, tail = null;
        int size;

        public MyStack() {
            size = 0;
        }

        public void push(int data) {
            if (head == null) {
                head = new DoubleNode(data);
                tail = head;
            } else {
                DoubleNode cur = new DoubleNode(data);
                cur.next = head.next;
                cur.pre = head;
                head.next = cur;
            }
            size++;
        }

        public int pop() {
            size--;
            DoubleNode cur = head;
            head = head.next;
            head.pre = null;
            cur.next = null;
            return cur.value;
        }

        public int peek() {
            return head.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }


    }

    static class MyQueue {
        DoubleNode head = null, tail = null;
        int size;

        public MyQueue() {
            size = 0;
        }

        public void push(int data) {
            if (head == null) {
                head = new DoubleNode(data);
                tail = head;
            } else {
                DoubleNode cur = new DoubleNode(data);
                cur.next = head.next;
                cur.pre = head;
                head.next = cur;
            }
            size++;
        }
        public int poll(){
            size--;
            DoubleNode cur = tail;
            tail = tail.pre;
            tail.next = null;
            cur.pre = null;
            return cur.value;
        }
        public boolean isEmpty(){
            return size == 0;
        }
        public int peek(){
            return tail.value;
        }
        public int size(){
            return size;
        }

    }

}
