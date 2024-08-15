package blog.wstx.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ClassName: Code08_Prim
 * @version: 1.0
 * @Author: pyipXt
 * @Description: Prim生成最小生成树
 */
public class Code08_Prim {
    // undirected graph only
    public static Set<Edge> primMST(Graph graph) {
        PriorityQueue<Edge> queue = new PriorityQueue<>((a, b) -> {
            return a.weight - b.weight;
        });
        HashSet<Node> set = new HashSet<>();// 已经在生成树中的点
        HashSet<Edge> result = new HashSet<>(); // 解锁的所有邻近边

        for (Node node : graph.nodes.values()) { // 以当前节点出发
            set.add(node);
            for (Edge edge : node.edges) {
                queue.add(edge);
            }
            // 弹出最小的边
            while (!queue.isEmpty()) {
                Edge cur = queue.poll();
                if(!set.contains(cur.to)){ // 新解锁的点
                    set.add(cur.to);
                    result.add(cur);
                    for (Edge nextEdge : cur.to.edges) {
                        queue.add(nextEdge);
                    }
                }
            }
        }
        return result;
    }
    // 请保证graph是连通图
    // graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
    // 返回值是最小连通图的路径之和
    public static int prim(int[][] graph) {
        int size = graph.length;
        int[] distances = new int[size];
        boolean[] visit = new boolean[size];
        visit[0] = true;
        for (int i = 0; i < size; i++) {
            distances[i] = graph[0][i];
        }
        int sum = 0;
        for (int i = 1; i < size; i++) {
            int minPath = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] < minPath) {
                    minPath = distances[j];
                    minIndex = j;
                }
            }
            if (minIndex == -1) {
                return sum;
            }
            visit[minIndex] = true;
            sum += minPath;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] > graph[minIndex][j]) {
                    distances[j] = graph[minIndex][j];
                }
            }
        }
        return sum;
    }
}
