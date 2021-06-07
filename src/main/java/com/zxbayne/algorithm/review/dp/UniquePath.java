package com.zxbayne.algorithm.review.dp;

/**
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class UniquePath {
    /**
     * leetcode-62
     * 一个机器人位于一个 m x n网格的左上角
     * <br>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角
     * <br>
     * 问总共有多少条不同的路径？
     *
     * @param m 网格的行数
     * @param n 网格的列数
     * @return 机器人从左上角到右下角，有多少种不同的行走路径
     */
    public static int solution(int m, int n) {
        // dp[i][j] 代表机器人走到 i,j 这个坐标有多少种不同的走法
        // 由于机器人只能往下或者右走，所以机器人在第一行或者第一列的所有位置都只有一种走法
        // dp[i][0] = 1    dp[0][j] = 1
        int[][] dp = new int[m][n];

        // 初始化
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // 推导
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
