package blog.dddt.class02;

/**
 * @ClassName: Code06_MinLengthForSort
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定一个数组arr，只能对arr中的一个子数组排序，但是想让arr整体都有序，
 * 返回满足这一设定的子数组中最短的是多长
 * https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/
 **/
public class Code06_MinLengthForSort {
    // 思路：搞出两个数组
    // 1 2 3 5 7 9 8 11 14 6
    // 1 2 3 5 7 9 9 11 14 14
    // 1 2 3 5 6 6 6 6  6   6
    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] max = new int[n];
        int[] min = new int[n];
        max[0] = nums[0];
        for (int i = 1; i < n; i++) {
            max[i] = Math.max(max[i-1],nums[i]);
        }
        min[n-1]=nums[n-1];
        for (int i = n - 2; i >= 0; i--) {
            min[i] = Math.min(min[i+1],nums[i]);
        }
        int start = n;
        for (int i = 0; i < n; i++) {
            if(nums[i] != min[i]){
                start = i;
                break;
            }
        }
        int end = -1;
        for (int i = n - 1; i > 0; i--) {
            if(nums[i] != max[i]){
                end = i;
                break;
            }
        }
        return start == n ? 0 : end - start + 1;
    }
}
