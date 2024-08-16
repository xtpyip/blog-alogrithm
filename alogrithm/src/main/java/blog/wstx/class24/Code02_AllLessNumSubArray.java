package blog.wstx.class24;

import java.util.LinkedList;

public class Code02_AllLessNumSubArray {

	// 暴力的对数器方法
	public static int right(int[] arr, int sum) {
		if (arr == null || arr.length == 0 || sum < 0) {
			return 0;
		}
		int N = arr.length;
		int count = 0;
		for (int L = 0; L < N; L++) {
			for (int R = L; R < N; R++) {
				int max = arr[L];
				int min = arr[L];
				for (int i = L + 1; i <= R; i++) {
					max = Math.max(max, arr[i]);
					min = Math.min(min, arr[i]);
				}
				if (max - min <= sum) {
					count++;
				}
			}
		}
		return count;
	}

	public static int num(int[] arr, int sum) {
		if (arr == null || arr.length == 0 || sum < 0) {
			return 0;
		}
		int n = arr.length;
		int ans = 0;
		LinkedList<Integer> qMax = new LinkedList<>(); // 存放下标（最大值及其在之后指定区间内的最大值）
		LinkedList<Integer> qMin = new LinkedList<>();
		int R = 0;
		for (int L = 0; L < n; L++) {
			while (R < n){
				while(!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]){
					qMax.pollLast();
				}
				qMax.addLast(R);
				while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[R]){
					qMin.pollLast();
				}
				qMin.addLast(R);
				// 以L~R当前区间的最大值与最小值之间是否满足条件
				if(arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > sum){
					break;
				}else{
					R++;
				}
			}
			ans += R - L;
			if(qMax.peekFirst() == L){
				qMax.pollFirst();
			}
			if(qMin.peekFirst() == L){
				qMin.pollFirst();
			}
		}

		return ans;
	}

	// for test
	public static int[] generateRandomArray(int maxLen, int maxValue) {
		int len = (int) (Math.random() * (maxLen + 1));
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int maxLen = 100;
		int maxValue = 200;
		int testTime = 100000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxLen, maxValue);
			int sum = (int) (Math.random() * (maxValue + 1));
			int ans1 = right(arr, sum);
			int ans2 = num(arr, sum);
			if (ans1 != ans2) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println(sum);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("测试结束");

	}

}
