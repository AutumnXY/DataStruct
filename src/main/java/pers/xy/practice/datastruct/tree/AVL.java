package pers.xy.practice.datastruct.tree;

/**
 * @author xinyu
 * @date 2020/08/23
 */
public class AVL {

    private Node root;

    class Node {
        int val;
        int height; // 高度
        Node left;
        Node right;

        Node(int key) {
            this.val = key;
        }
    }


    public void insert(int number) {
        root = insert(root, number);
    }

    private Node insert(Node node, int number) {
        if (node == null) {
            node = new Node(number);
        } else {
            int compare = compare(node.val, number);
            if (compare < 0) { // 往右添加
                node.right = insert(node.right, number);
                if (Math.abs(height(node.left) - height(node.right)) == 2) {// 需要调整
                    if (number > node.right.val) {
                        // RR型
                        node = leftRotate(node);
                    } else {
                        //RL型
                        node = rightLeftRotate(node);
                    }
                }
            } else if (compare > 0) { // 往左添加
                node.left = insert(node.left, number);
                if (Math.abs(height(node.left) - height(node.right)) == 2) {// 需要调整
                    if (number < node.left.val) {
                        // LL型
                        node = rightRotate(node);
                    } else {
                        // LR型
                        node = leftRightRotate(node);
                    }
                }
            } else {
                System.out.println("error");
            }
        }
        node.height = max(height(node.left), height(node.right)) + 1;
        return node;
    }

    private int max(int leftTreeHeight, int rightTreeHeight) {
        return leftTreeHeight > rightTreeHeight ? leftTreeHeight : rightTreeHeight;
    }

    private int height(Node node) {
        if (node == null)
            return 0;

        return node.height;
    }

    /**
     * -1 a<b ;0 a==b ;1 a>b
     *
     * @param a
     * @param b
     * @return
     */
    private int compare(int a, int b) {
        if (a == b)
            return 0;
        return a > b ? 1 : -1;
    }


    public void remove(int number) {
        // 先找到
        Node toDel = search(number);
        //如果没有就不删
        if (toDel != null)
            remove(root, toDel);

    }

    private Node remove(Node node, Node toDel) {
        if (node == null)
            return null;

        int compare = compare(node.val, toDel.val);
        if (compare < 0) {// 去右面接着找删除点
            node.right = remove(node.right, toDel);
            if (Math.abs(height(node.left) - height(node.right)) == 2) {
                Node l = node.left;
                if (height(l.left) > height(l.right)) {
                    node = rightRotate(node);
                } else {
                    node = rightLeftRotate(node);
                }
            }
        } else if (compare > 0) {// 去左面接着找
            node.left = remove(node.left, toDel);
            if (Math.abs(height(node.left) - height(node.right)) == 2) {
                Node r = node.right;
                if (height(r.left) > height(r.right)) {
                    node = leftRightRotate(node);
                } else {
                    node = leftRotate(node);
                }
            }
        } else { // 找到了，
            // 正常二叉树删除逻辑
            if (node.left != null && node.right != null) {// 有2个孩子
                if (height(node.left) > height(node.right)) {
                    Node leftTreeMax = findChildTreeMax(node.left);
                    node.val = leftTreeMax.val;
                    node.left = remove(node.left, leftTreeMax);
                } else {
                    Node rightTreeMin = findChildTreeMin(node.right);
                    node.val = rightTreeMin.val;
                    node.right = remove(node.right, rightTreeMin);
                }
            } else {
                node = node.left == null ? node.right : node.left;
            }
        }

        if (node != null)
            node.height = max(height(node.left), height(node.right)) + 1;
        return node;
    }

    private Node findChildTreeMin(Node root) {
        Node min = root.left;
        while (min.left != null) {
            min = min.left;
        }
        return min;
    }

    private Node findChildTreeMax(Node root) {
        Node max = root.right;
        while (max.left != null) {
            max = max.left;
        }
        return max;
    }

    public Node search(int number) {
        if (root == null)
            return null;
        return searchNode(root, number);

    }

    private Node searchNode(Node node, int number) {

        int compare = compare(node.val, number);
        if (compare < 0) {// 往右
            return searchNode(node.right, number);
        } else if (compare > 0) {// 往左
            return searchNode(node.left, number);
        } else {
            return node;
        }

    }


    // LL型
    public Node rightRotate(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        node.height = max(height(node.left), height(node.right)) + 1;
        newParent.height = max(height(newParent.left), height(newParent.right)) + 1;
        return newParent;
    }

    // RR型
    public Node leftRotate(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        node.height = max(height(node.left), height(node.right)) + 1;
        newParent.height = max(height(newParent.left), height(newParent.right)) + 1;
        return newParent;
    }

    // RL型对应的旋转
    public Node rightLeftRotate(Node node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    // LR型对应的旋转
    public Node leftRightRotate(Node node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }
}
