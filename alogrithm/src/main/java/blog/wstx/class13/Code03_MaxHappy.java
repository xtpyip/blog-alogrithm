package blog.wstx.class13;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Code03_MaxHappy
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 派对的最大happy值
 **/
public class Code03_MaxHappy {
    public static class Employee {
        public int happy;
        public List<Employee> nexts;
        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }
    }
    public static int maxHappy1(Employee boss){
        if(boss == null) return 0;
        return process1(boss,false); // boss自由选择
    }
    // 当前来到的节点叫cur，
    // up表示cur的上级是否来，
    // 该函数含义：
    // 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    public static int process1(Employee cur,boolean up){
        if(up){
            int ans = 0;
            for (Employee next : cur.nexts) {
                ans += process1(next,false);
            }
            return ans;
        }else{
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.nexts) {
                p1 += process1(next,true);
                p2 += process1(next,false);
            }
            return Math.max(p1,p2);
        }
    }

    public static int maxHappy2(Employee boss){
        if(boss == null) return 0;
        Info info = process2(boss);
        return Math.max(info.no,info.yes);
    }
    public static class Info {
        public int no;
        public int yes;

        public Info(int n, int y) {
            no = n;
            yes = y;
        }
    }

    public static Info process2(Employee cur){
        if(cur == null) return new Info(0,0);
        int yes = cur.happy;
        int no = 0;
        for (Employee next : cur.nexts) {
            Info nextInfo = process2(next);
            no += Math.max(nextInfo.yes,nextInfo.no);
            yes += nextInfo.no;
        }
        return new Info(no,yes);
    }
    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}

