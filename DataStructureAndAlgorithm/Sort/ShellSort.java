package DataStructureAndAlgorithm.Sort;

public class ShellSort {

    /**
     * 希尔排序：非递减序排序
     * 基本思想是把大的无序序列分割成若干个具有增量间隔的子序列，然后
     * 把每个子序列进行直接插入排序，依次缩减增量再次进行插入排序。
     * 其原理是利用了直接插入排序在元素基本有序时的高效性。
     * @param array 待排序数组
     */
    public static void shellSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        // 分组，第一次子序列间隔增量gap为数组长度的二倍
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            // 对每个子序列进行变相的插入排序
            for (int i = gap; i < array.length; i++) {
                int temp = array[i];
                // 每次从左往右进行插入排序，每隔gap个元素属于同一组
                int j;
                for (j = i - gap; j >= 0 && temp < array[j]; j -= gap) {
                    array[j + gap] = array[j];
                }
                array[j + gap] = temp;
            }
        }
    }
}