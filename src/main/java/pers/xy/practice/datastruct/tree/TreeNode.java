package pers.xy.practice.datastruct.tree;

/**
 * @author xinyu
 * @date 2021/7/1
 */
public class TreeNode<T extends Comparable<T>> {

    T data;
    TreeNode leftChild;
    TreeNode rightChild;

    int height;

    TreeNode(T data) {
        this.data = data;
        this.height = 1;
    }
}
