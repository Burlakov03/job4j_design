package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int sizeOutput;
    private int sizeInput;

    public T poll() {
        if (sizeInput == 0 && sizeOutput == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (sizeOutput == 0) {
            sizeOutput = sizeInput - 1;
            while (sizeInput > 0) {
                output.push(input.pop());
                sizeInput--;
            }
        }
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        sizeInput++;
    }
}
