package blog.wstx.class02;

/**
 * @ClassName: Code01_Swap
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 两数交换
 **/
public class Code01_Swap {

    public static void swap(int n,int m){
        n = n^m;
        m = n^m;
        n = n^m;
    }

}
