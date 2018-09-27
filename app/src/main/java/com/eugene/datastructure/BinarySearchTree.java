package com.eugene.datastructure;

import com.eugene.datastructure.base.BinaryNode;
import com.eugene.datastructure.base.Tree;

/**
 * 二叉树：
 * 1、若根结点的层次为1，则二叉树第i层最多有2^(i−1)(i≥1)个结点。
 * 2、在高度为h的二叉树中，最多有2h−1个结点（h≥0）。
 * <p>
 * 满二叉树：一棵高度为h的满二叉树（Full Binary Tree）是具有2^h−1(h≥0)个结点的二叉树。
 * 满二叉树的最大特点是每一层次的结点数都达到最大值。
 * <p>
 * 完全二叉树：假设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，
 * 第 h 层所有的结点都连续集中在最左边。
 * <p>
 * 一棵具有n个结点的完全二叉树，对于序号为i(0≤i＜n)的结点，则有如下规则：
 * ① 若i=0，则i为根结点，无父母结点；若i>0，则i的父母结点序号为(i−1)/2(向下取整)。
 * ② 若2i+1＜n,则i的左孩子结点序号为2i+1，否则i无左孩子。
 * ③ 若2i+2＞n,则i的右孩子结点序号为2i+2，否则i无右孩子。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 二叉查找树：对于树种的每个结点T（T可能是父结点）,它的左子树中所有项的值小T中的值，而它的右子树中所有项的值都大于T中的值。
 * 这意味着该树所有的元素可以用某种规则进行排序(取决于Comparable接口的实现)
 */
public class BinarySearchTree<T extends Comparable> implements Tree<T> {

    private BinaryNode root;


    @Override
    public void insert(T data) {
        if (data == null)
            throw new RuntimeException("data can\' Comparable be null !");

        insert(data, root);
    }

    /**
     * 递归插入
     *
     * @param data
     * @param node
     * @return
     */
    private BinaryNode insert(T data, BinaryNode node) {

        if (node == null)
            node = new BinaryNode(null, null, data);

        int result = data.compareTo(node.value);

        //递归查找
        //对值进行比较，决定是向左子树还是右子树插入
        //使用递归比较插入的数据与父节点的大小
        if (result < 0) {
            node.left = insert(data, node.left);
        } else if (result > 0) {
            node.right = insert(data, node.right);
        } else {
            //插入的节点已经存在
        }

        return node;
    }

    @Override
    public void remove(T data) {
        if (data == null)
            throw new RuntimeException("data can\' Comparable be null !");

        remove(data, root);
    }

    /**
     * 递归删除
     * <p>
     * ① 如果要删除的结点q恰好是叶子结点，那么它可以立即被删除
     * ② 如果要删除的结点q拥有一个孩子结点，则应该调整要被删除的父结点(p.left 或 p.right)指向被删除结点的孩子结点（q.left 或 q.right）
     * ③如果要删除的结点q拥有两个孩子结点，则删除策略是用q的右子树的最小的数据替代要被删除结点的数据，
     * 并递归删除用于替换的结点(此时该结点已为空)，此时二叉查找树的结构并不会被打乱，其特性仍旧生效。
     * 采用这样策略的主要原因是右子树的最小结点的数据替换要被删除的结点后可以满足维持二叉查找树的结构和特性，
     * 又因为右子树最小结点不可能有左孩子，删除起来也相对简单些。
     *
     * @param data
     * @param p
     * @return
     */
    private BinaryNode remove(T data, BinaryNode p) {
        if (p == null)
            return null;

        int result = data.compareTo(p.value);
        if (result < 0) {
            p.left = remove(data, p.left);
        } else if (result > 0) {
            p.right = remove(data, p.right);
        } else {
            if (p.left != null && p.right != null) {

                //查找右子树最小值
                p.value = findMin(p.right).value;

                p.right = remove((T) p.value, p.right);

            } else {
                //拥有一个孩子结点的结点和叶子结点的情况
                p = (p.left != null) ? p.left : p.right;
            }
        }

        return p;

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BinaryNode node){

        if (node == null)
            return 0;
        else {
            //与汉诺塔问题类似:H(n)=H(n-1) + 1 + H(n-1)
            return size(node.left) + 1 + size(node.right);
        }

    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(BinaryNode node) {
        if (node == null)
            return 0;
        else {
            int l = height(node.left);
            int r = height(node.right);
            //返回并加上当前层
            return l > r ? l + 1 : r + 1;
        }
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

    /**
     * 查找最小值,递归查找
     *
     * @return
     */
    @Override
    public T findMin() {

        return (T) findMin(root).value;

    }

    @Override
    public T findMax() {
        return (T) findMax(root).value;
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

    private BinaryNode findMin(BinaryNode p) {

        if (p == null) {
            return null;
        } else if (p.left == null) {
            //如果没有左结点,那么t就是最小的
            return p;
        } else {
            return findMin(p.left);
        }
    }

    private BinaryNode findMax(BinaryNode p) {

        if (p == null)
            return null;
        else if (p.right == null) {
            return p;
        } else {
            return findMin(p.right);
        }

    }

}
