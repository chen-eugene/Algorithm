package com.eugene.datastructure;

/**
 * 单链表实现
 */
public class LinkedQueue<T> {

    private Node<T> head, tail;

    private int size;

    public void push(T t) {

        Node<T> q = new Node<>(t, null);

        if (tail == null) {
            head = tail = q;
        } else {
            tail.next = q;
        }

        tail = q;
        size++;
    }


    public T poll() {

        if (head == null)
            return null;

        Node<T> temp = head;

        head = head.next;

        if (head == null) {
            tail = null;
        }

        size--;
        return temp.value;
    }


    private static class Node<T> {

        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public int getSize() {
        return size;
    }

}
