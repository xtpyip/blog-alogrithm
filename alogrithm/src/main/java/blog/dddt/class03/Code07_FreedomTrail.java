package blog.dddt.class03;

import java.util.*;

/**
 * @ClassName: Code07_FreedomTrail
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门
 * 给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数
 * 最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 * 您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
 * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 *
 * Leetcode题目：https://leetcode.cn/problems/freedom-trail/
 **/
public class Code07_FreedomTrail {

    // 思路一：暴力递归 超出时间限制 49 / 303 个通过的测试用例
    public int findRotateSteps1(String ring, String key) {
        // 首先，我们要记录一下所有的字符出现的位置
        HashMap<Character, List<Integer>> map = new HashMap<>();
        char[] chars = ring.toCharArray();
        int N = chars.length;
        for (int i = 0; i < N; i++) {
            if(!map.containsKey(chars[i])){
                map.put(chars[i],new ArrayList<>());
            }
            map.get(chars[i]).add(i);
        }
        // map中的记录为
        // a : 1,3,5 b:2,7 e:4,6
        // 如果key中存在ring中没有的字符，那么一定不可能搞出来
        char[] keyChars = key.toCharArray();
        int M = keyChars.length;
        for (int i = 0; i < M; i++) { // 这里题目保证一定能拼出了，不写也行
            if(!map.containsKey(keyChars[i])){
                return -1;
            }
        }
        return process(0,0,keyChars,map,N);
    }

    public static int process(int index, int preRingIndex, char[] key, Map<Character,List<Integer>> map,int N){
        if(index == key.length){
            return 0;
        }
        // 当前字符为key[index]，目前播盘的index在preRingIndex上
        // 我们要找的是从preRingIndex到所有ring上字符值为key[index]的下标上
        int ans = Integer.MAX_VALUE;
        for (Integer nextRingIndex : map.get(key[index])) {
            // 存在一个确认步
            int nextSteps = minStep(preRingIndex, nextRingIndex, N) + 1 + process(index + 1, nextRingIndex, key, map, N);
            ans = Math.min(ans,nextSteps);
        }
        return ans;
    }

    // 首先，我们需要一个小函数，帮助我们计算旋转的步数
    public static int minStep(int i,int j,int length){
        // 已经明确了要从i位置跑到j位置，但我们既可以顺时针也可以逆时针旋转
        // 只要取其最小的即可
        return Math.min(Math.abs(j - i),length - Math.max(i,j) + Math.min(i,j));

    }
    // 思路二：记忆化搜索,可以通过
    public int findRotateSteps2(String ring, String key) {
        // 首先，我们要记录一下所有的字符出现的位置
        HashMap<Character, List<Integer>> map = new HashMap<>();
        char[] chars = ring.toCharArray();
        int N = chars.length;
        for (int i = 0; i < N; i++) {
            if(!map.containsKey(chars[i])){
                map.put(chars[i],new ArrayList<>());
            }
            map.get(chars[i]).add(i);
        }
        // map中的记录为
        // a : 1,3,5 b:2,7 e:4,6
        // 如果key中存在ring中没有的字符，那么一定不可能搞出来
        char[] keyChars = key.toCharArray();
        int M = keyChars.length;
        for (int i = 0; i < M; i++) { // 这里题目保证一定能拼出了，不写也行
            if(!map.containsKey(keyChars[i])){
                return -1;
            }
        }
        // minStep[i][j]表示当前到了key[i],并且ring指针在j位置上之后最小值为多少
        int[][] minStep = new int[M][N];
        return process2(0,0,keyChars,map,N,minStep);
    }

