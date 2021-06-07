package com.zxbayne.algorithm.review.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortTest {
    int[] array = {99, 88, 6, 5, 7, 3, 8, 4, 1};
    int[] result = new int[9];

    @BeforeEach
    public void contextLoad() {
        result = Arrays.copyOf(array, array.length);
        Arrays.sort(result);
    }

    @Test
    public void testBubbleSort() {
        Sort.bubbleSort(array);
        Assertions.assertArrayEquals(result, array);
    }

    @Test
    public void testSelectionSort() {
        Sort.selectionSort(array);
        Assertions.assertArrayEquals(result, array);
    }

    @Test
    public void testInsertionSort() {
        Sort.insertionSort(array);
        Assertions.assertArrayEquals(result, array);
    }

    @Test
    public void testShellSort() {
        Sort.shellSort(array);
        Assertions.assertArrayEquals(result, array);
    }

    @Test
    public void testQuickSort() {
        Sort.quickSort(array, 0, array.length - 1);
        Assertions.assertArrayEquals(result, array);
    }

    @Test
    public void testMergeSort() {
        Sort.mergeSort(array, 0, array.length - 1);
        Assertions.assertArrayEquals(result, array);
    }

    @Test
    public void testRadixSort() {
        Sort.radixSort(array);
        Assertions.assertArrayEquals(result, array);
    }

}
