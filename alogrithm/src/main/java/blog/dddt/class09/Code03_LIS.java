package blog.dddt.class09;

/**
 * 给定一个数组arr，求最长递增子序列长度
 * Leetcode题目：https://leetcode.cn/problems/longest-increasing-subsequence
 */
public class Code03_LIS {
    public static int lengthOfLIS(int[] nums){
        if(nums == null || nums.length < 1) return 0;
        int N = nums.length;
//        int[] dp = new int[N]; // dp[i]为nums以i点为结尾最长的长度
        int[] endValue = new int[N]; // endValue[i]为以(i+1)长度结尾的最小结束值为endValue[i]
        // init
//        dp[0] = 1;
        endValue[0] = nums[0];
        int end = 0;
        for(int i = 1;i < N;i++){
            int cur = nums[i];
            // 找到endValue中的值< cur的位置
            int curIndex = getValueFrom(endValue,end,cur);
            if(curIndex == end){
                // 添加
                end++;
                endValue[curIndex+1] = cur;
            }else{
                endValue[curIndex+1] = Math.min(endValue[curIndex+1],cur);
            }
//            dp[i] = curIndex + 1;
        }
        return end+1;
    }
    // arr[0~r]为有效区域
    // cur为待求值
    public static int getValueFrom(int[] arr,int r,int cur){
        int l = 0;
        int ans = -1;
        while(l <= r){
            int mid = ((r-l)>>1) + l;
            if(arr[mid] < cur){
                ans = mid;
                l = mid+1;
            }else{
                r = mid - 1;
            }
        }
        return ans;
    }
}
