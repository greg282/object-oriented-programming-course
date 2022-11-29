package agh.ics.oop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GrassField extends AbstractWorldMap{
    private static final int range=1000;


    public GrassField(int n_of_fields,Vector2d[] positions){
        super(range,range);



        int pos_x,pos_y;

        for(Vector2d var:positions){
            map.put(var,new Object());//rezerwacja miejsc dla Animal w SimulationEngine
        }

        for(int i=0;i<n_of_fields;i++){
            pos_x=(int) ((Math.random() * (Math.sqrt(n_of_fields*10) - 0)) + 0);
            pos_y=(int) ((Math.random() * (Math.sqrt(n_of_fields*10) - 0)) + 0);
            while(map.get(new Vector2d(pos_x,pos_y))!=null){
                pos_x=(int) ((Math.random() * (Math.sqrt(n_of_fields*10) - 0)) + 0);
                pos_y=(int) ((Math.random() * (Math.sqrt(n_of_fields*10) - 0)) + 0);
            }
            map.put(new Vector2d(pos_x,pos_y),new Grass(new Vector2d(pos_x,pos_y)));
        }

        for(Vector2d var:positions){
            map.remove(var);
        }

    }

}


/*before abstract
public class GrassField implements IWorldMap{
    private Object[][] map;
    private MapVisualizer visualizer;
    private int range=1000;

    private int max_y=-1,max_x=-1;
    private int min_y=range,min_x=range;

    public GrassField(int n_of_fields,Vector2d[] positions){
        this.visualizer=new MapVisualizer(this);
        this.map=new Object[range][range];
        int pos_x,pos_y;

        for(Vector2d var:positions){
            map[var.y][var.x]=new Object();//rezerwacja miejsc dla Animal w SimulationEngine
        }

        for(int i=0;i<n_of_fields;i++){
            pos_x=(int) ((Math.random() * (Math.sqrt(n_of_fields*10) - 0)) + 0);
            pos_y=(int) ((Math.random() * (Math.sqrt(n_of_fields*10) - 0)) + 0);
            while(map[pos_x][pos_y]!=null){
                pos_x=(int) ((Math.random() * (Math.sqrt(n_of_fields*10) - 0)) + 0);
                pos_y=(int) ((Math.random() * (Math.sqrt(n_of_fields*10) - 0)) + 0);
            }

            updateCorners(pos_x,pos_y);
            map[pos_y][pos_x]=new Grass(new Vector2d(pos_x,pos_y));
        }

        for(Vector2d var:positions){
            map[var.y][var.x]=null;
        }

    }

    private void updateCorners(int pos_x,int pos_y){
        if(pos_x<min_x){
            min_x=pos_x;
        }
        if(pos_x>max_x){
            max_x=pos_x;
        }

        if(pos_y<min_y){
            min_y=pos_y;
        }
        if(pos_y>max_y){
            max_y=pos_y;
        }

    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if(position.y>=0 && position.y<range && position.x>=0 && position.x<range ){
            return this.map[position.y][position.x]==null;
        }

        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if(!isOccupied(animal.getPosition())){
            updateCorners(animal.getPosition().x,animal.getPosition().y);
            map[animal.getPosition().y][animal.getPosition().x]=animal;
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return map[position.y][position.x]!=null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return map[position.y][position.x];
    }

    @Override
    public String toString() {
        return visualizer.draw(new Vector2d(min_x,min_y),new Vector2d(max_x,max_y));
    }

    @Override
    public void clearPlace(Vector2d position) {
        map[position.y][position.x]=null;
    }
}

 */
