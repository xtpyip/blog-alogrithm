package blog.dddt.class02;

/**
 * @ClassName: Code02_Cola
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 贩卖机只支持硬币支付，且收退都只支持10 ，50，100三种面额
 * 一次购买只能出一瓶可乐，且投钱和找零都遵循优先使用大钱的原则
 * 需要购买的可乐数量是m，其中手头拥有的10、50、100的数量分别为a、b、c，可乐的价格是x(x是10的倍数)
 * 请计算出需要投入硬币次数
 **/
public class Code02_Cola {
    // 暴力尝试，为了验证正式方法而已
    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = { 100, 50, 10 };
        int[] zhang = { c, b, a };
        int puts = 0;
        while (m != 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            puts += cur;
            m--;
        }
        return puts;
    }

    public static int buy(int[] qian, int[] zhang, int rest) {
        int first = -1;
        for (int i = 0; i < 3; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }
        if (qian[first] >= rest) {
            zhang[first]--;
            giveRest(qian, zhang, first + 1, qian[first] - rest, 1);
            return 1;
        } else {
            zhang[first]--;
            int next = buy(qian, zhang, rest - qian[first]);
            if (next == -1) {
                return -1;
            }
            return 1 + next;
        }
    }
    public static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int times) {
        for (; i < 3; i++) {
            zhang[i] += (oneTimeRest / qian[i]) * times;
            oneTimeRest %= qian[i];
        }
    }

    // 正式的方法
    // 要买的可乐数量，m
    // 100元有a张
    // 50元有b张
    // 10元有c张
    // 可乐单价x
    public static int putTimes(int m, int a, int b, int c, int x) {
        //              0    1   2
        int[] qian = { 100, 50, 10 };
        int[] zhang = { c,  b,  a };
        // 总共需要多少次投币
        int puts = 0;
        // 之前面值的钱还剩下多少总钱数
        int preQianRest = 0;
        // 之前面值的钱还剩下多少总张数
        int preQianZhang = 0;
        for (int i = 0; i < 3 && m != 0; i++) {
            // 要用之前剩下的钱、当前面值的钱，共同买第一瓶可乐
            // 之前的面值剩下多少钱，是preQianRest
            // 之前的面值剩下多少张，是preQianZhang
            // 之所以之前的面值会剩下来，一定是剩下的钱，一直攒不出一瓶可乐的单价
            // 当前的面值付出一些钱+之前剩下的钱，此时有可能凑出一瓶可乐来
            // 那么当前面值参与搞定第一瓶可乐，需要掏出多少张呢？就是curQianFirstBuyZhang
            int curQianFirstBuyZhang = (x - preQianRest + qian[i] - 1) / qian[i];
            if (zhang[i] >= curQianFirstBuyZhang) { // 如果之前的钱和当前面值的钱，能凑出第一瓶可乐
                // 凑出来了一瓶可乐也可能存在找钱的情况，
                giveRest(qian, zhang, i + 1, (preQianRest + qian[i] * curQianFirstBuyZhang) - x, 1);
                puts += curQianFirstBuyZhang + preQianZhang;
                zhang[i] -= curQianFirstBuyZhang;
                m--;
            } else { // 如果之前的钱和当前面值的钱，不能凑出第一瓶可乐
                preQianRest += qian[i] * zhang[i];
                preQianZhang += zhang[i];
                continue;
            }
            // 凑出第一瓶可乐之后，当前的面值有可能能继续买更多的可乐
            // 以下过程就是后续的可乐怎么用当前面值的钱来买
            // 用当前面值的钱，买一瓶可乐需要几张
            int curQianBuyOneColaZhang = (x + qian[i] - 1) / qian[i];
            // 用当前面值的钱，一共可以搞定几瓶可乐
            int curQianBuyColas = Math.min(zhang[i] / curQianBuyOneColaZhang, m);
            // 用当前面值的钱，每搞定一瓶可乐，收货机会吐出多少零钱
            int oneTimeRest = qian[i] * curQianBuyOneColaZhang - x;
            // 每次买一瓶可乐，吐出的找零总钱数是oneTimeRest
            // 一共买的可乐数是curQianBuyColas，所以把零钱去提升后面几种面值的硬币数，
            // 就是giveRest的含义
            giveRest(qian, zhang, i + 1, oneTimeRest, curQianBuyColas);
            // 当前面值去搞定可乐这件事，一共投了几次币
            puts += curQianBuyOneColaZhang * curQianBuyColas;
            // 还剩下多少瓶可乐需要去搞定，继续用后面的面值搞定去吧
            m -= curQianBuyColas;
            // 当前面值可能剩下若干张，要参与到后续买可乐的过程中去，
            // 所以要更新preQianRest和preQianZhang
            zhang[i] -= curQianBuyOneColaZhang * curQianBuyColas;
            preQianRest = qian[i] * zhang[i];
            preQianZhang = zhang[i];
        }
        return m == 0 ? puts : -1;
    }





}
