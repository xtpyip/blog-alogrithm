package blog.dddt.class09;

import java.util.HashMap;

/**
 * 定义何为step sum？比如680，680 + 68 + 6 = 754，680的step sum叫754。
 * 给定一个正数num，判断它是不是某个数的step sum
 */
public class Code05_IsStepSum {
    // 我们不难观察到一个数的a的step sum为b,b>=a的
    public static boolean isStepSum(int target){
        int l = 0,r = target;
        while(l <= r){
            int mid = ((r-l)>>1) + l;
            int midStepSum = getStepSum(mid);
            if(midStepSum == target){
                return true;
            }else if(midStepSum < target){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return false;
    }
    public static int getStepSum(int n){
        int sum = 0;
        while(n != 0){
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
    // for test
    public static HashMap<Integer, Integer> generateStepSumNumberMap(int numMax) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= numMax; i++) {
            map.put(getStepSum(i), i);
        }
        return map;
    }

    // for test
    public static void main(String[] args) {
        int max = 1000000;
        int maxStepSum = getStepSum(max);
        HashMap<Integer, Integer> ans = generateStepSumNumberMap(max);
        System.out.println("测试开始");
        for (int i = 0; i <= maxStepSum; i++) {
            if (isStepSum(i) ^ ans.containsKey(i)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}
