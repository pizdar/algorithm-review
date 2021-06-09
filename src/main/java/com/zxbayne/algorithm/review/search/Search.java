package com.zxbayne.algorithm.review.search;

import java.util.Arrays;

/**
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class Search {
    /**
     * @param sequence 一个序列
     * @param target   被查找的元素值
     * @return target值在sequence序列中的下标，如果没找到则返回-1
     */
    public static int linearSearch(int[] sequence, int target) {
        for (int i = 0; i < sequence.length; i++) {
            if (target == sequence[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param sequence 一个序列
     * @param target   被查找的元素值
     * @return target值在sequence序列中的下标，如果没找到则返回-1
     */
    public static int binarySearch(int[] sequence, int target) {
        // 首先要对sequence排序
        Arrays.sort(sequence);

        int result = -1;
        int left = 0;
        int right = sequence.length - 1;

        while (left <= right) {
            // int mid = (left + right) / 2;
            int mid = left + (right - left) / 2;

            if (target > sequence[mid]) {
                left = mid + 1;
            } else if (target < sequence[mid]) {
                right = mid - 1;
            } else {
                result = mid;
                break;
            }
        }

        return result;
    }
}
