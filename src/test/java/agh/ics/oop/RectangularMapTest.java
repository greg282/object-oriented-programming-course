package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    /*lab5 tests*/
    @Test
    void canMoveTo() {
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f","b","f","b"});
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Assertions.assertFalse(map.canMoveTo(new Vector2d(2,4)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(3,2)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(0,0)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(-1,-1)));
    }

    @Test
    void place() {
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f","b","f","b"});
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Assertions.assertTrue(map.place(new Animal(map,new Vector2d(2,2))));
        Assertions.assertTrue(map.place(new Animal(map,new Vector2d(0,0))));
        Assertions.assertThrows(IllegalArgumentException.class,()->map.place(new Animal(map,new Vector2d(2,4))));//lab7
        Assertions.assertThrows(IllegalArgumentException.class,()->map.place(new Animal(map,new Vector2d(3,2))));//lab7
    }

    @Test
    void isOccupied() {
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f","b","f","b"});
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Assertions.assertTrue(map.isOccupied(new Vector2d(2,4)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(3,2)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(0,0)));
    }

    @Test
    void objectAt() {
        IWorldMap map = new RectangularMap(10, 5);
        Animal tmp=new Animal(map,new Vector2d(2,4));
        map.place(tmp);
        Assertions.assertEquals(tmp,map.objectAt(new Vector2d(2,4)));
    }

    @Test
    void clearPlace() {
        IWorldMap map = new RectangularMap(10, 5);
        Animal tmp=new Animal(map,new Vector2d(2,4));
        map.place(tmp);
        map.clearPlace(new Vector2d(2,4));
        assertNull(map.objectAt(new Vector2d(2, 4)));
    }
}