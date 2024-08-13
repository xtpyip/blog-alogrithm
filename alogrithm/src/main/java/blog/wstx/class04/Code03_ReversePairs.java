package blog.wstx.class04;

/**
 * @ClassName: Code03_ReversePairs
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 逆序对数量
 **/
// https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/description/
public class Code03_ReversePairs {
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
        int i = R - L + 1,reversePair = 0,p1 = M,p2 = R;
        int[] help = new int[R - L + 1];
        while (p1 >= L && p2 >= M + 1) {
            reversePair += arr[p1] > arr[p2] ? p2 - M : 0;
            help[--i] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= L) {
            help[--i] = arr[p1--];
        }
        while (p2 >= M + 1) {
            help[--i] = arr[p2--];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
        return reversePair;
    }

    public static void main(String[] args) {
        Code03_ReversePairs f = new Code03_ReversePairs();
        int i = f.reversePairs(new int[]{1, 3, 2, 3, 1});
        System.out.println(i);
    }
}
