package com.dilshan;

import javax.swing.*;

public class Tree {
    private class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node=" + value;
        }
    }

    private Node root;

    public void insert(int value) {
        var node = new Node(value);
        if (root == null) {
            root = node;
            return;
        }
        var current = root;
        while (true) {
            if (value < current.value) {
                if (current.leftChild == null) {
                    current.leftChild = node;
                    break;
                }
                current = current.leftChild;
            } else {
                if (current.rightChild == null) {
                    current.rightChild = node;
                    break;
                }
                current = current.rightChild;
            }
        }
    }

    public boolean find(int value) {
        var current = root;
        while (current != null) {
            if (value < current.value) {
                current = current.leftChild;
            } else if (value > current.value) {
                current = current.rightChild;
            }else
                return true;
        }
        return false;
    }
    public void traversePreOrder(){
        traversePreOrder(root);
    }
    private void traversePreOrder(Node root){
        if(root == null)
            return;
        System.out.println(root.value);
        traversePreOrder(root.leftChild);
        traversePreOrder(root.rightChild);
    }

    public int height(){
        return height(root);
    }

    private int height(Node root){
        if(root == null){
            return -1;
        }
        if(root.leftChild == null && root.rightChild == null){
            return 0;
        }
        return 1 + Math.max(height(root.leftChild), height(root.rightChild));
    }

    private boolean isLeaf(Node node){
        return node.leftChild == null && node.rightChild == null;
    }

//    public int min(){
//        return min(root);
//    }
    // O(log(n))
    public int min(){
        if(root == null){
            throw new IllegalStateException();
        }
        var current = root;
        var last = current;
        while (current != null){
            last = current;
            current = current.leftChild;
        }
        return last.value;
    }
    // O(n)
    private int min(Node root){
        if(isLeaf(root))
            return root.value;

        var left = min(root.leftChild);
        var right = min(root.rightChild);

        return Math.min(Math.min(left, right), root.value);
    }

    public boolean equals(Tree other){
        if(other == null){
            return false;
        }
        return equality(root, other.root);
    }

    private boolean equality(Node t1, Node t2){
        if(t1 == null && t2 == null){
            return true;
        }
        if(t1 != null && t2 != null)
            return t1.value == t2.value && equality(t1.leftChild,t2.leftChild) && equality(t1.rightChild,t2.rightChild);

        return false;
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node root, int min, int max){
        if(root == null)
            return true;
        if(root.value < min || root.value > max)
            return false;
        return isBinarySearchTree(root.leftChild, min, root.value - 1) && isBinarySearchTree(root.rightChild, root.value + 1, max);
    }

    public int findTheOfDistance(int k){
        int distance = height();
        return findTheOfDistance(k, root, distance);
    }
    private int findTheOfDistance(int k, Node root, int distance){
        if(k == root.value){
            return distance;
        }

        distance--;
        if(k < root.value) {
            return findTheOfDistance(k, root.leftChild, distance);
        }else if(k > root.value){
            return findTheOfDistance(k, root.rightChild, distance);
        }
        return 0;
    }

    public int size(){
        int size = 0;
        return size(size, root);
    }
    private int size(int size, Node root){
        if(root == null){
            return size;
        }else{
            size++;
            size = size(size,root.leftChild);
            size = size(size,root.rightChild);
            return size;
        }
    }

    public int countLeaves(){
        return countLeaves(0,root);
    }
    private int countLeaves(int count, Node root){
        if(root == null){
            return count;
        } else if (root.leftChild == null && root.rightChild == null) {
            count++;
            return count;
        }else{
            count = countLeaves(count,root.leftChild);
            count = countLeaves(count,root.rightChild);
            return count;
        }
    }
    public int max(){
        if(root == null){
            throw new IllegalStateException();
        }
        var current = root;
        var last = current;
        while (current != null){
            last = current;
            current = current.rightChild;
        }
        return last.value;
    }
}
