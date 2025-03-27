package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisCube() {
        Box box = new Box(8, 2);
        String result = box.whatsThis();
        assertThat(result).isNotNull()
                .isNotEmpty()
                .isEqualTo("Cube");
    }

    @Test
    void isThisUNKNOW() {
        Box figure = new Box(84, 2);
        String result = figure.whatsThis();
        assertThat(result).isNotNull()
                .isNotEmpty()
                .startsWith("Unknown");
    }

    @Test
    void whenVertexIsPositive() {
        Box figure = new Box(8, 5);
        int result = figure.getNumberOfVertices();
        assertThat(result).isPositive();
    }

    @Test
    void whenVertexIsEven() {
        Box figure = new Box(0, 6);
        int result = figure.getNumberOfVertices();
        assertThat(result).isEven();
    }

    @Test
    void whenIsNotExist() {
        Box figure = new Box(0, -23);
        boolean result = figure.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void whenIsExist() {
        Box figure = new Box(0, 23);
        boolean result = figure.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void whenEdge4Vertex8ThenArea96() {
        Box figure = new Box(8, 4);
        double result = figure.getArea();
        assertThat(result).isEqualTo(96.0d, withPrecision(0.001d));
    }

    @Test
    void whenEdgeMinus3Vertex8ThenArea0() {
        Box figure = new Box(8, -3);
        double result = figure.getArea();
        assertThat(result).isEqualTo(0.0d, withPrecision(0.001d));
    }
}