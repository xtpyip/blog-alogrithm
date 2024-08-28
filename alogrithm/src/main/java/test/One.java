package test;

public class One {
    public static void main(String[] args) {
        System.out.println(add(5));
    }
    public static String  add(float num){
        return "float";
    }
    public static String  add(double num){
        return "double";
    }
    public static String  add(short num){
        return "short";
    }
    public static String  add(long num){
        return "long";
    }
    // 1100 0101
    // 0101 0101 -> 0100 0101
    // 0101 0101 -> 0100 0000
    // 1100 0101 -> 1000 0101

    // 0011 0011 -> 0000 0001
    // 0011 0011 -> 0010 0001
    // 0010 0010

    // 0000 1111 -> 0000 0010
    // 0000 1111 -> 0000 0010
    // 0000 0100

    // 1111 1111 -> 0000 0100
    // 1111 1111 -> 0000 0000
    // 0000 0100

    // 1111 1111 -> 0000 0100
    // 1111 1111 -> 0000 0000

    // 0010 1000 0100
    // 0010 1010 0100
    // 2*m+2 = 32 => m = 15
    // 0010
    // 3,3 3*m+3 = 48
    // 0010 1011 0100
}
