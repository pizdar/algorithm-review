package com.zxbayne.algorithm.review.list;


import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SinglyRingLinkedListTest {
    private final SinglyRingLinkedList<String> list = new SinglyRingLinkedList<>();

    @BeforeEach
    public void init() {
        list.add("Alice");
        list.add("Bob");
        list.add("Cindy");
        list.add("David");
        list.add("Floyd");
    }

    @Test
    public void testAdd() {
        list.print();
        Assertions.assertEquals(5, list.getSize());
    }

    @Test
    public void testSolveJosephProblem() {
        List<String> result = list.solveJosephProblem(0, 1);
        System.out.println(result);
        List<String> expect = List.of("Alice", "Bob", "Cindy", "David", "Floyd");

        Assertions.assertEquals(expect, result);
    }
}
