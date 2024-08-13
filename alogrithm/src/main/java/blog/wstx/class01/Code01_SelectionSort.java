package blog.wstx.class01;

/**
 * @ClassName: Code01_SelectionSort
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 选择排序
 **/
public class Code01_SelectionSort {
    // 选择0~n-1的最小值，与0互换
    // 选择1~n-1的最小值，与1互换
    // ...
    // 选择n-2~n-1的最小值，与n-2互换
    public void selectionSort(int[] nums){
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if(nums[j] < nums[minIndex]){
                    minIndex = j;
                }
            }
            swap(nums,minIndex,i);
        }
    }
    public void swap(int[] nums,int i,int j){
        if(i == j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
