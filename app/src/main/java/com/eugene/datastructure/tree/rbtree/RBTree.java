package com.eugene.datastructure.tree.rbtree;

import com.eugene.datastructure.tree.ITree;
import com.eugene.datastructure.tree.binarytree.BinaryNode;

public class RBTree<T extends Comparable> implements ITree<T> {

    private RBNode<T> root;


    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public String preOrder() {
        return null;
    }

    @Override
    public String inOrder() {
        return null;
    }

    @Override
    public String postOrder() {
        return null;
    }

    @Override
    public String levelOrder() {
        return null;
    }

    @Override
    public void insert(T data) {

    }

    private RBNode<T> insert(T data, RBNode<T> node) {
        //找到叶子结点，可以进行插入
        if (node == null)
            node = new RBNode<T>(data, null, null, null);

        int ret = data.compareTo(node.data);

        if (ret < 0) {
            node.left = insert(data, node.left);
        } else if (ret > 0) {
            node.right = insert(data, node.right);
        } else {
            //已有元素就没必要重复插入了
        }

        return node;
    }

    /**
     * 修正红黑树
     * <p>
     * ① 如果红黑树为空树，只会违反特性②，所以只需要将结点涂成黑色。
     * ② 插入结点的父节点是黑色，不会违背红黑树的特性，什么也不需要做。
     * ③ 插入节点的父节点和其叔叔节点（祖父节点的另一个子节点）均为红色。
     * ④ 插入节点的父节点是红色的，叔叔节点是黑色的，且插入节点是其父节点的右子节点。
     * ⑤ 插入节点的父节点是红色的，叔叔节点是黑色的，且插入节点是其父节点的左子节点。
     *
     * @param node 插入的结点
     */
    private void insertFixUp(RBNode<T> node) {
        RBNode<T> parent;
        RBNode<T> gparent;

        //需要修正的条件：父结点存在，且父结点的颜色是红色
        while ((parent = node.parent) != null && parent.isRed()) {
            gparent = parent.parent;

            if (parent == gparent.left) {
                RBNode<T> uncle = gparent.right;

                //情况①：叔叔结点是红色
                if (uncle != null && uncle.isRed()) {
                    parent.color = RBNode.BLACK;
                    uncle.color = RBNode.BLACK;
                }


            } else {

            }


        }


    }


    @Override
    public void remove(T data) {

    }

    @Override
    public T findMin() {
        return null;
    }

    @Override
    public T findMax() {
        return null;
    }

    @Override
    public BinaryNode findNode(T data) {
        return null;
    }

    @Override
    public boolean contains(T data) throws Exception {
        return false;
    }

    @Override
    public void clear() {

    }
}
