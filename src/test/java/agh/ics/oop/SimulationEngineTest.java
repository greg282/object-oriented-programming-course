package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {


    @Test
    void run_test_1() {
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f","b","f","b"});
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Assertions.assertArrayEquals(
                engine.getPositions(),new Vector2d[]{new Vector2d(2,4),new Vector2d(3,2)}
        );
        Assertions.assertFalse(map.isOccupied(new Vector2d(2,0)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(3,0)));
        Animal tmp = new Animal(map,new Vector2d(2,0));
        Assertions.assertTrue(map.place(tmp));
        Assertions.assertEquals(tmp,map.objectAt(new Vector2d(2,0)));
    }
    @Test
    void run_test_2() {
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f","r","f","f"});
        IWorldMap map = new RectangularMap(50, 25);
        Vector2d[] positions = { new Vector2d(49,24), new Vector2d(20,15) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Assertions.assertArrayEquals(
                engine.getPositions(),new Vector2d[]{new Vector2d(49,24),new Vector2d(21,15)}
        );
        Assertions.assertTrue(map.isOccupied(new Vector2d(49,24)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(-3,100)));
        Animal tmp = new Animal(map,new Vector2d(10,5));
        Assertions.assertTrue(map.place(tmp));
        Assertions.assertEquals(tmp,map.objectAt(new Vector2d(10,5)));

    }
}