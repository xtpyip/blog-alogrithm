package blog.dddt.class02;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Code03_ReceiveAndPrintOrderLine
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 已知一个消息流会不断地吐出整数1~N，但不一定按照顺序依次吐出，如果上次打印的序号为i， 那么当i+1出现时
 * 请打印i+1及其之后接收过的并且连续的所有数，直到1~N全部接收并打印完，请设计这种接收并打印的结构
 **/
public class Code03_ReceiveAndPrintOrderLine {
    static class Node{
        private String info;
        public Node next;
        public Node(String str){info=str;}
    }
    public static class MessageBox{
        private Map<Integer,Node> headMap;
        private Map<Integer,Node> tailMap;
        private int wait;

        public MessageBox(){
            wait = 1;
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
        }
        public void receive(int x,String info){
            if (x < 1) {
                return;
            }
            Node node = new Node(info);
            headMap.put(x,node);
            tailMap.put(x,node);
            if(tailMap.containsKey(x-1)){
                tailMap.get(x-1).next = node;
                tailMap.remove(x-1);
                headMap.remove(x);
            }
            if(headMap.containsKey(x+1)){
                node.next = headMap.get(x+1);
                headMap.remove(x+1);
                tailMap.remove(x);
            }
            if(x == wait){
                print();
            }
        }
        public void print(){
            Node node = headMap.get(wait);
            headMap.remove(node);
            while (node != null) {
                System.out.print(node.info +" ");
                node = node.next;
                wait++;
            }
            tailMap.remove(wait - 1);
            System.out.println();
        }

    }
    public static void main(String[] args) {
        // MessageBox only receive 1~N
        MessageBox box = new MessageBox();
        // 1....
        System.out.println("这是2来到的时候");
        box.receive(2,"B"); // - 2"
        System.out.println("这是1来到的时候");
        box.receive(1,"A"); // 1 2 -> print, trigger is 1
        box.receive(4,"D"); // - 4
        box.receive(5,"E"); // - 4 5
        box.receive(7,"G"); // - 4 5 - 7
        box.receive(8,"H"); // - 4 5 - 7 8
        box.receive(6,"F"); // - 4 5 6 7 8
        box.receive(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3
        box.receive(9,"I"); // 9 -> print, trigger is 9
        box.receive(10,"J"); // 10 -> print, trigger is 10
        box.receive(12,"L"); // - 12
        box.receive(13,"M"); // - 12 13
        box.receive(11,"K"); // 11 12 13 -> print, trigger is 11

    }

}
