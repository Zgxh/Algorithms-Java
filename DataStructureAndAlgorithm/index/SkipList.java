package DataStructureAndAlgorithm.index;

import java.util.Random;

/**
 * 跳表 skip list 数据结构， 采用 链表 + 多级索引 的结构，牺牲空间换时间。
 * 查询、插入、删除的时间复杂度：O(log n)
 *
 * @author Yu Yang
 * @create 2020-03-25 16:29
 */

public class SkipList {

    private static final int MAX_LEVEL = 16;
    private int levelCount = 1;     // 索引级数从0开始，顶级索引为第 levelCount-1 级索引
    private Node head = new Node(); // 跳表的头结点
    private Random random = new Random();

    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        }
        return null;
    }

    public void insert(int value) {

        Node p = head;
        int level = randomLevel();
        Node node = new Node();
        node.data = value;
        node.maxLevel = level;
        Node update[] = new Node[level];
        for (int i = level; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }
        for (int i = 0; i < level; i++) {
            node.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = node;
        }
        if (levelCount < level) levelCount = level;
    }

    public void delete(int value) {
        Node[] deleteNode = new Node[MAX_LEVEL];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            deleteNode[i] = p;
        }
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; i--) {
                if (deleteNode[i] != null && deleteNode[i].forwards[i].data == value) {
                    deleteNode[i].forwards[i] = deleteNode[i].forwards[i].forwards[i];
                }
            }
        }
    }

    public void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    private int randomLevel() {

        int level = 0;
        for (int i = 0; i < MAX_LEVEL; i++) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    class Node {

        private int data;
        private Node[] forwards = new Node[MAX_LEVEL];
        private int maxLevel;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", maxLevel=" + maxLevel +
                    '}';
        }
    }
}