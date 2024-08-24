package blog.dddt.class04;

/**
 * @ClassName: Code05_CandyProblem
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 老师想给孩子们分发糖果，有N个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
 * 那么这样下来，返回老师至少需要准备多少颗糖果
 * 进阶：在原来要求的基础上，增加一个要求，相邻的孩子间如果分数一样，分的糖果数必须一样，返回至少需要准备多少颗糖果
 **/
public class Code05_CandyProblem {


    // 使用左右两个辅助数组，选择最大的那个值
    public static int candy1(int[] ratings) {
        int N = ratings.length;
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = 1;
        int ans = 0;
        for (int i = 1; i < N; i++) {
            left[i] = ratings[i] > ratings[i-1] ? left[i-1]+1 : 1;
        }
        right[N-1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            right[i] = ratings[i] > ratings[i+1] ? right[i+1]+1 : 1;
        }
        for (int i = 0; i < N; i++) {
            ans += Math.max(left[i],right[i]);
        }
        return ans;
    }

    // 进阶问题
    // 同样是生成left与right数组，但有三个原则
    // 生成左数组，比左边大就+1 和左边一样就继承 比左边小就变1
    // 生成右数组，同样
    public static int candy2(int[] ratings){
        int N = ratings.length;
        if(N == 0) return 0;
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = 1;
        for (int i = 1; i < N; i++) {
            left[i] =  ratings[i] >= ratings[i-1] ? (ratings[i] == ratings[i-1] ? left[i-1] : left[i-1] + 1): 1;
        }
        right[N-1] = 1;
        for (int i = N - 2; i > 0; i--) {
            right[i] =  ratings[i] >= ratings[i+1] ? (ratings[i] == ratings[i+1] ? right[i+1] : right[i+1] + 1): 1;
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.max(left[i],right[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test1 = { 3, 0, 5, 5, 4, 4, 0 };
        System.out.println(candy1(test1));

        int[] test2 = { 3, 0, 5, 5, 4, 4, 0 };
        System.out.println(candy2(test2));
    }

}
