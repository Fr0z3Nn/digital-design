package ru.project.unpacker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.project.unpacker.exception.InvalidInputException;
import ru.project.unpacker.service.UnpackService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@DisplayName("Сервис распаковки")
class UnpackServiceTest {

    private final UnpackService unpackService = new UnpackService();

    @Test
    @DisplayName("null")
    public void invalidTestNull() {
        assertThrows(InvalidInputException.class, () -> unpackService.unpack(null));
    }

    @Test
    @DisplayName("\"\"")
    public void invalidTestEmpty() {
        assertThrows(InvalidInputException.class, () -> unpackService.unpack(""));
    }

    @Test
    @DisplayName("\" \"")
    public void invalidTestBlank() {
        assertThrows(InvalidInputException.class, () -> unpackService.unpack(" "));
    }

    @Test
    @DisplayName("12[421]]")
    public void invalidTest1() {
        assertThrows(InvalidInputException.class, () -> unpackService.unpack("12[421]]"));
    }

    @Test
    @DisplayName("f[tyr]")
    public void invalidTest2() {
        assertThrows(InvalidInputException.class, () -> unpackService.unpack("f[tyr]"));
    }

    @Test
    @DisplayName("5[a2[b3[c]]4[d]]]")
    public void invalidTest3() {
        assertThrows(InvalidInputException.class, () -> unpackService.unpack("5[a2[b3[c]]4[d]]]"));
    }

    @Test
    @DisplayName("5[a2[b3[c]]4[d]]]")
    public void invalidTest4() {
        assertThrows(InvalidInputException.class, () -> unpackService.unpack("5[a2[b3[c]]4[d]]]"));
    }

    @Test
    @DisplayName("[ ]")
    public void invalidTest5() {
        assertThrows(InvalidInputException.class, () -> unpackService.unpack("[ ]"));
    }

    @Test
    @DisplayName("3[x]")
    public void easyTest() {
        String test = "3[x]";
        String actual = unpackService.unpack(test);
        String expected = "xxx";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("3[x3[t]2[n]1[m]]")
    public void mediumTest1() {
        String test = "3[x3[t]2[n]1[m]]";
        String actual = unpackService.unpack(test);
        String expected = "xtttnnmxtttnnmxtttnnm";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1[2[x]]gg1[2[x]]gg1[2[x]]")
    public void mediumTest2() {
        String test = "1[2[x]]gg1[2[x]]gg1[2[x]]";
        String actual = unpackService.unpack(test);
        String expected = "xxggxxggxx";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("5[a2[b3[c]4[d]]]")
    public void hardTest1() {
        String test = "5[a2[b3[c]4[d]]]";
        String actual = unpackService.unpack(test);
        String expected = "abcccddddbcccddddabcccddddbcccddddabcccddddbcccddddabcccddddbcccddddabcccddddbcccdddd";
        assertEquals(expected, actual);
    }
}