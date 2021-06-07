package com.zxbayne.algorithm.review.sort;

/**
 * 各种排序算法的实现，只提供了默认的递增排序
 *
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 * @date 2021/02/01
 */
public class Sort {

    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int minIndex = i;
            for (int j = i; j < length; j++) {
                minIndex = array[minIndex] > array[j] ? j : minIndex;
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public static void insertionSort(int[] array) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            // 有序表最后一个元素的index
            int indexOfOrdered = i - 1;
            // 无序表第一个元素的值
            int insertedValue = array[i];
            // indexOfOrdered指针从后往前找，符合while循环的条件则说明要被插入的元素比指针指向的元素大，
            // 需要将当前位置的值放到下一个空间，为insertedValue腾位置
            // 同时inserted指针前移
            // 退出while循环则说明找到要插入的位置了（要插入到indexOfOrdered + 1这个位置）
            while (indexOfOrdered >= 0 && insertedValue < array[indexOfOrdered]) {
                array[indexOfOrdered + 1] = array[indexOfOrdered];
                indexOfOrdered--;
            }
            // 插入
            array[indexOfOrdered + 1] = insertedValue;
        }
    }

    public static void shellSort(int[] array) {
        int length = array.length;
        int gap = length / 2;
        while (gap > 0) {
            for (int i = gap; i < length; i++) {
                int current = array[i];
                // 希尔排序分组后，在该gap分组下的有序表的index
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > current) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = current;
            }
            // gap = gap 整除 2
            gap /= 2;
        }
    }

    public static void quickSort(int[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);
            quickSort(array, begin, partitionIndex);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] array, int begin, int end) {
        int pivot = array[(begin + end) / 2];

        // 初始化双指针，指向前一个元素
        int leftPointer = begin - 1;
        int rightPointer = end + 1;
        while (true) {

            do {
                leftPointer++;
            } while (array[leftPointer] < pivot);

            do {
                rightPointer--;
            } while (array[rightPointer] > pivot);

            if (leftPointer >= rightPointer) {
                return rightPointer;
            }

            int temp = array[leftPointer];
            array[leftPointer] = array[rightPointer];
            array[rightPointer] = temp;
        }
    }

    public static void mergeSort(int[] array, int begin, int end) {
        if (begin < end) {
            int middle = (begin + end) / 2;
            mergeSort(array, begin, middle);
            mergeSort(array, middle + 1, end);
            merge(array, begin, middle, end);
        }
    }

    /**
     * 合并{@code array}的两个子数组
     * 第一个子数组: array[begin] ~ array[middle]
     * 第二个子数组: array[middle + 1] ~ array[end]
     */
    @SuppressWarnings("ManualArrayCopy")
    private static void merge(int[] array, int begin, int middle, int end) {
        // 两个子数组的大小
        int leftSize = middle - begin + 1;
        int rightSize = end - middle;

        // 初始化temp数组
        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[begin + i];
        }
        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = array[middle + i + 1];
        }

        // 初始化指针
        int l = 0;
        int r = 0;
        int current = begin;

        while (l < leftSize && r < rightSize) {
            if (leftArray[l] <= rightArray[r]) {
                array[current] = leftArray[l];
                l++;
            } else {
                array[current] = rightArray[r];
                r++;
            }
            current++;
        }

        while (l < leftSize) {
            array[current] = leftArray[l];
            l++;
            current++;
        }

        while (r < rightSize) {
            array[current] = rightArray[r];
            r++;
            current++;
        }
    }

    public static void radixSort(int[] array) {
        // 先得到数组中最大的数的位数
        int max = array[0];
        for (int element : array) {
            max = Math.max(max, element);
        }
        int digitOfMaxNumber = String.valueOf(max).length();

        // 定义一个二维数组，表示十个桶
        // 每个桶就是一个一维数组，为了防止溢出，一维数组的长度为array.length
        int[][] bucket = new int[10][array.length];

        // 为了记录每个桶中实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, div = 1; i < digitOfMaxNumber; i++, div *= 10) {
            for (int element : array) {
                int digitOfElement = element / div % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = element;
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0;
            // 按照这个桶的顺序，将一维数组的数据分别取出，放入原来的数组
            for (int j = 0; j < bucket.length; j++) {
                // 如果桶中有数据，我们才放入到原数组中
                if (bucketElementCounts[j] != 0) {
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        array[index] = bucket[j][k];
                        index++;
                    }
                }
                bucketElementCounts[j] = 0;
            }
        }
    }
}
