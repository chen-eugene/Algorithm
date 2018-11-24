package com.eugene.datastructure.stack;

import java.util.Arrays;

public class SeqStack<T> implements IStack<T> {

    private int capacity = 10;

    /**
     * 栈顶指针,-1代表空栈
     */
    private int top = -1;

    private T[] array;

    private int size;

    public SeqStack() {
        array = (T[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public void push(T data) {
        if (array.length == size)
            ensureCapacity(capacity * 2);

        array[++top] = data;
        size++;
    }

    @Override
    public T peek() {
        if (isEmpty())
            return null;

        return array[top];
    }

    @Override
    public T pop() {
        if (isEmpty())
            return null;

        size--;
        return array[top--];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 扩容
     */
    private void ensureCapacity(int capacity) {
        //如果需要拓展的容量比现在数组的容量还小,则无需扩容
        if (capacity < size)
            return;

        T[] old = array;
        array = Arrays.copyOf(old, old.length);
    }


}
