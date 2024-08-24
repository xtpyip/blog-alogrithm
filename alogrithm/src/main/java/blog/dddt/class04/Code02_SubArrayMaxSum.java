package blog.dddt.class04;

/**
 * @ClassName: Code02_SubArrayMaxSum
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 返回一个数组中子数组最大累加和
 * https://leetcode.cn/problems/maximum-subarray/
 **/
public class Code02_SubArrayMaxSum {

    // 贪心+动态规划
    // dp[i] 为从0~i这个范围内的最大子数组累加和
    // dp[j] max(arr[i]+dp[j-1],arr[i])
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE,N = nums.length;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        for (int i = 1; i < N + 1; i++) {
            dp[i] = Math.max(nums[i-1],nums[i-1]+dp[i-1]);
            max = Math.max(max,dp[i]);
        }
        return max;
    }
}
