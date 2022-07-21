package com.service;

import com.model.Product;
import com.model.ProductVersion;

import java.util.*;


public class ProductVersionList implements Iterable<ProductVersion> {
    private int currentVersion = 1;
    int size = 0;
    private Node first;
    private Node last;

    public int getCurrentVersion() {return size;}

    public void add(Product product) throws CloneNotSupportedException {
        Product product1 = product.copy();
        ProductVersion productVersion = new ProductVersion(product1, currentVersion++, new Date());
        final Node f = first;
        final Node newNode = new Node(null, productVersion, f);
        first = newNode;
        if (f == null){
            last = newNode;
        }else {
            f.prev = newNode;
        }
        size++;
    }

    public boolean replaceByVersion(Product product, int version){
        ProductVersion productVersion = searchByVersion(version);
        if (Objects.isNull(productVersion)){
            return false;
        }
        productVersion.setProduct(product);
        productVersion.setDateOfAdding(new Date());
        return true;
    }

    public ProductVersion searchByVersion(int version) {
        Node node;
        for (node = first; node != null; node = node.next){
            if (version == node.currentElement.getVersion()){
                return node.currentElement;
            }
        }
        return null;
    }

    public Date getDateFirstVersion(){
        if (size == 0){
            return null;
        }
        int firstVersion = Integer.MAX_VALUE;
        ProductVersion firstVersionObject = null;
        Node node;
        for (node = first; node != null; node = node.next){
            if (firstVersion < node.currentElement.getVersion()){
                firstVersion = node.currentElement.getVersion();
                firstVersionObject = node.currentElement;
            }
        }if (Objects.isNull(firstVersionObject)){
            return null;
        }else {
            return new Date(firstVersionObject.getDateOfAdding().getTime());
        }
    }
    
    public Date getLastVersion(){
        if (size == 0){
            return null;
        }
        int lastVersion = 0;
        ProductVersion lastVersionObject = null;
        Node node;
        for (node = first; node != null; node = node.next){
            if (lastVersion < node.currentElement.getVersion()){
                lastVersion = node.currentElement.getVersion();
                lastVersionObject = node.currentElement;
            }
        }
        if (Objects.isNull(lastVersionObject)){
            return null;
        }else{
            return new Date(lastVersionObject.getDateOfAdding().getTime());
        }
    }


    public boolean deleteByVersion(int version){
        ProductVersion productVersion = searchByVersion(version);
        if (Objects.isNull(productVersion)){
            return false;
        }
        return remove(productVersion);
    }

    private boolean remove(ProductVersion productVersion) {
        for (Node node = first; node != null; node = node.next ){
            if (productVersion.equals(node.currentElement)){
                Node next = node.next;
                Node prev = node.prev;
                if (prev == null){
                    first = next;
                }else {
                    prev.next = next;
                    node.prev = null;
                }
                if (next == null){
                    last = prev;
                }else {
                    next.prev = prev;
                    node.next = null;
                }
                node.currentElement = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        Node node = first;
        if (first != null){
            for (int i = 0; i < size; i++){
                if (i == size-1){
                    stringBuilder.append(node.currentElement + " ]");
                }else {
                    stringBuilder.append(node.currentElement + ", ");
                    node = node.next;
                }
            }
            result = stringBuilder.toString();
        }else {
            result = "[]";
        }
        return result;
    }

    @Override
    public Iterator<ProductVersion> iterator() {return new IteratorProductVersion();}

    public class IteratorProductVersion implements Iterator<ProductVersion> {
        private int nextIndex;

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public ProductVersion next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Node node2 = first;
            for (int i = 0; i < nextIndex; i++) {
                node2 = node2.next;
            }
            nextIndex++;
            return node2.currentElement;
        }


    }

    private class Node{
       ProductVersion currentElement;
       Node next;
       Node prev;

        public Node(Node prev, ProductVersion element, Node next) {
            this.prev = prev;
            this.currentElement = element;
            this.next = next;

        }
    }
}
