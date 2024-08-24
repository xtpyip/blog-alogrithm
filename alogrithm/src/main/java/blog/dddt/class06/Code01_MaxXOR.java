package blog.dddt.class06;

/**
 * @ClassName: Code01_MaxXOR
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 数组中所有数都异或起来的结果，叫做异或和。给定一个数组arr，返回arr的最大子数组异或和
 **/
public class Code01_MaxXOR {

    // 暴力尝试 O(N^2)
    public static int maxXorSubarray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 准备一个前缀异或和数组eor
        // eor[i] = arr[0...i]的异或结果
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        // 生成eor数组，eor[i]代表arr[0..i]的异或和
        for (int i = 1; i < arr.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < arr.length; j++) {
            // 依次尝试arr[0..j]、arr[1..j]..arr[i..j]..arr[j..j]
            for (int i = 0; i <= j; i++) {
                max = Math.max(max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1]);
            }
        }
        return max;
    }
    // 前缀树实现
    // 遍历整个数组，把当前的前缀xor数据放入前缀树中找到当前的最大值
    // 数组结束，则其最大值即为全局子数组最大xor值
    public static int maxXorSubarray2(int[] arr){
        if(arr == null || arr.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        // 0 ~i的整体异或和
        int xor = 0;
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i]; // 0 ~i
            max = Math.max(max, numTrie.maxXor(xor));
            numTrie.add(xor);
        }
        return max;
    }
    public static class Node{
        public Node[] nexts = new Node[2]; // 0 1两个方向
    }
    static class NumTrie{
        // 头节点
        public Node head = new Node();
        public void add(int num){
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                int path = ((num >> move) & 1);
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        public int maxXor(int num){
            // 想要xor最大，一定要使高位为1
            Node cur = head;
            int ans = 0;
            for (int move = 31; move >= 0; move--) {
                // 当前的路径
                int path = (num >> move) & 1;
                // 最好的路径 符号位要为正必须要正，要为负必须要负
                // 数值位要为1必须为0，反之也是
                int best = move == 31 ? path :(path ^ 1);
                // best可能不存在
                best = cur.nexts[best] != null ? best : (best ^ 1);
                // 记录当前位信息
                ans |= (path ^ best) << move;
                cur = cur.nexts[best]; // best要走的路径，可能不是最优
            }
            return ans;
        }
    }
    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int comp = maxXorSubarray1(arr);
            int res = maxXorSubarray2(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}

