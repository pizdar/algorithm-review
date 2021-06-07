package com.zxbayne.algorithm.review.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayQueueTest {
    @Test
    public void testArrayQueue() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        queue.push(1);
        queue.push(3);
        Assertions.assertEquals(2, queue.getCount(), "此时队列长度应该为2");
        queue.pop();
        queue.pop();
        Assertions.assertEquals(0, queue.getCount(), "此时队列长度应该为0");
        queue.push(1);
        queue.push(3);
        queue.push(1);
        queue.push(3);
        queue.push(1);
        Assertions.assertEquals(5, queue.getCount(), "此时队列长度应该为5");
    }
}
