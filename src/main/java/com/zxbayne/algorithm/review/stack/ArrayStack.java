package com.zxbayne.algorithm.review.stack;

public class ArrayStack<E> {
    private final E[] elements;
    private int top;
    private final int capacity;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        elements = ((E[]) new Object[capacity]);
        top = -1;
        this.capacity = capacity;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == (capacity - 1);
    }

    public int getCount() {
        return top + 1;
    }

    public boolean push(E data) {
        if (isFull()) {
            return false;
        }
        if (data == null) {
            return false;
        }
        elements[++top] = data;
        return true;
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E result = elements[top];
        top--;
        return result;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[top];
    }
}
