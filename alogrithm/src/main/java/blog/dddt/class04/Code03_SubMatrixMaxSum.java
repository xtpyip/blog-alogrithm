package blog.dddt.class04;

/**
 * @ClassName: Code03_SubMatrixMaxSum
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 返回一个二维数组中子矩阵最大累加和
 * 或返回一个二维数组中子矩阵最大累加和对应的子矩阵位置{左上行，左上列，右下行，右下列}
 **/
public class Code03_SubMatrixMaxSum {

    // 返回子矩阵最大累加和
    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int N = m.length,M = m[0].length;
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] s = new int[M];
            for (int j = i; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    s[k] += m[j][k];
                }
                max = Math.max(max,maxSubArray(s));
            }
        }
        return max;
    }

    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE,N = nums.length;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        for (int i = 1; i < N + 1; i++) {
            dp[i] = Math.max(nums[i-1],nums[i-1]+dp[i-1]);
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    // 本题测试链接 : https://leetcode.cn/problems/max-submatrix-lcci/description/
    public static int[] getMaxMatrix(int[][] m) {
        int N = m.length;
        int M = m[0].length;
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for (int i = 0; i < N; i++) {
            int[] s = new int[M];
            for (int j = i; j < N; j++) {
                cur = 0;
                int begin = 0;
                for (int k = 0; k < M; k++) {
                    s[k] += m[j][k];
                    cur += s[k];
                    if(max < cur){
                        max = cur;
                        a = i;
                        b = begin;
                        c = j;
                        d = k;
                    }
                    if(cur < 0){
                        cur = 0;
                        begin = k + 1;
                    }
                }
            }
        }
        return new int[] { a, b, c, d };
    }
}
