package blog.wstx.class15;

/**
 * @ClassName: Code01_FriendCircles
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 朋友圈的数量
 **/
public class Code01_FriendCircles {
    public static int findCircleNum(int[][] M) {
        int N = M.length;
        UnionFind uf = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if(M[i][j] == 1){
                    uf.union(i,j);
                }
            }
        }
        return uf.size();
    }
    public static class UnionFind{
        public static int[] parent;
        public static int[] size;
        public static int[] help;
        public UnionFind(int length){
            parent = new int[length];
            size = new int[length];
            help = new int[length];
            init();
        }
        public void init(){
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        // 查找代表位置
        public  int find(int i){
            int helpIndex = 0;
            while (i != parent[i]){
                help[helpIndex++] = i;
                i = parent[i];
            }
            while (helpIndex > 0){
                parent[help[--helpIndex]] = i;
            }
            return i;
        }
        // 合并
        public  void union(int x,int y){
            int xFather = find(x);
            int yFather = find(y);
            if(xFather != yFather){
                int xSize = size[xFather];
                int ySize = size[yFather];
                if(xSize > ySize){
                    parent[yFather] = xFather;
                    size[xFather] = ySize + xSize;
                    size[yFather] = 0;
                }else{
                    parent[xFather] = yFather;
                    size[yFather] = ySize + xSize;
                    size[xFather] = 0;
                }
            }
        }
        // 集合数量
        // 返回集合的数量
        public static int size(){
            int count = 0;
            for (int i = 0; i < size.length; i++) {
                if(size[i] != 0) count++;
            }
            return count;
        }
    }

}
