package DataStructureAndAlgorithm.Tree;

/**
 * 包含:(1)二叉查找树的结点实现 (2)二叉查找树的实现 该二叉查找树的功能有： a.插入结点 b.删除结点
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = { 7, 3, 10, 12, 5, 1, 9, 2 };
        BinarySortTree binarySortTree = new BinarySortTree();

        // 把arr[]中的数添加到二叉排序树中
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        // 对一个二叉排序树进行中序遍历，并打印遍历顺序，得到的值是按递增排序的
        System.out.println("中序遍历二叉排序树：");
        binarySortTree.inOrder();

        // 测试
        binarySortTree.delNode(3);
        System.out.println("删除叶子后：");
        binarySortTree.inOrder();
    }
}

/**
 * 定义二叉排序树的结点。
 */
class Node {
    int value;
    Node left;
    Node right;

    // 构造方法
    Node(int value) {
        this.value = value;
    }

    // toString()方法继承自Object类
    @Override
    public String toString() {
        return "Node : " + this.value;
    }

    // 添加节点，递归的方式
    public void add(Node node) {
        if (node == null) {
            return;
        }

        // 新结点小于当前结点，则往左边插
        if (this.value > node.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            // 新结点大于当前结点，往右边插
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 查找要删除的结点。
     * 
     * @param value 要删除的结点的值
     * @return 如果找到则返回该结点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            // 左子树为空
            if (this.left == null) {
                return null;
            }
            // 递归查找左子树
            return this.left.search(value);
        } else {
            // 右子树为空
            if (this.right == null) {
                return null;
            }
            // 递归查找右子树
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除的结点的父节点。
     * 
     * @param value 要查找的结点的值
     * @return 如果找到返回要查找的结点的父节点，如果没有就返回null
     */
    public Node searchParent(int value) {
        // 当前结点是要找的点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else { // 递归查找父节点
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    // 中序遍历并输出结点值
    public void inOrder() {
        if (this.left != null) {
            this.left.inOrder();
        }
        // 遍历当前结点，调用toString()方法
        System.out.println(this);
        if (this.right != null) {
            this.right.inOrder();
        }
    }
}

class SubNode extends Node {
    int value;
    SubNode left;
    SubNode right;

    SubNode(int value) {
        super(value);
        this.value = value;
    }

    public int height() {
        return left.height();
    }
}

/**
 * 定义二叉排序树
 */
class BinarySortTree {

    // 根节点
    public Node root;

    // 查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找要删除的结点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    // 获取根节点
    public Node getRoot() {
        return root;
    }

    // 往二叉排序树中插入一个新结点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    // 删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 首先找到要删除的那个结点
            Node targetNode = search(value);
            // 没有该结点的话，直接结束
            if (targetNode == null) {
                return;
            }

            // 如果要删除的结点就是根节点，并且这棵树只有一个结点：即根节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            // 找到目标结点的父结点
            Node parentNode = searchParent(value);
            // 如果目标结点是叶子结点,之前已经排除了只有一个根结点的情况
            if (targetNode.left == null && targetNode.right == null) {
                if (parentNode.left != null && parentNode.left.value == value) { // 目标结点是左叶子
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == value) { // 目标结点是右叶子
                    parentNode.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { // 目标结点有两棵子树的情况:包括目标结点是根或不是根的情况
                // 递归找到右子树中的最小值，删除，并把目标结点的值替换为该最小值
                // 另一种做法是：递归找到左子树的最大值，删除，并把目标结点的值替换为该最大值
                Node tempMin = targetNode.right;
                while (tempMin.left != null) {
                    tempMin = tempMin.left;
                }
                delNode(tempMin.value);
                targetNode.value = tempMin.value;
            } else { // 目标结点只有一棵子树，左子树或右子树
                // 如果是左子树
                if (targetNode.left != null) {
                    // 如果目标结点不是根结点
                    if (parentNode != null) {
                        // 目标结点是父节点的左孩子
                        if (parentNode.left == targetNode) {
                            parentNode.left = targetNode.left;
                        } else { // 目标结点是父节点的右孩子
                            parentNode.right = targetNode.left;
                        }
                    } else { // 如果目标结点是根结点
                        root = targetNode.left;
                    }
                } else { // 若目标结点只有右子树
                    // 如果目标结点不是根结点
                    if (parentNode != null) {
                        // 目标结点是父节点的左孩子
                        if (parentNode.left == targetNode) {
                            parentNode.left = targetNode.right;
                        } else { // 目标结点是父节点的右孩子
                            parentNode.right = targetNode.right;
                        }
                    } else { // 如果目标结点是根结点
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    // 二叉排序树的中序遍历
    public void inOrder() {
        if (root != null) {
            root.inOrder();
        } else {
            System.out.println("此二叉排序树为空，不能进行遍历");
        }
    }
}