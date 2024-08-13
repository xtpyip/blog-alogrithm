package blog.wstx.class03;

import java.util.Stack;

/**
 * @ClassName: Code07_TwoStacksImplementQueue
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 请你仅使用两个栈实现先入先出队列
 **/
public class Code07_TwoStacksImplementQueue {
    static class MyQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public MyQueue() {stackPush = new Stack<Integer>();stackPop = new Stack<Integer>();}

        // push栈向pop栈倒入数据
        private void pushToPop() {
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
        // pop栈向push栈倒入数据
        private void popToPush() {
            while (!stackPop.empty()) {
                stackPush.push(stackPop.pop());
            }
        }

        public void push(int pushInt) {
            popToPush();
            stackPush.push(pushInt);
        }

        public int pop() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }
        public boolean empty(){
            return stackPop.size() == 0 && stackPop.size() == 0;
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
}
