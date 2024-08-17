package blog.wstx.class27;

public class Code01_KMP {

	public static int getIndexOf(String s1, String s2) {
		if (s1 == null || s2 == null || s2.isEmpty() || s1.length() < s2.length()) {
			return -1;
		}
		char[] char1 = s1.toCharArray();
		char[] char2 = s2.toCharArray();
		int x = 0,y = 0;
		int[] next = getNextArray(char2);
		while (x < char1.length && y < char2.length){
			if(char1[x] == char2[y]){
				// 配对成功
				x++;
				y++;
			}else if(next[y] == -1){ // y == 0
				// 尝试下一个配对点
				x++;
			}else{
				y = next[y];
			}
		}
		return y == char2.length ? x - y  : -1;
	}

	public static int[] getNextArray(char[] str2) {
		if (str2.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[str2.length];
		next[0] = -1;
		next[1] = 0;
		int cnt = 0,i = 2;
		while (i < next.length){
			if(str2[i-1] == str2[cnt]){
				// 配对成功
				next[i++] = ++cnt;
			}else if(cnt > 0){
				// 去下一个位置的最后点判断
				cnt = next[cnt];
			}else{
				// 到最后也没配对成功
				next[i++] = 0;
			}
		}
		return next;
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
		int matchSize = 5;
		int testTimes = 5000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strSize);
			String match = getRandomString(possibilities, matchSize);
			if (getIndexOf(str, match) != str.indexOf(match)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
