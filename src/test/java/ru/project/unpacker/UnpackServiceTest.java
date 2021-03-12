package ru.project.unpacker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnpackServiceTest {

    private final UnpackService unpackService = new UnpackService();

    @Test
    public void invalidTestNull() {
        assertThrows(InvalidInputException.class, () -> unpackService.unpack(null));
    }

    @Test
    public void invalidTestEmpty() {
        assertThrows(InvalidInputException.class, () -> unpackService.unpack(""));
    }

    @Test
    public void invalidTest1() {
        String t1 = "12[421]]";
    }

    @Test
    public void invalidTest2() {
        String t1 = "f[tyr]";
    }


    @Test
    public void easyTest() {
        String test = "3[x]";
        String actual = unpackService.unpack(test);
        String expected = "xxx";
        assertEquals(actual, expected);
    }
}