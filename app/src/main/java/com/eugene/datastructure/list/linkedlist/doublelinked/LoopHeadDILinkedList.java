package com.eugene.datastructure.list.linkedlist.doublelinked;

import com.eugene.datastructure.list.linkedlist.ILinkedList;

/**
 * 循环双链表,带空头结点(不含数据),循环链表可以不需要尾部指针
 */
public class LoopHeadDILinkedList<T> implements ILinkedList<T> {

    /**
     * 不带数据的头结点
     */
    private DNode<T> head;

    public LoopHeadDILinkedList() {
        //循环链表初始化
        head = new DNode<>();
        head.prev = head.next = head;
    }

    @Override
    public boolean isEmpty() {
        return head.next == head;
    }

    @Override
    public int length() {
        DNode p = head.next;
        int i = 0;
        while (p != head) {
            p = p.next;
            i++;
        }
        return i;
    }

    @Override
    public T get(int index) {
        if (index < 0 || isEmpty())
            return null;

        DNode<T> p = head.next;
        int i = 0;
        while (p != head && i < index) {
            p = p.next;
            i++;
        }

        if (p != head)
            return p.data;

        return null;
    }

    @Override
    public T set(int index, T data) {
        if (index < 0 || data == null)
            return null;

        DNode<T> p = head.next;
        int i = 0;
        while (p != head && i < index) {
            p = p.next;
            i++;
        }

        if (p != head) {
            T old = p.data;
            p.data = data;
            return old;
        }
        return null;
    }

    /**
     * 循环链表中无论是prev还是next都不存在空的情况,因此添加时
     * 无论是头部还是尾部还是中,都视为一种情况对待
     */
    @Override
    public boolean add(int index, T data) {
        if (index < 0 || data == null || index > length())
            return false;

        DNode p = head.next;
        int i = 0;
        //寻找插入点的位置
        while (p != head && i < index) {
            p = p.next;
            i++;
        }

        DNode temp = new DNode<T>(data, p, p.next);
        p.next = temp;
        p.next.prev = temp;
        return true;
    }

    /**
     * 尾部添加
     */
    @Override
    public boolean add(T data) {
        if (data == null)
            return false;

        DNode temp = new DNode<T>(data, head.prev, head);

        head.prev.next = temp;
        head.prev = temp;

        return true;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= length())
            return null;

        int i = 0;
        DNode<T> p = head.next;
        while (p != head && i < index) {
            p = p.next;
            i++;
        }

        if (p != head) {
            p.prev.next = p.next;
            p.next.prev = p.prev;
            return p.data;
        }
        return null;
    }

    @Override
    public boolean removeAll(T data) {
        if (data == null || isEmpty())
            return false;

        boolean isRemove = false;
        DNode p = head.next;
        while (p != head) {
            if (p.data.equals(data)) {
                p.prev.next = p.next;
                p.next.prev = p.prev;
                isRemove = true;
            }
            p = p.next;
        }

        return isRemove;
    }

    @Override
    public void clear() {
        head.prev = head.next = head;
    }

    @Override
    public boolean contains(T data) {
        if (data == null || isEmpty())
            return false;

        DNode p = head.next;
        while (p != head) {
            if (p.data.equals(data)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }
}
