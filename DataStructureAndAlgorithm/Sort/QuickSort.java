package DataStructureAndAlgorithm.Sort;

public class QuickSort {

    /**
     * 快速排序的递归实现。按照非递减序排序。
     * @param array 待排序数组
     * @param low 待排序子序列左端点
     * @param high 待排序子序列右端点
     */
    public static void quickSort(int[] array, int low, int high) {
        if (array == null || array.length == 0 || low >= high) {
            return;
        }
        int mid = getMiddle(array, low, high);
        quickSort(array, low, mid - 1);
        quickSort(array, mid + 1, high);
    }

    /**
     * 进行一趟快速排序，并找到基准元素的最后落脚点。
     * @param array 待排序数组
     * @param low 待排序子序列左端点
     * @param high 待排序子序列右端点
     * @return 一趟快排后找出的中点
     */
    private static int getMiddle(int[] array, int low, int high) {
        int temp = array[low];
        while (low < high) {
            while (low < high && array[high] > temp) {
                high--;
            }
            array[low] = array[high];
            while (low < high && array[low] < temp) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = temp;
        return low;
    }
}