package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void test_ToString() {
        Assertions.assertNotEquals(new Vector2d(2,1),new Vector2d(3,7));
        Assertions.assertEquals(new Vector2d(2,1),new Vector2d(2,1));
    }

    @Test
    void test_precedes() {
        Assertions.assertTrue( new Vector2d(2,1).precedes(new Vector2d(3,7)));
        Assertions.assertFalse( new Vector2d(2,1).precedes(new Vector2d(1,7)));
    }

    @Test
    void test_follows() {
        Assertions.assertTrue( new Vector2d(3,7).follows(new Vector2d(2,1)));
        Assertions.assertFalse( new Vector2d(1,7).follows(new Vector2d(2,1)));
    }

    @Test
    void test_add() {
        Assertions.assertEquals(new Vector2d(2,4).add(new Vector2d(1,1)),new Vector2d(3,5));
        Assertions.assertEquals(new Vector2d(0,0).add(new Vector2d(-1,-1)),new Vector2d(-1,-1));
    }

    @Test
    void test_subtract() {
        Assertions.assertEquals(new Vector2d(2,4).subtract(new Vector2d(1,1)),new Vector2d(1,3));
        Assertions.assertEquals(new Vector2d(0,0).subtract(new Vector2d(-1,-1)),new Vector2d(1,1));
    }

    @Test
    void test_upperRight() {
        Assertions.assertEquals(new Vector2d(1,1).upperRight(new Vector2d(2,2)),new Vector2d(1,2));
        Assertions.assertEquals(new Vector2d(0,-1).upperRight(new Vector2d(10,5)),new Vector2d(0,5));
    }

    @Test
    void test_lowerLeft() {
        Assertions.assertEquals(new Vector2d(3,4).lowerLeft(new Vector2d(6,5)),new Vector2d(6,4));
        Assertions.assertEquals(new Vector2d(3,7).lowerLeft(new Vector2d(2,1)),new Vector2d(2,7));
    }

    @Test
    void test_opposite() {
        Assertions.assertEquals(new Vector2d(2,1).opposite(),new Vector2d(-2,-1));
        Assertions.assertEquals(new Vector2d(0,-10).opposite(),new Vector2d(0,10));
    }

    @Test
    void test_Equals() {
        Assertions.assertTrue(new Vector2d(2,5).equals(new Vector2d(2,5)));
        Assertions.assertFalse(new Vector2d(2,5).equals(new Vector2d(1,5)));
        Assertions.assertFalse(new Vector2d(4,4).equals(new Object()));
    }
}