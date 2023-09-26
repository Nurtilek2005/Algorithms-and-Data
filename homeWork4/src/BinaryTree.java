public class BinaryTree {
    private Node root;

    private static class Node {
        int value;
        Node left, right;
        boolean isRed;

        Node(int value) {
            this.value = value;
            this.isRed = true;
        }
    }

    private boolean isRed(Node node) {
        return node != null && node.isRed;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.isRed = node.isRed;
        node.isRed = true;
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.isRed = node.isRed;
        node.isRed = true;
        return left;
    }

    private void flipColors(Node node) {
        node.isRed = !node.isRed;
        node.left.isRed = !node.left.isRed;
        node.right.isRed = !node.right.isRed;
    }

    public void insert(int value) {
        root = insert(root, value);
        root.isRed = false;
    }

    private Node insert(Node node, int value) {
        if (node == null) return new Node(value);

        if (value < node.value)
            node.left = insert(node.left, value);
        else if (value > node.value)
            node.right = insert(node.right, value);
        else
            return node;

        if (isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    public boolean find(int value) {
        return find(root, value);
    }

    private boolean find(Node node, int value) {
        if (node == null) return false;
        if (node.value == value) return true;

        if (value < node.value)
            return find(node.left, value);
        else
            return find(node.right, value);
    }
}