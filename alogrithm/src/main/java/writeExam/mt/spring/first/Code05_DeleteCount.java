package writeExam.mt.spring.first;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Code05_DeleteCount {
    public static void main(String[] args) {
        // 5的数量2的数量决定了0的数量
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(),K = in.nextInt();
        int twoCount = 0,fiveCount = 0;
        int[] twoPres = new int[N];
        int[] fivePres =  new int[N];
        int i = 0;
        while(i < N){
            int cur = in.nextInt();
            int cur2 = getTwoCount(cur);
            int cur5 = getFiveCount(cur);
            twoPres[i] = cur2;
            fivePres[i] = cur5;
            twoCount += cur2;
            fiveCount += cur5;
            i++;
        }
        int L = 0;
        Long ans = 0l;
        for(int r = 0;r < N;r++){
            twoCount -= twoPres[r];
            fiveCount -= fivePres[r];
            while(Math.min(twoCount,fiveCount) < K && L <= r){
                twoCount += twoPres[L];
                fiveCount += fivePres[L];
                L++;
            }
            ans += r - L + 1 ;
        }
        System.out.print(ans);
    }
    public static int getTwoCount(int cur){
        int count = 0;
        while((cur & 1) != 1){
            // 偶数
            count++;
            cur >>= 1;
        }
        return count;
    }
    public static int getFiveCount(int cur){
        int count = 0;
        while(cur % 5 == 0 && cur > 0){
            count++;
            cur /= 5;
        }
        return count;
    }
}