package blog.dddt.class03;

/**
 * @ClassName: Code03_Largest1BorderedSquare
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定一个只有0和1组成的二维数组，返回边框全是1（内部无所谓）的最大正方形面积
 * https://leetcode.cn/problems/largest-1-bordered-square/
 **/
public class Code03_Largest1BorderedSquare {
    // 思路一：纯暴力
    public  int largest1BorderedSquare1(int[][] m) {
        // 纯暴力,i在0~M之间，j在0~N之间
        // 正方形，边长为K,在0~N之间
        // 时间复杂度O(n^4)
        int M = m.length,N = m[0].length;
        int ans = 1;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                // 以当前点为左上角点
                for (int k = 0; k < Math.min(N - i, M - j); k++) {
                    if(judge(i,j,k,m)){
                        ans = Math.max(ans,k);
                    }
                }
            }
        }
        return 0;
    }
    public static boolean judge(int i,int j,int k,int[][] m){
        // m[i][j]~m[i][j+k]全为1
        // m[i][j]~m[i+k][j]全为1
        // m[i+k][j]~m[i+k][j+k]全为1
        // m[i][j+k]~m[i+k][j+k]全为1
        // 同时满足上边四个条件，返回true
        return false;
    }
    // 思路二：辅助数组
    public  int largest1BorderedSquare2(int[][] m) {
        int M = m.length,N = m[0].length;
        int[][] right = new int[M][N]; // 从右往左连续1的数量，遇0重新计数(累似前缀和)
        int[][] down = new int[M][N]; // 从下往上
        generateRightAndDown(m,right,down);
        for (int size = Math.min(M,N); size != 0; size--) {
            if(hasSizeOfBorder(size,right,down)){
                return size * size;
            }
        }
        return 0;
    }
    public static void generateRightAndDown(int[][] m,int[][] right,int[][] down){
        int M = m.length,N = m[0].length;
        for (int i = M - 1; i >= 0; i--) {
            right[i][N-1] = m[i][N-1] == 0 ? 0 : 1;
            for (int j = N - 2; j >= 0; j--) {
                right[i][j] = m[i][j] == 0 ? 0 : right[i][j+1] + 1;
            }
        }
        for (int i = 0; i < N; i++) {
            down[M-1][i] = m[M-1][i];
            for (int j = M - 2; j >= 0; j--) {
                down[j][i] = m[j][i] == 0 ? 0 : down[j+1][i] + 1;
            }
        }
    }
    public static boolean hasSizeOfBorder(int size,int[][] right,int[][] down){
        int M = right.length,N = right[0].length;
        for (int i = 0; i != M - size + 1; i++) {
            for (int j = 0; j != N - size + 1; j++) {
                if(right[i][j] >= size &&
                   down[i][j] >= size &&
                   right[i+size-1][j] >= size &&
                   down[i][j+size-1] >= size){
                    return true;
                }
            }

        }
        return false;
    }
}
