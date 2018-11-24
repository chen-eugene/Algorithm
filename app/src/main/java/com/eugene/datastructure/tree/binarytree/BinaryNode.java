package com.eugene.datastructure.tree.binarytree;

public class BinaryNode<T extends Comparable> {

    public BinaryNode<T> left, right;

    public T data;

    public BinaryNode() {

    }

    /**
     * 判断是否为叶子节点
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }
}
