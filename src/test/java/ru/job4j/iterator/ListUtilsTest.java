package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void shouldRemoveElementsMatchingPredicate() {
        ListUtils.addBefore(input, 1, 2);
        Predicate<Integer> isNotEven = num -> num % 2 != 0;
        ListUtils.removeIf(input, isNotEven);
        assertThat(input).hasSize(2).containsSequence(2, 3);
    }

    @Test
    void shouldNotRemoveElementsWhenPredicateDoesNotMatch() {
        Predicate<Integer> isEven = num -> num % 2 == 0;
        ListUtils.removeIf(input, isEven);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }


    @Test
    void shouldReplaceElementsMatchingPredicate() {
        ListUtils.addBefore(input, 1, 2);
        Predicate<Integer> isNotEven = num -> num % 2 != 0;
        ListUtils.replaceIf(input, isNotEven, 7);
        assertThat(input).hasSize(3).containsSequence(7, 2, 7);
    }

    @Test
    void shouldNotReplaceElementsWhenPredicateDoesNotMatch() {
        Predicate<Integer> isEven = num -> num % 2 == 0;
        ListUtils.replaceIf(input, isEven, 7);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void shouldRemoveAllMatchingElementsFromList() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.removeAll(input, new ArrayList<>(Arrays.asList(1, 3, 5, 7)));
        assertThat(input).hasSize(1).containsSequence(2);
    }

    @Test
    void shouldNotRemoveAnyElementsWhenNoMatchFound() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.removeAll(input, new ArrayList<>(Arrays.asList(4, 5, 7)));
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }
}