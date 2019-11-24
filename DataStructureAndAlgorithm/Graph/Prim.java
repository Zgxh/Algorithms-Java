package DataStructureAndAlgorithm.Graph;

import java.util.Arrays;

/**
 * @author Yu Yang
 * @create 2019-11-23 15:37
 */
public class Prim {

    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        int[][] weight = new int[][]{
                {Integer.MAX_VALUE,5,7,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,2},
                {5,Integer.MAX_VALUE,Integer.MAX_VALUE,9,Integer.MAX_VALUE,Integer.MAX_VALUE,3},
                {7,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,8,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,9,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,4,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,8,Integer.MAX_VALUE,Integer.MAX_VALUE,5,4},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,4,5,Integer.MAX_VALUE,6},
                {2,3,Integer.MAX_VALUE,Integer.MAX_VALUE,4,6,Integer.MAX_VALUE},
        };
        MinSpanningTree mst = new MinSpanningTree();
        MGraph graph = mst.createGraph(vertex, data, weight);
        mst.showGraph(graph);
        mst.prim(graph, 0);
    }
}

class MinSpanningTree {

    /**
     * creat a graph with some inputs
     * @param vertex the number of the vertex
     * @param data nodes of the graph
     * @param weight weight of edges
     * @return a graph
     */
    public MGraph createGraph(int vertex, char[] data, int[][] weight) {
        MGraph graph = new MGraph(vertex);
        graph.vertex = vertex;
        for (int i = 0; i < vertex; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
        return graph;
    }

    /**
     * print the adjacent matrix of a graph
     * @param graph the input graph to be printed
     */
    public void showGraph(MGraph graph) {
        for (int[] row : graph.weight) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Use prim algorithm to create a minimum spanning tree. Find the current minimum weight
     * from visited nodes constantly, and add the corresponding edge into MST.
     * @param graph the input graph expressed as weight matrix
     * @param v the index of the node to start with
     */
    public void prim(MGraph graph, int v) {
        // mark node if it has been visited, initial value is 0, mark visited as 1
        int[] visited = new int[graph.vertex];
        visited[v] = 1;
        // h1 and h2 are used to mark the index of the minimum edge
        int h1 = -1;
        int h2 = -1;
        // for n nodes in a MST, there are n-1 edges. traverse them and add it into MST
        for (int e = 1; e < graph.vertex; e++) {
            // mark the current minimum weight as minWeight in Prim
            int minWeight = Integer.MAX_VALUE;
            // Traverse all the visited node, and find the current minimum weight. Then add the edge.
            for (int i = 0; i < graph.vertex; i++) {
                for (int j = 0; j < graph.vertex; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            visited[h2] = 1;
            System.out.println("edge: <" + graph.data[h1] + "," + graph.data[h2] + "> weight: " + minWeight);
        }
    }
}

/**
 * define a graph with adjacent matrix.
 */
class MGraph {
    int vertex; // number of vertex
    char[] data; // node of Graph
    int[][] weight; // adjacent matrix

    // constructor
    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }
}

