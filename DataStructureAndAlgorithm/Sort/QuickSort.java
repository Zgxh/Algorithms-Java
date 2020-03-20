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
     * 保证左边的小于等于分割点，右边的大于分割点。
     *
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
            while (low < high && array[low] <= temp) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = temp;
        return low;
    }
}

/**
 * 利用荷兰国旗问题对快速排序进行加速。荷兰国旗问题的思想是：给定界限x，大于x的放右边，
 * 小于x的放左边，等于x的放中间。所以一趟快排后，所有等于x的值全都被排好了序。
 * 而原始快排一次快排只能排好x这一个数。
 */
class ImprovedQuickSort {
    public static void improvedQuickSort(int[] array, int L, int R) {
        if (L < R) {
            swap(array, R, L + (int) Math.random() * (R - L + 1)); // 随机选择一个基准与R位置交换
            int[] index = partition(array, L, R);
            improvedQuickSort(array, L, index[0] - 1);
            improvedQuickSort(array, index[1] + 1, R);
        }
    }

    /**
     * 以R为基准，进行一趟快排，分割小于R，等于R，大于R的数，并返回中间等于R的序列的左右边界。
     * @param array
     * @param L
     * @param R
     * @return
     */
    public static int[] partition(int[] array, int L, int R) {
        int less = L - 1; // less左边的都小于基准
        int more = R; // more右边的都大于基准
        // 以L为指针，对数组进行遍历排序
        while (L < more) {
            // 当前值小于基准，则把less+1位置上的值与当前位置交换，然后less向右扩一位
            if (array[L] < array[R]) {
                swap(array, ++less, L++);
            } else if (array[L] > array[R]) { // 当前值大于基准，则把more-1位置上的值与当前位置交换，然后more向左扩一位
                swap(array, --more, L);
            } else {
                L++;
            }
        }
        // 把基准调到等于基准的序列中
        swap(array, more, R);
        // 返回等于基准的序列的左右边界
        return new int[]{less + 1, more};
    }

    /**
     * 交换数组两个位置的值。
     * @param array
     * @param index1
     * @param index2
     */
    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}