package pers.xy.practice.datastruct.tree;

/**
 * @author xinyu
 * @date 2020/8/21
 */
public class BInarySearchTree {

    private Node root;

    class Node {
        int val;
        Node left;
        Node right;
    }


    public void find(int num) {
    }


    public void insert(int num) {

    }

    public void delete(int num) {
        if (root == null) {
            return;
        }
        Node target = root, targetParent = null;
        boolean isLeftChild = true;

        //找到要删除的node
        while (target != null) {
            if (target.val == num) {
                break;
            }
            if (num > root.val) {
                target = root.right;
                targetParent = target;
                isLeftChild = false;
            } else {
                target = target.right;
                targetParent = target;
                isLeftChild = true;
            }
        }
        if (target == null) {
            System.out.println("没找到");
        }

        // 3种情况 叶子结点、有1个孩子的节点、有2个孩子的节点
        if (target.left == null && target.right == null) { // 叶子结点
            if (isLeftChild)
                targetParent.left = null;
            else
                targetParent.right = null;
        } else if (target.left == null || target.right == null) {//有1个孩子的节点
            if (isLeftChild)
                targetParent.left = target.left;
        } else {
            Node rightTreeMinNode = findRightTreeMinNode(target);
            if (target == null) {
                root = rightTreeMinNode;
            } else if (isLeftChild) {
                targetParent.left = rightTreeMinNode;
            } else {
                targetParent.right = rightTreeMinNode;
            }
            rightTreeMinNode.left = target.left;
        }
    }

    /**
     * 找到右子树中最小的值
     *
     * @param delete
     * @return
     */
    private Node findRightTreeMinNode(Node delete) {
        Node cur = delete.right;
        Node parent = delete;

        while (cur.left != null) {
            parent = cur;
            cur = cur.left;
        }
        if (cur.val != delete.val) { //
            parent.left = cur.right;
            cur.right = delete.right;
        }

        return cur;
    }

}
