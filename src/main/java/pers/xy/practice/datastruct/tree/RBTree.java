package pers.xy.practice.datastruct.tree;

/**
 * @author xinyu
 * @date 2020/9/3
 */
public class RBTree {

    Node root = null;

    class Node implements Comparable<Node> {
        public int key;
        public Node left;
        public Node right;
        public int color; // 0红 1黑

        Node(int key) {
            this.key = key;
        }

        public int compareTo(Node o) {
            if (key > o.key)
                return 1;
            else if (key < o.key)
                return -1;
            else return 0;
        }
    }


    public void insert(int key) {
        if (root == null) { // 空树
            Node node = new Node(key);
            node.color = 1;
            root = node;
            return;
        }
        insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        int compare = root.compareTo(node);
        if (compare > 0) {
            node.left = insert(node.left, key);
            if (!isBlack(node)) {

            }
        } else if (compare < 0) {
            node.right = insert(node.right, key);
            if (!isBlack(node)) {

            }
        }
        return node;
    }

    private Node leftRotate() {
        return null;
    }

    private Node rightRotate() {
        return null;
    }

    private boolean isBlack(Node node) {
        return node == null || node.color == 1;
    }

}
