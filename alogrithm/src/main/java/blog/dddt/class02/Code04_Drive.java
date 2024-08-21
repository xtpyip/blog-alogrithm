package blog.dddt.class02;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName: Code04_Drive
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 现有司机N*2人，调度中心会将所有司机平分给A、B两区域，
 * i号司机去A可得收入为income[i][0]，去B可得收入为income[i][1]
 * 返回能使所有司机总收入最高的方案是多少钱?
 * 	// 找到了leetcode上的测试
 * 	// leetcode上让求最小，课上讲的求最大
 * 	// 其实是一个意思
 * 	// 测试链接 : https://leetcode.cn/problems/two-city-scheduling/
 **/
public class Code04_Drive {
    // 暴力方法，递归
    public static int maxMoney1(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int N = income.length; // 司机数量一定是偶数，所以才能平分，A N /2 B N/2
        int M = N >> 1; // M = N / 2 要去A区域的人
        return process1(income, 0, M);
    }

    // index.....所有的司机，往A和B区域分配！
    // A区域还有rest个名额!
    // 返回把index...司机，分配完，并且最终A和B区域同样多的情况下，index...这些司机，整体收入最大是多少！
    public static int process1(int[][] income, int index, int rest) {
        if (index == income.length) {
            return 0;
        }
        // 还剩下司机！
        if (income.length - index == rest) {
            return income[index][0] + process1(income, index + 1, rest - 1);
        }
        if (rest == 0) {
            return income[index][1] + process1(income, index + 1, rest);
        }
        // 当前司机，可以去A，或者去B
        int p1 = income[index][0] + process1(income, index + 1, rest - 1);
        int p2 = income[index][1] + process1(income, index + 1, rest);
        return Math.max(p1, p2);
    }

    // 贪心
    // 让所有的人都先去A点，从这去A点的人中选择income.length/2个人去B点，怎么最大
    public static int maxMoney2(int[][] income){
        int ans = 0;
        for (int i = 0; i < income.length; i++) {
            ans += income[i][0];
        }
        Arrays.sort(income, (a,b)->{return (b[1]-b[0])-(a[1]-a[0]);});
        for (int i = 0; i < income.length / 2; i++) {
            ans += income[i][1] - income[i][0];
        }
        return ans;
    }
    // 返回随机len*2大小的正数矩阵
    // 值在0~value-1之间
    public static int[][] randomMatrix(int len, int value) {
        int[][] ans = new int[len << 1][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = (int) (Math.random() * value);
            ans[i][1] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 100;
        int testTime = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] matrix = randomMatrix(len, value);
            int ans1 = maxMoney1(matrix);
            int ans2 = maxMoney2(matrix);
            if (ans1 != ans2 ) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
