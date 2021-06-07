package com.zxbayne.algorithm.review.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class UniquePathTest {
    @Test
    public void testSolution() {
        int result = UniquePath.solution(3, 7);
        Assertions.assertEquals(28, result);
    }
}
