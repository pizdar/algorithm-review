package com.zxbayne.algorithm.review.dp;

/**
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class MinimumPathSum {
    /**
     * leetcode-64
     * 给定一个包含非负整数的m x n的网格，找出一条从左上角到右下角的路径，使得路径上的数字总和最小。一次只能向下或向右走一格。
     * @param grid 一个包含非负整数的 m x n 的网格，样例输入: <br/>
     *        grid = [
     *          [1, 3, 1],
     *          [1, 5, 1],
     *          [4, 2, 1]
     *        ]
     * @return 从左下角走到右下角，路径数字总和最小的值。样例输出: 7<br/>
     *         解释： 因为路径1 + 3 + 1 + 1 + 1 的和最小
     */
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m <= 0 || n <= 0) {
            return 0;
        }
        // dp[i][j]的含义是：从左上角走到(i, j)这个位置时，最小的路径和是dp[i][j]，那么dp[m-1][n-1]就是我们要的答案了
        int[][] dp = new int[m][n];
        // 初始化
        dp[0][0] = grid[0][0];
        // dp最上面一行以及最上面一列的值可以直接求：dp[0][i] = grid[0][i] + dp[0][i-1]
        // 求第一行的初始值
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        // 求第一列的初始值
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        // 推导出dp[m - 1][n - 1]
        // dp[i][j] = min(dp[i][j-1], dp[i-1][j]) + grid[i][j]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

}
