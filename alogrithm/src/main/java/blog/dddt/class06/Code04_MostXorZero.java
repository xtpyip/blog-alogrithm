package blog.dddt.class06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Code04_MostXorZero
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 数组中所有数都异或起来的结果，叫做异或和。
 * 给定一个数组arr，可以任意切分成若干个不相交的子数组。
 * 其中一定存在一种最优方案，使得切出异或和为0的子数组最多，返回这个最多数量
 **/
public class Code04_MostXorZero {
    // 暴力方法
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 生成预处理结构
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        for (int i = 1; i < eor.length; i++) {
            eor[i] = eor[i-1] ^ arr[i];
        }
        // [1,3] = arr[1] ^ arr[2] ^ arr[3] = eor[4] ^ eor[0]
        int ans = process(1,new ArrayList<Integer>(),eor);
        return ans;
    }
    private static int process(int index, List<Integer> part,int[] eor){
        // index这个位置是否做隔断
        if(index == eor.length) {
            // 计算结果
            part.add(index);
            int ans = eorZeroParts(eor,part);
            part.remove(part.size()-1);
            return ans;
        }else{
            part.add(index);
            int p1 = process(index+1,part,eor);
            part.remove(part.size()-1);// 恢复现场
            int p2 = process(index+1,part,eor);// 不做隔断
            return Math.max(p1,p2);
        }
    }
    public static int eorZeroParts(int[] eor, List<Integer> parts) {
        int L = 0;
        int ans = 0;
        for (Integer end : parts) {
            if ((eor[end - 1] ^ (L == 0 ? 0 : eor[L - 1])) == 0) {
                ans++;
            }
            L = end;
        }
        return ans;
    }
    // 时间复杂度O(N)的方法
    public static int mostXor(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // dp[i]为0~i这个区间上最大的子数组划分为0的数量
        int[] dp = new int[arr.length];
        // key: 异或值，value:下标(最右的)
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            if(map.containsKey(eor)){
                dp[i] =  (map.get(eor) == -1 ? 0 : dp[map.get(eor)]) + 1;
            }
            if(i > 0){
                dp[i] = Math.max(dp[i-1],dp[i]);
            }
            map.put(eor,i);
        }
        return dp[dp.length-1];
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
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
        int testTime = 150000;
        int maxSize = 12;
        int maxValue = 5;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = mostXor(arr);
            int comp = comparator(arr);
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

