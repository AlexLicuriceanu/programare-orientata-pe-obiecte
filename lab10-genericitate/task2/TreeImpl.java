package task2;

import java.util.HashSet;
import java.util.List;

//TODO: Add your implementation
class TreeImpl<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;
    private int size;

    public TreeImpl() {
        this.root = null;
        this.size = 0;
    }

    public TreeImpl(T value) {
        this.root = new Node<>(value, null, null);
        this.size = 0;
    }

    @Override
    public void addValue(T value) {
        this.root = addRecursive(this.root, value);
        this.size++;
    }

    private Node<T> addRecursive(Node<T> root, T value) {
        if (root == null) {
            root = new Node<>(value, null, null);
            return root;
        } else if (value.compareTo(root.getValue()) < 0) {
            root.setLeft(addRecursive(root.getLeft(), value));
        } else if (value.compareTo(root.getValue()) > 0) {
            root.setRight(addRecursive(root.getRight(), value));
        }
        return root;
    }

    @Override
    public void addAll(List<T> values) {
        values.forEach(this::addValue);
    }

    @Override
    public HashSet<T> getValues(T inf, T sup) {
        if (inf.compareTo(sup) > 0) {
            T aux = inf;
            inf = sup;
            sup = aux;
        }

        HashSet<T> valuesSet = new HashSet<>();
        Node<T> currentNode = this.root;
        while (currentNode != null) {
            if (inf.compareTo(currentNode.getValue()) <= 0 &&
                    sup.compareTo(currentNode.getValue()) >= 0) {
                valuesSet.add(currentNode.getValue());
            }

            currentNode = currentNode.getRight();
        }
        return valuesSet;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (this.root == null || this.size == 0) {
            return true;
        }
        return false;
    }
}
