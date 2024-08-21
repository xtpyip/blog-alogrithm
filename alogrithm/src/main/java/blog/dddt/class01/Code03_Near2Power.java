package blog.dddt.class01;

/**
 * @ClassName: Code03_Near2Power
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定一个非负整数num,如何不用循环语句，
 * 返回>=num，并且离num最近的，2的某次方
 **/
public class Code03_Near2Power {
    // 已知n是正数
    // 返回大于等于，且最接近n的，2的某次方的值
    // 0000 0000 0010 0110 0011 0010 1001 0011
    // 0000 0000 0010 0110 0011 0010 1001 0011 | 0000 0000 0000 1001 1000 1100 1010 0100
    // 0000 0000 0010 1111 1011 1110 1011 0111
    // 0000 0000 0010 1111 1011 1110 1011 0111 | 0000 0000 0000 0010 1111 1011 1110 1011
    // 0000 0000 0010 1111 1111 1111 1111 1111
    public static final int tableSizeFor(int n) {
        // 思路：n与其n>>1,n>>2,n>>4,n>>8,n>>16的值相|,得出所有的1从低位0到高位皆是连成一片的1
        // 则>= n的最近二的某次方就为这个值+1
        if(n <= 1) return 1;
        n--; // 如果原来就是2的某次方
        n = n | n >>> 1;
        n = n | n >>> 2;
        n = n | n >>> 4;
        n = n | n >>> 8;
        n = n | n >>> 16;
        return n+1;
    }
    public static final int test(int n) {
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }
    public static void main(String[] args) {
        System.out.println("测试开始");
        for (int i = 0; i < 20000000; i++) {
            int ans1 = tableSizeFor(i);
            int ans2 = test(i);
            if(ans1 != ans2){
                System.out.println("error");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
