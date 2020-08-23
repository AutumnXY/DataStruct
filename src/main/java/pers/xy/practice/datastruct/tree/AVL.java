package pers.xy.practice.datastruct.tree;

/**
 * @author xinyu
 * @date 2020/08/23
 */
public class AVL {

    private Node root;

    class Node {
        int val;
        int height;
        Node left;
        Node right;
    }


    public void insert(Node insertNode) {

    }

    public void delete(int val) {

    }

    public void balance(Node root) {

    }

    // LL
    public void leftRotate(Node node) {

        Node newParet = node.left;

        node.left = newParet.right;

        newParet.right = node;

    }

    // RR
    public void rightRotate(Node node) {

        Node newParent = node.right;

        node.right = newParent.left;

        newParent.left = node;

    }

}
