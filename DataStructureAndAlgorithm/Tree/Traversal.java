package DataStructureAndAlgorithm.Tree;

import java.util.*;

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

    /**
     * 二叉树的后序遍历，递归实现。
     * @param root 二叉树的根
     */
    public void postOrderTraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderTraversalRec(root.left);
        postOrderTraversalRec(root.right);
        System.out.println(root.val + "->");
    }

    /**
     * 二叉树的后序遍历，非递归实现。（这个版本没有真正体现后序遍历的思想）
     * @param root 二叉树的根
     * @return 顺序排列的后序遍历二叉树结点列表
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode temp = null;
        while (!stack.empty()) {
            temp = stack.pop();
            result.add(0, temp.val);
            if (temp.left != null) {
                stack.push(temp.left);
            }
            if (temp.right != null) {
                stack.push(temp.right);
            }
        }
        return result;
    }

    /**
     * 二叉树的后序遍历方法2.非递归实现。（真正体现后序遍历的思想）
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList();
        stack.addFirst(root);
        TreeNode lastTraversal = root;
        TreeNode stackTop = null;
        while (!stack.isEmpty()) {
            stackTop = stack.getFirst();
            // 若left不null，并且不是从左右回来的，则说明左边还未遍历
            if (stackTop.left != null && lastTraversal != stackTop.left
                    && lastTraversal != stackTop.right) {
                stack.push(stackTop.left);
            } else if (stackTop.right != null
                    && stackTop.right != lastTraversal) {
                // 若right不null，并且不是从right回来的，则说明右边还未遍历
                stack.push(stackTop.right);
            } else { // 若不满足以上两项，则说明左右为空，或者是已经遍历完了左右子树，则后序遍历该结点
                result.add(stackTop.val);
                stack.removeFirst();
                lastTraversal = stackTop;
            }
        }

        return result;
    }

    /**
     * 二叉树的层次遍历。使用队列辅助实现。
     * @param root 二叉树的根结点
     */
    public void levelOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.val + "->");
           if (node.left != null) {
               queue.add(node.left);
           }
           if (node.right != null) {
               queue.add(node.right);
           }
       }
    }
}