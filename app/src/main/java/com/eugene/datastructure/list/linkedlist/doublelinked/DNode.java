package com.eugene.datastructure.list.linkedlist.doublelinked;

public class DNode<T> {

    public T data;
    public DNode prev;
    public DNode next;


    public DNode() {
    }

    public DNode(T data, DNode prev, DNode next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

}
