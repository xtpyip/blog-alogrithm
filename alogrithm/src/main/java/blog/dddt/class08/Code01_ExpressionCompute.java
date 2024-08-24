package blog.dddt.class08;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @ClassName: Code01_ExpressionCompute
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定一个字符串表达式str，str表示一个公式，公式里可能有整数、加减乘除符号和左右括号。返回公式的计算结果
 * 难点在于括号可能嵌套很多层，str="48*((70-65)-43)+8*1"，返回-1816。str="3+1*4"，返回7。str="3+(1*4)"，返回7。
 * 1，可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查
 * 2，如果是负数，就需要用括号括起来，比如"4*(-3)"但如果负数作为公式的开头或括号部分的开头，则可以没有括号，比如"-3*4"和"(-3*4)"都是合法的
 * 3，不用考虑计算过程中会发生溢出的情况。
 * 测试链接 : https://leetcode.cn/problems/basic-calculator-iii/
 **/
public class Code01_ExpressionCompute {
    // 思路：使用一个栈，一个是数据栈，里面的数据默认为+法，所有的-法操作可以作为一个整体负数
    public static int calculate(String str) {
        return f(str.toCharArray(),0)[0];
    }

    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值，长度为2的数组
    // 0) 负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    public static int[] f(char[] chars,int i){
        int[] bra = null;
        LinkedList<String> queue = new LinkedList<>();
        int cur = 0;
        // 从当前i开始
        while(i < chars.length && chars[i] != ')'){
            if(chars[i] >= '0' && chars[i] <= '9'){
                // 数字
                cur = cur * 10 + chars[i++] - '0';
            }else if(chars[i] != '('){ // 运算符号 第一个就是+或者-,我们在之前加一个0
                // 保证queue中数据为n1 s1 n2 s2...
                // n为数据，s为符号
                addNum(queue,cur,chars[i++]);
            }else{
                // 遇见( 要开启新的数据栈
                bra = f(chars,i+1);
                cur = bra[0];
                i = bra[1] + 1;
            }
        }
        // 每次都是添加一个数据+一个符号位
        addNum(queue,cur,'+');
        return new int[]{getAns(queue),i};
    }

    public static void addNum(LinkedList<String> queue, int num, char op) {
        if (!queue.isEmpty() && (queue.peekLast().equals("*") || queue.peekLast().equals("/"))) {
            String top = queue.pollLast();
            int pre = Integer.valueOf(queue.pollLast());
            num = top.equals("*") ? (pre * num) : (pre / num);
        }
        queue.addLast(String.valueOf(num));
        queue.addLast(String.valueOf(op));
    }
    public static int getAns(LinkedList<String> queue){
        if(queue.isEmpty()) return 0;
        Integer pre = Integer.valueOf(queue.pollFirst());
        while (!queue.isEmpty()) {
            String signal = queue.pollFirst();
            int num = Integer.valueOf(queue.pollFirst());
            pre = "+".equals(signal) ? pre + num : pre - num;
        }
        return pre;
    }



}
