package blog.wstx.class14;

import java.io.*;

/**
 * @ClassName: Code06_UnionFind
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 数组实现并查集
 **/
public class Code06_UnionFind {
    public static class UnionFind{
        public static int MAXN = 1000001;
        public static int[] father = new int[MAXN];
        public static int[] size = new int[MAXN];
        public static int[] help = new int[MAXN];

        // 初始化并查集
        public static void init(int n) {
            for (int i = 0; i <= n; i++) {
                father[i] = i;
                size[i] = 1;
            }
        }
        // 从i开始寻找集合代表点
        public static int find(int i){
            int hi = 0;
            while (i != father[i]) {
                help[hi++] = i;
                i = father[i];
            }
            while (hi > 0){
                father[help[--hi]] = i;
            }
            return i;
        }
        // 查询x和y是不是一个集合
        public static boolean isSameSet(int x,int y){
            return find(x) == find(y);
        }
        // 合并
        public static void union(int x,int y){
            int xFather = find(x);
            int yFather = find(y);
            if(xFather != yFather){
                int xSize = size[xFather];
                int ySize = size[yFather];
                if(xSize > ySize){
                    father[yFather] = xFather;
                    size[xFather] = xSize + ySize;
                    size[yFather] = 0;
                }else{
                    father[xFather] = yFather;
                    size[yFather] = xSize + ySize;
                    size[xFather] = 0;
                }
            }
        }
        // 返回集合的数量
        public static int size(){
            int count = 0;
            for (int i = 0; i < size.length; i++) {
                if(size[i] != 0) count++;
            }
            return count;
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StreamTokenizer in = new StreamTokenizer(br);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
            while (in.nextToken() != StreamTokenizer.TT_EOF) {
                int n = (int) in.nval;
                init(n);
                in.nextToken();
                int m = (int) in.nval;
                for (int i = 0; i < m; i++) {
                    in.nextToken();
                    int op = (int) in.nval;
                    in.nextToken();
                    int x = (int) in.nval;
                    in.nextToken();
                    int y = (int) in.nval;
                    if (op == 1) {
                        out.println(isSameSet(x, y) ? "Yes" : "No");
                        out.flush();
                    } else {
                        union(x, y);
                    }
                }
            }
        }
    }


}
