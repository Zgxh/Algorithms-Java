package DataStructureAndAlgorithm.Tree;

import java.util.Stack;

public class Traversal {

    /**
     * 二叉树的前序遍历，递归实现。
     * @param root 二叉树的根结点
     */
    public static void preOrderTraversalRec(TreeNode root) {
        //递归的终止条件
        if (root == null) {
            return;
        }
        System.out.println(root.val + "->");
        preOrderTraversalRec(root.left);
        preOrderTraversalRec(root.right);
    }

    /**
     * 二叉树的先序遍历，非递归实现。使用栈来辅助实现。
     * @param root 二叉树的根结点
     */
    public void preOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            if (node != null) {
                System.out.println(node.val + "->");
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop().right;
            }
        }
    }

    /**
     * 二叉树的中序遍历，递归实现。
     * @param root 二叉树的根结点
     */
    public void inOrderTraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversalRec(root.left);
        System.out.println(root.val + "->");
        inOrderTraversalRec(root.right);
    }

    /**
     * 二叉树的中序遍历，非递归实现。使用栈进行辅助实现。
     * @param root 二叉树的根结点
     */
    public void inOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode temp = stack.pop();
                System.out.println(temp.val + "->");
                node = temp.right;
            }
        }
    }

    public static void postOrder
}