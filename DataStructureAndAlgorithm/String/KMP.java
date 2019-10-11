package DataStructureAndAlgorithm.String;

/**
 * KMP字符串匹配算法。
 * 基本思想：之前比较过的部分避免重复比较，不要把搜索位置移动回已经比较过的位置。
 * 方法：建立部分匹配值，用来引导下次主串指针移动的次数。
 */
public class KMP {

    /**
     * KMP算法。
     * @param str1 主串
     * @param str2 模式串
     * @return 返回模式串在主串中的位置，即第一个相同字符的位置；若匹配失败则返回-1
     */
    public static int kmp(String str1, String str2) {
        int[] next = getKmpNext(str2);
        for (int i = 0, j = 0; i < str1.length(); i++) {
            /**
             * 若匹配失败，把模式串右移j-next[j-1]。
             * 1.如果匹配上了，就跳出while接着比较下一个位置。
             * 2.j变0了，跳出while，并比较下个i的str1和str2[0],然后依次进行。
             * 3.j没变0，同时该位置也没匹配上，那就继续模式串右移j-next[j-1]。
             */
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            //该位置匹配成功则二者同时走向下一个位置
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            //匹配成功返回：模式串在主串中第一个字符相等的位置
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        //匹配失败返回-1
        return -1;
    }

    /**
     * 获取部分匹配表。每个位置元素对应模式串数组开头对应元素的index+1。
     * @param dest 模式串
     * @return 部分匹配表
     */
    private static int[] getKmpNext(String dest) {
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
        int index = kmp(str1, str2);
        System.out.println("index = " + index); 
    }
}