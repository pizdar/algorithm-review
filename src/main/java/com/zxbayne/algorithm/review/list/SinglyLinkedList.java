package com.zxbayne.algorithm.review.list;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 单向链表
 */
public class SinglyLinkedList<E extends Comparable<E>> {
    /**
     * 头节点，它的 next 指向第一个元素
     */
    private final Node<E> head;

    public SinglyLinkedList() {
        // 初始化头节点，头节点不动，不存放具体的数据
        this.head = new Node<>();
    }

    public SinglyLinkedList(Node<E> node) {
        this.head = new Node<>();
        this.head.next = node;
    }

    /**
     * 将 value 添加到单链表的末尾
     *
     * @param value 要被加入的元素
     */
    public void add(E value) {
        Node<E> temp = head;
        // 遍历单链表，找到一个 Node，它的 next域为null，即为单链表的末尾
        while (temp.next != null) {
            temp = temp.next;
        }
        // 让单链表的末尾Node指向新Node
        temp.next = new Node<>(value);
    }

    public void add(Node<E> node) throws IllegalArgumentException {
        if (node == null) {
            throw new IllegalArgumentException("Argument node shouldn't be null.");
        }
        if (node.value == null) {
            throw new IllegalArgumentException("Argument node.value shouldn't be null.");
        }
        add(node.value);
    }

    /**
     * 获取单链表的节点个数，不统计头节点
     *
     * @return 单链表的节点个数
     */
    public int size() {
        if (head.next == null) {
            return 0;
        }
        int size = 0;
        Node<E> temp = head;
        while (temp.next != null) {
            temp = temp.next;
            size++;
        }
        return size;
    }

    public void print() {
        Node<E> current = head.next;
        while (current != null) {
            System.out.print("Node[" + current.value + "]->");
            current = current.next;
        }
        System.out.print("null\n");
    }

    /**
     * 【新浪面试题】 查找单链表中倒数第 k 个节点
     *
     * @param k 指定index
     * @return 倒数第 k 个节点的值
     */
    public E findLastIndexNode(int k) {
        int size = this.size();
        if (k < 0 || k > size) {
            return null;
        }
        // 得到size后，我们从链表的第一个元素开始遍历(size - k) 次就可以了，
        Node<E> current = head.next;
        int v = size - k;
        while (v != 0) {
            current = current.next;
            v--;
        }
        return current.value;
    }

    /**
     * 【腾讯面试题】反转单链表
     * 1 -> 3 -> 5 -> 7 -> 9 转换为 9 -> 7 -> 5 -> 3 -> 1
     * 这里使用遍历实现，还可以使用栈  请参考 this.reversePrint()
     * @return 反转后的单链表
     */
    public SinglyLinkedList<E> reverse() {
        // 只有一个节点，不用逆序
        if (head.next == null || head.next.next == null) {
            return this;
        }
        // 作为反转后的单链表的头节点
        Node<E> reversedHead = new Node<>();
        // current先赋为原始链表的第一个元素
        Node<E> current = head.next;
        // 遍历整个单链表
        while (current != null) {
            // 反转单链表过程如下
            // 遍历整个单链表，指针current指向的元素脱离原链表
            Node<E> temp = new Node<>(current.value);
            // 让 current 指向的元素指向 reversedHead.next，使其排到链表的最前面并连接后面的元素
            temp.next = reversedHead.next;
            // 单链表的头节点指向第一个元素
            reversedHead.next = temp;
            // 指针步进
            current = current.next;
        }
        // reversedHead去掉头节点
        return new SinglyLinkedList<>(reversedHead.next);
    }

    /**
     * 【百度面试题】逆序打印单链表
     * 思路一：先逆序this.reverse()再顺序打印this.print()
     * 思路二：利用栈后进先出的特性，遍历一遍单链表，每遍历一个元素就入栈，遍历完后再出栈打印
     */
    public void reversePrint() {
        Deque<Node<E>> stack = new LinkedList<>();
    
        Node<E> current = head.next;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        while (!stack.isEmpty()) {
            System.out.print("Node(" + stack.pop().value + ")->");
        }
        System.out.print("null\n");
    }

    /**
     * 提供两个有序的单链表，合并后的链表依然有序
     * example: a: 1->2->5->17->19 and b: 3->6->9->18->22
     * return => 1->2->3->5->6->9->17->18->19->22
     * @param other 另一个有序的单链表
     * @return 合并后的链表
     */
    public SinglyLinkedList<E> mergeInOrder(SinglyLinkedList<E> other) {
        // 各自的指针指向链表第一个元素
        Node<E> thisPointer = this.head.next;
        Node<E> otherPointer = other.head.next;
        SinglyLinkedList<E> result = new SinglyLinkedList<>();
        // 判断边界条件，两个链表其中一个为空，那么合并后的结果就是另一个链表
        if (thisPointer == null) {
            return other;
        }
        if (otherPointer == null) {
            return this;
        }
        // 同时遍历两个单链表指针，while循环的终止条件为：this或者other被遍历完
        while (thisPointer != null && otherPointer != null) {
            E thisValue = thisPointer.value;
            E otherValue = otherPointer.value;
            // 比较 thisValue 和 otherValue， 小的元素放入 result，并且自己的指针步进
            if (thisValue.compareTo(otherValue) < 0) {
                result.add(thisValue);
                thisPointer = thisPointer.next;
            } else {
                result.add(otherValue);
                otherPointer = otherPointer.next;
            }
        }
        // 寻找没被遍历完的链表，直接加入到 result后
        if (thisPointer == null) {
            result.add(otherPointer);
        } else {
            result.add(thisPointer);
        }
        return result;
    }

    // 单链表的结点定义
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public Node() {
            // do nothing
        }
    }
}
