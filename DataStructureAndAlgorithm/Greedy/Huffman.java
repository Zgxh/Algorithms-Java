package DataStructureAndAlgorithm.Greedy;

import java.util.PriorityQueue;

public class Huffman {
    private static final int R = 256; //R为字母表，但是为什么要256个呢？

    /**
     * 定义Huffman树的结点，实现Comparable接口，为了方便建堆
     */
    private static class Node implements Comparable<Node> {
        private final char ch; //该结点对应的字符
        private final int freq; //以该结点为根的子树中所有字符的频次和
        private final Node left, right; //左右子结点
        //Node的构造函数
        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
        //判断是否为叶子结点
        private boolean isLeaf() {
            return (left == null) && (right == null);
        }
        //继承了Comparable接口必须实现compareTo()方法
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    /**
     * 构建Huffman树
     * @param freq 每个字符的频次数组
     * @return Huffman树的根结点
     */
    private static Node buildTree(int[] freq) {
        //用小顶堆存放所有的字符结点，保证每次取出最小频次的结点，方便建树
        PriorityQueue<Node> pq = new PriorityQueue<>();
        //遍历一遍字母表，把所有字符及其频次全都加入小顶堆中
        for (char i = 0; i < R; i++) {
            if (freq[i] > 0) {
                pq.add(new Node(i, freq[i], null, null));
            }
        }
        //只有一个结点的时候特殊处理：因为需要至少两个结点才能出现链接，才能进行编码
        if (pq.size() == 1) {
            if (freq['\0'] == 0) {
                pq.add(new Node('\0', 0, null, null));
            } else {
                pq.add(new Node('\1', 0, null, null));
            }
        }
        //每次取两个频次最小的结点合并到一个新根中，直到最后只剩一个总根结点
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.add(parent);
        }
        return pq.poll();
    }

    /**
     * 构造编译表：把每个字符与其比特字符串相关联
     * @param table 传入的空的编译表
     * @param x Huffman树的根
     * @param s 初始字符串
     */
    private static void buildCode(String[] table, Node x, String s) {
        //左连接编码为0，右连接编码为1
        if (!x.isLeaf()) {
            buildCode(table, x.left, s + '0');
            buildCode(table, x.right, s + '1');
        } else {
            table[x.ch] = s;
        }
    }

    /**
     * 压缩数据。按理说应该对Huffman树也进行编码，这里为了方便取消了对Huffman树的编码，
     * 即Huffman树直接作为返回参数。
     * 这里只是模拟一下编码过程，所以采用了String来保存编码后的值，实质并没有压缩
     * @param s 待压缩的字符串
     * @return Huffman树
     */
    public static Node compress(String s) {
        char[] input = s.toCharArray();

        //统计字符频次
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }

        //构建Huffman树
        Node root = buildTree(freq);

        //构建编译表
        String[] table = new String[R];
        buildCode(table, root, "");

        //压缩数据
        String result = "";
        for (int i = 0; i < input.length; i++) {
            result += table[input[i]];
        }
        System.out.println(result);
        return root;
    }

    /**
     * 对二进制码进行解码，得到原来的字符串。
     * @param binCode 压缩得到的二进制码
     * @param root 压缩过程中得到的Huffman树的根节点
     * @param len 原字符串的长度
     */
    public static void expand(String binCode, Node root, int len) {
        int index = 0;
        int lenOfBin = binCode.length();
        String result = "";
        //依次解码len个字符
        for (int i = 0; i < len; i++) {
            //每次新解码一个字符都要重新返回根节点
            Node x = root;
            while (!x.isLeaf()) {
                if (index < lenOfBin) {
                    if (binCode.charAt(index++) == '0') {
                        x = x.left;
                    } else {
                        x = x.right;
                    }
                } else {
                    System.out.println("解码出现错误，数组长度越界！");
                }
            }
            result += x.ch;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        String input = "woshinidie";
        String binCode = "00110100001001110111101011011";
        Node root = compress(input);
        expand(binCode, root, input.length());
    }
}