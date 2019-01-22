package com.eugene.datastructure.tree.avltree;

import com.eugene.datastructure.queue.LinkedQueue;
import com.eugene.datastructure.tree.ITree;
import com.eugene.datastructure.tree.binarytree.BinaryNode;

public class AVLTree<T extends Comparable> implements ITree<T> {

    private AVLNode<T> root;

    public AVLTree() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(AVLNode<T> node) {
        if (node == null)
            return 0;
        else
            return size(node.left) + 1 + size(node.right);
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(AVLNode<T> node) {
        return node == null ? -1 : node.height;
    }

    @Override
    public String preOrder() {
        String sb = preOrder(root);
        if (sb.length() > 0) {
            //去掉尾部","号
            sb = sb.substring(0, sb.length() - 1);
        }
        return sb;
    }

    private String preOrder(AVLNode<T> node) {
        StringBuilder sb = new StringBuilder();

        if (node != null) {

            sb.append(node.data).append(",");

            sb.append(node.left.data);

            sb.append(node.right.data);
        }
        return sb.toString();
    }

    @Override
    public String inOrder() {
        String sb = inOrder(root);
        if (sb.length() > 0) {
            //去掉尾部","号
            sb = sb.substring(0, sb.length() - 1);
        }
        return sb;
    }

    private String inOrder(AVLNode<T> node) {
        StringBuilder sb = new StringBuilder();
        if (node != null) {
            sb.append(node.left.data);

            sb.append(node.data).append(",");

            sb.append(node.right.data);
        }
        return sb.toString();
    }

    @Override
    public String postOrder() {
        String sb = postOrder(root);
        if (sb.length() > 0) {
            //去掉尾部","号
            sb = sb.substring(0, sb.length() - 1);
        }
        return sb;
    }

    private String postOrder(AVLNode<T> node) {
        StringBuilder sb = new StringBuilder();
        if (node != null) {
            sb.append(node.right.data);

            sb.append(node.left.data);

            sb.append(node.data).append(",");
        }
        return sb.toString();
    }

    @Override
    public String levelOrder() {
        StringBuilder sb = new StringBuilder();

        LinkedQueue<AVLNode> queue = new LinkedQueue<>();

        AVLNode p = root;

        while (p != null) {

            sb.append(p.data).append(",");

            if (p.left != null)
                queue.add(p.left);

            if (p.right != null)
                queue.add(p.right);

            p = queue.poll();
        }

        return sb.toString();
    }

    //region 插入结点
    @Override
    public void insert(T data) {
        if (data == null)
            throw new IllegalArgumentException("data can\'Comparable be null !");
        this.root = insert(data, root);
    }

    private AVLNode<T> insert(T data, AVLNode<T> node) {
        //说明已没有孩子结点,可以创建新结点插入了.
        if (node == null)
            node = new AVLNode<>(data);

        int ret = data.compareTo(node.data);

        if (ret < 0) {
            node.left = insert(data, node.left);

            //插入后计算子树的高度,等于2则需要重新恢复平衡,由于是左边插入,左子树的高度肯定大于等于右子树的高度
            if (height(node.left) - height(node.right) == 2) {
                //判断data是插入点的左孩子还是右孩子
                if (data.compareTo(node.left.data) < 0) {
                    //进行LL旋转
                    node = singleRotateLeftLeft(node);
                } else {
                    //进行左右旋转
                    node = doubleRotateLeftRight(node);
                }

            }
        } else if (ret > 0) {
            node.right = insert(data, node.right);

            if (height(node.right) - height(node.left) == 2) {

                if (data.compareTo(node.right.data) < 0) {
                    //进行右左旋转
                    node = doubleRotateRightLeft(node);
                } else {
                    //进行右右旋转
                    node = singleRotateRightRight(node);
                }

            }

        } else {
            //已有元素就没必要重复插入了
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }
    //endregion

    //region 删除结点
    @Override
    public void remove(T data) {
        if (data == null)
            throw new IllegalArgumentException("data can\'Comparable be null !");

        this.root = remove(data, root);
    }

    private AVLNode<T> remove(T data, AVLNode<T> node) {
        if (node == null)
            return null;

        int ret = data.compareTo(node.data);

        if (ret < 0) {

            node.left = remove(data, node.left);

            //监测是否平衡
            if (height(node.right) - height(node.left) == 2) {
                AVLNode<T> temp = node.right;

                //判断需要那种旋转
                //与插入相反，在左子树删除，相当于在右子树添加
                if (height(temp.right) > height(temp.left)) {
                    //RL
                    node = doubleRotateRightLeft(node);
                } else {
                    //RR
                    node = singleRotateRightRight(node);
                }
            }
        } else if (ret > 0) {

            node.right = remove(data, node.right);

            if (height(node.left) - height(node.right) == 2) {
                AVLNode<T> temp = node.left;

                if (height(temp.left) > height(temp.right)) {
                    node = singleRotateLeftLeft(node);
                } else {
                    node = doubleRotateLeftRight(node);
                }
            }
        } else {
            //已找到需要删除的元素,并且要删除的结点拥有两个子节点
            if (node.left != null && node.right != null) {
                //在右子树寻找替换结点
                node.data = findMin(node.right).data;
                //移除替换结点
                node.right = remove(node.data, node.right);
            } else {
                //只有一个孩子结点或者只是叶子结点的情况
                node = (node.left != null) ? node.left : node.right;
            }
        }

        //重新计算高度
        if (node != null)
            node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }
    //endregion

    //region 查找最小值
    @Override
    public T findMin() {
        if (isEmpty())
            throw new IllegalArgumentException("BinarySearchTree is empty!");
        return findMin(root).data;
    }

    private AVLNode<T> findMin(AVLNode<T> node) {
        if (node == null)
            return null;
            //左子树为空，则当前结点为最小
        else if (node.left == null)
            return node;
        else
            //左子树不为空，则继续在左子树查找
            return findMin(node.left);
    }
    //endregion

    //region 查找最大值
    @Override
    public T findMax() {
        if (isEmpty())
            throw new IllegalArgumentException("BinarySearchTree is empty!");
        return findMax(root).data;
    }

    private AVLNode<T> findMax(AVLNode<T> node) {
        if (node == null)
            return null;
        else if (node.right == null)
            return node;
        else
            return findMax(node.right);
    }
    //endregion

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
        this.root = null;
    }

    //region 左左单旋转

    /**
     * 左左单旋转(LL旋转)
     * 在结点X的左孩子结点的左子树中插入元素
     *
     * @param p 失衡点
     */
    private AVLNode<T> singleRotateLeftLeft(AVLNode<T> p) {
        //把p的左孩子结点旋转为根结点
        AVLNode<T> q = p.left;

        //同时q的右子树变为p的左子树
        p.left = q.right;

        //p变为q的右子树
        q.right = p;

        //重新计算p q的高度
        p.height = Math.max(height(p.left), height(p.right)) + 1;
        q.height = Math.max(height(q.left), height(q.right)) + 1;

        //返回新的根结点
        return q;
    }
    //endregion

    //region 右右单旋转

    /**
     * 右右单旋转(RR旋转)
     * 在结点X的右孩子结点的右子树中插入元素
     */
    private AVLNode<T> singleRotateRightRight(AVLNode<T> p) {
        //把p的右孩子结点旋转为根结点
        AVLNode<T> q = p.right;

        //同时q的左子树变为p的右子树
        p.right = q.left;

        //p变为q的左子树
        q.left = p;

        //重新计算p q的高度
        p.height = Math.max(height(p.left), height(p.right)) + 1;
        q.height = Math.max(height(q.left), height(q.right)) + 1;

        return q;
    }
    //endregion

    //region 左右旋转

    /**
     * 左右旋转(LR旋转)
     * 在结点p的左孩子结点的右子树中插入元素
     */
    private AVLNode<T> doubleRotateLeftRight(AVLNode<T> p) {
        //p先进行RR旋转
        p.left = singleRotateRightRight(p.left);
        //再进行p的LL旋转
        return singleRotateLeftLeft(p);
    }
    //endregion

    //region 右左旋转

    /**
     * 右左旋转(RL旋转)
     * 在结点X的右孩子结点的左子树中插入元素
     */
    private AVLNode<T> doubleRotateRightLeft(AVLNode<T> p) {
        //先进行LL旋转
        p.right = singleRotateLeftLeft(p.right);
        //再进行RR旋转
        return singleRotateRightRight(p);
    }
    //endregion


}
