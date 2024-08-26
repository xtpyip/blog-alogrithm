package writeExam.mt.spring.first;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Code03_CountOfMAndT {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(),K = in.nextInt();
        in.nextLine();
        String str = in.nextLine();
        char[] chars = str.toCharArray();
        int mCount =0,tCount = 0,other = 0;
        for(int i=0;i < N;i++){
            switch(chars[i]){
                case 'M':
                    mCount++;
                    break;
                case 'T':
                    tCount++;
                    break;
                default :
                    other++;
                    break;
            }
        }
        System.out.print((mCount+tCount+Math.min(K,other)));
    }
}