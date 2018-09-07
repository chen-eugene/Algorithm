package com.eugene.algorithm;

public class LinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;


    public void addLast(E e) {

        Node<E> l = tail;
        Node<E> newNode = new Node<>(e, l, null);

        tail = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }

        size++;

    }

    public void addFirst(E e) {

        Node<E> l = head;
        Node<E> newNode = new Node<>(e, null, l);

        head = newNode;
        if (l == null) {
            tail = newNode;
        } else {
            l.prev = newNode;
        }

        size++;
    }


    private void addIndex(int index, E e) {

        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException();

        Node<E> x;
        if (index << 1 < size) {
            x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }

        Node<E> prev = x.prev;
        Node<E> next = x.next;
        Node<E> newNode = new Node<>(e, prev, x);

        if (prev == null) {
            head = newNode;
        } else {
            prev.next = newNode;
        }

        if (next == null) {
            tail = newNode;
        }

        size++;
    }


    public E deleteFirst() {

        Node<E> l = head;

        if (l == null)
            throw new ArrayIndexOutOfBoundsException();

        E e = l.item;
        Node<E> next = l.next;
        head = next;

        l.item = null;
        l.next = null;

        if (next == null) {
            tail = null;
        } else {
            next.prev = null;
        }

        size--;
        return e;
    }

    public E deleteLast() {

        Node<E> l = tail;

        if (l == null)
            throw new ArrayIndexOutOfBoundsException();

        E e = l.item;
        Node<E> prev = l.prev;

        tail = prev;

        if (prev == null) {
            head = null;
        } else {
            prev.next = null;
        }

        size--;
        return e;
    }

    public E deleteAt(int index) {

        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException();

        Node<E> x;
        if (index << 1 < size) {
            x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }

        E e = x.item;
        Node<E> prev = x.prev;
        Node<E> next = x.next;

        x.item = null;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.next = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.prev = null;
        }

        size--;
        return e;
    }



    private static class Node<E> {

        E item;
        Node<E> prev;
        Node<E> next;

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

}
