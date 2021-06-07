package com.zxbayne.algorithm.review.tree;

/**
 * 删除二叉树的结点
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class TreeDelete {
    /**
     * 删除值为 nodeId 的结点，如果这个结点是叶子结点，则直接删除，如果这个结点为
     * 非叶子结点，则删除这个子树
     * @param root 二叉树的根节点
     * @param nodeId 要删除的node的value
     * @see TreeNode
     */
    public static void delNode(TreeNode root, int nodeId) {
        if (root == null) {
            return;
        }
        if (root.value == nodeId) {
            root = null;
            return;
        }
        if (root.left != null && root.left.value == nodeId) {
            root.left = null;
            return;
        }
        if (root.right != null && root.right.value == nodeId) {
            root.right = null;
            return;
        }
        delNode(root.left, nodeId);
        delNode(root.right, nodeId);
    }
}
