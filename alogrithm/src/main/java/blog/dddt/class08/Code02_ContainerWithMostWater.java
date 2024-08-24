package blog.dddt.class08;

/**
 * @ClassName: Code02_ContainerWithMostWater
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 
 * 给定n个非负整数a1，a2，...an，每个数代表坐标中的一个点 (i, ai)。在坐标内画n条垂直线
 * 垂直线i的两个端点分别为(i, ai)和(i, 0)，找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水
 * Leetcode题目：https://leetcode.cn/problems/container-with-most-water/
 **/
public class Code02_ContainerWithMostWater {
    // 贪心
    public int maxArea1(int[] height) {
        int ans = Integer.MIN_VALUE;
        int L = 0,R = height.length - 1;
        while (L < R){
            ans = Math.max(ans,(R-L)*(Math.min(height[L],height[R])));
            if(height[L] < height[R]){
                L++;
            }else{
                R--;
            }
        }
        return ans;
    }
    // 暴力
    public static int maxArea2(int[] h) {
        int max = 0;
        int N = h.length;
        for (int i = 0; i < N; i++) { // h[i]
            for (int j = i + 1; j < N; j++) { // h[j]
                max = Math.max(max, Math.min(h[i], h[j]) * (j - i));
            }
        }
        return max;
    }
}
