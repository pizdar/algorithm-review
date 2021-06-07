package com.zxbayne.algorithm.review.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class FrogJumpTest {
    @Test
    public void testSolution() {
        int result1 = FrogJump.solution(1);
        Assertions.assertEquals(1, result1);

        int result2 = FrogJump.solution(2);
        Assertions.assertEquals(2, result2);

        int result3 = FrogJump.solution(3);
        Assertions.assertEquals(3, result3);

        int result4 = FrogJump.solution(4);
        Assertions.assertEquals(5, result4);

        int result5 = FrogJump.solution(5);
        Assertions.assertEquals(8, result5);
    }
}
