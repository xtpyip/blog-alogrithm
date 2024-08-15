package blog.wstx.class16;

import java.util.*;

/**
 * @ClassName: Code07_Kruskal
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 小根堆+并查集
 **/

public class Code07_Kruskal {
    //undirected graph only
    // Union-Find Set
    public static class UnionFind {
        // key 某一个节点， value key节点往上的节点
        private HashMap<Node, Node> fatherMap;
        // key 某一个集合的代表节点, value key所在集合的节点个数
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findFather(Node n) {
            Stack<Node> path = new Stack<>();
            while(n != fatherMap.get(n)) {
                path.add(n);
                n = fatherMap.get(n);
            }
            while(!path.isEmpty()) {
                fatherMap.put(path.pop(), n);
            }
            return n;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aDai = findFather(a);
            Node bDai = findFather(b);
            if (aDai != bDai) {
                int aSetSize = sizeMap.get(aDai);
                int bSetSize = sizeMap.get(bDai);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aDai, bDai);
                    sizeMap.put(bDai, aSetSize + bSetSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai, aDai);
                    sizeMap.put(aDai, aSetSize + bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }
    public static Set<Edge> kruskalMST(Graph graph){
        UnionFind uf = new UnionFind();
        uf.makeSets(graph.nodes.values());
        // 从小到大依次把边弹出
        PriorityQueue<Edge> queue = new PriorityQueue<>((a, b) -> {
            return a.weight - b.weight;
        });
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }

        Set<Edge> result = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            if(!uf.isSameSet(cur.from,cur.to)){
                uf.union(cur.from,cur.to);
                result.add(cur);
            }

        }
        return result;
    }
}
