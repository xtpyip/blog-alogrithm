package blog.wstx.class31;

import java.util.*;

public class Code02_FallingSquares {

    public List<Integer> fallingSquares(int[][] positions) {
        // 为了防止贴边，我们把positions[i]的所在x轴区间改为positions[i][0]~positions[i][0]+positions[i][1]-1
        // index用于节省空间，将100，199，200，299直接映射为1，2，3，4，只需要使用5*4的max空间
        // 在100~199之间进行更新等价于在1~2之间进行更新
        HashMap<Integer, Integer> map = index(positions);
        int N = map.size();
        SegmentTree segmentTree = new SegmentTree(N);
        int max = 0;
        List<Integer> res = new ArrayList<>();
        // 每落一个正方形，收集一下，所有东西组成的图像，最高高度是什么
        for (int[] arr : positions) {
            int L = map.get(arr[0]);
            int R = map.get(arr[0] + arr[1] - 1);
            int height = segmentTree.query(L, R, 1, N, 1) + arr[1];
            max = Math.max(max, height);
            res.add(max);
            segmentTree.update(L, R, height, 1, N, 1);
        }
        return res;
    }
    public static HashMap<Integer,Integer> index(int[][] positions){
        TreeSet<Integer> pos = new TreeSet<>();
        for (int[] arr : positions) {
            pos.add(arr[0]);
            pos.add(arr[0] + arr[1] - 1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (Integer index : pos) {
            map.put(index,++count);
        }
        return map;
    }
    public static class SegmentTree{
        int MAXN;
        int[] max;
        int[] arr;
        int[] change;
        boolean[] update;
        public SegmentTree(int N){
            MAXN = N + 1;
            arr = new int[MAXN];
            max = new int[MAXN << 2];
            change = new int[MAXN << 2];
            update = new boolean[MAXN << 2];
        }

        public void pushUp(int rt){
            max[rt] = Math.max(max[rt << 1] , max[rt << 1 | 1]);
        }
        public void pushDown(int rt){
            if(update[rt]){
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                max[rt << 1] = change[rt];
                max[rt << 1 | 1] = change[rt];
                update[rt] = false;
            }
        }
        public void update(int L,int R,int C,int l,int r,int rt){
            if(L <= l && r <= R){
                update[rt] = true;
                change[rt] = C;
                max[rt] = C;
                return;
            }
            int mid = ((r - l) >> 1) + l;
            pushDown(rt);
            if(L <= mid){
                update(L,R,C,l,mid,rt << 1);
            }
            if(mid < R){
                update(L,R,C,mid+1,r,rt << 1 | 1);
            }
            pushUp(rt);
        }
        public int query(int L,int R,int l,int r,int rt){
            if(L <= l && r <= R){
                return max[rt];
            }
            int mid = ((r - l) >> 1) + l;
            pushDown(rt);
            int ans = 0;
            if(L <= mid) ans = Math.max(ans,query(L,R,l,mid,rt << 1));
            if(mid < R) ans = Math.max(ans,query(L,R,mid + 1,r,rt << 1 | 1));
            return ans;
        }
    }

    public static void main(String[] args) {
        Code02_FallingSquares f = new Code02_FallingSquares();
        int[][] dp = new int[2][2];
        dp[0][0] = 100;
        dp[0][1] = 100;
        dp[1][0] = 200;
        dp[1][1] = 100;
        List<Integer> integers = f.fallingSquares(dp);
        integers.stream().forEach(System.out::println);
    }


}