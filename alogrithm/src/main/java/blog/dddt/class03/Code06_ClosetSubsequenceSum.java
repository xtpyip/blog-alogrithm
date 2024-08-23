package blog.dddt.class03;

import java.util.Arrays;

/**
 * @ClassName: Code06_ClosesetSubsequenceSum
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定整数数组nums和目标值goal，需要从nums中选出一个子序列，使子序列元素总和最接近goal
 * 也就是说如果子序列元素和为sum ，需要最小化绝对差abs(sum - goal)，返回 abs(sum - goal)可能的最小值
 * 注意数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。
 **/
public class Code06_ClosetSubsequenceSum {
    // 本题目的数据量来看，nums.length较小，直接使用分治
    public static int[] l = new int[1<<20];
    public static int[] r = new int[1<<20];
    public static int minAbsDifference1(int[] nums, int goal) {
        if(nums == null || nums.length < 1) return goal;
        int le = process(nums,0,nums.length>>1,0,0,l);
        int re = process(nums,nums.length>>1,nums.length,0,0,r);
        Arrays.sort(l);
        Arrays.sort(r);
        int ans = Math.abs(goal);
        // 因为排序之后定位数字可以不回退
        // 比如你想累加和尽量接近10
        // 左半部分累加和假设为 : 0, 2, 3, 7, 9
        // 右半部分累加和假设为 : 0, 6, 7, 8, 9
        // 左部分累加和是0时，右半部分选哪个累加和最接近10，是9
        // 左部分累加和是2时，右半部分选哪个累加和最接近10，是8
        // 左部分累加和是3时，右半部分选哪个累加和最接近10，是7
        // 左部分累加和是7时，右半部分选哪个累加和最接近10，是0
        // 左部分累加和是9时，右半部分选哪个累加和最接近10，是0
        // 上面你可以看到，
        // 当你从左往右选择左部分累加和时，右部分累加和的选取，可以从右往左
        // 这就非常的方便
        // 下面的代码就是这个意思
        for (int i = 0; i < le; i++) {
            int rest = goal - l[i];
            while (re > 0 && Math.abs(rest - r[re - 1]) <= Math.abs(rest - r[re])) {
                re--;
            }
            ans = Math.min(ans, Math.abs(rest - r[re]));
        }
        return ans;
    }

    // nums[0..index-1]已经选了一些数字，组成了累加和sum
    // 当前来到nums[index....end)这个范围，所有可能的累加和
    // 填写到arr里去
    // fill参数的意思是: 如果出现新的累加和，填写到arr的什么位置
    // 返回所有生成的累加和，现在填到了arr的什么位置
    public static int process(int[] nums, int index, int end, int sum, int fill, int[] arr) {
        if (index == end) { // 到了终止为止了，该结束了
            // 把当前的累加和sum
            // 填写到arr[fill]的位置
            // 然后fill++，表示如果后续再填的话
            // 该放在什么位置了
            arr[fill++] = sum;
        } else {
            // 可能性1 : 不要当前的数字
            // 走一个分支，形成多少累加和，都填写到arr里去
            // 同时返回这个分支把arr填到了什么位置
            fill = process(nums, index + 1, end, sum, fill, arr);
            // 可能性2 : 要当前的数字
            // 走一个分支，形成多少累加和，都填写到arr里去
            // 接着可能性1所填到的位置，继续填写到arr里去
            // 这就是为什么要拿到上一个分支填到哪了
            // 因为如果没有这个信息，可能性2的分支不知道往哪填生成的累加和
            fill = process(nums, index + 1, end, sum + nums[index], fill, arr);
        }
        // 可能性1 + 可能性2，总共填了多少都返回
        return fill;
    }

}
