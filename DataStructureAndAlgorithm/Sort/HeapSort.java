package DataStructureAndAlgorithm.Sort;

public class HeapSort {
    
    /**
     * 堆排序：采用大顶堆实现元素的非递减序排序。
     *
     * @param array 存放堆元素的数组
     */
    public static void heapSort(int[] array) {
        // 从第一个非叶子节点开始，建大顶堆
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapAdjust(array, array.length, i);
        }
        // 利用堆进行排序，每次将堆顶弹出，放到比之前次大的位置
        for (int i = array.length - 1; i > 0; i--) {
            // 选出当前最大元素，即堆顶元素，交换到堆的末尾
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            // 重新调整，使堆符合结构
            heapAdjust(array, i, 0);
        }
    }

    /**
     * 堆调整：把堆重新调整为大顶堆。
     * 堆的特点是任意子树的根大于其孩子的值，如果不满足，则选择左右孩子中较大的一个进行上浮，
     * 并递归实现堆的结构
     *
     * @param array 存放堆数据的数组
     * @param len 截止的数组长度
     * @param i 当前需要调整的元素所在的位置
     */
    private static void heapAdjust(int[] array, int len, int i) {
        int k = i, temp = array[i], index = 2 * i + 1;
        while (index < len) {
            // 找出左右孩子节点中最大的那个，让父节点与之交换
            if (index + 1 < len) {
                if (array[index + 1] > array[index]) {
                    index = index + 1;
                }
            }
            if (array[index] > temp) {
                // 左右孩子中更大的结点上浮
                array[k] = array[index];
                // 递归解决由于孩子节点上浮导致的子树不符合堆结构的问题
                k = index;
                index = 2 * index + 1;
            } else {
                break;
            }
        }
        array[k] = temp;
    }
}