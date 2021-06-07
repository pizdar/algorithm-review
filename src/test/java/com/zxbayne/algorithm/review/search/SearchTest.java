package com.zxbayne.algorithm.review.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class SearchTest {
    int[] array = {99, 88, 6, 5, 7, 3, 8, 4, 1};
    int target = 88;
    int result = 1;
    int notExistTarget = 555;
    int resultOfNotExist = -1;

    @Test
    public void testLinearSearch() {
        int linearResult1 = Search.linearSearch(array, target);
        int linearResult2 = Search.linearSearch(array, notExistTarget);
        Assertions.assertEquals(result, linearResult1);
        Assertions.assertEquals(resultOfNotExist, linearResult2);
    }

    @Test
    public void testBinarySearch() {
        int binaryResult1 = Search.binarySearch(array, target);
        int binaryResult2 = Search.binarySearch(array, notExistTarget);
        Assertions.assertEquals(7, binaryResult1);
        Assertions.assertEquals(resultOfNotExist, binaryResult2);
    }
}
