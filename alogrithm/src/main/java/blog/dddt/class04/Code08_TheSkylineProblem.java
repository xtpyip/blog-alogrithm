package blog.dddt.class04;

import java.util.*;

/**
 * @ClassName: Code08_TheSkylineProblem
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 大楼轮廓线问题
 * https://leetcode.cn/problems/the-skyline-problem/
 **/
public class Code08_TheSkylineProblem {
     class Help{
        int x;
        boolean isAdd;
        int height;
        public Help(){}
        public Help(int x,boolean isAdd,int height){this.x=x;this.isAdd=isAdd;this.height=height;}
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        Help[] helps = new Help[buildings.length * 2];
        for (int i = 0; i < buildings.length; i++) {
            helps[i*2] = new Help(buildings[i][0],true,buildings[i][2]);
            helps[i*2+1] = new Help(buildings[i][1],false,buildings[i][2]);
        }
        // 按照x的值从小到大，也可以把+放前面，防止出现[17,17,9]这种数据干扰
        Arrays.sort(helps,(a,b)->{return a.x-b.x;});
        // key:height,time:次数，次数为0时删除，防止数据干扰
        TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
        // key:x轴位置 value:最大高度
        TreeMap<Integer, Integer> mapXHeight = new TreeMap<>();
        for (Help help : helps) {
            if (help.isAdd) {
                if (!mapHeightTimes.containsKey(help.height)) {
                    mapHeightTimes.put(help.height, 1);
                } else {
                    mapHeightTimes.put(help.height, mapHeightTimes.get(help.height) + 1);
                }
            }else {
                if (mapHeightTimes.get(help.height) == 1) {
                    mapHeightTimes.remove(help.height);
                } else {
                    mapHeightTimes.put(help.height, mapHeightTimes.get(help.height) - 1);
                }
            }
            if(mapHeightTimes.isEmpty()){
                mapXHeight.put(help.x,0);
            }else{
                // 不为空，放当前最大
                mapXHeight.put(help.x,mapHeightTimes.lastKey());
            }
        }

        // 对mapXHeight的所有数据进行处理
        ArrayList<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : mapXHeight.entrySet()) {
            int curX = entry.getKey();
            int curMaxHeight = entry.getValue();
            if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight) {
                ans.add(new ArrayList<>(Arrays.asList(curX, curMaxHeight)));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code08_TheSkylineProblem f = new Code08_TheSkylineProblem();
        List<List<Integer>> skyline = f.getSkyline(new int[][]{
                {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}
        });
    }

    
}
