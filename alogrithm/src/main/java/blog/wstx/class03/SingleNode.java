package blog.wstx.class03;

public class SingleNode {
     public int val;
     public SingleNode next;
     public SingleNode() {}
     public SingleNode(int val) { this.val = val; }
     public SingleNode(int val, SingleNode next) { this.val = val; this.next = next; }
 }