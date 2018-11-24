package com.eugene.datastructure.linkedlist.singlelinked;

import com.eugene.datastructure.linkedlist.ILinkedList;

/**
 * 不含独立头结点,不含尾部指针
 * 头结点就是第一个结点
 */
public class SingleLinkedList<T> implements ILinkedList<T> {
    /**
     * 带数据头结点
     */
    private SNode<T> head;

    private int length = 0;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public T get(int index) {
        if (head == null || index < 0)
            return null;

        int count = 0;
        SNode p = head;
        //找到对应索引的结点，如果index大于链表长度，返回null
        while (p != null && count < index) {
            p = p.next;
            count++;
        }

        if (p != null)
            return (T) p.data;

        return null;
    }

    @Override
    public T set(int index, T data) {
        if (head == null || index < 0 || data == null)
            return null;

        int count = 0;
        SNode p = head;
        //先查找到index位置上的结点
        while (p != null && count < index) {
            p = p.next;
            count++;
        }

        if (p != null) {
            T old = (T) p.data;
            p.data = data;
            return old;
        }
        return null;
    }

    @Override
    public boolean add(int index, T data) {
        if (data == null)
            return false;

        //在头部插入
        if (head == null || index <= 0) {
            head = new SNode<T>(data, null);
        } else {
            //在尾部或中间插入
            int count = 0;
            SNode p = head;
            //当index大于链表长度时，则在尾部插入
            while (p.next != null && count < index) {
                p = p.next;
                count++;
            }

            //尾部添加和中间插入属于同种情况,毕竟当front为尾部结点时front.next=null
            p.next = new SNode(data, p.next);
        }

        return true;
    }

    @Override
    public boolean add(T data) {
        return add(Integer.MAX_VALUE, data);
    }

    @Override
    public T remove(int index) {
        if (index < 0 || head == null)
            return null;

        T old = null;

        //直接删除的是头结点
        if (index == 0) {
            old = head.data;
            head = head.next;
        } else {
            int count = 0;
            SNode p = head;
            //找到index的前一个结点
            while (p.next != null && count < index - 1) {
                p = p.next;
                count++;
            }

            //获取到要删除的结点
            SNode r = p.next;

            if (r != null) {
                old = (T) r.data;
                p.next = r.next;
                r = null;
            }

        }

        return old;
    }

    @Override
    public boolean removeAll(T data) {
        if (head == null || data == null)
            return false;

        boolean isRemove = false;

        //如果移除的是头结点
        if (head.data.equals(data)) {
            head = head.next;
        } else {

            SNode p = head;
            SNode q = p.next;
            //查找所有数据相同的结点并删除
            while (q != null) {
                if (q.data.equals(data)) {
                    p.next = q.next;
                    q = p.next;
                    isRemove = true;
                } else {
                    p = q;
                    q = p.next;
                }
            }

        }

        return isRemove;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public boolean contains(T data) {
        if (head == null || data == null)
            return false;

        SNode p = head;
        //从头部依次进行判断
        while (p != null) {
            if (p.data.equals(data))
                return true;

            p = p.next;
        }
        return false;
    }

    /**
     * 两个链表首尾相连
     */
    public void concat(SingleLinkedList<T> list) {
        if (this.head == null)
            this.head = list.head;
        else {
            SNode<T> pre = this.head;
            while (pre.next != null)
                pre = pre.next;
            pre.next = list.head;
        }
    }
}
