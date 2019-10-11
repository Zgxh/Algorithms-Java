package DataStructureAndAlgorithm.String;

import java.util.Arrays;

/**
 * KMP字符串匹配算法。
 * 基本思想：之前比较过的部分避免重复比较，不要把搜索位置移动回已经比较过的位置。
 * 方法：建立部分匹配值，用来引导下次主串指针移动的次数。
 */
public class KMP {
    public int kmp(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            } else {
                i -= next[i - 1];
                j = 0;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
    }

    /**
     * 获取部分匹配表。
     * @param dest 子串
     * @return 部分匹配表
     */
    private static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        //若字符串长度为1，则部分匹配值为0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //前缀与后缀不匹配时
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //当前缀与后缀匹配时
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        System.out.println("next=" + Arrays.toString(next));
    }
}