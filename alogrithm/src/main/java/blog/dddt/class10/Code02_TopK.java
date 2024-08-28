package blog.dddt.class10;

import java.util.*;

/**
 * 本题测试链接：https://www.lintcode.com/problem/top-k-frequent-words-ii/
 * Top K Frequent Words II
 * Implement three methods for Topk Class:
 * TopK(k). The constructor.
 * add(word). Add a new word.
 * topk(). Get the current top k frequent words.
 * LintCode题目：https://www.lintcode.com/problem/550/
 */
public class Code02_TopK {
    public static class Node{
        public String str;
        public int times;
        public Node(String s,int t){str=s;times=t;}
    }
    Node[] heap;
    int heapSize;
    private Map<String,Node> strNodeMap; // "abc"->("abc",3)
    private HashMap<Node,Integer> nodeIndexMap; // ("abc",3) -> 2
    private TreeSet<Node> treeSet;
    private NodeHeapComp comp;
    public Code02_TopK(int k){
        heap = new Node[k];
        heapSize= 0 ;
        comp = new NodeHeapComp();
        strNodeMap = new HashMap<>();
        nodeIndexMap = new HashMap<>();
        treeSet = new TreeSet<>((o1,o2) -> {return
                o1.times != o2.times ?
                        (o2.times - o1.times) :
                        o1.str.compareTo(o2.str);});
    }
    public static class NodeHeapComp implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.times != o2.times ? (o1.times - o2.times) : (o2.str.compareTo(o1.str));
        }

    }
    public void add(String str){
        if(heap.length == 0){
            return;
        }
        Node curNode = null;
        int preIndex = -1;
        if(!strNodeMap.containsKey(str)){
            curNode = new Node(str,1);
            strNodeMap.put(str,curNode);
            nodeIndexMap.put(curNode,-1);
        }else{
            curNode = strNodeMap.get(str);
            if (treeSet.contains(curNode)) {
                treeSet.remove(curNode);
            }
            curNode.times++;
            preIndex = nodeIndexMap.get(curNode);
        }
        if(preIndex == -1){
            if(heapSize == heap.length){
                if(comp.compare(heap[0],curNode) < 0){
                    //move and set
                    treeSet.remove(heap[0]);
                    treeSet.add(curNode);
                    nodeIndexMap.put(heap[0],-1);
                    nodeIndexMap.put(curNode,0);
                    heap[0] = curNode;
                    heapify(0,heapSize);
                }
            }else{
                treeSet.add(curNode);
                nodeIndexMap.put(curNode,heapSize);
                heap[heapSize] = curNode;
                heapInsert(heapSize++);
            }
        }else{
            treeSet.add(curNode);
            heapify(preIndex,heapSize);
        }
    }

    public List<String> topK(){
        ArrayList<String> ans = new ArrayList<>();
        for (Node node : treeSet) {
            ans.add(node.str);
        }
        return ans;
    }
    public void heapInsert(int index){
        while(index != 0){
            int parent = (index - 1)/2;
            if(comp.compare(heap[index],heap[parent]) < 0){
                swap(parent,index);
                index = parent;
            }else{
                break;
            }
        }
    }
    public void heapify(int index,int heapSize){
        int l = index * 2 + 1;
        int r = index * 2 + 2;
        int smallest = index;
        while(l < heapSize){
            if(comp.compare(heap[l],heap[index]) < 0){
                smallest = l;
            }
            if(r < heapSize && comp.compare(heap[r],heap[smallest]) < 0){
                smallest = r;
            }
            if(smallest != index){
                swap(smallest,index);
            }else{
                break;
            }
            index = smallest;
            l = index * 2 + 1;
            r = index * 2 + 2;
        }
    }
    public void swap(int i,int j){
        nodeIndexMap.put(heap[i],j);
        nodeIndexMap.put(heap[j],i);
        Node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
