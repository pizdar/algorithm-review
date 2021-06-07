package com.zxbayne.algorithm.review.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ArrayStackTest {
    
    @Test
    public void test() {
        ArrayStack<String> stack = new ArrayStack<>(5);
        Assertions.assertEquals(0, stack.getCount());
        stack.push("foo");
        stack.push("bar");
        Assertions.assertEquals(2, stack.getCount());
        String aPop = stack.pop();
        Assertions.assertEquals("bar", aPop);
        Assertions.assertFalse(stack.isEmpty());
        Assertions.assertFalse(stack.isFull());
        stack.pop();
        Assertions.assertTrue(stack.isEmpty());
        Assertions.assertFalse(stack.isFull());
        stack.push("data");
        stack.push("foo");
        stack.push("bar");
        stack.push("baz");
        stack.push("wtf");
        Assertions.assertTrue(stack.isFull());
    }
}
