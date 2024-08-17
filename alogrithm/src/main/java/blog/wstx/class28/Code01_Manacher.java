package blog.wstx.class28;

public class Code01_Manacher {

	public static int manacher(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = manacherString(s);
		// 回文半径的大小
		int[] pArr = new int[str.length];
		int C = -1;// 当前扩到最远R位置的那个下标 R = C + pArr[C]
		int R = -1;// 当前搞到最远R的位置的下一个
		int max = 1; // 最长回文字符串长度
		for (int i = 0; i < str.length; i++) {
			// 当前i如果在R内，则最少半径为min(pArr[2 * C - i],R - i)
			// 当前i如果在R外，则最少半径为1
			pArr[i] = R > i ? Math.min(pArr[2 * C - i],R - i) : 1;
			// 尝试下一个对应的两个位置是否相等
			while (i + pArr[i] < str.length && i - pArr[i] >= 0){
				if(str[i + pArr[i]] == str[i - pArr[i]]){
					pArr[i]++;
				}else{
					break; // 有不需要验的，第一次验证也会直接失败
				}
			}
			if(i + pArr[i] > R){ // 可以扩充长度
				C = i; // 扩充的下标
				R = i + pArr[i];
			}
			max = Math.max(max,pArr[i]);

		}
	    return max - 1;
	}

	public static char[] manacherString(String str) {
		char[] chars = new char[str.length() * 2 + 1];
		chars[0] = '#';
		for (int i = 0; i < str.length(); i++) {
			chars[i * 2 + 1] = str.charAt(i);
			chars[(i + 1) * 2] = '#';
		}
		return chars;
	}

	// for test
	public static int right(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = manacherString(s);
		int max = 0;
		for (int i = 0; i < str.length; i++) {
			int L = i - 1;
			int R = i + 1;
			while (L >= 0 && R < str.length && str[L] == str[R]) {
				L--;
				R++;
			}
			max = Math.max(max, R - L - 1);
		}
		return max / 2;
	}

	// for test
	public static String getRandomString(int possibilities, int size) {
		char[] ans = new char[(int) (Math.random() * size) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
		}
		return String.valueOf(ans);
	}

	public static void main(String[] args) {
		int possibilities = 5;
		int strSize = 20;
		int testTimes = 5000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strSize);
			if (manacher(str) != right(str)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
