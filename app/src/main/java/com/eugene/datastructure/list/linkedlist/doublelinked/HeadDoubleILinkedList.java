package com.eugene.datastructure.list.linkedlist.doublelinked;

import com.eugene.datastructure.list.linkedlist.ILinkedList;

/**
 * 双链表的实现,带头结点(不带数据)的双链表,为了更高的效率该类包含指向尾部的指针tail
 */
public class HeadDoubleILinkedList<T> implements ILinkedList<T> {

    /**
     * 不带数据的头结点
     */
    private DNode<T> head;
    /**
     * 尾结点
     */
    private DNode<T> tail;

    public HeadDoubleILinkedList() {
        head = tail = new DNode<>();
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public int length() {
        DNode p = head.next;
        int i = 0;
        while (p != null) {
            p = p.next;
            i++;
        }
        return i;
    }

    @Override
    public T get(int index) {
        if (index < 0)
            return null;

        DNode<T> p = head;
        int i = 0;
        //注意起始结点为this.head.next
        //如果起始点为this.head时，j的判断为j<=index，
        //因为需要寻找的是当前结点而不是前一个结点。
        while (p != null && i <= index) {
            p = p.next;
            i++;
        }

        if (p != null)
            return p.data;

        return null;
    }

    @Override
    public T set(int index, T data) {
        if (index < 0 || data == null)
            return null;

        DNode<T> p = head;
        int i = 0;
        //查找需要替换的位置
        while (p != null && i <= index) {
            p = p.next;
            i++;
        }

        if (p != null) {
            //替换数据
            T old = p.data;
            p.data = data;
            return old;
        }

        return null;
    }

    @Override
    public boolean add(int index, T data) {
        if (index < 0 || data == null)
            throw new NullPointerException("index < 0 || data == null");

        int i = 0;
        DNode p = head;
        //查找要插入结点位置的前一个结点
        while (p.next != null && i < index) {
            p = p.next;
            i++;
        }

        DNode q = new DNode<T>(data, null, null);

        //空双链表插入,需要确保p.next不为空
        if (p.next != null) {
            p.next.prev = q;
        }

        //更改p的后继指针
        p.next = q;

        //在尾部插入时需要注意更新tail指向
        if (p == tail) {
            tail = q;
        }

        return true;
    }

    @Override
    public boolean add(T data) {
        if (data == null)
            return false;

        DNode<T> p = new DNode<>(data, tail, null);

        tail.next = p;
        tail = p;
        return true;
    }

    /**
     * 根据下标删除结点
     * 1.头删除
     * 2.中间删除
     * 3.尾部删除,更新tail指向
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= length() || isEmpty())
            return null;

        DNode<T> p = head;
        int i = 0;
        //头删除/尾删除/中间删除,查找需要删除的结点(要删除的当前结点因此i<=index)
        while (p != null && i <= index) {
            p = p.next;
            i++;
        }

        p.prev.next = p.next;

        if (p.next != null) {
            p.next.prev = p.prev;
        }

        //如果是尾结点，更新尾结点指向
        if (p == tail) {
            tail = p.prev;
        }
        return p.data;
    }

    /**
     * * 根据data删除结点,无需像单向链表那样去存储要删除结点的前一个结点
     * 1.头删除
     * 2.中间删除
     * 3.尾部删除,更新tail指向
     */
    @Override
    public boolean removeAll(T data) {
        if (data == null || isEmpty())
            return false;

        //注意这里的起点,如果起点为this.head,那么情况区别如同前面的根据index的删除实现
        DNode p = head.next;

        boolean isRemove = false;
        while (p != null) {
            if (p.data.equals(data)) {
                //如果是尾结点
                if (p == tail) {
                    p.prev.next = null;
                    tail = p.prev;
                    tail.next = null;
                } else {
                    //如果是在中间删除,更新前继和后继指针指向
                    p.prev.next = p.next;
                    p.next.prev = p.prev;
                }
                isRemove = true;
            }
            //继续查找
            p = p.next;
        }
        return isRemove;
    }

    @Override
    public void clear() {
        head.next = null;
        tail = head;
    }

    @Override
    public boolean contains(T data) {
        if (data == null || isEmpty())
            return false;

        DNode p = head.next;

        while (p != null) {
            if (p.data.equals(data)) {
                return true;
            } else {
                p = p.next;
            }
        }
        return false;
    }
}
