package edu.isu.cs.cs3308.structures;

public class Node<E> {

    private Node<E> next;

    private E data;

    public Node<E> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }

    private Node<E> previous;

    public Node(E data) {
        this.data = data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public E getData() {
        return data;
    }

    // Basic constructor for node including data.
    public void setData(E data) {
        this.data = data;
    }
}
