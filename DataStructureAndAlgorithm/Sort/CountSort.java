package DataStructureAndAlgorithm.Sort;

/**
 * @author Yu Yang
 * @create 2020-01-17 15:59
 */
public class CountSort {

    /**
     * 计数排序：通过确定一个数组中比某个元素小的所有元素的个数，来确定其在最终排序数组中的位置。
     * 过程：
     * （1）通过max和min值来设定中间数组，并统计每个值出现的个数；
     * （2）通过统计的个数来确定每个元素所在的位置。
     * 时间复杂度：O(n + K), 空间复杂度： O(n + K)。 其中K为格外空间的大小。
     * 算法分析：
     * （1）适用于元素值比较集中的情况；
     * （2）统计元素出现个数时使用了额外空间，而其是以元素本身大小为index，所以当元素不是整数的时候，
     *      需要对元素进行变换，保证对应一个非负整数。
     * @param arr 待排序数组
     * @return 排序后的数组
     */
    public static int[] countSort(int[] arr) {
        int[] result = new int[arr.length];
        // 找arr的max 和 min，来确定中间数组的大小
        int max = arr[0], min = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        // 优化temp数组，减小数组大小
        int[] temp = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            temp[arr[i] - min] += 1;
        }
        for (int i = 1; i < temp.length; i++) {
            temp[i] += temp[i - 1];
        }
        for (int i = 0; i < arr.length; i++) {
            result[temp[arr[i] - min] - 1] = arr[i];
            temp[arr[i] - min]++;
        }
        return result;
    }
}
