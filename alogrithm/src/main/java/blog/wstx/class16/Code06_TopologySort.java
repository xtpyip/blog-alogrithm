package blog.wstx.class16;

import java.util.*;

/**
 * @ClassName: Code06_TopologySort
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 拓扑排序
 **/
public class Code06_TopologySort {
    // directed graph and no loop
    public static List<Node> sortedTopology(Graph graph) {
        HashMap<Node, Integer> map = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            map.put(node,node.in);
            if(node.in == 0) zeroInQueue.add(node);
        }
        ArrayList<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                map.put(next,map.get(next)-1);
                if(map.get(next) == 0){
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
