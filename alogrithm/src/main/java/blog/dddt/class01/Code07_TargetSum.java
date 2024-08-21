package blog.dddt.class01;

import java.util.Arrays;

/**
 * @ClassName: Code07_TargetSum
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定一个数组arr，你可以在每个数字之前决定+或者-但是必须所有数字都参与
 * 再给定一个数target，请问最后算出target的方法数是多少?
 **/
public class Code07_TargetSum {
    // X-Y=target
    // X + Y = sum
    // => X = (target+sum)/2
    // 等价于从arr中找到子序列和为(target+sum)/2的方法数
    public static int findTargetSumWays(int[] arr,int target){
        int sum = Arrays.stream(arr).sum();
        // 奇数-奇数=偶数 奇数-偶数=奇数 偶数-偶数= 偶数
//        return sum < target || ((target & 1) ^ (sum & 1)) != 0 ? 0 : dp(arr,(target+sum)>>1);
        return sum < target || ((target & 1) ^ (sum & 1)) != 0 ? 0 : process2(arr,0,(target+sum)>>1);
//        return sum < target || ((target & 1) ^ (sum & 1)) != 0 ? 0 : process(arr,0,0,(target+sum)>>1);
    }

    // 使用index~n这个区间的值任意使用，能够得到sum的结果数
    public static int process(int[] arr,int index,int path,int sum){
        if(index == arr.length){
            return path == sum ? 1 : 0;
        }
        int ans = 0;
        ans += process(arr,index+1,path+arr[index],sum);
        ans += process(arr,index+1,path,sum);
        return ans;
    }
    // 使用index~n这个区间的值任意使用，能够得到sum的结果数
    public static int process2(int[] arr,int index,int rest){
        if(index == arr.length){
            return rest == 0 ? 1 : 0;
        }
        int ans = 0;
        ans += process2(arr,index+1,rest - arr[index]);
        ans += process2(arr,index+1,rest);
        return ans;
    }
    public static int subset1(int[] nums, int s) {
        if (s < 0) {
            return 0;
        }
        int n = nums.length;
        // dp[i][j] : nums前缀长度为i的所有子集，有多少累加和是j？
        int[][] dp = new int[n + 1][s + 1];
        // nums前缀长度为0的所有子集，有多少累加和是0？一个：空集
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= s; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][s];
    }
    // 求非负数组nums有多少个子集，累加和是s
    // 二维动态规划
    // 用空间压缩:
    // 核心就是for循环里面的：for (int i = s; i >= n; i--) {
    // 为啥不枚举所有可能的累加和？只枚举 n...s 这些累加和？
    // 因为如果 i - n < 0，dp[i]怎么更新？和上一步的dp[i]一样！所以不用更新
    // 如果 i - n >= 0，dp[i]怎么更新？上一步的dp[i] + 上一步dp[i - n]的值，这才需要更新
    public static int subset2(int[] nums, int s) {
        if (s < 0) {
            return 0;
        }
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums) {
            for (int i = s; i >= n; i--) {
                dp[i] += dp[i - n];
            }
        }
        return dp[s];
    }


}
