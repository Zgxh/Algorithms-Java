package DataStructureAndAlgorithm.Sort;

public class MergeSortPlus {

    /**
     * 改进版本的非递减序的归并排序（非递归实现）。取消了临时数组temp向输入数组E的回复制过程。
     * @param E 待排序数组
     */
    public static void mergeSort(int[] E) {
        int len = 1;
        boolean isOdd = true; // 记录当前len被调整次数的奇偶性
        int[] temp = new int[E.length]; // 新开辟的临时数组，空间占用 O(n)
        while (len <= E.length) {
            for (int i = 0; i < E.length; i += 2 * len) {
                int left = i, mid = i + len - 1, right = i + 2 * len - 1;
                // 右边界数组越界时，拉回数组右边界
                if (right > E.length - 1) {
                    right = E.length - 1;
                }
                // isOdd为奇数时，从E[]归并入temp[]
                if (isOdd) {
                    // 把本轮未参加归并的单独子序列复制到目标数组temp[]中
                    if (mid >= E.length - 1) {
                        copy(E, temp, left, E.length - 1);
                        continue;
                    }
                    merge(E, temp, left, mid, right, isOdd);
                }
                // isOdd为偶数时，从temp[]归并入E[]
                else {
                    // 把本轮未参加归并的单独子序列复制到目标数组E[]中
                    if (mid >= E.length - 1) {
                        copy(temp, E, left, E.length - 1);
                        continue;
                    }
                    merge(temp, E, left, mid, right, isOdd);
                }
            }
            len *= 2;
            isOdd = !isOdd;
        }
    }

    /**
     * 把数组send复制到receive数组中
     * @param send 被复制的数组
     * @param receive 接受元素的数组
     * @param begin 需要被复制部分的左边界
     * @param end 需要被复制部分的右边界
     */
    private static void copy(int[] send, int[] receive, int begin, int end) {
        for (int i = begin; i <= end; i++) {
            receive[i] = send[i];
        }
    }

    /**
     * 把两个相邻的子序列归并到一起。
     * @param E 待归并的数组
     * @param temp 临时数组
     * @param left 第一个子序列的左边界
     * @param mid 第一个子序列的右边界
     * @param right 第二个子序列的右边界
     * @param isOdd 是一个判断奇偶次归并的flag，用于判断是否要回赋值到E数组
     */
    private static void merge(int[] E, int[] temp, int left, int mid, 
                              int right, boolean isOdd) {
        int l = left;
        int m = mid + 1;
        int index = left;
        // 顺序比较两个子序列中元素的大小，并进行归并。
        while (l <= mid && m <= right) {
            if (E[l] < E[m]) {
                temp[index++] = E[l++];
            }
            else {
                temp[index++] = E[m++];
            }
        }
        // 把剩余的子序列中的元素添加到temp中。
        while (l <= mid) {
            temp[index++] = E[l++];
        }
        while (m <= right) {
            temp[index++] = E[m++];
        }
        // 当最后一次归并时，如果是从E[]往temp[]归并，则需要把归并完毕后的temp[]再复制回E[]
        if ((2 * (mid - left + 1) > E.length) && isOdd) {
            index = left;
            while (index <= right) {
                E[index] = temp[index++];
            }
        }
    }
}