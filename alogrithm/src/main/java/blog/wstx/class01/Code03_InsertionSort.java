package blog.wstx.class01;

/**
 * @ClassName: Code01_SelectionSort
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 选择排序
 **/
public class Code03_InsertionSort {

    public void insertionSort(int[] nums){
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            int j = i;
            while (j >= 1 && nums[j-1] > cur){
                nums[j] = nums[j-1];
                j--;
            }
            nums[j] = cur;
        }
    }
    public void swap(int[] nums,int i,int j){
        if(i == j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
