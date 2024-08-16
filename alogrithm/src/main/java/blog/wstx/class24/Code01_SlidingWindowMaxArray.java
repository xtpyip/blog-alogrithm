package blog.wstx.class24;

/**
 * @ClassName: Code01_SildingWindowMaxArray
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 滑动窗口的最大值
 **/

import java.util.LinkedList;

public class Code01_SlidingWindowMaxArray {

    // 暴力的对数器方法
    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);

            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    public static int[] getMaxWindow(int[] arr, int w) {
        if(arr == null || w < 1 || arr.length < w) return null;
        int n = arr.length,index = 0;
        int[] ans = new int[n - w + 1];
        LinkedList<Integer> qMax = new LinkedList<>(); // 存储下标
        for (int R = 0; R < n; R++) {
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]){
                qMax.pollLast();
            }
            qMax.addLast(R);
            if(qMax.peekFirst() == R - w){ // L = 0 R = 3 w = 3=> 1,2,3为窗口，当L=R-w时L出队
                qMax.pollFirst();
            }
            if(R >= w - 1){
                ans[index++] = arr[qMax.peekFirst()];
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
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

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = right(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
