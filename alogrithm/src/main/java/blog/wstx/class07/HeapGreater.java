package blog.wstx.class07;

import java.util.*;

/**
 * @ClassName: HeapGreater
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 加强堆
 **/
public class HeapGreater<T> {
    private List<T> heap; // 元素值
    private Map<T,Integer> indexMap; // 反向索引表
    private int heapSize; // 当前堆的大小（有效值）
    private Comparator<? super T> comparator; // 该泛型的比较器

    public HeapGreater(Comparator<? super T> comparator){
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }
    public void push(T obj){
        heap.add(obj);
        indexMap.put(obj,heapSize);
        heapInsert(heapSize++);
    }
    public void heapInsert(int index){
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0){
            swap(index,(index - 1)/2);
            index = (index - 1)/2;
        }
    }
    public void swap(int i,int j){
        T t1 = heap.get(i);
        T t2 = heap.get(j);
        indexMap.put(t1,j);
        indexMap.put(t2,i);
        heap.set(j,t1);
        heap.set(i,t2);
    }
    public T pop(){
        T popValue = heap.get(0);
        swap(0,heapSize-1);
        indexMap.remove(popValue);
        heap.remove(--heapSize);
        heapify(0);
        return popValue;
    }
    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int best = left + 1 < heapSize && comparator.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
            best = comparator.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            left = index * 2 + 1;
        }
    }
    // 请返回堆上的所有元素
    public List<T> getAllElements() {
        List<T> ans = new ArrayList<>();
        for (T c : heap) {
            ans.add(c);
        }
        return ans;
    }
    // 删除指定元素值
    public void remove(T obj){
        T replace = heap.get(heapSize - 1);
        Integer index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if(obj != replace){
            heap.set(index,replace);
            indexMap.put(replace,index);
            resign(replace);
        }
    }
    public void resign(T obj) {
        Integer index = indexMap.get(obj);
        heapInsert(index);
        heapify(index);
    }
}
