package blog.wstx.class03;

public class DoubleNode {
     int val;
     DoubleNode next;
     DoubleNode pre;
     DoubleNode() {}
     DoubleNode(int val) { this.val = val; }
     DoubleNode(int val, DoubleNode next) { this.val = val; this.next = next; }
 }