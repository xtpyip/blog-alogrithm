package blog.dddt.class08;

import java.util.Arrays;

/**
 * @ClassName: Code04_SnakeGame
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定一个矩阵matrix，值有正、负、0。
 * 蛇可以空降到最左列的任何一个位置，初始增长值是0。蛇每一步可以选择右上、右、右下三个方向的任何一个前进
 * 沿途的数字累加起来，作为增长值；但是蛇一旦增长值为负数，就会死去。蛇有一种能力，可以使用一次：把某个格子里的数变成相反数
 * 蛇可以走到任何格子的时候停止，返回蛇能获得的最大增长值
 *
 **/
public class Code04_SnakeGame {
    public static int walk1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Info cur = process(matrix, i, j);
                ans = Math.max(ans, Math.max(cur.no,cur.yes));
            }
        }
        return ans;
    }
    public static class Info{
        private int no;
        private int yes;
        public Info(int n,int y){yes=y;no=n;}
    }
    public static Info process(int[][] matrix,int i,int j){
        if(j == 0){
            int no = Math.max(matrix[i][0],-1);
            int yes = Math.max(-matrix[i][0],-1);
            return new Info(no,yes);
        }
        int preNo = -1;
        int preYes = -1;
        Info pre = process(matrix, i, j - 1);
        preNo = Math.max(preNo,pre.no);
        preYes = Math.max(preYes,pre.yes);
        if(i > 0){
            pre = process(matrix,i-1,j-1);
            preNo = Math.max(preNo,pre.no);
            preYes = Math.max(preYes,pre.yes);
        }
        if(i < matrix.length - 1){
            pre = process(matrix,i+1,j-1);
            preNo = Math.max(preNo,pre.no);
            preYes = Math.max(preYes,pre.yes);
        }
        // 此时在来当前i,j位置前的数据preNo,preYes已经拿到了
        int curNo = -1;
        int curYes = -1;
        // 一次能力也不用，如果到达不了当前，则为-1，到达的了，则与取当前值与-1的最大值
        curNo = preNo == -1 ? -1 : Math.max(preNo + matrix[i][j],-1);
        // 用了一次能力也到达不了当前，则为-1，到达的了，用不了能力，取当前值与-1的最大值
        int p1 = preYes == -1 ? -1 : Math.max(preYes + matrix[i][j],-1);
        // 当前使用能力，要先不使用一次能力能到达当前，才能使用能力
        int p2 = preNo == -1 ? -1 : Math.max(preNo - matrix[i][j],-1);
        curYes = Math.max(Math.max(p1,p2),-1);
        return new Info(curNo,curYes);
    }

    public static int walk2(int[][] matrix){
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        // dp[i][j][0]为不使用能力到达i,j的最大值
        // dp[i][j][1]为使用能力到达i,j的最大值
        int[][][] dp = new int[matrix.length][matrix[0].length][2];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0][0] = matrix[i][0];
            dp[i][0][1] = -matrix[i][0];
            max = Math.max(max,Math.max(dp[i][0][0],dp[i][0][1]));
        }
        for (int j = 1; j < dp[0].length; j++) {
            for (int i = 0; i < dp.length; i++) {
                int preUnUse = dp[i][j-1][0];
                int preUse = dp[i][j-1][1];
                if(i > 0){
                    preUnUse = Math.max(preUnUse,dp[i-1][j-1][0]);
                    preUse = Math.max(preUse,dp[i-1][j-1][1]);
                }
                if(i < dp.length - 1){
                    preUnUse = Math.max(preUnUse,dp[i+1][j-1][0]);
                    preUse = Math.max(preUse,dp[i+1][j-1][1]);
                }
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
                if(preUnUse >= 0){
                    // 不使用能力能走到当前
                    dp[i][j][0] = preUnUse+matrix[i][j];
                    // 当前使用能力
                    dp[i][j][1] = preUnUse-matrix[i][j];
                }
                if(preUse >= 0){
                    // 已经使用过了能力,且能走到当前
                    dp[i][j][1] = Math.max(dp[i][j][1],
                            preUse+matrix[i][j]);
                }
                max = Math.max(max,Math.max(dp[i][j][0],dp[i][j][1]));
            }
        }
        return max;
    }

    public static int[][] generateRandomArray(int row, int col, int value) {
        int[][] arr = new int[row][col];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int) (Math.random() * value) * (Math.random() > 0.5 ? -1 : 1);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 7;
        int M = 7;
        int V = 10;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int r = (int) (Math.random() * (N + 1));
            int c = (int) (Math.random() * (M + 1));
            int[][] matrix = generateRandomArray(r, c, V);
            int ans1 = walk1(matrix);
            int ans2 = walk2(matrix);
            if (ans1 != ans2) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.println(Arrays.toString(matrix[j]));
                }
                System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
                break;
            }
        }
        System.out.println("finish");
    }

}

