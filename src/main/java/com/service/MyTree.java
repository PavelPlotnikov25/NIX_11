package com.service;

import com.model.Product;
import com.model.ProductComparator;
import com.model.phone.Phone;

public class MyTree<E extends Product> {
    private Node<E> root;
    private final ProductComparator<E> productComparator = new ProductComparator<>();


    private Node<E> addRecursive(Node<E> current, E value) {
        if (current == null) {
            return new Node(value);
        }

        if (productComparator.compare((E) current.value, value) > 0) {
            current.leftChild = addRecursive(current.leftChild, value);
        } else if (productComparator.compare((E) current.value, value) < 0) {
            current.rightChild = addRecursive(current.rightChild, value);
        } else {
            return current;
        }
        return current;
    }

    public void add(E value) {
        root = addRecursive(root, value);
    }

    private long sum(Node<E> root) {
        if (root == null)
            return 0;
        return (long) (root.value.getPrice() + sum(root.leftChild) +
                sum(root.rightChild));
    }

    private long sumLeft() {
        if (root == null)
            return 0;
        return (long) sum(root.leftChild);
    }

    private long sumRight() {
        if (root == null)
            return 0;
        return (long) sum(root.rightChild);
    }

public static class Node<T extends Product> {
    T value;
    Node<T> leftChild;
    Node<T> rightChild;

    public Node<T> getLeft() {
        return leftChild;
    }

    public Node<T> getRight() {
        return rightChild;
    }

    public Node(T value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }
}

    public static void printBinaryTree(Node node, int level){
        if(node==null)
            return;
        printBinaryTree(node.rightChild, level+1);
        if(level!=0){
            for(int i=0;i<level-1;i++)
                System.out.print("|\t");
            System.out.println("|-------"+node.value);
        }
        else
            System.out.println(node.value);
        printBinaryTree(node.leftChild, level+1);
    }
    public static void main(String[] args) {

            PhoneService phoneService = PhoneService.getInstance();
            phoneService.createAndSaveProduct(10);
            MyTree<Phone> tree = new MyTree<>();
            phoneService.getAll().forEach(tree::add);
            printBinaryTree(tree.root, 0);
            System.out.println();
            System.out.println("Sum of left branch products = : " + tree.sumLeft());
            System.out.println("Sum of right branch products: " + tree.sumRight());
        }
    }

