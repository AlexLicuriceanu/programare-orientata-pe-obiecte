package task2;

class Node<T extends Comparable<T>> {

    private T value;
    private Node<T> right;
    private Node<T> left;

    public Node() {
    }

    public Node(T value, Node<T> right, Node<T> left) {
        this.value = value;
        this.right = right;
        this.left = left;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }
}