    public static int process2(int index, int preRingIndex, char[] key, Map<Character,List<Integer>> map,int N,int[][] dp){
        if(index == key.length){
            return 0;
        }
        if(dp[index][preRingIndex] != 0) return dp[index][preRingIndex];
        // 当前字符为key[index]，目前播盘的index在preRingIndex上
        // 我们要找的是从preRingIndex到所有ring上字符值为key[index]的下标上
        int ans = Integer.MAX_VALUE;
        for (Integer nextRingIndex : map.get(key[index])) {
            // 存在一个确认步
            int nextSteps = minStep(preRingIndex, nextRingIndex, N) + 1 + process2(index + 1, nextRingIndex, key, map, N,dp);
            ans = Math.min(ans,nextSteps);
        }
        dp[index][preRingIndex] = ans;
        return ans;
    }
    // 可以省掉枚举行为
    // 提交时把findRotateSteps2方法名字改成findRotateSteps可以直接通过
    // 来龙去脉 : https://www.mashibing.com/question/detail/67299
    // 例 :
    // ring = aaca
    // key = ca
    // 首先来到 2 位置的 c, 下一步有 0 1 3 三个位置的 a 可以选择
    // 只需要在 1 3 里面选择最优的即可, 因为 0 位置的 a 在任何情况下都不会比 1 位置的 a 更优
    // 这是对的
    // 应该是可以的，课上讲述的方法根据数据量能过，就没有继续优化了。
    // 如下的贪心方式不对：
    // 当前来到的位置，然后要去下一个字符，左边离下一个字符近就选左边，左边离下一个字符近就选右边。
    // 两条路只选1条，彻底的每一步都选当前步最优。
    // 但陆振星同学的贪心方式是：
    // 1）当前来到的位置，然后要去下一个字符，左边离下一个字符最近的位置，去往左边，然后可能性展开，选最好的解；
    // 2）当前来到的位置，然后要去下一个字符，右边离下一个字符最近的位置，去往右边，然后可能性展开，选最好的解；
    // 1）和2）中最好的解，选出来，返回。
    // 这是对的，因为如果去往离左右两边都更远的位置，那么为什么不在走的过程中，顺带就满足了下一个字符呢？
    // 这样还能省掉枚举行为
    // 如下所有代码都提交，再次注意：提交时把findRotateSteps3方法名字改成findRotateSteps可以直接通过
    private int ringLength;
    private char[] key;
    private ArrayList<TreeSet<Integer>> ringSet;
    private final HashMap<Integer, Integer> dp = new HashMap<>();

    public int findRotateSteps3(String ring, String key) {
        char[] chars = ring.toCharArray();
        this.key = key.toCharArray();
        ringLength = chars.length;
        ringSet = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            ringSet.add(new TreeSet<>());
        }
        for (int i = 0; i < chars.length; i++) {
            ringSet.get(chars[i] - 'a').add(i);
        }
        return findRotateSteps(0, 0);
    }

    // kIndex : 当前要搞定的字符
    // cur : 当前所在的位置
    private int findRotateSteps(int kIndex, int cur) {
        if (kIndex == key.length) {
            return 0;
        }
        // kIndex 和 cur 的最大值为 100, 所以用 十进制的低两位 表示 cur, 用高两位表示 kIndex
        int k = kIndex * 100 + cur - 1;
        Integer v = dp.get(k);
        if (v != null) {
            return v;
        } else {
            v = Integer.MAX_VALUE;
        }
        // key[kIndex] 的所有位置
        TreeSet<Integer> treeSet = ringSet.get(key[kIndex] - 'a');
        // 从 cur 向左走, 最近的符合 key[kIndex] 的位置
        Integer floor = treeSet.floor(cur);
        if (floor == null) {
            floor = treeSet.last();
        }
        int len = Math.abs(cur - floor);
        len = Math.min(len, ringLength - len);
        v = Math.min(v, len + 1 + findRotateSteps(kIndex + 1, floor));
        // 从 cur 向右走, 最近的符合 key[kIndex] 的位置
        Integer ceiling = treeSet.ceiling(cur);
        if (ceiling == null) {
            ceiling = treeSet.first();
        }
        len = Math.abs(cur - ceiling);
        len = Math.min(len, ringLength - len);
        v = Math.min(v, len + 1 + findRotateSteps(kIndex + 1, ceiling));
        dp.put(k, v);
        return v;
    }

}
