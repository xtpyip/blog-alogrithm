package blog.wstx.class05;

/**
 * @ClassName: Code01_CountOfRangeSum
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 范围内的值
 **/
// https://leetcode.com/problems/count-of-range-sum/
public class Code01_CountOfRangeSum {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        if(nums == null || n < 1) return 0;
        long[] preSum = new long[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }

        return process(0,n-1,preSum,lower,upper);
    }
    public static int process(int L,int R,long[] preSum,int lower,int upper){
        // 只有一个元素时，判断是否符合要求
        if(L == R) return preSum[L] <= upper && preSum[L] >= lower ? 1 : 0;
        int mid = (R - L)/2 + L; //归并查询
        return process(L,mid,preSum,lower,upper)
                + process(mid+1,R,preSum,lower,upper)
                +merge(L,mid,R,preSum,lower,upper);
    }
    public static int merge(int L,int M,int R,long[] preSum,int lower,int upper){
        int ans = 0;
        int windowL = L;
        int windowR = L;
        // [windowL, windowR)
        for (int i = M+1; i <= R; i++) {
            long min = preSum[i] - upper;
            long max = preSum[i] - lower;
            while(windowR <= M && preSum[windowR] <= max){
                windowR++;
            }
            while (windowL <= M && preSum[windowL] < min){
                windowL++;
            }
            ans += windowR - windowL;
        }

        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = preSum[p1] <= preSum[p2] ? preSum[p1++] : preSum[p2++];
        }
        while (p1 <= M) {
            help[i++] = preSum[p1++];
        }
        while (p2 <= R) {
            help[i++] = preSum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            preSum[L + i] = help[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = countRangeSum(new int[]{3,4,-5,7,2,8,5,9}, 4, 10);
        System.out.println(i);

    }
}
