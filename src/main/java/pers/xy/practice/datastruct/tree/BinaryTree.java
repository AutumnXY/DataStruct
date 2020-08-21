package pers.xy.practice.datastruct.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author xinyu
 * @date 2020/8/21
 */
public class BinaryTree {
    private TreeNode<String> root;


    class TreeNode<T> {
        private T val;
        private TreeNode<T> left;
        private TreeNode<T> right;


        TreeNode() {

        }

        public TreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        public TreeNode<T> getRight() {
            return right;
        }

        public void setRight(TreeNode<T> right) {
            this.right = right;
        }

        public T getVal() {
            return val;
        }

        public void setVal(T val) {
            this.val = val;
        }
    }


    public void preOrder(TreeNode<String> root) {
        if (root != null) {
            System.out.print(root.getVal() + " ");
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    public void preOrderWithoutRecursion(TreeNode<String> node) {
        Stack<TreeNode<String>> stack = new Stack<TreeNode<String>>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                System.out.print(node.getVal() + " ");
                node = node.getLeft();
            } else {
                TreeNode<String> pop = stack.pop();
                node = pop.getRight();
            }
        }
    }

    public void inOrder(TreeNode<String> root) {
        if (root != null) {
            inOrder(root.getLeft());
            System.out.print(root.getVal() + " ");
            inOrder(root.getRight());
        }
    }

    public void inOrderWithoutRecursion(TreeNode<String> node) {
        Stack<TreeNode<String>> stack = new Stack<TreeNode<String>>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                TreeNode<String> pop = stack.pop();
                System.out.print(pop.getVal() + " ");
                node = pop.getRight();
            }
        }
    }

    public void postOrder(TreeNode<String> root) {
        preOrder(root.getLeft());
        preOrder(root.getRight());
        System.out.print(root.getVal() + " ");
    }

    public void postOrderWithoutRecursion(TreeNode<String> node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode<String>> stack = new Stack<TreeNode<String>>();
        TreeNode<String> pre = null, cur = null;// 上一个弹出的，当前栈顶的
        stack.push(node);
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if ((cur.getLeft() == null && cur.getRight() == null) || (pre != null && (pre == cur.getLeft() || pre == cur.getRight()))) {// 是叶子节点|| 是上一次弹出节点的父节点
                pre = stack.pop();
                System.out.print(pre.getVal() + " ");
            } else {
                if (cur.getRight() != null)
                    stack.push(cur.getRight());
                if (cur.getLeft() != null)
                    stack.push(cur.getLeft());
            }
        }
    }

    public void levelOrder(TreeNode<String> root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode<String>> queue = new LinkedList<TreeNode<String>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<String> poll = queue.poll();
            System.out.print(poll.getVal() + " ");
            if (poll.getLeft() != null)
                queue.add(poll.getLeft());
            if (poll.getRight() != null)
                queue.add(poll.getRight());
        }
    }
}
