package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<T> s = head;
            while (s.next != null) {
                s = s.next;
            }
            s.next = new Node<>(value, null);
        }
        modCount++;
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        modCount++;
        size++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> oldHead = head;
        head = head.next;
        oldHead.next = null;
        T result = oldHead.item;
        oldHead.item = null;
        modCount++;
        size--;
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }

    private class LinkedIterator implements Iterator<T> {

        private Node<T> cursor = head;

        private final int expectedModCount = modCount;

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T value = cursor.item;
            cursor = cursor.next;
            return value;
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return cursor != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
