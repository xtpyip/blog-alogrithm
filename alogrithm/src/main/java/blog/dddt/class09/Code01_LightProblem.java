package blog.dddt.class09;

/**
 * 给定一个数组arr，长度为N，arr中的值不是0就是1。arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
 * 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+1栈灯的状态
 * <p>
 * 问题一：如果N栈灯排成一条直线,请问最少按下多少次开关？
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关只能影响0和1位置的灯
 * N-1号灯的开关只能影响N-2和N-1位置的灯
 * <p>
 * 问题二：如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关能影响N-1、0和1位置的灯
 * N-1号灯的开关能影响N-2、N-1和0位置的灯
 */
public class Code01_LightProblem {

    // 给定一个数组arr，长度为N，arr中的值不是0就是1。arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
    // 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+1栈灯的状态
    public static int lightProblem1(int[] arr) {
        if (arr == null || arr.length < 1) return 0;
        if (arr.length == 1) return arr[0] ^ 1;
        // 当且仅当arr[0]与arr[1]一致时，才可能有使共全亮
        if (arr.length == 2) return (arr[0] ^ arr[1]) == 0 ? arr[0] ^ 1 : Integer.MAX_VALUE;
        // 三条数据及以上
        int p1 = process(arr, 2, arr[0], arr[1]);// 0位置没有按
        int p2 = process(arr,2,arr[0]^1,arr[1]^1);// 0位置按了一下
        p2 = p2==Integer.MAX_VALUE? p2 : p2+1;
        return Math.min(p1,p2);
    }

