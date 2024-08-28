package blog.dddt.class09;

import java.util.Arrays;

/**
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）
 * 注意：不允许旋转信封
 * https://leetcode.cn/problems/russian-doll-envelopes/
 */
public class Code04_EnvelopesProblem {

    // 思路：本质上还是求最长递增子序列的长度
    public static int maxEnvelopes(int[][] envelopes) {
        // 以信封的宽度升序，高度在宽度相等时降序
        // 原：   [[5,4],[6,4],[6,7],[2,3]]
        // sort: [[2,3],[5,4],[6,7],[6,4]]
        // =>    [ 3,     4 ,   7,     4] => 3
        Arrays.sort(envelopes,(a,b)->{return a[0]==b[0] ? b[1]-a[1] : a[0] - b[0];});
        // 转变为求第二维组成数组的最长递增子序列
        int N = envelopes.length;
        int[] endValues = new int[N];
        endValues[0] = envelopes[0][1];
        int end = 0;
        for(int i = 1;i < N;i++){
            int cur = envelopes[i][1];
            int curIndex = getCurIndex(endValues,end,cur);
            if(curIndex == end){
                end++;
                endValues[end] = cur;
            }else{
                endValues[curIndex + 1] = Math.min(endValues[curIndex+1],cur);
            }
        }
        return end+1;
    }
    public static int getCurIndex(int[] arr,int r,int cur){
        int l = 0, ans = -1;
        while(l <= r){
            int mid = ((r-l)>>1) + l;
            if(arr[mid] < cur){
                l = mid + 1;
                ans = mid;
            }else{
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = maxEnvelopes(new int[][]{
                {1, 3}, {3, 5}, {6, 7}, {6, 8}, {8, 4}, {9, 5}
        });
        System.out.println(i);
    }
}
