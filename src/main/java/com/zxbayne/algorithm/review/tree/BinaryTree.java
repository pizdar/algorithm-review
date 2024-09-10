package com.zxbayne.algorithm.review.tree;

import java.util.List;

public class BinaryTree {
	//根结点,默认为null
	private TreeNode root = null;

	public TreeNode getRoot() {
		return root;
	}

	//    树的节点已经不需要按顺序排好
	public void buildBinaryNode(TreeNode node, int data) {
		//如果根结点是空,那么设置根结点,并且设置数据域
		if (root == null) {
			root = new TreeNode(data);
		} else {
			/**
			 * 根结点不为空,那么判断数据是否小于当前结点的数据
			 */
			if (data < node.value) {
				//如果小于,判断当前结点是否有左叶子结点
				if (node.left == null) {
					//左叶子结点为空,设置左叶子结点,并且设置数据
					node.left = new TreeNode(data);
				} else {
					//左叶子结点不为空,递归调用构建二叉树的函数
					buildBinaryNode(node.left, data);
				}
			} else {
				//如果大于或等于,判断当前结点是否存在右叶子结点
				if (node.right == null) {
					//右叶子结点为空,设置右叶子结点,并且设置数据域
					node.right = new TreeNode(data);
				} else {
					//右叶子几点不为空,递归调用构建二叉树的函数
					buildBinaryNode(node.right, data);
				}
			}
		}
	}

	public BinaryTree createBinaryTree(int[] datas) {
		BinaryTree binaryTree = new BinaryTree();
		for (int data : datas) {
			binaryTree.buildBinaryNode(binaryTree.getRoot(), data);
		}
		return binaryTree;
	}

	public static void main(String[] args) {
		int[] data2 = {8,7,4,10,9,10};
		BinaryTree binaryTree = new BinaryTree();
		BinaryTree biTree = binaryTree.createBinaryTree(data2);

		System.out.println("深度优先遍历递归版:");
		DFS dfs = new DFS();
		List<TreeNode> treeNodeList = dfs.depthFirstSearchRec(biTree.root);
		for (TreeNode treeNode : treeNodeList) {
			System.out.print(treeNode.value+" ");
		}
		System.out.println();


		System.out.println("深度优先遍历非递归版:");
		List<TreeNode> treeNodes = dfs.depthFirstSearch(biTree.root);
		for (TreeNode treeNode : treeNodes) {
			System.out.print(treeNode.value+" ");
		}
		System.out.println();

		System.out.println("广度优先遍历:");
		BFS bfs = new BFS();
		List<TreeNode> bfsResult = bfs.bfs(biTree.root);
		for (TreeNode treeNode : bfsResult) {
			System.out.print(treeNode.value+" ");
		}
		System.out.println();
	}

}
