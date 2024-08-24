package blog.dddt.class04;

/**
 * @ClassName: Code04_SubArrayMaxSumFollowUp
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 返回一个数组中所选数字不能相邻的情况下最大子序列累加和
 * // 在线测试链接 : https://leetcode.cn/problems/house-robber/
 **/
public class Code04_SubArrayMaxSumFollowUp {

    // 思路一：暴力递归 超出时间限制 55 / 70 个通过的测试用例
    public static int rob1(int[] arr) {
        return process(arr,0);
    }
    public static int process(int[] arr,int index){
        if(index >= arr.length) return 0;
        // 当前值要选择的话
        int p1 = arr[index] + process(arr,index+2);
        // 当前值要不选择的话
        int p2 = process(arr,index + 1);
        return Math.max(p1,p2);
    }
    // 思路二：在此基础上可进行记忆化搜索 略
    // 思路三：dp 从右向左(也可以改写为从左向右) dp[i]表示从i~n-1范围内任意取不相邻的数据，最大值
    public static int rob2(int[] arr){
        int N = arr.length;
        int[] dp = new int[N];
        dp[N -1] = arr[N -1];
        for (int i = N - 2; i >= 0; i--) {
            dp[i] = dp[i+1];
            if(i+2 < N){
                dp[i] = Math.max(dp[i],arr[i]+dp[i+2]);
            }else{
                dp[i] = Math.max(dp[i],arr[i]);
            }
        }
        return dp[0];
    }
}
