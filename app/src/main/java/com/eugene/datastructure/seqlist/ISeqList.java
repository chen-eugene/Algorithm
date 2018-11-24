package com.eugene.datastructure.seqlist;

/**
 * 顺序表顶层接口
 */
public interface ISeqList<T> {

    /**
     * 判断是否为空
     */
    boolean isEmpty();

    /**
     * 顺序表长度
     */
    int length();

    /**
     * 获取元素
     */
    T get(int index);

    /**
     * 设置某个元素的值
     */
    T set(int index, T data);

    /**
     * 根据index添加元素
     */
    boolean add(int index, T data);

    /**
     * 根据index移除元素
     */
    T remove(int index);

    /**
     * 根据data移除元素
     */
    boolean remove(T data);

    /**
     * 根据data移除所有元素
     */
    boolean removeAll(T data);

    /**
     * 清空数据
     */
    void clear();

    /**
     * 是否包含data元素
     */
    boolean contains(T data);

    /**
     * 根据值查询下标
     */
    int indexOf(T data);

    /**
     * 根据data值查询最后一个出现在顺序表中的下标
     */
    int lastIndexOf(T data);

}
