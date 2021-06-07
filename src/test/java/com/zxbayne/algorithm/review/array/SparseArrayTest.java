package com.zxbayne.algorithm.review.array;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SparseArrayTest {
    @Test
    public void testTransformAndRecover() {
        String[][] origin = {
                {"hello", "world", "nope"},
                {"nope", "world", "nope"},
                {"hello", "nope", "nope"},
                {"hello", "world", "nope"},
        };
        SparseArray<String> sparseArray = new SparseArray<>(origin, "nope");
        String[][] recover = sparseArray.recover(String.class);
        System.out.println("原始数组:");
        for (String[] row : origin) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("***************************************");
        System.out.println("稀疏数组还原:");
        for (String[] row : recover) {
            System.out.println(Arrays.toString(row));
        }
        Assertions.assertArrayEquals(origin, recover, "稀疏数组恢复失败");
    }
}
