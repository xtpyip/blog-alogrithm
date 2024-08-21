package blog.dddt.class02;

import java.util.HashMap;

/**
 * @ClassName: Code05_SetAll
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 设计有setAll功能的哈希表，put、get、setAll方法，时间复杂度O(1)
 **/
public class Code05_SetAll {
     static class HashWithSetAll{
        static class Help{
             Integer value;
             Integer time;
             public Help(){}
             public Help(int value,int time){this.value=value;this.time=time;}
         }
        private int time;
         private Help allValue;
        private HashMap<Integer,Help> map;

        public HashWithSetAll(){
            map = new HashMap<>();
            time = 0;
            allValue = new Help(0,-1);
        }

        public void add(Integer key,Integer value){
            // 添加和修改
            map.put(key,new Help(value,time++));
        }
        public Integer get(Integer key){
            Help help = map.get(key);
            return allValue.time > help.time ? allValue.value : help.value;
        }
        public void setAll(int value){
            allValue = new Help(time++,value);
        }
    }
}
