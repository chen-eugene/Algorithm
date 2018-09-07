package com.eugene.algorithm;

public class Stack<E> {

    private int size;

    private int DEFAULT_CAPACITY = 10;

    private Object[] values = new Object[DEFAULT_CAPACITY];


    public void push(E e) {

        //扩容
        if (size > values.length) {
            Object[] temp = new Object[values.length << 1];

            for (int i = 0; i < values.length; i++) {
                temp[i] = values[i];
            }
            values = temp;
        }

        values[size++] = e;
    }


    public void pop() {

        if (size <= 0)
            throw new IndexOutOfBoundsException();

        E e = (E) values[size--];

        values[size] = null;

    }


}
