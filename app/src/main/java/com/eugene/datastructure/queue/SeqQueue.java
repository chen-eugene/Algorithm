package com.eugene.datastructure.queue;

import java.util.NoSuchElementException;

/**
 * 顺序队列的实现
 */
public class SeqQueue<T> implements IQueue<T> {

    private static final int DEF_CAPACITY = 10;

    private T[] elements;

    private int front;

    /**
     * 尾指针指向下一个空元素位置，
     * 添加元素时不用更新头指针，方便操作
     */
    private int rear;

    private int size;

    public SeqQueue() {
        elements = (T[]) new Object[DEF_CAPACITY];
        front = rear = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        //在队列为空和队列满时，front == rear
        return size == 0;
    }

    /**
     * data 入队,添加成功返回true,否则返回false,可扩容
     */
    @Override
    public boolean add(T data) {
        if (data == null)
            return false;

        //判断队列的方法
        //头指针等于尾指针，并且队列不为空：front == rear && !isEmpty
        if (size == elements.length) {
            ensureCapacity(elements.length * 2 + 1);
        }

        elements[rear] = data;
        //更新rear指向下一个空元素的位置
        rear = (rear + 1) % elements.length;
        size++;
        return true;
    }

    @Override
    public boolean offer(T data) {
        if (data == null)
            throw new NullPointerException("The data can\'t be null");

        //队满抛出异常
        if (size == elements.length)
            throw new IllegalArgumentException("The capacity of SeqQueue has reached its maximum");

        elements[rear] = data;
        rear = (rear + 1) % elements.length;
        size++;
        return true;
    }

    @Override
    public T peek() {
        return elements[front];
    }

    @Override
    public T element() {
        if (isEmpty())
            throw new NoSuchElementException("The SeqQueue is empty");

        return peek();
    }

    @Override
    public T poll() {
        T old = elements[front];
        //可以不置空，因为在后面添加元素的时候为覆盖
        //elements[front] = null;

        //头指针后移
        front = (front + 1) & elements.length;
        size--;
        return old;
    }

    @Override
    public T remove() {
        if (isEmpty())
            throw new NoSuchElementException("The SeqQueue is empty");

        return poll();
    }

    @Override
    public void clearQueue() {
        for (int i = front; i != rear; i = (i + 1) % elements.length) {
            elements[i] = null;
        }
        //指针复位
        front = rear = 0;
        size = 0;
    }

    /**
     * 扩容
     */
    private void ensureCapacity(int newCapacity) {
        //如果需要拓展的容量比现在数组的容量还小,则无需扩容
        if (newCapacity < size)
            return;

        T[] old = elements;

        elements = (T[]) new Object[newCapacity];
        //复制元素,必须从front指针开始，即队列的头
        for (int i = front; i != rear; i = (front + 1) % old.length) {
            elements[i] = old[i];
        }
        //复位front、rear指针
        front = 0;
        rear = old.length;
    }
}
