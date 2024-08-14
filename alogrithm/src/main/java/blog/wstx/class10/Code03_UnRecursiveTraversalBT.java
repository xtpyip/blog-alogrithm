package blog.wstx.class10;

import java.util.Stack;

/**
 * @ClassName: Code03_UnRecursiveTraversalBT
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 非递归打印二叉树
 **/
public class Code03_UnRecursiveTraversalBT {
    public static void pre(Node head) {
        System.out.print("pre-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }
    public static void in(Node cur) {
        System.out.print("in-order: ");
        if (cur != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || cur != null) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    cur = stack.pop();
                    System.out.print(cur.value + " ");
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }
    public static void pos1(Node head) {
        System.out.print("pos-order: ");
        if(head != null){
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.add(head);
            // 进入s1时使用头 右 左
            while (!s1.isEmpty()) {
                Node cur = s1.pop(); // 头 右 左
                s2.push(cur);
                if(cur.left != null){
                    s1.add(cur.left);
                }
                if(cur.right != null){
                    s1.add(cur.right);
                }
            }
            while (!s2.isEmpty()){
                System.out.print(s2.pop().value+" ");
            }
        }
        System.out.println();
    }

    public static void pos2(Node h) {
        System.out.print("pos-order: ");
        if(h != null){
            Stack<Node> stack = new Stack<>();
            stack.add(h);
            Node cur = null;
            while (!stack.isEmpty()){
                cur = stack.peek();
                if(cur.left != null && cur.left != h && cur.right != h){
                    stack.push(cur.left);
                }else if(cur.right != null && cur.right != h){
                    stack.push(cur.right);
                }else{
                    System.out.print(stack.pop().value +" ");
                    h = cur;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }

}
