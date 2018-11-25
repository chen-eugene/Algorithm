package com.eugene.datastructure.tree.binarytree;

/**
 * 二叉树结点
 */
public class BinaryNode<T extends Comparable> {

    public BinaryNode<T> left, right;

    public T data;

    public BinaryNode(T data, BinaryNode left, BinaryNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * 判断是否为叶子节点
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }
}
