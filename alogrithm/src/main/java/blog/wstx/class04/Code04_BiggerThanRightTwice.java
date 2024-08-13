package blog.wstx.class04;

/**
 * @ClassName: Code04_BiggerThanRightTwice
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 翻转对的数量
 **/
// https://leetcode.cn/problems/reverse-pairs/description/
public class Code04_BiggerThanRightTwice {
    public int reversePairs(int[] record) {
        if(record == null || record.length < 2) return 0;
        return process(record,0,record.length - 1);
    }
    // arr[L..R]既要排好序，也要求小和返回
    // 所有merge时，产生的小和，累加
    // 左 排序   merge
    // 右 排序  merge
    // merge
    public static int process(int[] arr, int l, int r) {
        if(l == r) return 0;
        int mid = (r - l) / 2 + l;
        return process(arr,l,mid)
                +process(arr,mid+1,r)
                +merge(arr,l,mid,r);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int reversePair = 0;
        int windowR = M + 1;
        for (int i = L; i <= M; i++) {
            while (windowR <= R && (long) arr[i] > (long) arr[windowR] * 2){
                windowR++;
            }
            reversePair += windowR - M - 1;
        }
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return reversePair;
    }
}
