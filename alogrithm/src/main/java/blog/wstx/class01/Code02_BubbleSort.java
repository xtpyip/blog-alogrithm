package blog.wstx.class01;

/**
 * @ClassName: Code01_SelectionSort
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 选择排序
 **/
public class Code02_BubbleSort {

    public void bubbleSort(int[] nums){
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length;
        boolean isSwap = false;
        for (int i = 0; i < n - 1; i++) {
            isSwap = false;
            for (int j = 1; j < n-i; j++) {
                if(nums[j] < nums[j-1]){
                    swap(nums,j,j-1);
                    {isSwap = true;}
                }
            }
            if(!isSwap){
                return;
            }
        }
    }
    public void swap(int[] nums,int i,int j){
        if(i == j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
