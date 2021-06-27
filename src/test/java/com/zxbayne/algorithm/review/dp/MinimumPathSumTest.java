package com.zxbayne.algorithm.review.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class MinimumPathSumTest {
    int[][] testCase = {
            {1, 3, 1},
            {1, 5 ,1},
            {1, 8, 1}
    };
    @Test
    public void test() {
        int result = MinimumPathSum.minPathSum(testCase);
        Assertions.assertEquals(7, result);
    }
}
