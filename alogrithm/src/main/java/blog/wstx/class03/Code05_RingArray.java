package blog.wstx.class03;

/**
 * @ClassName: Code05_RingArray
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 环形队列
 **/
public class Code05_RingArray {
    public static class MyQueue {
        private int[] arr;
        private int pushi;// end
        private int polli;// begin
        private int size;
        private final int limit;
        public MyQueue(int limit){
            size = 0;
            arr = new int[limit];
            this.limit = limit;
            pushi = 0;
            polli = 0;
        }

        public void push(int value) {
            if(size == limit) {
                throw new RuntimeException("队列已满");
            }
            arr[pushi] = value;
            pushi = nextIndex(pushi);
            size++;
        }

        public int poll() {
            if(size == 0){
                throw new RuntimeException("队列已空");
            }
            size--;
            int pollValue = arr[polli];
            polli = nextIndex(polli);
            return pollValue;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1: 0;
        }
    }
}
