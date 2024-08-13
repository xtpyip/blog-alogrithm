package blog.wstx.class02;

/**
 * @ClassName: Code02_OneOddTimes
 * @version: 1.0
 * @Author: pyipXt
 * @Description: —个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 **/
public class Code02_OneOddTimes {
    public static int oneOddTimes(int[] nums){
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}
