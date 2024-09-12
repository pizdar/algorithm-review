package com.zxbayne.algorithm.review.dp;

public class ZeroOneBackup {
	public int maxValue(int[] value, int [] weight, int size) {
		int m = value.length;

		int[][] dp = new int[m][size + 1];

		// 重量为0的时候，最大价值为0
		for (int i = 0; i < m; i++) dp[i][0] = 0;
		// 只考虑物品0，那么只要放得下，那么最大价值就是这个物品的价值
		for (int i = 0; i < size; i++) {
			if (i >= weight[0]) dp[0][i] = value[0];
		}

		for (int i = 1; i < m; i++) { // 先遍历物品
			for (int j = 1; j <= size; j++) { // 再遍历重量
				if (j < weight[i]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], value[i] + dp[i - 1][j - weight[i]]);
				}
			}
		}
		return dp[m - 1][size];
	}

	public static void main(String[] args) {
		ZeroOneBackup solution = new ZeroOneBackup();
		System.out.println(solution.maxValue(new int[]{15,20,30}, new int[]{1,3,4}, 4));
	}
}
