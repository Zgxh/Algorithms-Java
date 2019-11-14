package DataStructureAndAlgorithm.Graph;

/**
 * @author Yu Yang
 * @create 2019-11-14 14:39
 */
public class MinimumSpaningTree {

    /**
     * kruskal算法求最小生成树，（适用于带权图），主要思想：
     * （1）从n棵树（只包含root结点）的森林开始，把每棵树划分到各自等价类。
     * （2）每次取权值最小的边，如果两个顶点属于不同的等价类，则把这条边加入MST，
     *     并合并两个等价类，直到最后只剩一个等价类。
     *
     */
    public void kruskal() {
        // implementation
    }
}

/**
 * The implementation of equivalence class. Each equivalence class is stored as a tree.
 *
 */
class UnionFind {

    // store the index of equivalence class, which is corresponding to the index of root;
    // negative represents root; the absolute value is the height of the tree.
    private int[] array;

    /**
     * Constructor : initialize the array of equivalence class
     * @param length
     */
    public UnionFind(int length) {
        array = new int[length];
        for (int i = 0; i < length - 1; i++) {
            array[i] = -1;
        }
    }

    /**
     * Find the index of equivalence class, that is, the index of root.
     * @param x the index of the node
     * @return the index of the root
     */
    public int find(int x) {
        // if root
        if (array[x] < 0) {
            return x;
        } else { // if not root, recursion. And set the node's value as the index of root.
            array[x] = find(array[x]);
            return array[x];
        }
    }

    /**
     * Merge the first equivalence classes to the second one.
     * @param node1 one node of the first equivalence class
     * @param node2 one node of the second equivalence class
     */
    public void union(int node1, int node2) {
        // find root
        int root1 = find(node1);
        int root2 = find(node2);

        // if the height of tree2 is greater than tree1 (because of negative), merge tree1 into tree2
        if (array[root1] > array[root2]) {
            array[root1] = root2;
        } else {
            // if equal, merge tree2 into tree1, and the height of tree1 increase
            if (array[root1] == array[root2]) {
                array[root1]--;
            }
            // merge tree2 into tree1
            array[root2] = root1;
        }
    }
}