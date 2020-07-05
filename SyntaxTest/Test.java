package SyntaxTest;

/**
 * This is a test!
 */
public class Test {

    public static void main(String[] args) {
        String a = "3:start:4";
        String b = "2:end:5";
        String[] c = null;
        System.out.println((c = a.split("end")).length);
//        Arrays.stream(a.split("end")).forEach(System.out::println);
    }
}
