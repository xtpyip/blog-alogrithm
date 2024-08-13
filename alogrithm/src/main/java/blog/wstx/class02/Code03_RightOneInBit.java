package blog.wstx.class02;

/**
 * @ClassName: Code03_RightOne
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 怎么把一个int类型的数，提取出最右侧的1来
 **/
public class Code03_RightOneInBit {
    private static int rightOne(int num){
        return num & (-num);
    }
}
