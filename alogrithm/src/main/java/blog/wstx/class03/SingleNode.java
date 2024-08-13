package blog.wstx.class03;

public class SingleNode {
     int val;
     SingleNode next;
     SingleNode() {}
     SingleNode(int val) { this.val = val; }
     SingleNode(int val, SingleNode next) { this.val = val; this.next = next; }
 }