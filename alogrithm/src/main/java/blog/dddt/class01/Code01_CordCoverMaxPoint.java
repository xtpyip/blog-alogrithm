package blog.dddt.class01;

import java.util.Arrays;

/**
 * @ClassName: Code01_CordCoverMaxPoint
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定一个有序数组arr，代表坐落在X轴上的点给定一个正数K，代表绳子的长度
 * 返回绳子最多压中几个点?
 * 即使绳子边缘处盖住点也算盖住
 **/
public class Code01_CordCoverMaxPoint {
    // 贪心思想，绳子的末尾不必压着一个不存在的点
    // 以arr[0]为末尾压住的点，最长能到哪里
    public static int maxPoint(int[] arr, int L) {
        int ans = 1;
        for (int i = 0; i < arr.length; i++) {
            // 二分查找
            int leftIndex = f1(arr,arr[i] - L);
            ans = Math.max(ans,i - leftIndex + 1);
        }
        return ans;
    }
    // 通过num查找arr中，>= num 的最左位置
    public static int f1(int[] arr,int num){
        int ans = Integer.MAX_VALUE;
        int L = 0,R = arr.length - 1;
        while (L <= R){
            int mid = ((R - L)>>1)+L;
            if(arr[mid] >= num){
                ans = mid;
                R = mid - 1;
            }else{
                L = mid + 1;
            }
        }
        return ans;
    }
    // 当前i到达j，j是第一个，超过distance的距离，那i+1到达j-1一定是小于distance的距离
    // 可以不回退的计算
    public static int maxPoint1(int[] arr, int distance) {
        int ans = 0;
        int L = 0,R = 0;
        // R一旦到达了arr.length,那么此时扩到最大了，R+1也能搞到arr.length,但R所在长度最长
        while (R < arr.length){
            while (R < arr.length && arr[R] - arr[L] <= distance){
                R++;
            }
            ans = Math.max(ans,R - L);
            L++;
        }
        return ans;
    }
    // 暴力方法，从当前i往下扩
    public static int maxPoint2(int[] arr, int L) {
        int ans = 1;
        for (int i = 0; i < arr.length; i++) {
            int j = i+1;
            while (j < arr.length && arr[j] - arr[i] <= L){
                j++;
            }
            ans = Math.max(ans,j-i);
        }
        return ans;
    }
    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint(arr, L);
            int ans2 = maxPoint1(arr, L);
            int ans3 = maxPoint2(arr, L);
            int ans4 = test(arr, L);
            if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
                System.out.println("oops!");
                break;
            }
        }

    }
}
