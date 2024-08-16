package blog.wstx.class17;

import java.util.HashSet;
import java.util.Stack;

/**
 * @ClassName: Code01_Hanoi
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 汉诺塔实现
 **/
public class Code01_Hanoi {

    // 把num个数据从左移动到中间
    public static void leftToMid(int num){
        if(num == 0){ // 终止条件
            return;
        }
        // 上面num-1移动到右边
        leftToRight(num-1);
        System.out.print("move left to mid\t");// 最后一个从左移动到中间
        rightToMid(num-1);// 把右边的移动到中间
    }
    public static void leftToRight(int num){
        if(num == 0){ // 终止条件
            return;
        }
        leftToMid(num-1);
        System.out.print("move left to right\t");
        midToRight(num-1);
    }
    public static void rightToLeft(int num){
        if(num == 0){ // 终止条件
            return;
        }
        rightToMid(num-1);
        System.out.print("move right to left\t");
        midToLeft(num-1);
    }
    public static void rightToMid(int num){
        if(num == 0){ // 终止条件
            return;
        }
        rightToLeft(num-1);
        System.out.print("move right to mid\t");
        leftToMid(num-1);
    }
    public static void midToLeft(int num){
        if(num == 0){ // 终止条件
            return;
        }
        midToRight(num-1);
        System.out.print("move mid to left\t");
        rightToLeft(num-1);
    }
    public static void midToRight(int num){
        if(num == 0){ // 终止条件
            return;
        }
        midToLeft(num-1);
        System.out.print("move mid to right\t");
        leftToRight(num-1);
    }

    public static void func(String from,String to,String other,int n){
        if(n == 0) return;
        func(from,other,to,n-1);
        System.out.print("move " + from + " to "+to+"\t");
        func(other,to,from,n - 1);
    }

    // 迭代
    public static class Record {
        public int level;
        public String from;
        public String to;
        public String other;

        public Record(int l, String f, String t, String o) {
            level = l;
            from = f;
            to = t;
            other = o;
        }
    }
    // 迭代
    public static void hanoi3(int n){
        if(n < 1) return;
        Stack<Record> stack = new Stack<>();
        HashSet<Record> records = new HashSet<>();
        stack.add(new Record(n,"left","right","mid"));
        while (!stack.isEmpty()) {
            Record cur = stack.pop();
            if(cur.level == 1){
                System.out.print("move " + cur.from + " to "+cur.to+"\t");
            }else{
                if(!records.contains(cur)){
                    records.add(cur);
                    stack.push(cur);
                    stack.push(new Record(cur.level-1,cur.from, cur.other, cur.to));
                }else{
                    // 如果当前任务加入过左子树的任务
                    // 说明此时已经是第二次弹出了！
                    System.out.print("move " + cur.from + " to "+cur.to+"\t");
                    stack.push(new Record(cur.level - 1,cur.other, cur.to, cur.from));
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println();
        leftToRight(n);
        System.out.println();
        func("left","right","mid",n);
        System.out.println();
        hanoi3(n);
    }    







}
