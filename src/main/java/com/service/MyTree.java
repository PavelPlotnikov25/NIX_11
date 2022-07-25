package com.service;

import com.model.Product;
import com.model.ProductComparator;
import com.model.phone.Phone;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyTree<E extends Product> {
    private Node root;
    private final ProductComparator<E> productComparator = new ProductComparator<>();


    private Node addRecursive(Node current, E value) {
        if (current == null) {
            return new Node<E>(value);
        }

        if (productComparator.compare((E) current.value, value) > 0) {
            current.left = addRecursive(current.left, value);
        } else if (productComparator.compare((E) current.value, value) < 0) {
            current.right = addRecursive(current.right, value);
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
        return (long) (root.value.getPrice() + sum(root.left) +
                sum(root.right));
    }

    private long sumLeft() {
        if (root == null)
            return 0;
        return (long) sum(root.left);
    }

    private long sumRight() {
        if (root == null)
            return 0;
        return (long) sum(root.right);
    }

public static class Node<T extends Product> {
    T value;
    Node<T> left;
    Node<T> right;

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

    public static void printBinaryTree(Node node, int level){
        if(node==null)
            return;
        printBinaryTree(node.right, level+1);
        if(level!=0){
            for(int i=0;i<level-1;i++)
                System.out.print("|\t");
            System.out.println("|-------"+node.value);
        }
        else
            System.out.println(node.value);
        printBinaryTree(node.left, level+1);
    }
    public static void main(String[] args) {
            PhoneService phoneService = PhoneService.getInstance();
            for (int i = 0; i < 10; i++) {
                phoneService.createAndSaveProduct(1);
            }

            MyTree<Phone> tree = new MyTree<>();
            phoneService.getAll().forEach(phone -> tree.add(phone));
            printBinaryTree(tree.root, 0);

            System.out.println("\n\n\n\n\n");
            System.out.println("Left branch sum: " + tree.sumLeft());
            System.out.println("Right branch sum: " + tree.sumRight());
        }
    }

