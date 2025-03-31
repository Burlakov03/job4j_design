package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class ArrayIteratorTest {
    @Test
    void whenMultiCallhasNextThenTrue() {
        ArrayIterator iterator = new ArrayIterator(
                new int[]{1, 2, 3}
        );
        assertThat(iterator.hasNext());
        assertThat(iterator.hasNext());
    }

    @Test
    void whenReadSequence() {
        ArrayIterator iterator = new ArrayIterator(
                new int[]{1, 2, 3}
        );
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(3);
    }

    @Test
    void whenNextEmpty() {
        ArrayIterator iterator = new ArrayIterator(
                new int[]{}
        );
        assertThatThrownBy(iterator::next).isInstanceOf(NoSuchElementException.class);
    }
}