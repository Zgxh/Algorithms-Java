package SyntaxTest.reviewToAlgorithm;

/**
 * @author Yu Yang
 * @create 2019-12-14 10:05
 */
public class BubbleSort {

    /**
     * 原始版本的bubbleSort
     * @param E
     * @param n
     */
    void bubbleSort(int[] E, int n) {
        int numpairs = n - 1;
        boolean didSwap = true;
        while (didSwap) {
            didSwap = false;
            for (int j = 0; j < numpairs; j++) {
                if (E[j] > E[j + 1]) {
                    swap(E, j, j + 1);
                    didSwap = true;
                }
            }
            numpairs--;
        }
    }

    /**
     * 改进1：记录最后一次交换元素的位置
     * @param E
     * @param n
     */
    void bubbleSort1(int[] E, int n) {
        int lastSwap = n - 1;
        boolean didSwap = true;
        while (didSwap) {
            didSwap = false;
            for (int j = 0; j < lastSwap; j++) {
                if (E[j] > E[j + 1]) {
                    swap(E, j, j + 1);
                    didSwap = true;
                    lastSwap = j;
                }
            }
        }
    }

    /**
     * 改进2：同时记录第一次交换的位置和最后一次交换的位置
     * @param E
     * @param n
     */
    void bubbleSort2(int[] E, int n) {
        int firstSwap = 0;
        int lastSwap = n - 1;
        boolean didSwap = true;
        while (didSwap) {
            didSwap = false;
            for (int j = firstSwap; j < lastSwap; j++) {
                if (E[j] > E[j + 1]) {
                    swap(E, j, j + 1);
                    if (!didSwap) {
                        firstSwap = j;
                    }
                    didSwap = true;
                    lastSwap = j;
                }
            }
        }
    }

    void swap(int[] E, int i, int j) {
        int temp = E[i];
        E[i] = E[j];
        E[j] = temp;
    }
}
