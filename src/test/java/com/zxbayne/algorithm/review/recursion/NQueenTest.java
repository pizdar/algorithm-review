package com.zxbayne.algorithm.review.recursion;



import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 * @date 2021/02/01
 */
public class NQueenTest {
    @Test
    public void testSolveNQueen() {
        NQueen nQueen = new NQueen(8);
        List<int[]> result = nQueen.solveNQueens();
        for (int[] list : result) {
            System.out.println(Arrays.toString(list));
        }
        Assertions.assertEquals(92, result.size());
    }
}
