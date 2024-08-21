package blog.dddt.class02;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @ClassName: Code01_ChooseWork
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定数组hard和money，长度都为N，hard[i]表示i号工作的难度， money[i]表示i号工作的收入
 * 给定数组ability，长度都为M，ability[j]表示j号人的能力，每一号工作，都可以提供无数的岗位，难度和收入都一样
 * 但是人的能力必须>=这份工作的难度，才能上班。返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
 **/
public class Code01_ChooseWork {
    public static int[] getMoneys(int[] hard,int[] money, int[] ability) {
        int n = hard.length;
        int[][] help = new int[n][2];
        for (int i = 0; i < n; i++) {
            help[i][0] = money[i];
            help[i][1] = hard[i];
        }
        Arrays.sort(help,(a,b)->{return a[1]==b[1] ? (b[0] - a[0]) : (a[1]-b[1]);});
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(help[0][1],help[0][0]);
        int[] pre = help[0];
        for (int i = 1; i < help.length; i++) {
            if(help[i][1] != pre[1] && help[i][0] > pre[0]){
                pre = help[i];
                map.put(pre[1],pre[0]);
            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            Integer key = map.floorKey(ability[i]);
            ans[i] = key != null ? map.get(key) : 0;
        }
        return ans;


    }
}
