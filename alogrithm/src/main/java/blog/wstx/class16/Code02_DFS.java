package blog.wstx.class16;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @ClassName: Code02_DFS
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 深度优先遍历 栈
 **/
public class Code02_DFS {
    // 从node出发，进行深度优先遍历
    public static void dfs(Node start) {
        if(start == null) return;
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.add(start);
        set.add(start);
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
