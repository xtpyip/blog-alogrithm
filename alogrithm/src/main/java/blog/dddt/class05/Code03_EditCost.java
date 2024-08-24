package blog.dddt.class05;

/**
 * @ClassName: Code03_EditCost
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 编辑距离
 **/
public class Code03_EditCost {
    // 插入代价ic,删除代价dc,替换代价rc
    public static int minCost1(String s1, String s2, int ic, int dc, int rc) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int N = chars1.length,M = chars2.length;
        // dp[i][j]表示s1从0开始长度为i变成s2从0开始长度为j的最小代价是多少
        int[][] dp = new int[N+1][M+1];
        dp[0][0] = 0;
        for (int i = 1; i < M + 1; i++) {
            dp[0][i] = ic * i;
        }
        for (int i = 1; i < N + 1; i++) {
            dp[i][0] = dc * i;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i-1][j-1] + (chars1[i-1] == chars2[j-1] ? 0 : rc);
                dp[i][j] = Math.min(dp[i][j],dp[i-1][j]+dc);
                dp[i][j] = Math.min(dp[i][j],dp[i][j-1]+ic);
            }
        }
        return dp[N][M];
    }
    // 从上面我们可以看出，当前i,j位置只依赖三个位置，即i-1,j-1和i-1,j，及i,j-1
    // 可以进行数组的压缩
    public static int minCost2(String s1, String s2, int ic, int dc, int rc) {
        // 我们也可以对长度长的那个做数组
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        int N = char1.length,M = char2.length;
        char[] longest = N < M ? char2 : char1;
        char[] smallest = longest == char2 ? char1 : char2;
        int[] dp = new int[smallest.length+1];
        int pre = 0;
        // 如果数据对换了话，那么原来的s1对s2的删除操作就变成了s2对s1的添加操作
        if(longest == char2){
            int temp = ic;
            ic = dc;
            dc = temp;
        }
        // dp进行初始化
        // longest长度为0变成smallest长度为i，要进行i个ic操作
        for (int i = 0; i < dp.length; i++) {
            dp[i] = i*ic;
        }
        for (int i = 1; i < longest.length+1; i++) {
            // pre为j=0时确定，其余由上一个值决定
            for (int j = 0; j < dp.length; j++) {
              if(j == 0){
                  pre = i*dc;
                  continue;
              }
              int temp = Math.min(Math.min(pre + ic, dp[j] + dc),dp[j-1] + (longest[i-1] == smallest[j-1] ? 0 : rc));
              dp[j-1] = pre;
              pre = temp;
            }
            dp[dp.length-1] = pre;
        }
        return dp[dp.length-1];
    }
    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(minCost1(str1, str2, 5, 3, 2));
        System.out.println(minCost2(str1, str2, 5, 3, 2));

        str1 = "abcdf";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 3, 2, 4));
        System.out.println(minCost2(str1, str2, 3, 2, 4));

        str1 = "";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 1, 7, 5));
        System.out.println(minCost2(str1, str2, 1, 7, 5));

        str1 = "abcdf";
        str2 = "";
        System.out.println(minCost1(str1, str2, 2, 9, 8));
        System.out.println(minCost2(str1, str2, 2, 9, 8));

    }
}
