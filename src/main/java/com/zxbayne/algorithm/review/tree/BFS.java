package com.zxbayne.algorithm.review.tree;

import java.util.*;

public class BFS {
	/**
	 * 递归版本
	 */
	public List<TreeNode> bfs(TreeNode root) {
		ArrayList<TreeNode> result = new ArrayList<>();
		if (root == null) {
			return result;
		}

		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			result.add(node);

			if (node.left != null) {
				queue.add(node.left);
			}

			if (node.right != null) {
				queue.add(node.right);
			}
		}
		return result;
	}


}
