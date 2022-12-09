package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f","r","f","f"});
        Assertions.assertArrayEquals(new MoveDirection[]{MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.FORWARD,MoveDirection.FORWARD},directions);

        Assertions.assertThrows(IllegalArgumentException.class,()-> new OptionsParser().parse(new String[]{"jd","2137","f","f"}));

    }
}