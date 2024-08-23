package blog.dddt.class03;

import java.util.Arrays;

/**
 * @ClassName: Code04_MaxPairNumber
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定一个数组arr，代表每个人的能力值。再给定一个非负数k，如果两个人能力差值正好为k，那么可以凑在一起比赛
 * 一局比赛只有两个人，返回最多可以同时有多少场比赛
 **/
public class Code04_MaxPairNumber {
    // 思路一： 暴力递归解(求arr的全排列，肯定会存在一个序列如
    // 3 5 9 11 14 这种，k为2，有2场比赛)
    public static int maxPairNum1(int[] arr, int k) {
        if (k < 0) {
            return -1;
        }
        return process1(arr, 0, k);
    }

    public static int process1(int[] arr, int index, int k) {
        int ans = 0;
        if (index == arr.length) {
            for (int i = 1; i < arr.length; i += 2) {
                if (arr[i] - arr[i - 1] == k) {
                    ans++;
                }
            }
        } else {
            for (int r = index; r < arr.length; r++) {
                swap(arr, index, r);
                ans = Math.max(ans, process1(arr, index + 1, k));
                swap(arr, index, r);
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    // 思路二：贪心思路
    // 我们知道，如果k=3时，arr中的数据为1 4 4 7 7 11
    // 如果4与7配对，只有两种，1与4，4与7，7与11配对则有3种
    // 我们尽可能匹配两个小的值，防止次小的那个与中间配对，导致数据减少
    public static int maxPairNum2(int[] arr,int K){
        Arrays.sort(arr);
        int ans = 0,n = arr.length;
        int L = 0,R = 0;
        boolean[] flag = new boolean[n]; // false为未访问配对
        while (L < n){
            while (R < n && arr[R] - arr[L] <= K){
                R++;
            }
            // [L,R)，对此区间进行数据配对
            // 5,5,5 ,7,7,9,9,9,9 K=4时，我们要找到==5+4的最左位置
            if(arr[L] + K != arr[R-1]){
                // 不相等，肯定不存在
                L++;
            }else{
                // 相等，可能存在，也可能不存在(flag[i]=false),也可能存在多个
                int pre = Integer.MAX_VALUE;
                int temp = R-1;
                // k=0时，5,5,5,5,5 要找到除L所在位置的最前面未使用的5
                while (temp != L && !flag[temp] && !flag[L] && arr[temp] == arr[L] + K){
                    pre = temp;
                    temp--;
                }
                // 可以配对的当前位置为pre(也可以使用temp==R-1来判断)
                // 进行配对
//                if(pre == Integer.MAX_VALUE){
                if(temp == R - 1){
                    // 不存在可以配对的位置(5,5,5,9,9)
                    L++;
                }else{
                    ans++;
                    flag[L++] = true;
                    flag[pre] = true;
                }
            }
        }
        return ans;
    }
    // 为了测试
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int maxK = 5;
        int testTime = 1000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * (maxLen + 1));
            int[] arr = randomArray(N, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int k = (int) (Math.random() * (maxK + 1));
            int ans1 = maxPairNum1(arr1, k);
            int ans2 = maxPairNum2(arr2, k);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }
}
