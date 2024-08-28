package blog.dddt.class10;

/**
 * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
 * 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
 * Leetcode题目：https://leetcode.cn/problems/boolean-evaluation-lcci/
 */
public class Code05_BooleanEvaluation {

    public static int countEval(String s, int result) {
        if(s == null || s.length() % 2 == 0) return 0;
        int[] ans = process(s.toCharArray(),0,s.length()-1);
        return ans[result];
    }

    // 从当前[l,r]范围内有多少等于result的值
    // int[0]任意划分后取0的数量
    // int[1]任意划分后取1的数量
//    超出时间限制 68 / 78 个通过的测试用例
    public static int[] process(char[] str,int l,int r){
        int zero = 0;
        int one = 0;
        if(l == r){
            zero = str[l] == '0' ? 1 : 0;
            one = str[l] == '1' ? 1 : 0;
        }else{
            // 划分点一定要在符号位上
            for(int index = l + 1;index < r;index += 2){
                int[] p1 = process(str,l,index-1);
                int[] p2 = process(str,index+1,r);
                switch (str[index]){
                    case '&':
                        zero += p1[0]*p2[0] + p1[0] * p2[1]+p1[1]*p2[0];
                        one += p1[1]*p2[1];
                        break;
                    case '^':
                        zero += p1[0]*p2[0] +p1[1]*p2[1];
                        one += p1[1]*p2[0] + p1[0]*p2[1];
                        break;
                    case '|':
                        zero += p1[0]*p2[0];
                        one += p1[0]*p2[1] + p1[1]*p2[1] + p1[1]*p2[0];
                        break;
                    default:
                        break;
                }
            }
        }
        return new int[]{zero,one};
    }
    public static int countEval2(String s, int result) {
        if(s == null || s.length() % 2 == 0) return 0;
        Help[][] dp = new Help[s.length()][s.length()];
        int[] ans = process(s.toCharArray(),0,s.length()-1,dp).dp;
        return ans[result];
    }
    public static class Help{
        int[] dp = new int[2];
        public Help(int z,int o){dp[0]=z;dp[1]=o;}
    }

    // 从当前[l,r]范围内有多少等于result的值
    // int[0]任意划分后取0的数量
    // int[1]任意划分后取1的数量
//    超出时间限制 68 / 78 个通过的测试用例
    public static Help process(char[] str,int l,int r,Help[][] dp){
        if(dp[l][r] != null){
            return dp[l][r];
        }
        int zero = 0;
        int one = 0;
        if(l == r){
            zero = str[l] == '0' ? 1 : 0;
            one = str[l] == '1' ? 1 : 0;
        }else{
            // 划分点一定要在符号位上
            for(int index = l + 1;index < r;index += 2){
                int[] p1 = process(str,l,index-1,dp).dp;
                int[] p2 = process(str,index+1,r,dp).dp;
                switch (str[index]){
                    case '&':
                        zero += p1[0]*p2[0] + p1[1]*p2[0] + p1[0]*p2[1];
                        one += p1[1]*p2[1];
                        break;
                    case '^':
                        zero += p1[0]*p2[0] +p1[1]*p2[1];
                        one += p1[1]*p2[0] + p1[0]*p2[1];
                        break;
                    case '|':
                        zero += p1[0]*p2[0];
                        one += p1[0]*p2[1] + p1[1]*p2[1] + p1[1]*p2[0];
                        break;
                    default:
                        break;
                }
            }
        }
        dp[l][r] = new Help(zero,one);
        return dp[l][r];
    }

    public static void main(String[] args) {
        int i = countEval("1^0|0|1", 0);
        System.out.println(i);
    }
}
