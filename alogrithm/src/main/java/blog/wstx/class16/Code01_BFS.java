package blog.wstx.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName: Code01_BFS
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 图的宽度优先遍历（队列）
 **/
public class Code01_BFS {
    // 从node出发，进行宽度优先遍历
    public static void bfs(Node start) {
        if(start == null) return;
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.value);
            for (Node next : poll.nexts) {
                if(!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

}
