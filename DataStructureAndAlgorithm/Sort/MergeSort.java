package DataStructureAndAlgorithm.Sort;

public class MergeSort {
    
    /**
     * 归并排序: 非递减序的递归版本。先把长序列划分为足够短的小序列，然后一层层向上归并，
     * 直到所有元素都被排序完成。
     * @param E 待排序的数组
     * @param left 当前需要贵冰的
     * @param right
     */
    public static void mergeSort(int[] E, int left, int right) {
        int mid = left + (right - left) / 2;
        if (left < right) {
            mergeSort(E, left, mid);
            mergeSort(E, mid + 1, right);
            merge(E, left, mid, right);
        }
    }

    /**
     * 对两个相邻的子序列进行归并
     * @param E 待归并的大数组
     * @param left 第一个子序列的左端点
     * @param mid 第一个子序列的右端点
     * @param right 第二个子序列的右端点
     */
    private static void merge(int[] E, int left, int mid, int right) {
        int[] temp = new int[E.length];
        int l = left, m = mid + 1, index = left;
        while (l <= mid && m <= right) {
            if (E[l] <= E[m]) {
                temp[index++] = E[l++];
            } else {
                temp[index++] = E[m++];
            }
        }
        while (l <= mid) {
            temp[index++] = E[l++];
        }
        while (m <= right) {
            temp[index++] = E[m++];
        }
        // 把临时数组中的变量复制回原数组中
        index = left;
        while (index <= right) {
            E[index] = temp[index++];
        }
    }
}