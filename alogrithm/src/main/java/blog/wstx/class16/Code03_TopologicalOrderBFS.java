package blog.wstx.class16;

import java.util.*;

/**
 * @ClassName: Code03_TopologicalOrderBFS
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 图的宽度优先遍历
 **/
public class Code03_TopologicalOrderBFS {
    public class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }
    // 提交下面的
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        // 得到所有的节点
        // 获取所有的节点的入度
        HashMap<DirectedGraphNode, Integer> map = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            map.put(cur, 0);
        }
        for (DirectedGraphNode cur : graph) {
            for (DirectedGraphNode next : cur.neighbors) {
                map.put(next, map.get(next) + 1);
            }
        }
        // 入度为0
        Queue<DirectedGraphNode> zeroQueue = new LinkedList<>();
        for (DirectedGraphNode node : map.keySet()) {
            if(map.get(node) == 0){
                zeroQueue.add(node);
            }
        }
        // 入度为0出队
        while (!zeroQueue.isEmpty()) {
            DirectedGraphNode cur = zeroQueue.poll();
            ans.add(cur);
            for (DirectedGraphNode next : cur.neighbors) {
                int in = map.get(next) - 1;
                map.put(next,in);
                if(in == 0){
                    zeroQueue.add(next);
                }
            }
        }
        return ans;
    }
}
