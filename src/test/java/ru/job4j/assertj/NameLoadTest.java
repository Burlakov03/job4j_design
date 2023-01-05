package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void nameEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty");
    }

    @Test
    void nameNotContainsEqualSign() {
        NameLoad nameLoad = new NameLoad();
        String name = "Hello";
        assertThatThrownBy(() -> nameLoad.parse(name, "By=e"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining("not contain");
    }

    @Test
    void nameStartWithEqualSign() {
        NameLoad nameLoad = new NameLoad();
        String name = "=Hello";
        assertThatThrownBy(() -> nameLoad.parse(name, "Hello="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining("not contain a key");
    }

    @Test
    void nameContainsEqualSignLast() {
        NameLoad nameLoad = new NameLoad();
        String name = "Hello=";
        assertThatThrownBy(() -> nameLoad.parse("Hello = Bye", name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining("not contain a value");
    }
}