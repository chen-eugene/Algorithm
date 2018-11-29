package com.eugene.datastructure.tree.binarytree;

import com.eugene.datastructure.queue.LinkedQueue;

/**
 * 利用层次遍历原理构造完全二叉树
 */
public class CompleteBinaryTree<T extends Comparable> extends BinarySearchTree<T> {

    public CompleteBinaryTree() {
        super();
    }

    /**
     * @param levelOrderArray 生成完全二叉树的数组
     */
    public CompleteBinaryTree(T[] levelOrderArray) {
        //根结点序号为0
        this.root = create(levelOrderArray, 0);
    }

    /**
     * 层次遍历构造完全二叉树
     *
     * @param levelOrderArray 生成完全二叉树的数组
     * @param i               完全二叉树的序号
     */
    public BinaryNode<T> create(T[] levelOrderArray, int i) {
        if (levelOrderArray == null) {
            throw new RuntimeException("the param 'array' of create method can\'t be null !");
        }

        BinaryNode<T> p = null;

        //递归结束条件
        if (i < levelOrderArray.length) {
            p = new BinaryNode<>(levelOrderArray[i], null, null);
            //根据完全二叉树的性质 2*i+1 为左孩子结点
            p.left = create(levelOrderArray, 2 * i + 1);
            //2*i+2 为右孩子结点
            p.right = create(levelOrderArray, 2 * i + 2);
        }
        return p;
    }

    /**
     * 搜索二叉树的包含操作和移除操作不适合层次遍历构造的完全二叉树
     * 根据层次遍历构建的二叉树必须用层次遍历来判断(仅适用层次遍历构建的完全二叉树)
     */
    @Override
    public boolean contains(T data) throws Exception {
        if (data == null)
            return false;

        StringBuilder sb = new StringBuilder();

        //存放需要遍历的结点,左结点一定优先右节点遍历
        LinkedQueue<BinaryNode> queue = new LinkedQueue<>();

        BinaryNode p = this.root;

        while (p != null) {

            //先判断是否存在要找的结点
            if (data.compareTo(p.data) == 0)
                return true;

            //先按层次遍历结点,左结点一定在右结点之前访问
            if (p.left != null)
                queue.add(p.left);

            if (p.right != null)
                queue.add(p.right);

            //访问下一个结点
            p = queue.poll();
        }
        return false;
    }

    /**
     * 搜索二叉树的包含操作和移除操作不适合层次遍历构造的完全二叉树
     */
    @Override
    public void remove(T data) {
        //do nothing 取消删除操作
    }

    /**
     * 完全二叉树只通过层次遍历来构建,取消insert操作
     */
    @Override
    public void insert(T data) {
        //do nothing //取消insert操作
    }



}
