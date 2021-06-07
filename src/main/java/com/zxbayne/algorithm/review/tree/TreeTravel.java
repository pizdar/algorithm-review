package com.zxbayne.algorithm.review.tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的三种遍历方式：前序遍历、中序遍历、后序遍历
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class TreeTravel {
    public static void prefixOrderTravel(TreeNode root) {
        if (root != null) {
            System.out.println(root.value);
            prefixOrderTravel(root.left);
            prefixOrderTravel(root.right);

        }
    }

    public static void infixOrderTravel(TreeNode root) {
        if (root != null) {
            infixOrderTravel(root.left);
            System.out.println(root.value);
            infixOrderTravel(root.right);
        }
    }

    public static void postfixOrderTravel(TreeNode root) {
        if (root != null) {
            postfixOrderTravel(root.left);
            postfixOrderTravel(root.right);
            System.out.println(root.value);
        }
    }

    public static void levelOrderTravel(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode element = queue.poll();
            System.out.println(element.value);
            if (element.left != null) {
                queue.offer(element.left);
            }
            if (element.right != null) {
                queue.offer(element.right);
            }
        }
    }

}
