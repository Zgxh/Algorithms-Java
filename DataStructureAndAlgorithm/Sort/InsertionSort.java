package DataStructureAndAlgorithm.Sort;

public class InsertionSort {
    
    /**
     * 直接插入排序：实现非递减序排序。
     * @param array 待排序数组
     */
    public static void insertionSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int index = i - 1;
            while (index >= 0 && array[index] > temp) {
                array[index + 1] = array[index];
                index--;
            }
            array[index + 1] = temp;
        }
    }
}