    // cur = next-1
    // preStatus = arr[cur-1]
    // curStatus = arr[cur]
    // 这有个默认的潜台词，next-2之前(不包括next-2)是全为1的
    // 两条路只会走其中一条
    public static int process(int[] arr, int next, int preStatus, int curStatus) {
        if (next == arr.length) {
            // cur来到是最后一个位置，如果此时preStatus与curStatus一致，则能使其全亮
            // 如果一致，preStatus为0，则需要按一下，否则，不用按
            return (preStatus ^ curStatus) == 0 ? (preStatus ^ 1) : Integer.MAX_VALUE;
        }
        if (preStatus == 0) {
            // 必须要按，不然不能保证所有的数据全为1
            int p = process(arr, next + 1, curStatus ^ 1, arr[next] ^ 1);
            return p == Integer.MAX_VALUE ? p : p + 1; // 当前next-1时按了一下
        } else {
            // 必须不能按
            return process(arr, next + 1, curStatus, arr[next]);
        }
    }
    // 无环改灯问题的迭代版本
    public static int noLoopMinStep2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        int p1 = traceNoLoop(arr, arr[0], arr[1]);
        int p2 = traceNoLoop(arr, arr[0] ^ 1, arr[1] ^ 1);
        p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
        return Math.min(p1, p2);
    }

    public static int traceNoLoop(int[] arr, int preStatus, int curStatus) {
        int i = 2;
        int op = 0;
        while (i != arr.length) {
            if (preStatus == 0) {
                op++;
                preStatus = curStatus ^ 1;
                curStatus = arr[i++] ^ 1;
            } else {
                preStatus = curStatus;
                curStatus = arr[i++];
            }
        }
        return (preStatus != curStatus) ? Integer.MAX_VALUE : (op + (curStatus ^ 1));
    }

    // 有环
    // 给定一个数组arr，长度为N，arr中的值不是0就是1。arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
    // 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+1栈灯的状态
    // 0位置会改变0，N-1，1位置值
    // N-1位置会改变0，N-1,N-2位置值
    public static int lightProblem2(int[] arr) {
        if (arr == null || arr.length < 1) return 0;
        if (arr.length == 1) return arr[0] ^ 1;
        // 当且仅当arr[0]与arr[1]一致时，才可能有使共全亮
        if (arr.length == 2) return (arr[0] ^ arr[1]) == 0 ? arr[0] ^ 1 : Integer.MAX_VALUE;
        if (arr.length == 3) {
            return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        int N = arr.length;
        // 三条数据以上
        // 0不变，1不变
        int p1 = processLoop(arr, 3, arr[1], arr[2], arr[arr.length - 1], arr[0]);
        // 0改变，1不变
        int p2 = processLoop(arr, 3, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
        // 0不变，1改变
        int p3 = processLoop(arr, 3, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
        // 0改变，1改变
        int p4 = processLoop(arr, 3, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
        p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
        p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
        p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }

    // cur = next-1
    // preStatus = arr[cur-1]
    // curStatus = arr[cur]
    // firstStatus = arr[0]更改后的数据
    // endStatus = arr[N-1]更改后的数据
    // 这有个默认的潜台词，next-2之前(不包括next-2)是全为1的
    // 两条路只会走其中一条
    public static int processLoop(int[] arr,
                               int nextIndex, int preStatus, int curStatus,
                               int endStatus, int firstStatus) {

        if (nextIndex == arr.length) { // 最后一按钮！
            return (endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (endStatus ^ 1);
        }
        // 当前位置，nextIndex - 1
        // 当前的状态，叫curStatus
        // 如果不按下按钮，下一步的preStatus, curStatus
        // 如果按下按钮，下一步的preStatus, curStatus ^ 1
        // 如果不按下按钮，下一步的curStatus, arr[nextIndex]
        // 如果按下按钮，下一步的curStatus, arr[nextIndex] ^ 1
        int noNextPreStatus = 0;
        int yesNextPreStatus = 0;
        int noNextCurStatus = 0;
        int yesNextCurStatus = 0;
        int noEndStatus = 0;
        int yesEndStatus = 0;
        if (nextIndex < arr.length - 1) {// 当前没来到N-2位置
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = arr[nextIndex];
            yesNextCurStatus = arr[nextIndex] ^ 1;
        } else if (nextIndex == arr.length - 1) {// 当前来到的就是N-2位置
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = endStatus;
            yesNextCurStatus = endStatus ^ 1;
            noEndStatus = endStatus;
            yesEndStatus = endStatus ^ 1;
        }
        if (preStatus == 0) {
            int next = processLoop(arr, nextIndex + 1, yesNextPreStatus, yesNextCurStatus,
                    nextIndex == arr.length - 1 ? yesEndStatus : endStatus, firstStatus);
            return next == Integer.MAX_VALUE ? next : (next + 1);
        } else {
            return processLoop(arr, nextIndex + 1, noNextPreStatus, noNextCurStatus,
                    nextIndex == arr.length - 1 ? noEndStatus : endStatus, firstStatus);

        }
    }
    // 有环改灯问题的迭代版本
    // 有环改灯问题的迭代版本
    public static int loopMinStep2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        if (arr.length == 3) {
            return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        // 0不变，1不变
        int p1 = traceLoop(arr, arr[1], arr[2], arr[arr.length - 1], arr[0]);
        // 0改变，1不变
        int p2 = traceLoop(arr, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
        // 0不变，1改变
        int p3 = traceLoop(arr, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
        // 0改变，1改变
        int p4 = traceLoop(arr, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
        p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
        p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
        p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }
    public static int traceLoop(int[] arr, int preStatus, int curStatus, int endStatus, int firstStatus) {
        int i = 3;
        int op = 0;
        while (i < arr.length - 1) {
            if (preStatus == 0) {
                op++;
                preStatus = curStatus ^ 1;
                curStatus = (arr[i++] ^ 1);
            } else {
                preStatus = curStatus;
                curStatus = arr[i++];
            }
        }
        if (preStatus == 0) {
            op++;
            preStatus = curStatus ^ 1;
            endStatus ^= 1;
            curStatus = endStatus;
        } else {
            preStatus = curStatus;
            curStatus = endStatus;
        }
        return (endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (op + (endStatus ^ 1));
    }
    // 生成长度为len的随机数组，值只有0和1两种值
    public static int[] randomArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 2);
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("如果没有任何Oops打印，说明所有方法都正确");
        System.out.println("test begin");
        int testTime = 20000;
        int lenMax = 12;
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * lenMax);
            int[] arr = randomArray(len);
            int ans1 = lightProblem1(arr);
            int ans3 = noLoopMinStep2(arr);
            if (ans1 != ans3) {
                System.out.println("1 Oops!");
            }
        }
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * lenMax);
            int[] arr = randomArray(len);
            int ans1 = lightProblem2(arr);
            int ans3 = loopMinStep2(arr);
            if ( ans1 != ans3) {
                System.out.println("2 Oops!");
            }
        }
        System.out.println("test end");

        int len = 100000000;
        System.out.println("性能测试");
        System.out.println("数组大小：" + len);
        int[] arr = randomArray(len);
        long start = 0;
        long end = 0;
        start = System.currentTimeMillis();
        noLoopMinStep2(arr);
        end = System.currentTimeMillis();
        System.out.println("noLoopMinStep2 run time: " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        loopMinStep2(arr);
        end = System.currentTimeMillis();
        System.out.println("loopMinStep2 run time: " + (end - start) + "(ms)");
    }

}
