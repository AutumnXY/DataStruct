package pers.xy.practice.datastruct.tree;

/**
 * @author xinyu
 * @date 2021/7/6
 */
public class AVL2<E extends Comparable<E>> {

    public TreeNode<E> root;


    private int getHeight(TreeNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    public void add(E addData) {
        root = add(root, addData);
    }

    public void remove(E removeData) {
        //TODO 判断是否存在
        remove(root, removeData);
    }


    /**
     * 增加节点
     *
     * @param node
     * @param addData
     * @return 上层栈的根结点
     */
    private TreeNode add(TreeNode node, E addData) {
        if (node == null)
            return new TreeNode(addData);
        if (node.data.compareTo(addData) > 0)
            node.leftChild = add(node.leftChild, addData);
        else if (node.data.compareTo(addData) < 0)
            node.rightChild = add(node.rightChild, addData);
        node.height = 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1 && getBalanceFactor(node.leftChild) > 0)
            node = rightRotate(node);
        else if (balanceFactor > 1 && getBalanceFactor(node.leftChild) < 0)
            node = leftRightRotate(node);
        else if (balanceFactor < -1 && getBalanceFactor(node.rightChild) < 0)
            node = leftRotate(node);
        else if (balanceFactor < -1 && getBalanceFactor(node.rightChild) > 0)
            node = rightLeftRotate(node);
        return node;
    }

    private TreeNode remove(TreeNode node, E removeData) {
        if (node == null)
            return null;
        TreeNode ret;
        if (node.data.compareTo(removeData) > 0) {
            node.leftChild = remove(node.leftChild, removeData);
            ret = node;
        } else if (node.data.compareTo(removeData) < 0) {
            node.rightChild = remove(node.rightChild, removeData);
            ret = node;
        } else {
            if (node.leftChild == null) {// 左孩子为null
                TreeNode rightChild = node.rightChild;
                node.rightChild = null;
                ret = rightChild;
            } else if (node.rightChild == null) {// 右孩子为null
                TreeNode leftChild = node.leftChild;
                node.leftChild = null;
                ret = leftChild;
            } else {  //   左右孩子都存在，找右子树最小的node
                TreeNode min = findMin(node.rightChild);
                min.rightChild = remove(node.rightChild, removeData);
                min.leftChild = node.leftChild;
                //
                node.leftChild = null;
                node.rightChild = null;
                ret = min;
            }
        }
        if (ret == null) {
            return null;
        }
        ret.height = 1 + Math.max(getHeight(ret.leftChild), getHeight(ret.rightChild));
        int balanceFactor = getBalanceFactor(ret);
        //
        if (balanceFactor > 1 && getBalanceFactor(ret.leftChild) > 0)
            rightRotate(ret);
        else if (balanceFactor > 1 && getBalanceFactor(ret.leftChild) < 0)
            leftRightRotate(ret);
        else if (balanceFactor < -1 && getBalanceFactor(ret.rightChild) < 0)
            leftRotate(ret);
        else if (balanceFactor < -1 && getBalanceFactor(ret.rightChild) > 0)
            rightLeftRotate(ret);

        return ret;
    }


    private int getBalanceFactor(TreeNode node) {
        return getHeight(node.leftChild) - getHeight(node.rightChild);
    }


    private TreeNode leftRotate(TreeNode node) {
        TreeNode tmp = node.rightChild;
        node.rightChild = tmp.leftChild;
        tmp.leftChild = node;
        //更新height
        node.height = Math.max(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        tmp.height = Math.max(getHeight(tmp.leftChild), getHeight(tmp.rightChild)) + 1;
        return tmp;
    }

    private TreeNode rightRotate(TreeNode node) {
        TreeNode tmp = node.leftChild;
        node.leftChild = tmp.rightChild;
        tmp.rightChild = node;
        //更新height
        node.height = Math.max(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        tmp.height = Math.max(getHeight(tmp.leftChild), getHeight(tmp.rightChild)) + 1;
        return tmp;
    }

    private TreeNode leftRightRotate(TreeNode node) {
        node.leftChild = leftRotate(node.leftChild);
        return rightRotate(node);
    }

    private TreeNode rightLeftRotate(TreeNode node) {
        node.rightChild = rightRotate(node.rightChild);
        return leftRotate(node);
    }

    public void preOrder() {
        preOrder(root);
    }


    private void preOrder(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrder(node.leftChild);
        preOrder(node.rightChild);
    }

    public void middleOrder() {
        middleOrder(root);
    }


    private void middleOrder(TreeNode node) {
        if (node == null)
            return;
        middleOrder(node.leftChild);
        System.out.print(node.data + " ");
        middleOrder(node.rightChild);
    }

    public void lastOrder() {
        lastOrder(root);
    }

    private void lastOrder(TreeNode node) {
        if (node == null)
            return;
        lastOrder(node.leftChild);
        lastOrder(node.rightChild);
        System.out.print(node.data + " ");
    }

    private TreeNode findMin(TreeNode root) {
        TreeNode ret = root;
        while (ret.leftChild != null)
            ret = root.leftChild;
        return ret;
    }

}
