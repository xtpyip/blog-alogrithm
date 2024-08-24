package blog.dddt.class08;

/**
 * @ClassName: Code03_FindWordInMatrix
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 
 * 给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word，
 * 可以从任何一个某个位置出发，可以走上、下、左、右，能不能找到word？
 *  char[][] m = {  { 'a', 'b', 'z' }, 
 *                  { 'c', 'd', 'o' }, 
 *                  { 'f', 'e', 'o' } }
 * 设定1：可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是可以找到的，z -> o -> o -> o -> z，因为允许走一条路径中已经走过的字符
 * 设定2：不可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走 
 **/
public class Code03_FindWordInMatrix {
    // 可以走重复的设定
    public static boolean findWord1(char[][] m, String word) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if(process(m,i,j,0,word.toCharArray())){
                    return true;
                }
            }
        }
        return false;
    }
    // 可变参数i,j,k
    // 从i,j出发，能否走出k...的子串
    public static boolean process(char[][] m,int i,int j,int k,char[] word){
        if(k == word.length) return true;
        if(i < 0 || i >= m.length || j < 0 || j >= m[0].length || m[i][j] != word[k]) return false;
        int[][] directions = new int[][]{
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };
        for (int l = 0; l < 4; l++) {
            if(process(m,i+directions[l][0],j+directions[l][1],k+1,word)){
                return true;
            }
        }
        return false;
    }
    public static boolean findWord1Dp(char[][] m, String word) {
        int[][][] dp = new int[m.length][m[0].length][word.length()];
        // dp[-1]走不了，dp[1]走的了
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if(processDp(m,i,j,0,word.toCharArray(),dp)){
                    return true;
                }
            }
        }
        return false;
    }
    // 可变参数i,j,k
    // 从i,j出发，能否走出k...的子串
    public static boolean processDp(char[][] m,int i,int j,int k,char[] word,int[][][] dp){
        if(dp[i][j][k] == 1 || dp[i][j][k] == -1) return dp[i][j][k] == 1;
        if(k == word.length) return true;
        if(i < 0 || i >= m.length || j < 0 || j >= m[0].length || m[i][j] != word[k]) return false;
        int[][] directions = new int[][]{
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };
        boolean ans = false;
        for (int l = 0; l < 4; l++) {
            if(process(m,i+directions[l][0],j+directions[l][1],k+1,word)){
                ans = true;
                break;
            }
        }
        dp[i][j][k] = ans ? 1 : -1;
        return ans;
    }
    // 不能走重复路
    public static boolean processNoLoop(char[][] m,int i,int j,int k,char[] word){
        if(k == word.length) return true;
        if(i < 0 || i >= m.length || j < 0 || j >= m[0].length || m[i][j] != word[k] || m[i][j] == '0') return false;
        boolean ans = false;
        char cur = m[i][j];
        m[i][j] = '0';
        if(processNoLoop(m,i-1,j,k+1,word) || processNoLoop(m,i,j-1,k+1,word)
          || processNoLoop(m,i+1,j,k+1,word) || processNoLoop(m,i,j+1,k+1,word)){
            ans = true;
        }
        m[i][j] = cur;
        return ans;
    }
    public static boolean findWord3(char[][] m,String str){
        if (str == null || str.equals("")) {
            return true;
        }
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return false;
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if(processNoLoop(m,i,j,0,str.toCharArray())){
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        char[][] m = { { 'a', 'b', 'z' }, { 'c', 'd', 'o' }, { 'f', 'e', 'o' }, };
        String word1 = "zoooz";
        String word2 = "zoo";
        // 可以走重复路的设定
        System.out.println(findWord1(m, word1));
        System.out.println(findWord1(m, word2));
        // 可以走重复路的设定
        System.out.println(findWord1Dp(m, word1));
        System.out.println(findWord1Dp(m, word2));
        // 不可以走重复路的设定
        System.out.println(findWord3(m, word1));
        System.out.println(findWord3(m, word2));

    }





}
