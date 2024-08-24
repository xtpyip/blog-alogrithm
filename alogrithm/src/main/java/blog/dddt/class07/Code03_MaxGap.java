package blog.dddt.class07;

/**
 * @ClassName: Code03_MaxGap
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定一个数组arr，返回如果排序之后（注意是如果排序），相邻两数的最大差值。
 * 要求时间复杂度O(N)，不能使用非基于比较的排序
 **/
public class Code03_MaxGap {
    // 借助桶排序
    public int maximumGap(int[] nums) {
       if(nums == null || nums.length < 2) return 0;
       int min = Integer.MAX_VALUE;
       int max = Integer.MIN_VALUE;
       int N = nums.length;
        for (int i = 0; i < N; i++) {
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);
        }
        if(min == max){
            return 0;
        }
        boolean[] hasNum = new boolean[N + 1];
        int[] maxBuckets = new int[N + 1];
        int[] minBuckets = new int[N + 1];
        int bid = -1;
        for (int i = 0; i < N; i++) {
            // 要进入哪个桶
            bid = bucket(nums[i],N,min,max);
            maxBuckets[bid] = hasNum[bid] ? Math.max(maxBuckets[bid],nums[i]) : nums[i];
            minBuckets[bid] = hasNum[bid] ? Math.min(minBuckets[bid],nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxBuckets[0];
        int i = 1;
        for(;i < N+1;i++){
            if(hasNum[i]){
                res = Math.max(res,minBuckets[i] - lastMax);
                lastMax = maxBuckets[i];
            }
        }
        return res;
    }

    private int bucket(int num, int n, int min, int max) {
        double range = (double)(max - min)/(double) n;
        double distance = (double) (num - min);
        return (int)(distance/range);
    }

    public static void main(String[] args) {
        Code03_MaxGap f = new Code03_MaxGap();
        System.out.println(f.maximumGap(new int[]{100, 3, 2, 1}));
    }

}
//    30 20 15 70 56 12 90
// l  30 30 30 70 70 70 90
// r  12 12 12 12 12 12 90
//    12 15 20 20 56 70 90
