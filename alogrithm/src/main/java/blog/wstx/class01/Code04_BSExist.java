package blog.wstx.class01;

/**
 * @ClassName: Code04_BSExist
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 二分查找是否存在
 **/
public class Code04_BSExist {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length < 1){
            return -1;
        }
        int L = 0, R = nums.length - 1;
        while (L <= R) {
            int mid = (R - L) / 2 + L;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                R = mid - 1;
            }else{
                L = mid + 1;
            }
        }

        return -1;
    }
}
