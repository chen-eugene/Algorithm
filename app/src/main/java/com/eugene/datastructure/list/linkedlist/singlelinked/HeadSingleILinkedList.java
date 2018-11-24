package com.eugene.datastructure.list.linkedlist.singlelinked;

import com.eugene.datastructure.list.linkedlist.ILinkedList;

/**
 * 带独立头结点并含有尾指针的链表
 * 头结点不含数据，head.next为第一个结点
 */
public class HeadSingleILinkedList<T> implements ILinkedList<T> {

    /**
     * 不带数据头结点
     */
    private SNode<T> head;
    /**
     * 尾部指针
     */
    private SNode<T> rear;

    public HeadSingleILinkedList() {
        this.head = rear = new SNode<T>(null, null);
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public int length() {
        int i = 0;
        SNode q = head.next;
        while (q != null) {
            q = q.next;
            i++;
        }
        return i;
    }

    @Override
    public T get(int index) {
        if (index < 0)
            return null;

        int i = 0;
        SNode q = head.next;
        //找到对应索引的结点
        while (q != null && i < index) {
            q = q.next;
            i++;
        }

        if (q != null)
            return (T) q.data;

        return null;
    }

    @Override
    public T set(int index, T data) {
        if (index < 0 || data == null)
            return null;

        int i = 0;
        SNode q = head.next;
        while (q != null && i < index) {
            q = q.next;
            i++;
        }

        if (q != null) {
            T old = (T) q.data;
            q.data = data;
            return old;
        }

        return null;
    }

    /**
     * 根据下标添加结点
     * 1.头部插入
     * 2.中间插入
     * 3.末尾插入
     *
     * @param index 该值从0开始,如传4就代表插入在第5个位置
     */
    @Override
    public boolean add(int index, T data) {
        if (index < 0 && data == null)
            return false;

        //无需区分位置操作,中间/头部/尾部插入
        int i = 0;
        SNode q = head.next;
        while (q != null && i < index) {
            q = q.next;
            i++;
        }

        SNode p = new SNode(data, q.next);
        q.next = p;

        //如果是为指针，修改尾指针
        if (q == rear)
            rear = p;

        return false;
    }

    @Override
    public boolean add(T data) {
        if (data == null)
            return false;

        SNode q = new SNode(data, null);
        rear.next = q;
        //更新末尾指针的指向
        rear = q;
        return true;
    }

    @Override
    public T remove(int index) {
        if (index < 0)
            return null;

        int i = 0;
        SNode q = head.next;
        //查找需要删除位置的前一个结点
        while (q != null && i < index) {
            q = q.next;
            i++;
        }

        //获取到要删除的结点
        SNode p = q.next;

        if (p != null) {
            T old = (T) p.data;
            if (p == rear) {
                q.next = rear.next;
                rear = q;
            }
            return old;
        }
        return null;
    }

    @Override
    public boolean removeAll(T data) {
        if (data == null)
            return false;

        //用于记录要删除结点的前一个结点
        SNode p = head;
        //当前遍历的结点
        SNode q = head.next;
        boolean isRemove = false;
        while (q != null) {

            if (data.equals(q.data)) {

                //如果恰好是尾部结点,则更新rear的指向
                if (q == rear) {
                    rear = p;
                }

                //相等则删除q并更改指针指向,继续向下遍历
                p.next = q.next;
                q = p.next;
                isRemove = true;
            } else {
                p = q;
                q = q.next;
            }
        }
        return isRemove;
    }

    @Override
    public void clear() {
        head.next = null;
        rear = head;
    }

    @Override
    public boolean contains(T data) {
        if (data == null)
            return false;

        SNode q = head.next;
        while (q != null) {
            if (data.equals(q.data)) {
                return true;
            }
            q = q.next;
        }
        return false;
    }
}
