package com.zxbayne.algorithm.review.array;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/**
 * 将一个原始大小为 n * n 的二维数组，其中有意义的数的数量为 t，忽略掉无意义的值后，将有意义的值以及所在位置存储在一个
 * 大小为 t * 3 的稀疏数组内
 */
public class SparseArray<E> {

    //存储非空对象的位置信息，若原始的二维数组有n个非空元素则 position 大小为 n * 2
    private final int[][] position;
    // 存储非空对象本身
    private final E[] elements;
    // 存储被忽略的无意义值
    private final E ignored;
    // 记录原始数组的行数
    private final int originRows;
    // 记录原始数组的列数
    private final int originCols;

    /**
     * 构造稀疏数组对象
     *
     * @param array   待压缩的原始二维数组
     * @param ignored 需要忽略的对象
     */
    @SuppressWarnings("unchecked")
    public SparseArray(E[][] array, E ignored) {
        this.originRows = array.length;
        this.originCols = array[0].length;
        this.ignored = ignored;
        // 遍历二维数组，得到不忽略的元素个数
        int countNotIgnored = 0;
        for (E[] row : array) {
            for (E item : row) {
                if (!item.equals(ignored)) {
                    countNotIgnored++;
                }
            }
        }

        // 初始化数组
        position = new int[countNotIgnored][2];
        elements = (E[]) new Object[countNotIgnored];
        // 记录当前处理到第几个不忽略的元素
        int count = 0;
        // 遍历原始二维数组
        for (int i = 0; i < originRows; i++) {
            for (int j = 0; j < originCols; j++) {
                // 遇到不能被忽略的对象，将其加入到稀疏数组里
                if (!array[i][j].equals(ignored)) {
                    position[count][0] = i;
                    position[count][1] = j;
                    elements[count] = array[i][j];
                    count++;
                }
            }
        }
    }

    /**
     * 返回原始的二维数组
     *
     * @return 原始二维数组，同时传递值给receiver和作为函数返回值
     */
    @SuppressWarnings("unchecked")
    public E[][] recover(Class<?> componentType) {
        // 以下方法是不允许的
        // E[][] result = (E[][]) new Object[originRows][originCols];
        E[][] result = (E[][]) Array.newInstance(componentType, originRows, originCols);
        if (elements.length == 0) {
            return result;
        }
        int count = 0;
        int limit = position.length;
        for (int i = 0; i < originRows; i++) {
            for (int j = 0; j < originCols; j++) {
                if (count < limit && i == position[count][0] && j == position[count][1]) {
                    result[i][j] = elements[count];
                    count++;
                } else {
                    result[i][j] = ignored;
                }
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SparseArray<?> that = (SparseArray<?>) o;

        if (originRows != that.originRows) {
            return false;
        }
        if (originCols != that.originCols) {
            return false;
        }
        if (!Arrays.deepEquals(position, that.position)) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(elements, that.elements)) {
            return false;
        }
        return Objects.equals(ignored, that.ignored);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(position);
        result = 31 * result + Arrays.hashCode(elements);
        result = 31 * result + (ignored != null ? ignored.hashCode() : 0);
        result = 31 * result + originRows;
        result = 31 * result + originCols;
        return result;
    }
}
