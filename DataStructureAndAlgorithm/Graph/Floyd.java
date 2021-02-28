package DataStructureAndAlgorithm.Graph;

/**
 * Floyd 算法求任意两点间的最短距离，时间复杂度：O(n ^ 3)
 *
 * 算法流程：
 * 枚举所有的结点，以该节点为中转结点，更新任意两点之间的最短距离
 *
 * @author Yu Yang
 * @create 2020-01-04 19:28
 */
public class Floyd {

    /**
     * Floyd algorithm to calculate the shortest paths between any pairs of graph nodes.
     * @param weight adjacent matrix of input Graph
     * @return 2-dimensional path array between node pairs
     */
    public int[][] floyd(int[][] weight) {
        int size = weight.length;
        int[][] pathLength = new int[size][size];
        // initialize pathLength[][] as adjacent matrix: the diagonal element is infinity.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                pathLength[i][j] = weight[i][j];
            }
        }
        // Floyd algorithm, time complexity: O(n^3)
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int currentLength = pathLength[i][k] + pathLength[k][j];
                    if (currentLength < pathLength[i][j]) {
                        pathLength[i][j] = currentLength;
                    }
                }
            }
        }

        return pathLength;
    }
}
