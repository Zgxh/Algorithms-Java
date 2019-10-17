package SyntaxTest;

import java.util.Arrays;

public class Algo1 {

    public static int[] mergeDifference(int[] A, int[] B) {
        int i = 0, j = 0, k = 0;
        int[] C = new int[A.length + B.length];
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                C[k++] = A[i++];
            } else if (A[i] > B[j]) {
                C[k++] = B[j++];
            } else {
                i++;
                j++;
            }
        }
        while (i < A.length) {
            C[k++] = A[i++];
        }
        while (j < B.length) {
            C[k++] = B[j++];
        }
        return C;
    }

    public static void main(String[] args) {
        int[] A = {4, 1, 2, 3};
        int[] B = {4, 2, 6, 7, 1, 8};
        Arrays.sort(A);
        Arrays.sort(B);
        int[] C = mergeDifference(A, B);
        System.out.println(Arrays.toString(C));
    }
}