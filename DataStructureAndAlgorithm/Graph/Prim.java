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

