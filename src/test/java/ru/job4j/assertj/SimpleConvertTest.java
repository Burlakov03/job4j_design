package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void satisfyArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("a", "bb", "ccc", "dddd", "iiiii");
        assertThat(array).isNotNull()
                .allSatisfy(e -> assertThat(e.length()).isGreaterThan(0))
                .anySatisfy(e -> assertThat(e.length()).isEqualTo(3))
                .allMatch(e -> e.length() < 6)
                .doesNotContain("aa");
    }

    @Test
    void checkNavigationList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        ArrayList<String> list = new ArrayList<>(simpleConvert.toList("a", "bb", "ccc", "dddd", "iiiii"));
        assertThat(list).first().isEqualTo("a");
        assertThat(list).element(3)
                .isNotNull()
                .isEqualTo("dddd");
        assertThat(list).last().isEqualTo("iiiii");
    }

    @Test
    void checkFilteredList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        ArrayList<String> list = new ArrayList<>(simpleConvert.toList("a", "bb", "ccc", "dddd", "iiiii", "red"));
        assertThat(list).filteredOn(e -> e.length() > 3)
                .last()
                .isNotNull()
                .isEqualTo("iiiii");
    }

    @Test
    void checkFilteredSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = new HashSet<>(simpleConvert.toSet("a", "bb", "ccc", "dddd", "iiiii", "red"));
        assertThat(set).filteredOnAssertions(e -> assertThat(e.length()).isLessThan(4))
                .isNotNull()
                .doesNotContain("iiiii", "3");
    }

    @Test
    void generalSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = new HashSet<>(simpleConvert.toSet("a", "bb", "ccc", "dddd", "iiiii", "red"));
        assertThat(set)
                .hasSize(6)
                .isNotNull()
                .containsOnly("iiiii", "bb", "red", "ccc", "a", "dddd");
    }

    @Test
    void assertMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = new HashMap<>(simpleConvert.toMap("a", "bb", "ccc", "dddd", "iiiii", "red"));
        assertThat(map)
                .isNotNull()
                .containsKeys("iiiii", "bb", "red")
                .containsValue(1)
                .doesNotContainKey("bul")
                .containsEntry("a", 0);
    }
}