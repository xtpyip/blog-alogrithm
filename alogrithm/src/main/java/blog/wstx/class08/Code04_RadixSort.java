package blog.wstx.class08;

import java.util.Arrays;

/**
 * @ClassName: Code04_RadixSort
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 桶排序
 **/
public class Code04_RadixSort {
    // only for no-negative value
    public static void radixSort(int[] arr) {
        if(arr == null || arr.length < 2) return;
        radixSort(arr,0,arr.length-1,maxBits(arr));
    }
    // 最长的位数
    public static int maxBits(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        int bits = 0;
        while (max != 0) {
            bits++;
            max /= 10;
        }
        return bits;
    }
    // arr[L..R]排序  ,  最大值的十进制位数MaxBit
    public static void radixSort(int[] arr,int L,int R,int MaxBit){
        final int radix = 10;//10个桶
        int i = 0,j = 0;
        int[] help = new int[R - L + 1];// 记录每次出桶的顺序
        for (int d = 1; d <= MaxBit; d++) {
            int[] count = new int[radix];
            for(i = L;i <= R; i++){
                count[getDigit(arr[i],d)]++;
            }
            // 出桶准备
            for(i = 1;i < radix;i++){
                count[i] += count[i-1];
            }
            // 出桶
            for (i = R; i >= L; i--) { // 这里看解释
                j = getDigit(arr[i],d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            for(i=L,j=0;i<=R;i++,j++){
                arr[i] = help[j];
            }
        }
    }

    // value的第bit位的值
    public static int getDigit(int value,int bit){
        return ((value / (int)Math.pow(10,bit-1))) % 10;
    }
    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
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
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
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
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }

}
