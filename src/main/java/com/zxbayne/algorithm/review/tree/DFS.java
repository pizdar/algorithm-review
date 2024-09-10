package com.zxbayne.algorithm.review.tree;

import java.util.*;

public class DFS {
	private ArrayList<TreeNode> result = new ArrayList<>();

	/**
	 * 递归版本
	 */
	public List<TreeNode> depthFirstSearchRec(TreeNode root) {
		if (root == null) {
			return result;
		}

		result.add(root);
		depthFirstSearchRec(root.left);
		depthFirstSearchRec(root.right);
		return result;
	}

	/**
	 * 非递归版本
	 */
	public List<TreeNode> depthFirstSearch(TreeNode root) {
		this.result = new ArrayList<>();
		if (root == null) {
			return result;
		}

		Deque<TreeNode> stack = new LinkedList<>();
		stack.add(root);

		while (!stack.isEmpty()) {
			TreeNode node = stack.poll();
			result.add(node);

			//向栈中先压入右子树，在压入左子树。这样出栈时，先出左子树再出右子树.也就是,先遍历左边，后遍历右边
			if (node.right != null) {
				stack.push(node.right);
			}

			if (node.left != null) {
				stack.push(node.left);
			}
		}

		return result;
	}

}
