package writeExam.mt.spring.first;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Code01_MatrixSum {
    public static class Help{
        public int zero;
        public int one;
        public Help(){}
        public Help(int z,int o){zero=z;one=o;}
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        // 奇数*奇数肯定为奇数，肯定不存在0与1相等的情况
        int N = in.nextInt();
        char[][] data = new char[N][N];
        int i = 0;
        in.nextLine();
        while(i < N){
            data[i++] = in.nextLine().toCharArray();
        }
        // for(i = 0;i<N;i++){
        //     for(int j = 0;j<N;j++){
        //         System.out.print(data[i].length + " "+data[i][j]);
        //     }
        //     System.out.println();
        // }


        int[] ans = new int[N];
        // 默认i,j为左上点
        Help[][] helps = new Help[N][N];
        // 生成helps的数据
        for(i = 0;i<N;i++){ // 第0行只影响自己
            if(i == 0){
                helps[i][0] = data[i][0] == '0' ? new Help(1,0) : new Help(0,1);
                int nextIndex = 1;
                Help pre = helps[i][0];
                while(nextIndex < N){
                    helps[i][nextIndex] = data[i][nextIndex] == '0' ? new Help(pre.zero+1,pre.one) : new Help(pre.zero,pre.one+1);
                    pre = helps[i][nextIndex++];
                }
            }else{
                Help up = helps[i-1][0];
                Help left = null;
                Help leftUp = null;
                for(int j = 0;j<N;j++){
                    if(j == 0){
                        helps[i][j] = data[i][j] == '0' ? new Help(up.zero+1,up.one) : new Help(up.zero,up.one+1);
                        leftUp = up;
                        up = j < N-1 ? helps[i-1][j+1] : null;
                        left = helps[i][j];
                    }else{
                        helps[i][j] = data[i][j] == '0' ? new Help(up.zero+left.zero - leftUp.zero + 1,up.one+left.one-leftUp.one) :  new Help(up.zero+left.zero - leftUp.zero,up.one+left.one-leftUp.one+1);
                        leftUp = up;
                        up = j < N-1 ? helps[i-1][j+1] : null;
                        left = helps[i][j];
                    }
                }
            }
        }
        // for(i = 0;i<N;i++){
        //     for(int j = 0;j<N;j++){
        //         System.out.print(helps[i][j].zero+","+helps[i][j].one+" ");
        //     }
        //     System.out.println();
        // }

        for(i = 0;i< N;i++){
            for(int j = 0;j<N;j++){
                // (i,j)开始向左向下延申size长度
                for(int size=1;i+size < N && j+size < N;size+=2){
                    // size为1对应(size+1)*(size+1)这个区间的
                    // 右下角
                    Help rightOne = helps[i+size][j+size];
                    int p1 = (j-1 < 0 ? 0 : helps[i+size][j-1].zero);
                    int p2 = (i -1 <0 ? 0 : helps[i-1][j+size].zero);
                    int p3 = (i - 1 >= 0 && j - 1 >= 0? helps[i-1][j-1].zero : 0);
                    int zeros = rightOne.zero - p1 - p2 + p3;
                    int r1 = (j-1 < 0 ? 0 : helps[i+size][j-1].one);
                    int r2 = (i -1 <0 ? 0 : helps[i-1][j+size].one);
                    int r3 = (i-1 >=0 && j -1 >= 0 ? helps[i-1][j-1].one : 0);
                    int ones = (rightOne.one - r1 - r2 + r3);
                    if(zeros == ones){
                        ans[size]++;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(i=0;i<N;i++){
            if(i != N - 1)
                sb.append(ans[i]+"\n");
            else
                sb.append(ans[i]);
        }
        System.out.print(sb.toString());
    }
}