package blog.dddt.class01;

/**
 * @ClassName: Code04_MinSwapStep
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 一个数组中只有两种字符'G'和'B’，
 * 想让所有的G都放在左侧，所有的B都放在右侧
 * 或者所有的B都放在右侧，所有的G都放在左侧
 * 但是只能在相邻字符之间进行交换操作，返回至少需要交换几次
 **/
public class Code04_MinSwapStep {


    // 贪心，若原来的数据为BBBGGBGBGG
    // 我们没有必要让最后一个G放到位置0的位置上
    // 只要让第i个G放到指定的下标为(i-1)的位置上即可
    public static int minSteps1(String s) {
        int minStep = 0;
        int num = 1; // 第几个G
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == 'G'){ //G放到右侧
                // i为G的下标位置，num-1为要去的下标位置，邻居互换要
                minStep += (i - (num - 1));
                num++;
            }
        }
        int minStep2 = 0;
        num = 1;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == 'B'){ // B放到右侧
                minStep2 += (i - (num - 1));
                num++;
            }
        }

        return Math.min(minStep2,minStep);
    }
    public static int minSteps2(String s) {
        char[] chars = s.toCharArray();
        int step1 = 0,step2 = 0,gi=0,bi=0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == 'G'){
                step1 += i - (gi++);
            }else{
                step2 += i - (bi++);
            }

        }
        return Math.min(step1,step2);
    }
    // 为了测试
    public static String randomString(int maxLen) {
        char[] str = new char[(int) (Math.random() * maxLen)];
        for (int i = 0; i < str.length; i++) {
            str[i] = Math.random() < 0.5 ? 'G' : 'B';
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            String str = randomString(maxLen);
            int ans1 = minSteps1(str);
            int ans2 = minSteps2(str);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
