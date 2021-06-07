package com.zxbayne.algorithm.review.dp;

/**
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class FrogJump {
    /**
     * 青蛙跳台阶问题：一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     *
     * @param n 台阶的数量
     * @return 返回青蛙跳上这个台阶共有多少种跳法
     */
    public static int solution(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] dp = new int[n];
        // 初始化dp数组
        // 青蛙跳上1级的台阶，有一种跳法
        dp[0] = 1;
        // 青蛙跳上2级的台阶，有两种跳法
        dp[1] = 2;
        // 青蛙跳上n级的台阶，有 dp[n] = dp[n-1] + dp[n-2] 种跳法

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    public static int solution2(int n) {
        int a = 1, b = 1, sum = 0;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
}
