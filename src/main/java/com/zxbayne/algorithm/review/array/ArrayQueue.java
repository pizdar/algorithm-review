package com.zxbayne.algorithm.review.array;

/**
 * 使用数组模拟队列
 */
public class ArrayQueue<E> {
    private final E[] array;
    /* 指向队头元素 */
    private int front;
    /* 队列内元素的个数 */
    private int count;
    /* 队列数组的容量 */
    private final int capacity;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.front = 0;
        this.count = 0;
        this.array = (E[]) new Object[capacity];
    }

    public boolean isFull() {
        return count == capacity;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int getCount() {
        return count;
    }

    /**
     * 入队列
     * @param element 需要添加的元素
     * @throws RuntimeException 异常信息
     */
    public void push(E element) throws RuntimeException{
        // 排除边界条件
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        // 队列尾的计算公式
        int tail = (front + count) % capacity;
        array[tail] = element;
        // 队列内元素的个数增加
        this.count++;
    }

    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        // 获取当前队头元素
        E result = array[front];
        // 队头指针后移
        front = (front + 1) % capacity;
        // 队列内元素的个数减少
        count--;

        return result;
    }

    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的");
        }
        return array[front];
    }
}
