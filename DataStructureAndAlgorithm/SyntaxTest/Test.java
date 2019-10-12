package DataStructureAndAlgorithm.SyntaxTest;

public class Test {
    public static void main(String[] args) {
        System.out.println("-1234".matches("-?\\d+")); //true
        System.out.println("5678".matches("-?\\d+")); //true
        System.out.println("+911".matches("-?\\d+")); //false
        System.out.println("+911".matches("(-|\\+)?\\d+")); //true
        System.out.println("-81.23".matches("(-|\\+)?(\\d+)?.?\\d*")); //true
        System.out.println("+81.23".matches("(-|\\+)?(\\d+)?.?\\d*")); //true
        System.out.println("xx".matches("(-|\\+)?(\\d+)?.?\\d*")); //false
        System.out.println(".5".matches("(-|\\+)?(\\d+)*.?\\d*")); //true
        System.out.println(" ".matches("(-|\\+)?(\\d+)?.?\\d+")); //false
        System.out.println(".5".matches("(-|\\+)?(\\d+).?\\d\\d*")); //false
        System.out.println("55.0".matches("(-|\\+)?(\\d+)*.?\\d+")); //true
    }
}