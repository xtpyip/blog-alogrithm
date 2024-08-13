package blog.wstx.class01;

/**
 * @ClassName: Code05_BSNearLeft
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 大于等于target的最左侧位置
 **/
public class Code05_BSNearLeft {
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length < 1) return 0;
        int L = 0,R = nums.length - 1,ans = nums.length;
        while (L <= R) {
            int mid = (R - L)/2 + L;
            if(nums[mid] >= target){
                ans = mid;
                R = mid - 1;
            }else{
                L = mid + 1;
            }
        }
        return ans;
    }
}
