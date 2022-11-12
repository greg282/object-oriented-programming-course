package agh.ics.oop;

import java.util.Map;

public class RectangularMap implements IWorldMap{
    private int width;
    private int height;

    private MapVisualizer visualizer;

    private Animal[][] animalsMap;

    public RectangularMap(int width,int height){
        this.width=width;
        this.height=height;
        this.animalsMap=new Animal[height][width];
        this.visualizer=new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
            if(position.y>=0 && position.y<height && position.x>=0 && position.x<width ){
                return this.animalsMap[position.y][position.x]==null;
            }

        return false;
    }

    @Override
    public boolean place(Animal animal) {
       if(!isOccupied(animal.getPosition())){
           animalsMap[animal.getPosition().y][animal.getPosition().x]=animal;
           return true;
       }
           return false;
    }

    @Override
    public String toString(){
        return visualizer.draw(new Vector2d(0,0),new Vector2d(width-1,height-1));
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animalsMap[position.y][position.x]!=null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animalsMap[position.y][position.x];
    }

    @Override
    public void clearPlace(Vector2d position) {
       animalsMap[position.y][position.x]=null;
    }



}
