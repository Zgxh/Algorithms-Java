package DataStructureAndAlgorithm.Stack1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolandNotation {


    /**
     * 将中缀表达式转换为逆波兰表达式。
     * @param s 中缀表达式字符串
     * @return 分隔的逆波兰表达式列表
     */
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0;
        char c;
        String str;
        while (i < s.length()) {
            c = s.charAt(i);
            //如果不是数字（其实就是运算符），直接加入
            if (c < 48 || c > 57) {
                ls.add("" + c);
                i++;
            } else { //如果是数字，考虑多位数字的情况，对数字进行拼接
                str = "";
                while (i < s.length() && (c = s.charAt(i)) < 48 && (c = s.charAt(i)) > 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }
        return ls;
    }

    /**
     * 计算逆波兰表达式。
     * @param tokens 逆波兰表达式对应的字符串数组
     * @return 计算结果
     */
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            //判断是否为数字，使用正则表达式
            if (token.matches("-?\\d+")) {
                stack.push(token);
            } else {
                //把字符串解析为多位整数
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                //分别匹配四种运算符
                if (token.equals("+")) {
                    res = num2 + num1;
                } else if (token.equals("-")) {
                    res = num2 - num1;
                } else if (token.equals("*")) {
                    res = num2 * num1;
                } else {
                    res = num2 / num1;
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
    }
}