package blog.dddt.class07;

import blog.wstx.class12.TreeNode;

/**
 * @ClassName: Code02_MinCameraCover
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定一个二叉树，我们在树的节点上安装摄像头，
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象，
 * 计算监控树的所有节点所需的最小摄像头数量
 * https://leetcode.cn/problems/binary-tree-cameras/description/
 **/
public class Code02_MinCameraCover {
    public int minCameraCover1(TreeNode root) {
        Help data = process1(root);
        return (int)(Math.min(data.uncovered+1, Math.min(data.coveredNoCamera,data.coveredHasCamera)));
    }
    public static class Help{
        public long uncovered;// x没有被覆盖，x为头的树至少需要几个相机
        public long coveredNoCamera; // x被相机覆盖，但是x没相机，x为头的树至少需要几个相机
        public long coveredHasCamera; // x被相机覆盖了，并且x上放了相机，x为头的树至少需要几个相机
        public Help(long u,long c,long h){uncovered=u;coveredNoCamera=c;coveredHasCamera=h;}
    }

    public static Help process1(TreeNode cur){
        if(cur == null) return new Help(Integer.MAX_VALUE,0l,Integer.MAX_VALUE);
        Help l = process1(cur.left);
        Help r = process1(cur.right);
        // 一共有下面几种情况
        // 左右两边被全覆盖且当前没有被覆盖
        long uncovered = l.coveredNoCamera + r.coveredNoCamera;
        // cur要被覆盖，但不能有相机
        long coveredNoCamera = Math.min(
                l.coveredHasCamera+r.coveredHasCamera,
                Math.min(l.coveredHasCamera+r.coveredNoCamera,
                        l.coveredNoCamera+r.coveredHasCamera)
        );
        // cur要被覆盖，且要有相机
//        long coveredHasCamera = Math.min(
//           l.uncovered + r.uncovered,
//           l.uncovered + r.coveredNoCamera,
//           l.uncovered + r.coveredHasCamera,
//                l.coveredNoCamera + r.uncovered,
//                l.coveredNoCamera + r.coveredNoCamera,
//                l.coveredNoCamera + r.coveredHasCamera,
//                l.coveredHasCamera + r.uncovered,
//                l.coveredHasCamera + r.coveredNoCamera,
//                l.coveredHasCamera + r.coveredHasCamera
//        )+1;
//        简化
        long coveredHasCamera = Math.min(l.uncovered, Math.min(l.coveredNoCamera, l.coveredHasCamera))

                + Math.min(r.uncovered, Math.min(r.coveredNoCamera, r.coveredHasCamera))

                + 1;

        return new Help(uncovered,coveredNoCamera,coveredHasCamera);
    }

    public int minCameraCover2(TreeNode root) {
        Data data = process2(root);
        return data.camera + (data.status == Status.NO_COVERED ? 1 : 0);
    }
    public static enum Status{
        NO_COVERED,COVERED_NO_CAMERA,COVERED_HAS_CAMERA
    }
    public static class Data{
        public Status status;
        public int camera;
        public Data(Status x,int c){status=x;camera=c;}
    }
    // 叶子节点全部不能放相机(贪心)
    public static Data process2(TreeNode cur){
        if(cur == null) return new Data(Status.COVERED_NO_CAMERA,0);
        Data l = process2(cur.left);
        Data r = process2(cur.right);
        int cameras = l.camera + r.camera;

        // 左右都没覆盖，必要放一个
        if(l.status == Status.NO_COVERED || r.status == Status.NO_COVERED){
            return new Data(Status.COVERED_HAS_CAMERA,cameras+1);
        }
        // 左右孩子，不存在没被覆盖的情况
        if(l.status == Status.COVERED_HAS_CAMERA || r.status == Status.COVERED_HAS_CAMERA){
            return new Data(Status.COVERED_NO_CAMERA,cameras);
        }
        // 左右孩子，不存在没被覆盖的情况，且都没有放相机
        return new Data(Status.NO_COVERED,cameras);
    }

}
