package blog.wstx.class15;

/**
 * @ClassName: Code02_NumberOfIsLands
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 岛屿的数量
 **/
public class Code02_NumberOfIsLands {
    public int numIslands1(char[][] grid) {
        int n = grid.length,m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == '1'){
                    ans++;
                    inject(grid,i,j);
                }
            }
        }
        return ans;
    }
    public static void inject(char[][] grid,int i,int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1'){
            return;
        }
        grid[i][j] = '#';
        inject(grid,i-1,j);
        inject(grid,i,j-1);
        inject(grid,i+1,j);
        inject(grid,i,j+1);
    }

    public int numIslands2(char[][] grid) {
        int n = grid.length,m = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                if(grid[i][j] == '1' && grid[i][j+1] == '1'){
                    uf.union(i,j,i,j+1);
                }
                if(grid[i][j] == '1' && grid[i+1][j] == '1'){
                    uf.union(i,j,i+1,j);
                }
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if(grid[i][m-1] == '1' && grid[i+1][m-1] == '1') uf.union(i,m-1,i+1,m-1);
        }
        for (int i = 0; i < m - 1; i++) {
            if(grid[n-1][i] == '1' && grid[n-1][i+1] == '1') uf.union(n-1,i,n-1,i+1);
        }

        return uf.size();
    }
    public static class UnionFind{
        private static int[] parent;
        private static int[] size;
        private static int[] help;
        private static int col;
        private static int sets;
        public UnionFind(char[][] board) {
            col = board[0].length;
            sets = 0;
            int row = board.length;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c] == '1') {
                        int i = r * col + c;
                        parent[i] = i;
                        size[i] = 1;
                        sets++;
                    }
                }
            }
        }
        // find
        public int find(int r,int c){
            int index = col * r + c;
            int hi = 0;
            while (index != parent[index]){
                help[hi++] = index;
                index = parent[index];
            }
            while (hi > 0){
                parent[help[--hi]] = index;
            }
            return index;
        }
        // union
        public void union(int r1,int c1,int r2,int c2){
            int father1 = find(r1, c1);
            int father2 = find(r2, c2);
            if(father1 != father2){
                int size1 = size[father1];
                int size2 = size[father2];
                if(size1 > size2){
                    parent[father2] = father1;
                    size[father1] = size1 + size2;
                    size[father2] = 0;
                }else{
                    parent[father1] = father2;
                    size[father2] = size1 + size2;
                    size[father1] = 0;
                }
                sets--;
            }
        }
        public int size(){
            return sets;
        }
    }

}
