package DataStructureAndAlgorithm.String;

/**
 * KMP字符串匹配算法。
 * 基本思想：之前比较过的部分避免重复比较，不要把搜索位置移动回已经比较过的位置。
 * 方法：建立部分匹配值，用来引导下次主串指针移动的次数。
 */
public class KMP {

    /**
     * KMP算法。
     * @param str 主串
     * @param pattern 模式串
     * @return 返回模式串在主串中的位置，即第一个相同字符的位置；若匹配失败则返回-1
     */
    public static int kmp(String str, String pattern) {
        int[] next = getKmpNext(pattern);
        int sLen = str.length(), pLen = pattern.length();
        for (int i = 0, j = 0; i < sLen; i++) {
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) { // 匹配失败，则字符串右移到合适的位置
                j = next[j - 1];
            }
            //该位置匹配成功则二者同时走向下一个位置
            if (str.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            //匹配成功返回已匹配字符串在主串中的位置
            if (j == pLen) {
                return i - (pLen - 1);
            }
        }

        //匹配失败返回-1
        return -1;
    }

    /**
     * 获取部分匹配表的next[]数组。next[]数组代表以i为结尾的子串中最长公共前后缀长度。
     * 若在第i个元素处不匹配，则模式串位置返回到next[i-1]的值对应的位置。
     * 即返回到已匹配字符长度处。因为从next[i-1]-1及之前都是匹配的。
     */
    private static int[] getKmpNext(String dest) {
        int len = dest.length();
        int[] next = new int[len];
        // 若字符串长度为1，则部分匹配值为0
        next[0] = 0;
        for (int i = 1, j = 0; i < len; i++) {
            // 前缀与后缀不匹配时，一直找到匹配的位置，若一直不匹配则回到模式串的开头位置0处
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            // 当前缀与后缀匹配时
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j; // 记录最长公共前后缀的长度
        }

        return next;
    }

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int index = kmp(str1, str2);
        System.out.println("index = " + index); 
    }
}