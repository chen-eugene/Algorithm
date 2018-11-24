package com.eugene.datastructure.linkedlist.singlelinked;

/**
 * 单向链表节点
 */
public class SNode<T> {

    public T data;

    public SNode<T> next;

    public SNode(T data) {
        this.data = data;
    }

    public SNode(T data, SNode<T> next) {
        this.data = data;
        this.next = next;
    }
}
