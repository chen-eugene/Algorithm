package com.eugene.datastructure.stack;

/**
 * 栈接口抽象数据类型
 */
public interface IStack<T> {

    /**
     * 栈是否为空
     */
    boolean isEmpty();

    /**
     * data元素入栈
     */
    void push(T data);

    /**
     * 返回栈顶元素,未出栈
     */
    T peek();

    /**
     * 出栈,返回栈顶元素,同时从栈中移除该元素
     */
    T pop();

    int size();

}
