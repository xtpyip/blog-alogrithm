package blog.wstx.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName: Code08_TwoQueuesImplementStack
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 两个队列实现栈
 **/
public class Code08_TwoQueuesImplementStack {

    static class MyStack {
        Queue<Integer> in ,out;
        public MyStack() {
            in = new LinkedList<>();
            out = new LinkedList<>();
        }

        public void push(int x) {
            in.add(x);
        }

        public int pop() {
            while (!in.isEmpty()) {
                Integer cur = in.poll();
                if(in.isEmpty()){
                    return cur;
                }
                out.add(cur);
            }
            while (!out.isEmpty()){
                Integer cur = out.poll();
                if(out.isEmpty()){
                    return cur;
                }
                in.add(cur);
            }
            return -1;
        }

        public int top() {
            while (!in.isEmpty()) {
                Integer cur = in.poll();
                out.add(cur);
                if(in.isEmpty()){
                    return cur;
                }
            }
            while (!out.isEmpty()){
                Integer cur = out.poll();
                in.add(cur);
                if(out.isEmpty()){
                    return cur;
                }
            }
            return -1;
        }

        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }
    };

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
}
