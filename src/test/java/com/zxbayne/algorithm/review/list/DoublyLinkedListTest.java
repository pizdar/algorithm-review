package com.zxbayne.algorithm.review.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoublyLinkedListTest {
    private final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

    @Test
    public void testAdd() {
        list.add(1);
        list.add(2);
        list.add(3);
        Assertions.assertEquals(3, list.getSize(), "链表长度应该为3");
    }
}
