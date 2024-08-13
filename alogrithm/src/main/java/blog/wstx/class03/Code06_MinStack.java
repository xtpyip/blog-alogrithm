package blog.wstx.class03;

import java.util.Stack;

/**
 * @ClassName: Code06_MinStack
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 最小栈
 **/
public class Code06_MinStack {
    static class MinStack {
        Stack<Integer> dataStack;
        Stack<Integer> minStack;
        public MinStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            dataStack.push(val);
            if(!minStack.isEmpty()){
                minStack.push(Math.min(minStack.peek(),val));
            }else{
                minStack.push(val);
            }
        }

        public void pop() {
            dataStack.pop();
            minStack.pop();
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
