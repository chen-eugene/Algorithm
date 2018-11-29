package com.eugene.datastructure.tree.avltree;

public class AVLNode<T extends Comparable> {

    public AVLNode<T> left;

    public AVLNode<T> right;

    public T data;

    /**
     * 当前结点的高度
     */
    public int height;

    public AVLNode(T data) {
        this(data, null, null, 0);
    }

    public AVLNode(T data, AVLNode<T> left, AVLNode<T> right, int height) {
        this.left = left;
        this.right = right;
        this.data = data;
        this.height = height;
    }
}
