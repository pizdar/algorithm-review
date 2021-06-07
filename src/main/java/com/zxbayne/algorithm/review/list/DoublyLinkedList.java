package com.zxbayne.algorithm.review.list;

/**
 * 双向链表
 */
public class DoublyLinkedList<E> {
    /**
     * 头节点，指向第一个元素
     */
    private Node<E> head;

    /**
     * 尾结点，指向最后一个元素
     */
    private Node<E> tail;

    private int size;

    public DoublyLinkedList() {
        this.head = new Node<>();
        this.tail = head;
        this.size = 0;
    }

    /**
     * 增加一个元素到末尾
     *
     * @param element 要添加的元素值
     */
    public void add(E element) {
        if (element == null) {
            throw new NullPointerException("Argument e should't be null");
        }
        Node<E> newNode = new Node<>(element);
        // 双向链表为空的情况
        if (size == 0) {
            // 头结点指向新结点
            this.head = newNode;
            // 尾结点指向头节点
            this.tail = this.head;
        } else {
            // 双向链表不为空的情况
            // 尾结点的 next 指向新结点
            this.tail.next = newNode;
            // 新结点的 prev 指向尾节点
            newNode.prev = this.tail;
            // 尾结点指向新结点
            this.tail = newNode;
        }
        size++;
    }

    /**
     * 正向遍历输出双向链表
     */
    public void print() {
        if (size == 0) {
            throw new RuntimeException("链表为空");
        }
        // temp 现在是第一个元素
        Node<E> temp = head;
        int count = 0;
        while (temp != null) {
            System.out.printf("list[%d] = %s%n", count, temp.value);
            temp = temp.next;
            count++;
        }
    }

    public int getSize() {
        return size;
    }

    @SuppressWarnings("unused")
    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(T value) {
            this.value = value;
        }

        public Node() {
            // do nothing
        }
    }
}
