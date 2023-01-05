package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void ifSphereVertex5ThenArea1256dot63() {
        Box box = new Box(0, 10);
        assertThat(box.getArea())
                .isEqualTo(1256.63, offset(0.01));
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 5);
        assertThat(box.whatsThis())
                .isEqualTo("Cube");
    }

    @Test
    void ifCubeVertex5ThenArea150() {
        Box box = new Box(8, 5);
        assertThat(box.getArea())
                .isEqualTo(150, offset(0.1));
    }

    @Test
    void isThisTetrahedronThenVertex8() {
        Box box = new Box(4, 5);
        assertThat(box.getNumberOfVertices())
                .isEqualTo(4);
    }

    @Test
    void sphereIsExistTrue() {
        Box box = new Box(0, 15);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isThisUnknownBox() {
        Box box = new Box(1, 4);
        assertThat(box.whatsThis())
                .isEqualTo("Unknown object");
    }

    @Test
    void isThisUnknownBoxThenExistFalse() {
        Box box = new Box(1, 4);
        assertThat(box.isExist())
                .isFalse();
    }

    @Test
    void isThisUnknownBoxThenArea0() {
        Box box = new Box(1, 4);
        assertThat(box.getArea())
                .isEqualTo(0);
    }

}