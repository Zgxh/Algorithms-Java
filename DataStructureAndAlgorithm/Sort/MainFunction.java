package DataStructureAndAlgorithm.Sort;

import java.util.Arrays;

class MainFunction {

    public static void main(String[] args) {
        int[] input = new int[100];
        for (int i = 0; i < input.length; i++) {
            input[i] = 100 - i;
        }
        // MergeSort.mergeSort(input, 0, input.length - 1);
        // HeapSort.heapSort(input);
        // MergeSortPlus.mergeSort(input);
        // QuickSort.quickSort(input, 0, input.length - 1);
        // InsertionSort.insertionSort(input);
//        ShellSort.shellSort(input);
        ImprovedQuickSort.improvedQuickSort(input, 0, input.length - 1);
//        Arrays.stream(input).forEach(System.out::print);
        System.out.println(Arrays.toString(input));
    }
}