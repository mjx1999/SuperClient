package test;

/**
 * Created by twisty on 14-8-10.
 */
public class CommonTest {
    public static void main(String[] args) throws Exception {
        String s = "中国制造中国制造中国制造";
        String s2 = "ABCDEFABCDEF";
        String s3 = "abcdefabcdef";

        System.out.println(s.length());
        System.out.println(s.getBytes("GBK").length);
    }
}
