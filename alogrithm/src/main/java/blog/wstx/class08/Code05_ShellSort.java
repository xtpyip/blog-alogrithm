package blog.wstx.class08;

/**
 * @ClassName: Code05_ShellSort
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 希尔排序
 **/
public class Code05_ShellSort {
    // 希尔排序为插入排序的优化
    public static void shellSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        int stepSize = len / 2;
        while (stepSize != 0) {
            for (int i = stepSize; i < len; i++) {
                int value = arr[i];
                int j;
                for (j = i - stepSize; j >= 0 && arr[j] > value; j-=stepSize) {
                    arr[j + stepSize] = arr[j];
                }
                arr[j + stepSize] = value;
            }
            stepSize /= 2;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
