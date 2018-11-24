package com.eugene.datastructure.stack;

import com.eugene.datastructure.list.linkedlist.singlelinked.SNode;

/**
 * 栈的链式实现  链表不含头结点
 * top指针指向头部结点，push操作表示第一个结点添加，pop表示从第一个结点删除
 */
public class LinkedStack<T> implements IStack<T> {

    private SNode<T> top;

    private int size;

    @Override
    public boolean isEmpty() {
        return top == null || top.data == null;
    }

    @Override
    public void push(T data) {
        if (data == null)
            return;

        if (top == null) {
            top = new SNode(data, null);
        }

        SNode q = new SNode(data, null);

        top.next = q;
        top = q;
        size++;
    }

    @Override
    public T peek() {
        if (isEmpty())
            return null;

        return top.data;
    }

    @Override
    public T pop() {
        if (isEmpty())
            return null;

        //删除链表的第一个结点
        T old = top.data;
        top = top.next;
        size--;
        return old;
    }

    @Override
    public int size() {
        return size;
    }
}
