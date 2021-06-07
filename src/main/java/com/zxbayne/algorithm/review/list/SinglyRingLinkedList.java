package com.zxbayne.algorithm.review.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 单向环形链表，不带头节点
 */
public class SinglyRingLinkedList<E> {
    /**
     * first指向链表的第一个元素
     */
    private Node<E> first;

    private int size;

    public int getSize() {
        return size;
    }

    public void add(E value) {
        if (value == null) {
            throw new NullPointerException("Argument value should't be null");
        }
        Node<E> newNode = new Node<>(value);
        // 判断当前链表是否为空
        if (size == 0) {
            first = newNode;
            // 链表只有一个结点时，那个结点的 next 指向自己
            newNode.next = newNode;
        } else {
            Node<E> tail = tail();
            // 最后一个节点的 next 指向新值
            tail.next = newNode;
            // 新值的next指向第一个元素
            newNode.next = first;
        }
        size++;
    }

    public void print() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        int bound = size - 1;
        // temp 现在是第一个元素
        Node<E> temp = first;
        int count = 0;
        while (count <= bound) {
            System.out.printf("list[%d] = %s%n", count, temp.value);
            temp = temp.next;
            count++;
        }
    }

    /**
     * 遍历找到最后一个结点
     *
     * @return 单向环形链表的最后一个结点
     */
    private Node<E> tail() {
        if (size == 0) {
            throw new RuntimeException("size = 0");
        }
        Node<E> current = first;
        while (current.next != first) {
            current = current.next;
        }
        return current;
    }

    /**
     * 使用单向循环链表解决约瑟夫问题
     * alice -> bob -> cindy 这样的链表，假如 countNum是 5，startFrom是 0,即从 alice开始报数，数 5 下，最后数的那个人出队，
     * 即 bob 出队
     *
     * @param startFrom 开局时，从第 startFrom 开始数。 取值范围 [0, size - 1]
     * @param count     数 count 下
     * @return 出队的结果
     */
    public List<E> solveJosephProblem(int startFrom, int count) {
        ArrayList<E> result = new ArrayList<>();
        Itr iter = iterator();
        if (size == 0 || count == 0) {
            throw new IllegalArgumentException();
        }
        if (startFrom < 0 || startFrom > size - 1) {
            throw new IllegalArgumentException("从哪多出来的人?");
        }
        if (size == 1) {
            result.add(first.value);
            return result;
        }

        // 让 iter 指向索引为 startFrom的元素
        while (startFrom != 0) {
            iter.next();
            startFrom--;
        }
        // 玩游戏
        while (size != 0) {
            // 数 count 下，因为自己也要数，因此迭代器移动的次数是 count - 1 下
            int moveStep = count - 1;

            while (moveStep != 0) {
                iter.next();
                moveStep--;
            }
            E poped = iter.peek();
            result.add(poped);

            iter.remove();
        }

        return result;
    }

    /**
     * 返回一个单向循环链表的迭代器，这个迭代器会无限循环
     *
     * @return 迭代器对象
     */
    public Itr iterator() {
        return new Itr();
    }

    /**
     * 实现迭代器接口
     */
    private class Itr implements Iterator<E> {
        private Node<E> current;
        private Node<E> beforeCurrent;

        public Itr() {
            this.current = first;
            beforeCurrent = current;
        }

        @Override
        public boolean hasNext() {
            // 会导致无限循环
            return size != 0;
        }

        @Override
        public E next() {
            Node<E> temp = current;
            beforeCurrent = current;
            current = current.next;

            return temp.value;
        }

        public E peek() {
            return current.value;
        }

        /**
         * 删除 current 指针指向的元素，并且 current 指向下一个元素
         */
        @Override
        public void remove() {
            // 判断边界条件
            if (size == 0) {
                throw new RuntimeException("current list is empty");
            }
            // 如果只剩一个元素
            if (size == 1) {
                first = null;
                current = null;
                size = 0;
            } else if (current == first) { // 如果要删除的元素是第一个元素
                Node<E> tail = tail();
                // 最后一个元素指向第二个元素
                tail.next = first.next;
                // first指向后一个元素
                first = first.next;
                // current 重新指向 first
                current = first;
                size--;
            } else { // 其他情况
                // 前一个元素指向 current 的后一个元素
                beforeCurrent.next = current.next;
                // current指针指向下一个元素
                current = current.next;
                size--;
            }
        }
    }

    /**
     * 单向循环链表的结点定义
     */
    private static class Node<T> {
        T value;

        Node<T> next;

        Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
