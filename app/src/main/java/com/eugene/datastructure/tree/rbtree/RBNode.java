package com.eugene.datastructure.tree.rbtree;

public class RBNode<T extends Comparable> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    /**
     * 颜色
     */
    public boolean color = RED;

    public T data;

    public RBNode<T> left;

    public RBNode<T> right;

    /**
     * 父结点
     */
    public RBNode<T> parent;

    public RBNode() {
    }

    public RBNode(T data, RBNode<T> left, RBNode<T> right, RBNode<T> parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public boolean isRed(){
        return color == RED;
    }

}
