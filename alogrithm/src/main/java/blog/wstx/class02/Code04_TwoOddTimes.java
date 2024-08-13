package blog.wstx.class02;

/**
 * @ClassName: TwoOddTimes
 * @version: 1.0
 * @Author: pyipXt
 * @Description: —个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 **/
public class Code04_TwoOddTimes {
    public static int[] twoOddTimes(int[] arr){
        if(arr == null || arr.length < 2) return new int[]{-1,-1};
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int rightOne = eor & (-eor);
        int eor1 = 0;
        for (int i = 0; i < arr.length; i++) {
            if((rightOne & arr[i]) != 0){
                eor1 ^= arr[i];
            }
        }
        return new int[]{eor1,eor ^ eor1};
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 7;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

        int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
//        printOddTimesNum1(arr1);

        int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
        int[] ints = twoOddTimes(arr2);
        System.out.print(ints[0] +" "+ ints[1]);


    }
}
