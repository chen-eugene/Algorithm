package com.eugene.datastructure.tree.binarytree;

import com.eugene.datastructure.queue.LinkedQueue;
import com.eugene.datastructure.stack.LinkedStack;
import com.eugene.datastructure.tree.ITree;

/**
 * 二叉搜索树
 */
public class BinarySearchTree<T extends Comparable> implements ITree<T> {

    private BinaryNode<T> root;

    public BinarySearchTree() {
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

    /**
     * 递归实现：定义根节点root后，再用subtree实现递归
     */
    private int size(BinaryNode node) {
        if (node == null)
            return 0;
        else
            //对比汉诺塔:H(n)=H(n-1) + 1 + H(n-1)
            //递归遍历左子树 + 根结点 + 右子树
            return size(node.left) + 1 + size(node.right);
    }

    @Override
    public int height() {
        return height(root);
    }

    /**
     * 递归遍历层数，返回最大的层数
     */
    private int height(BinaryNode node) {
        if (node == null)
            return 0;
        else {
            int left = height(node.left);
            int right = height(node.right);
            //返回层数 + 根结点的层数
            return (left > right) ? (left + 1) : (right + 1);
        }
    }

    //region 先根次遍历：根结点 + 左子树 + 右子树

    /**
     * 先根次遍历：根结点 + 左子树 + 右子树
     */
    @Override
    public String preOrder() {
        String result = preOrder(root);
        if (result.length() > 0)
            //去掉尾部","
            result = result.substring(0, result.length() - 1);
        return result;
    }

    /**
     * 先根遍历，递归实现
     */
    private String preOrder(BinaryNode node) {
        StringBuilder sb = new StringBuilder();
        if (node != null) {
            //获取结点数据
            sb.append(node.data).append(",");

            //遍历左子树
            sb.append(node.left);

            //遍历右子树
            sb.append(node.right);
        }
        return sb.toString();
    }

    /**
     * 先根遍历，非递归实现
     */
    public String preOrderTraverse() {
        StringBuilder sb = new StringBuilder();
        //存放遍历路径的栈
        LinkedStack<BinaryNode> stack = new LinkedStack<>();

        BinaryNode p = root;
        //p为空但栈不为空，则表示已完整走完一条路径
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                sb.append(p.data);
                //将已访问过的结点入栈
                stack.push(p);
                //继续访问其左孩子
                p = p.left;
            } else {
                //若p=null 栈不为空,则说明已沿左子树访问完一条路径, 从栈中弹出栈顶结点,并访问其右孩子
                p = stack.pop();
                p = p.right;
            }
        }
        //去掉最后一个逗号
        if (sb.length() > 0) {
            return sb.toString().substring(0, sb.length() - 1);
        } else {
            return sb.toString();
        }
    }
    //endregion

    //region 中根次遍历：左子树 + 根结点 + 右子树

    /**
     * 中根次遍历：左子树 + 根结点 + 右子树
     */
    @Override
    public String inOrder() {
        String sb = inOrder(root);
        if (sb.length() > 0) {
            //去掉尾部","号
            sb = sb.substring(0, sb.length() - 1);
        }
        return sb;
    }

    /**
     * 中根次遍历递归实现
     */
    private String inOrder(BinaryNode node) {
        StringBuilder sb = new StringBuilder();
        //递归结束条件
        if (node != null) {
            //先遍历左子树
            sb.append(node.left);

            sb.append(node.data).append(",");
            //最后遍历右子树
            sb.append(node.right);
        }
        return sb.toString();
    }

    public String inOrderTraverse() {
        StringBuilder sb = new StringBuilder();
        //存放遍历路径的栈
        LinkedStack<BinaryNode> stack = new LinkedStack<>();

        BinaryNode p = root;

        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                //把左孩子都入栈,至到左孩子为null
                stack.push(p);

                p = p.left;
            }
            //如果栈不为空,因为前面左孩子已全部入栈
            if (!stack.isEmpty()) {
                p = stack.pop();

                sb.append(p.data).append(",");
                //访问p结点的右孩子
                p = p.right;
            }
        }

        //去掉最后一个逗号
        if (sb.length() > 0) {
            return sb.toString().substring(0, sb.length() - 1);
        } else {
            return sb.toString();
        }
    }
    //endregion

    //region 后根次遍历：左子树 + 右子树+  根结点

    /**
     * 后根次遍历：左子树 + 右子树+  根结点
     */
    @Override
    public String postOrder() {
        String sb = postOrder(root);
        if (sb.length() > 0) {
            //去掉尾部","号
            sb = sb.substring(0, sb.length() - 1);
        }
        return sb;
    }

    /**
     * 后根次遍历递归实现
     */
    private String postOrder(BinaryNode node) {
        StringBuilder sb = new StringBuilder();

        while (node != null) {
            sb.append(postOrder(node.left));

            sb.append(postOrder(node.right));

            sb.append(node.data).append(",");
        }

        return sb.toString();
    }

    /**
     * 后根次遍历非递归实现
     */
    public String postOrderTraverse() {
        StringBuilder sb = new StringBuilder();

        LinkedStack<BinaryNode> stack = new LinkedStack<>();

        BinaryNode cur = root;
        BinaryNode p = root;

        while (cur != null || !stack.isEmpty()) {
            //把左子树加入栈中,直到叶子结点为止
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            //开始访问当前结点的父结点右孩子
            if (!stack.isEmpty()) {
                //获取右孩子
                BinaryNode temp = stack.peek().right;
                //先判断是否有右孩子或者右孩子是否已被访问过
                //没有右孩子||右孩子已被访问过
                if (temp == null || p == temp) {
                    //如果没有右孩子或者右孩子已被访问,则弹出父结点并访问
                    cur = stack.pop();

                    sb.append(cur.data).append(",");

                    //记录已经访问过的结点
                    p = cur;
                    //置空当前结点
                    cur = null;
                } else {
                    //有右孩子,则开始遍历右子树
                    cur = temp;
                }
            }

        }

        //去掉最后一个逗号
        if (sb.length() > 0) {
            return sb.toString().substring(0, sb.length() - 1);
        } else {
            return sb.toString();
        }
    }
    //endregion

    @Override
    public String levelOrder() {
        StringBuilder sb = new StringBuilder();
        //存放需要遍历的结点,左结点一定优先右节点遍历
        LinkedQueue<BinaryNode> queue = new LinkedQueue<>();

        BinaryNode p = root;

        while (p != null) {
            sb.append(p.data);

            //先按层次遍历结点,左结点一定在右结点之前访问
            if (p.left != null)
                queue.add(p.left);

            if (p.right != null)
                queue.add(p.right);

            //访问下一个结点
            p = queue.poll();
        }
        return sb.toString();
    }

    //region 插入操作
    @Override
    public void insert(T data) {
        if (data == null)
            throw new IllegalArgumentException("data can\'Comparable be null !");
        insert(data, root);
    }

    /**
     * 递归插入
     */
    private BinaryNode insert(T data, BinaryNode node) {
        if (node == null)
            node = new BinaryNode(data, null, null);

        int result = data.compareTo(node.data);

        //遍历左子树
        if (result < 0)
            insert(data, node.left);
        else if (result > 0)
            insert(data, node.right);
        else {
            //已经存在，不需要插入
        }
        return node;
    }
    //endregion

    //region 删除操作
    @Override
    public void remove(T data) {
        if (data == null)
            throw new RuntimeException("data can\'Comparable be null !");
        //删除结点
        remove(data, root);
    }

    /**
     * 分3种情况
     * 1.删除叶子结点(也就是没有孩子结点)
     * 2.删除拥有一个孩子结点的结点(可能是左孩子也可能是右孩子)
     * 3.删除拥有两个孩子结点的结点
     */
    private BinaryNode<T> remove(T data, BinaryNode<T> p) {
        //没有找到要删除的元素,递归结束
        if (p == null)
            return p;

        int result = data.compareTo(p.data);

        if (result < 0)
            remove(data, p.left);
        else if (result > 0)
            remove(data, p.right);
        else {
            //已找到结点并判断是否有两个子结点(情况3)
            //中继替换,找到右子树中最小的元素并替换需要删除的元素值
            if (p.left != null && p.right != null) {
                p.data = findMin(p.right).data;
                //移除用于替换的结点
                p.right = remove(p.data, p.right);
            } else {
                //情况1和情况2
                p = (p.left != null) ? p.left : p.right;
            }
        }
        return p;
    }
    //endregion

    //region 查找最小值
    @Override
    public T findMin() {
        if (isEmpty())
            throw new IllegalArgumentException("BinarySearchTree is empty!");

        return findMin(root).data;
    }

    /**
     * 查找最小值递归实现
     */
    private BinaryNode<T> findMin(BinaryNode node) {
        if (node == null)
            return null;
            //没有左孩子结点，当前节点为最小结点
        else if (node.left == null)
            return node;
        else
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

    /**
     * 查找最大值递归实现
     */
    private BinaryNode<T> findMax(BinaryNode node) {
        //递归结束条件
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
        root = null;
    }
}
