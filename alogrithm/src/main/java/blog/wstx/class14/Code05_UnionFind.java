package blog.wstx.class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName: Code05_UnionFind
 * @version: 1.0
 * @Author: pyipXt
 * @Description: map实现并查集
 **/
public class Code05_UnionFind {
    public static class UnionFind<V> {
        public HashMap<V,V> father; // 当前V的父节点为什么
        public HashMap<V,Integer> size; // 当前V的节点为根的节点数量
        public UnionFind(List<V> values){
            father = new HashMap<>();
            size = new HashMap<>();
            for (V cur : values) {
                father.put(cur,cur);
                size.put(cur,1);
            }
        }
        // 能过一个节点，往上到不能往上，返回代表节点
        public V findFather(V cur){
            Stack<V> path = new Stack<>();
            while(cur != father.get(cur)){
                path.push(cur);
                cur = father.get(cur);
            }
            while (!path.isEmpty()){
                father.put(path.pop(),cur); // 记录节点的父代表节点
            }
            return cur;
        }
        // 是否为同一个连通集
        public boolean isSameSet(V a,V b){
            return findFather(a) == findFather(b);
        }
        // 合并两个连通集
        public void union(V a,V b){
            V aFather = findFather(a);
            V bFather = findFather(b);
            if(aFather != bFather){
                int aSize = size.get(aFather);
                int bSize = size.get(bFather);
                if(aSize > bSize){
                    father.put(bFather,aFather);
                    size.put(aFather,aSize+bSize);
                    size.remove(bFather);
                }else{
                    father.put(aFather,bFather);
                    size.put(bFather,aSize+bSize);
                    size.remove(aFather);
                }
            }
        }
        // 有多少个集合
        public int sets(){
            return size.size();
        }
    }
}
