package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class AnimalTest {
    @Test
    public void test_orientaion(){
        Assertions.assertTrue(test_util(new Animal(),new String[]{"r", "r", "r", "r"}).direction_equals(MapDirection.NORTH));
        Assertions.assertTrue(test_util(new Animal(),new String[]{"r", "l", "l"}).direction_equals(MapDirection.WEST));
        Assertions.assertTrue(test_util(new Animal(),new String[]{"l", "r", "l", "r"}).direction_equals(MapDirection.NORTH));
        Assertions.assertTrue(test_util(new Animal(),new String[]{"l", "l"}).direction_equals(MapDirection.SOUTH));
        Assertions.assertTrue(test_util(new Animal(),new String[]{"r"}).direction_equals(MapDirection.EAST));
    }
    private Animal test_util(Animal animal, String[] args){
        OptionsParser optionsParser = new OptionsParser();
        MoveDirection[] moveDirections = optionsParser.parse(args);
        for (MoveDirection var:moveDirections){
            animal.move(var);
        }
        return animal;
    }
    @Test
    public void test_position_move(){
        Assertions.assertTrue(test_util(new Animal(),new String[]{"r","f","f"}).position_equals(new Vector2d(4,2)));
        Assertions.assertTrue(test_util(new Animal(),new String[]{"b","r","f"}).position_equals(new Vector2d(3,1)));
        Assertions.assertTrue(test_util(new Animal(),new String[]{"r","b","b"}).position_equals(new Vector2d(0,2)));
        Assertions.assertTrue(test_util(new Animal(),new String[]{"r","f","r","b"}).position_equals(new Vector2d(3,3)));
    }

    @Test
    public void test_out_of_bounds(){
        Assertions.assertTrue(test_util(new Animal(),new String[]{"r","f","f","f","f"}).position_equals(new Vector2d(4,2)));
        Assertions.assertTrue(test_util(new Animal(),new String[]{"r","b","b","b","b"}).position_equals(new Vector2d(0,2)));
        Assertions.assertTrue(test_util(new Animal(),new String[]{"f","f","f","f"}).position_equals(new Vector2d(2,4)));
        Assertions.assertTrue(test_util(new Animal(),new String[]{"b","b","b","b"}).position_equals(new Vector2d(2,0)));
        Assertions.assertTrue(test_util(new Animal(),new String[]{"f","f","f","f","r","f","f","f","f","f"}).position_equals(new Vector2d(4,4)));
    }

    @Test
    public void test_parser_args(){
        Assertions.assertArrayEquals(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT},
                new OptionsParser().parse(new String[]{"f", "b", "l"}));

        Assertions.assertArrayEquals(new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD},
                new OptionsParser().parse(new String[]{"r", "f", "b"}));

        Assertions.assertArrayEquals(new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.LEFT}, new OptionsParser().parse(new String[]{"r", "f", "abcdef", "b", "l", "2137"}));

    }

}