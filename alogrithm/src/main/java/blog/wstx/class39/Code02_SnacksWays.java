package blog.wstx.class39;

import java.util.Map;
import java.util.TreeMap;

public class Code02_SnacksWays {

	public static int ways1(int[] arr, int w) {
		// arr[0...]
		return process(arr, 0, w);
	}

	// 从左往右的经典模型
	// 还剩的容量是rest，arr[index...]自由选择，
	// 返回选择方案
	// index ： 0～N
	// rest : 0~w
	public static int process(int[] arr, int index, int rest) {
		if (rest < 0) { // 没有容量了
			// -1 无方案的意思
			return -1;
		}
		// rest>=0,
		if (index == arr.length) { // 无零食可选
			return 1;
		}
		// rest >=0
		// 有零食index
		// index号零食，要 or 不要
		// index, rest
		// (index+1, rest)
		// (index+1, rest-arr[i])
		int next1 = process(arr, index + 1, rest); // 不要
		int next2 = process(arr, index + 1, rest - arr[index]); // 要
		return next1 + (next2 == -1 ? 0 : next2);
	}

	public static int ways2(int[] arr, int w) {
		int N = arr.length;
		int[][] dp = new int[N + 1][w + 1];
		for (int j = 0; j <= w; j++) {
			dp[N][j] = 1;
		}
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j <= w; j++) {
				dp[i][j] = dp[i + 1][j] + ((j - arr[i] >= 0) ? dp[i + 1][j - arr[i]] : 0);
			}
		}
		return dp[0][w];
	}

	public static int ways3(int[] arr, int w) {
		int N = arr.length;
		int[][] dp = new int[N][w + 1];
		for (int i = 0; i < N; i++) {
			dp[i][0] = 1;
		}
		if (arr[0] <= w) {
			dp[0][arr[0]] = 1;
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= w; j++) {
				dp[i][j] = dp[i - 1][j] + ((j - arr[i]) >= 0 ? dp[i - 1][j - arr[i]] : 0);
			}
		}
		int ans = 0;
		for (int j = 0; j <= w; j++) {
			ans += dp[N - 1][j];
		}
		return ans;
	}
	// 分治
	public static long ways(int[] arr, int bag) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0] <= bag ? 2 : 1;
		}
		int mid = (arr.length - 1) >> 1;
		TreeMap<Long, Long> lmap = new TreeMap<>();
		long ways = process(arr, 0, 0, mid, bag, lmap);
		TreeMap<Long, Long> rmap = new TreeMap<>();
		ways += process(arr, mid + 1, 0, arr.length - 1, bag, rmap);
		TreeMap<Long, Long> rpre = new TreeMap<>();
		long pre = 0;
		for (Map.Entry<Long, Long> entry : rmap.entrySet()) {
			pre += entry.getValue();
			rpre.put(entry.getKey(), pre);
		}
		for (Map.Entry<Long, Long> entry : lmap.entrySet()) {
			long lweight = entry.getKey();
			long lways = entry.getValue();
			Long floor = rpre.floorKey(bag - lweight);
			if (floor != null) {
				long rways = rpre.get(floor);
				ways += lways * rways;
			}
		}
		return ways + 1;
	}
	// arr 30
	// func(arr, 0, 14, 0, bag, map)
	// func(arr, 15, 29, 0, bag, map)
	// 从index出发，到end结束
	// 之前的选择，已经形成的累加和sum
	// 零食[index....end]自由选择，出来的所有累加和，不能超过bag，每一种累加和对应的方法数，填在map里
	// 最后不能什么货都没选
	// [3,3,3,3] bag = 6
	// 0 1 2 3
	// - - - - 0 -> （0 : 1）
	// - - - $ 3 -> （0 : 1）(3, 1)
	// - - $ - 3 -> （0 : 1）(3, 2)
	public static long func(int[] arr, int index, int end, long sum, long bag, TreeMap<Long, Long> map) {
		if (sum > bag) {
			return 0;
		}
		// sum <= bag
		if (index > end) { // 所有商品自由选择完了！
			// sum
			if (sum != 0) {
				if (!map.containsKey(sum)) {
					map.put(sum, 1L);
				} else {
					map.put(sum, map.get(sum) + 1);
				}
				return 1;
			} else {
				return 0;
			}
		}
		// sum <= bag 并且 index <= end(还有货)
		// 1) 不要当前index位置的货
		long ways = func(arr, index + 1, end, sum, bag, map);
		// 2) 要当前index位置的货
		ways += func(arr, index + 1, end, sum + arr[index], bag, map);
		return ways;
	}
	public static long process(int[] arr, int index, long w, int end, int bag, TreeMap<Long, Long> map) {
		if (w > bag) {
			return 0;
		}
		if (index > end) {
			if (w != 0) {
				if (!map.containsKey(w)) {
					map.put(w, 1L);
				} else {
					map.put(w, map.get(w) + 1);
				}
				return 1;
			} else {
				return 0;
			}
		} else {
			long ways = process(arr, index + 1, w, end, bag, map);
			ways += process(arr, index + 1, w + arr[index], end, bag, map);
			return ways;
		}
	}

	public static void main(String[] args) {
		int[] arr = { 4, 3, 2, 9 };
		int w = 8;
		System.out.println(ways1(arr, w));
		System.out.println(ways2(arr, w));
		System.out.println(ways3(arr, w));

	}

}
