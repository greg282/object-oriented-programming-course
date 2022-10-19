package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    public void test_next() {
        Assertions.assertEquals(MapDirection.EAST,MapDirection.NORTH.next());
        Assertions.assertEquals(MapDirection.WEST,MapDirection.SOUTH.next());
        Assertions.assertEquals(MapDirection.NORTH,MapDirection.WEST.next());
        Assertions.assertEquals(MapDirection.SOUTH,MapDirection.EAST.next());
    }

    @Test
    public void test_previous(){
        Assertions.assertEquals(MapDirection.NORTH,MapDirection.EAST.previous());
        Assertions.assertEquals(MapDirection.SOUTH,MapDirection.WEST.previous());
        Assertions.assertEquals(MapDirection.WEST,MapDirection.NORTH.previous());
        Assertions.assertEquals(MapDirection.EAST,MapDirection.SOUTH.previous());
    }


}