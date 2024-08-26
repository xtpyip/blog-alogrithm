package writeExam.mt.spring.first;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Code02_MinAndMax {
    // 5/30 组用例通过
    //运行时间
    //2001ms
    //占用内存
    //11340KB
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(),Q = in.nextInt();
        int zeroCount = 0;
        Long sum = 0L;
        in.nextLine();
        String[] data = in.nextLine().split(" ");
        for(int i = 0;i<N;i++){
            Long cur = Long.valueOf(data[i]);
            if(cur != 0L){
                sum += cur;
            }else{
                zeroCount++;
            }
        }
        // System.out.println(sum);
        // System.out.println(zeroCount);
        int i = 0;
        while(i < Q){
            String[] lr = in.nextLine().split(" ");
            Long l =Long.valueOf(lr[0]),r = Long.valueOf(lr[1]);
            // System.out.println(l);
            // System.out.println(r);
            System.out.println((sum+l * zeroCount)+" "+(sum+r*zeroCount));
            i++;
        }
    }
}