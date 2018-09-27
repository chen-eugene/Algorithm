package com.eugene.datastructure.base;

import java.io.Serializable;

public class BinaryNode<T extends Comparable> implements Serializable {

    public BinaryNode left;
    public BinaryNode right;
    public T value;

    public BinaryNode(BinaryNode left, BinaryNode right, T value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }


    /**
     * 判断是否为叶子节点
     *
     * @return
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

}
