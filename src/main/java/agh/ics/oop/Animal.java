package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Animal extends AbstractWorldMapElement {
    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    private MapDirection mapDirection =MapDirection.NORTH;
    private IWorldMap map=null;

    /*Bezparemetrowy konstruktor nie będzie miał sensu, ponieważ będziemy korzystać z pola map które w takim
    wywołaniu będzie nullem. Możemy uprościć ustawiając wartośći domyślne w przypadku nie podania argumentu*/
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map=map;
        this.position=initialPosition;
        addObserver((IPositionChangeObserver) this.map);
    }
    public Animal(IWorldMap map){
        this.map=map;
        this.position=new Vector2d(2,2);
        addObserver((IPositionChangeObserver) this.map);
    }

    public  Animal(){
        this.position=new Vector2d(2,2);
    }

    @Override
    public String toString() {
        return switch ((this.mapDirection)){
            case NORTH -> "N";
            case SOUTH -> "S";
            case WEST -> "W";
            case EAST -> "E";
        };
    }



    public void move(MoveDirection direction){
        switch(direction){
            case RIGHT -> this.mapDirection = this.mapDirection.next();
            case LEFT -> this.mapDirection = this.mapDirection.previous();

            case FORWARD ->{
                if(map.canMoveTo(this.position.add(this.mapDirection.toUnitVector()))) {
                    //map.clearPlace(this.position);
                    Vector2d old_pos=this.position;
                    this.position=this.position.add(this.mapDirection.toUnitVector());
                    //map.place(this);
                    positionChanged(old_pos);
                }
            }
            case BACKWARD -> {
                if( map.canMoveTo(this.position.subtract(this.mapDirection.toUnitVector())) ) {
                    //map.clearPlace(this.position);
                    Vector2d old_pos=this.position;
                    this.position=this.position.subtract(this.mapDirection.toUnitVector());
                    //map.place(this);
                    positionChanged(old_pos);
                }
            }
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
    void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    void positionChanged(Vector2d position) {
        for (IPositionChangeObserver observer: this.observers) {
            observer.positionChanged(position,this.position);
        }
    }
}
