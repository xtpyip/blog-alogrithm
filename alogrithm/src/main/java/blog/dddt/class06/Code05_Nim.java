package blog.dddt.class06;

/**
 * @ClassName: Code05_Nim
 * @version: 1.0
 * @Author: pyipXt
 * @Description: Nim博弈
 * 给定一个正数数组arr，先手和后手每次可以选择在一个位置拿走若干值，这个值要大于0，但是要小于该处的剩余，
 * 谁最先拿空arr谁赢，根据arr返回谁赢
 **/
public class Code05_Nim {
    // 保证arr是正数数组
    public static void printWinner(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }
        if (eor == 0) {
            System.out.println("后手赢");
        } else {
            System.out.println("先手赢");
        }

    }

}
