package SyntaxTest;

import java.util.Arrays;

public class Algo2 {

    public static void findDifference(int[] arr) {
        // 新建Node数组并进行初始化
        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i], i);
        }
        // 对数组进行排序
        Arrays.sort(nodes, (node1, node2) -> node1.val - node2.val);
        // 把重复的元素设为待排序数组最大值+1
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i].val == nodes[i - 1].val) {
                arr[nodes[i].index] = nodes[nodes.length - 1].val + 1;
            }
        }
        // 输出不为 数组元素最大值+1 的其他元素，即为所求
        Arrays.stream(arr).filter(a -> a != nodes[nodes.length - 1].val + 1).forEach(System.out::println);
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 2, 6, 7, 1, 1};
        findDifference(arr);
    }
}

/**
 * 新建Node同时储存元素值和元素原始位置
 */
class Node {
    int val;
    int index;
    Node (int val, int index) {
        this.val = val;
        this.index = index;
    }
}