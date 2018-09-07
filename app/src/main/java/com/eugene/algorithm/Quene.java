package com.eugene.algorithm;

public class Quene<E> {

    private int DEF_CAPACITY = 10;

    private Object[] values = new Object[DEF_CAPACITY];

    private int size;

    /**
     * 使用循环数组
     */
    private int head, tail = -1;

    public void add(E e) {

        if (values.length == size) {

            Object[] temp = new Object[values.length * 2];
            for (int i = 0; i < values.length; i++) {
                temp[i] = values[i];
            }

            values = temp;
        }

        tail = (tail++ % values.length);
        values[tail] = e;
        size++;

    }

    public E poll() {

        if (size <= 0)
            throw new IndexOutOfBoundsException();

        E e = (E) values[head];
        values[head] = null;

        head = head++ % values.length;

        size++;

        return e;
    }


}
