package writeExam.pdd;

import com.sun.org.apache.bcel.internal.generic.FALOAD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: Code03_SwapMIn
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 原始数据为 2 1 3 4 3
 * 给你一个默认的值 X 5 让你把数据变成从左到右不递减的数组
 * 你可以使用X与原始数组中小于X的值进行交换
 * 如： 5<->3     2 1 3 4 5  X=3
 *     3<->1     2 3 3 4 5  X=1
 * 一共交换两次
 *
 **/
public class Code03_SwapMIn {

    public static int getMinSwap(int[] arr,int X){
        int N = arr.length;
        int[] maxs = new int[N];
        boolean[] status = new boolean[N]; // status[i] = true表示0~i这个范围是非递减的
        int maxIndex = 0;
        boolean flag = true; // 是否本来就是非递减数组
        for (int i = 0; i < N; i++) {
            if(arr[i] >= arr[maxIndex]){
                maxIndex = i;
            }else{
                flag = false;
            }
            status[i] = flag;
            maxs[i] = maxIndex;
        }
        if(flag){ // 已经是非递减数组了
            return 0;
        }
        return getMinSwap(arr, maxs, X,status);
    }
    public static int getMinSwap(int[] arr,int[] maxs,int X,boolean[] status){
        int count = 0,N = arr.length;
        for (int index = N - 1; index >= 0; index--) {
            if(status[index]){ // 之前的数据已经是非递减的了
                return count;
            }
            if(arr[index] < arr[maxs[index]] || arr[index] < X){
                // 必须要交换
                count++;
                X = arr[index];
            }
        }
        return Integer.MAX_VALUE;
    }

    // 暴力解
    public static int getMinSwap2(int[] arr,int X){
        int[] path = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            path[i] = arr[i];
        }
        return process(arr,arr.length-1,X,path);
    }
    public static int process(int[] arr, int i, int X, int[] path){
        if(i == -1){
            // 判断记录的arr是否是非递减的
            return checkedList(path) ? 0 : Integer.MAX_VALUE;
        }
        int cur = arr[i];
        // 不替换
        int p1 = process(arr,i-1,X,path);
        // 替换

        int p2 = Integer.MAX_VALUE;
        if(X > arr[i]){ // 只有大于才能替换
            path[i] = X;
            p2 = process(arr,i-1,arr[i],path);
            // 恢复现场
            path[i] = cur;
        }
        int ans = Math.min(p1 == Integer.MAX_VALUE ? p1 : p1, p2 == Integer.MAX_VALUE ? p2 : p2 + 1);

        return  ans;
    }
    public static boolean checkedList(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j] < arr[i]){
                    return false;
                }
            }
        }
        return true;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue,int minValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.max((int)((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random()) , minValue);
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

    public static void main(String[] args) {
//        int[] arr = {1,1,1,3,14,10};
//        int ans1 = getMinSwap(arr, 14);
//        int ans2 = getMinSwap2(arr, 14);
//        System.out.println(ans1);
//        System.out.println(ans2);
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 50;
        int count = 0;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue,1);
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < arr.length; j++) {
                max = Math.max(max,arr[j]);
            }
            if (Math.random() < 0.5) {
                max = max+1;
            }
            int ans2 = getMinSwap2(arr, max);
            if(i % 200 == 0){
                System.out.println("pass: "+i/200);
            }
            if(ans2 == Integer.MAX_VALUE){
                continue;
            }
            int ans1 = getMinSwap(arr, max); // 在源数据无解时会出错
            if(ans1 != ans2){
                System.out.println("fuck");
                for (int j = 0; j < arr.length; j++) {
                    System.out.print(arr[j]+" ");
                }
                System.out.println();
                System.out.println("max: "+max);
                System.out.println("ans1: "+ans1);
                System.out.println("ans2: "+ans2);
                break;
            }
            count++;
            if(count % 200 == 0){
                System.out.println("success: "+count);
            }

        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
