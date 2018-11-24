package com.eugene.datastructure.queue;

import com.eugene.datastructure.list.linkedlist.singlelinked.SNode;

import java.util.NoSuchElementException;

/**
 * 链式队列的实现
 * 采用单链表实现
 */
public class LinkedQueue<T> implements IQueue<T> {

    private SNode<T> front, rear;

    private int size;

    private static final int MAX_CAPACITY = 128;

    public LinkedQueue() {
        front = rear = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
//        return front == null && rear == null;
        return size == 0;
    }

    @Override
    public boolean add(T data) {
        if (data == null)
            return false;

        SNode p = new SNode(data, null);
        if (isEmpty()) {
            //空队列插入
            front = p;
        } else {
            //非空队列,尾部插入
            rear.next = p;
        }
        rear = p;
        size++;
        return true;
    }

    @Override
    public boolean offer(T data) {
        if (data == null)
            throw new NullPointerException("The data can\'t be null");
        if (size >= MAX_CAPACITY)
            throw new IllegalArgumentException("The capacity of LinkedQueue has reached its maxSize:128");

        SNode p = new SNode(data, null);
        if (isEmpty()) {
            //空队列插入
            front = p;
        } else {
            //非空队列,尾部插入
            rear.next = p;
        }
        rear = p;
        size++;
        return true;
    }

    @Override
    public T peek() {
        return isEmpty() ? null : front.data;
    }

    @Override
    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException("The LinkedQueue is empty");
        }
        return this.front.data;
    }

    @Override
    public T poll() {
        T old = isEmpty() ? null : front.data;
        front = front.next;
        //判断还有没有元素
        if (front == null)
            rear = null;
        size--;
        return old;
    }

    @Override
    public T remove() {
        if (isEmpty())
            throw new NoSuchElementException("The LinkedQueue is empty");

        T old = front.data;
        front = front.next;
        if (front == null)
            rear = null;
        size--;
        return old;
    }

    @Override
    public void clearQueue() {
        front = rear = null;
        size = 0;
    }
}
