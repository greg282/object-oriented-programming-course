package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void canMoveTo() {
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"r","r","l","l"});
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IWorldMap map = new GrassField(10,positions);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Assertions.assertFalse(map.canMoveTo(new Vector2d(2,2)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(3,4)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(800,800)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(900,900)));

    }

    @Test
    void place() {
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"l","r","l","l"});
        Vector2d[] positions = { new Vector2d(10,10), new Vector2d(15,4) };
        IWorldMap map = new GrassField(10,positions);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Assertions.assertTrue(map.place(new Animal(map,new Vector2d(997,997))));
        Assertions.assertThrows(IllegalArgumentException.class,()-> map.place(new Animal(map,new Vector2d(10,10))));//lab7
        Assertions.assertTrue(map.place(new Animal(map,new Vector2d(889,999))));

    }

    @Test
    void isOccupied() {
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"l","r","l","l"});
        Vector2d[] positions = { new Vector2d(10,10), new Vector2d(15,4) };
        IWorldMap map = new GrassField(10,positions);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Assertions.assertTrue(map.isOccupied(new Vector2d(10,10)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(15,4)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(999,999)));
    }

    @Test
    void objectAt() {
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"l","r","l","l"});
        Vector2d[] positions = { new Vector2d(10,10), new Vector2d(15,4) };
        IWorldMap map = new GrassField(10, positions);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Animal tmp=new Animal(map,new Vector2d(999,999));
        map.place(tmp);
        Assertions.assertEquals(tmp,map.objectAt(new Vector2d(999,999)));
        Assertions.assertTrue(map.objectAt(new Vector2d(15,4)) instanceof Animal);
        Assertions.assertTrue(map.objectAt(new Vector2d(10,10)) instanceof Animal);

    }

    @Test
    void clearPlace() {
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"l","r","l","l"});
        Vector2d[] positions = { new Vector2d(10,10), new Vector2d(15,4) };
        IWorldMap map = new GrassField(10, positions);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Animal tmp=new Animal(map,new Vector2d(999,999));
        map.place(tmp);
        map.clearPlace(new Vector2d(999,999));
        assertNull(map.objectAt(new Vector2d(999, 999)));
    }
}