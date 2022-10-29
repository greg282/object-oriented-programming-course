package agh.ics.oop;

import java.util.Objects;

public class Animal {
    private Vector2d position = new Vector2d(2,2);
    private MapDirection mapDirection =MapDirection.NORTH;

    @Override
    public String toString() {
        return "Animal {" +
                "position=" + position +
                ", mapDirection=" + mapDirection +
                '}';
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction){
        switch(direction){
            case RIGHT -> this.mapDirection = this.mapDirection.next();
            case LEFT -> this.mapDirection = this.mapDirection.previous();
            case FORWARD-> this.position = check_bounds_forward();
            case BACKWARD -> this.position=check_bounds_backward();
        }
    }

    private Vector2d check_bounds_forward(){
        Vector2d tmp=this.position.add(this.mapDirection.toUnitVector());
        if ( tmp.x>4 || tmp.x <0  || tmp.y>4 || tmp.y <0) {
            return this.position;
        }
        else{
            return tmp;
        }
    }
    private Vector2d check_bounds_backward(){
        Vector2d tmp=this.position.subtract(this.mapDirection.toUnitVector());
        if ( tmp.x>4 || tmp.x <0  || tmp.y>4 || tmp.y <0) {
            return this.position;
        }
        else{
            return tmp;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(position, animal.position) && mapDirection == animal.mapDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, mapDirection);
    }

    public boolean direction_equals(MapDirection direction){/*funkjca pomocnicza do testów*/
        return this.mapDirection.equals(direction);
    }

    public boolean position_equals(Vector2d vector2d){/*funkjca pomocnicza do testów*/
        return this.position.equals(vector2d);
    }
}
