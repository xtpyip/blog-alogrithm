package blog.dddt.class03;

import java.util.Arrays;

/**
 * @ClassName: Code05_BoatsToSavePeople
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定一个正数数组arr，代表若干人的体重，再给定一个正数limit，表示所有船共同拥有的载重量
 * 每艘船最多坐两人，且不能超过载重
 * 想让所有的人同时过河，并且用最好的分配方法让船尽量少，返回最少的船数
 * https://leetcode.cn/problems/boats-to-save-people/description/
 **/
public class Code05_BoatsToSavePeople {

    //思路一：双指针
    public static int numRescueBoats1(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        Arrays.sort(arr);
        if (arr[N - 1] > limit) {
            return -1;
        }
        int lessR = -1;
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] <= (limit / 2)) {
                lessR = i;
                break;
            }
        }
        if (lessR == -1) {
            return N;
        }
        int L = lessR;
        int R = lessR + 1;
        int noUsed = 0;
        while (L >= 0) {
            int solved = 0;
            while (R < N && arr[L] + arr[R] <= limit) {
                R++;
                solved++;
            }
            if (solved == 0) {
                noUsed++;
                L--;
            } else {
                L = Math.max(-1, L - solved);
            }
        }
        int all = lessR + 1;
        int used = all - noUsed;
        int moreUnsolved = (N - all) - used;
        return used + ((noUsed + 1) >> 1) + moreUnsolved;
    }

    public static int numRescueBoats2(int[] arr, int limit) {
        Arrays.sort(arr);
        int N = arr.length;
        int L = 0,R = N -1;
        int ans = 0;
        while (L <= R){
            int sum = L == R ? arr[L] : arr[L]+arr[R];
            if(sum <= limit){
                // 能坐两个
                L++;
            }
            R--;
            ans++;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(numRescueBoats1(new int[]{3, 5, 3, 4,1,2,3,1,1,1,1,4}, 5));
    }

}
