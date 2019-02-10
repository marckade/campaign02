package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Queue;

public class LinkedQueue<E> implements Queue<E> {

    private DoublyLinkedList<E> linkedQueue= new DoublyLinkedList<>();


    @Override
    public int size() {

        return linkedQueue.size();

    }

    @Override
    public boolean isEmpty() {

        return linkedQueue.isEmpty();

    }

    @Override
    public void offer(E element) {

        // Checks if the element is null, if its not, add to bottom of list.
        if(element != null)
        {
            linkedQueue.addLast(element);
        }

    }

    @Override
    public E peek() {

        // Check size of the linked que first.
        if(linkedQueue.size() > 0)
        {
            return linkedQueue.first();
        }
        else
        {
            return null;
        }
    }

    @Override
    public E poll() {

        // Check is the size is less than 1 or empty
        if(linkedQueue.size() < 1)
        {
            return null;
        }
        else
        {
            return linkedQueue.removeFirst();
        }
    }

    @Override
    public void printQueue() {

        // Call printlist from doubly linked list class.
        linkedQueue.printList();

    }
}
