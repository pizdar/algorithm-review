package com.zxbayne.algorithm.review.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SinglyLinkedListTest {
    @Test
    public void testSinglyLinkedList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println("size:" + list.size());
        Assertions.assertEquals(3, list.size());
    }

    @Test
    public void testFindLastIndexNode() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(9);
        System.out.println("size:" + list.size());
        Integer last = list.findLastIndexNode(1);
        System.out.println("last is " + last);
        Assertions.assertEquals(9, (int) last);

        Integer first = list.findLastIndexNode(5);
        System.out.println("first is " + first);
        Assertions.assertEquals(1, (int) first);
    }

    @Test
    public void testReverse() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(9);
        list.print();
        SinglyLinkedList<Integer> reversedList = list.reverse();
        reversedList.print();
        Assertions.assertEquals(reversedList.size(), list.size());
    }

    @Test
    public void testReversePrint() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(9);
        list.reversePrint();
        SinglyLinkedList<Integer> reversedList = list.reverse();
        reversedList.reversePrint();
        Assertions.assertEquals(reversedList.size(), list.size());
    }

    @Test
    public void testMergeInOrder() {
        SinglyLinkedList<Integer> a = new SinglyLinkedList<Integer>() {{
            add(1);
            add(2);
            add(5);
            add(17);
            add(19);
        }};
        SinglyLinkedList<Integer> b = new SinglyLinkedList<Integer>() {{
            add(3);
            add(6);
            add(9);
            add(18);
            add(22);
        }};
        SinglyLinkedList<Integer> result = a.mergeInOrder(b);
        result.print();
        Assertions.assertEquals(result.size(), (a.size() + b.size()));
    }
}
