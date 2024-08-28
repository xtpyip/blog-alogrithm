package blog.dddt.class10;

/**
 * 给出两个整数n和k，找出所有包含从1到n的数字，且恰好拥有k个逆序对的不同的数组的个数
 * 逆序对的定义如下：对于数组的第i个和第j个元素，如果满i<j且a[i]>a[j]，则其为一个逆序对，否则不是
 * 由于答案可能很大，只需要返回 答案 mod 10^9+ 7 的值
 * Leetcode题目：https://leetcode.cn/problems/k-inverse-pairs-array/
 */
public class Code03_KInversePairs {
    /** 1 0
     *  2 ->1,2 0
     *      2,1 1
     *  3 ->1,2,3 0
     *      1,3,2 1
     *      2,1,3 1
     *      2,3,1 3
     *      3,1,2 2
     *      3,2,1 3
     * k < n => 有k个逆序对-> 1~n-1一个没有+(min(0),max(n-1)) 凑成k个
     *                   ->  有一个  +(min(0),max(n-1)) 凑成k个
     *
     */
    public int kInversePairs(int n, int k) {
        if(n < 1 || k < 0) return 0;
        // dp[i][j] 1,2,...i有多少个逆序对为k的个数
        int[][] dp = new int[n + 1][k + 1];
        // init
        dp[0][0] = 1;
        int mod = 1000000007;
        for(int i = 1;i <= n;i++){
            dp[i][0] = 1;
            for(int j = 1;j <= k;j++){
                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % mod;
                if(j >= i){
                    dp[i][j] = (dp[i][j] - dp[i-1][j-i] + mod) % mod;
                }
            }
        }
        return dp[n][k];
    }
}
