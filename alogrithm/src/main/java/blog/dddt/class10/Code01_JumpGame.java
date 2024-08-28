package blog.dddt.class10;

/**
 * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。假设你总是可以到达数组的最后一个位置。
 * https://leetcode.cn/problems/jump-game-ii/
 */
public class Code01_JumpGame {

    public static int jump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int step = 0; // 当前使用步数
        int cur = 0; // 当前使用的步数最远能到达的位置
        int next = 0; // 下一个步数最远能到达的位置
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                step++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }



}
