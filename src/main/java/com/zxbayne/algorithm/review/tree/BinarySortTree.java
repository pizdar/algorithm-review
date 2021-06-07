package com.zxbayne.algorithm.review.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉排序树
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class BinarySortTree {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public static void infixOrderTravel(Node root) {
        if (root != null) {
            infixOrderTravel(root.left);
            System.out.println(root.value);
            infixOrderTravel(root.right);
        }
    }

    public static void prefixOrderTravel(Node root) {
        if (root != null) {
            System.out.println(root.value);
            prefixOrderTravel(root.left);
            prefixOrderTravel(root.right);

        }
    }

    public static void postfixOrderTravel(Node root) {
        if (root != null) {
            postfixOrderTravel(root.left);
            postfixOrderTravel(root.right);
            System.out.println(root.value);
        }
    }

    public static void levelOrderTravel(Node root) {
        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            Node element = queue.poll();
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

class Node {
    public Node left;
    public Node right;
    public Integer value;

    public Node(Integer value) {
        this.value = value;
    }

    /**
     * 在 this 下添加节点，并且符合二叉排序树的要求
     * @param node 被添加的节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }

        // 判断传入的节点值与当前节点的关系
        if (this.value > node.value) {
            // 如果当前节点的left为null
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归地向左子树添加
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }
}
