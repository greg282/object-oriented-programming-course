package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeMap;

public class MapBoundary implements IPositionChangeObserver{
    private TreeMap<Vector2d, Integer> elementsByX = new TreeMap<>(Comparator.comparingInt(vector2d -> vector2d.x));
    private TreeMap<Vector2d, Integer> elementsByY = new TreeMap<>(Comparator.comparingInt(vector2d -> vector2d.y));


    void clear_position(Vector2d pos){
        elementsByX.remove(pos.x);
        elementsByY.remove(pos.y);
    }
    void add_position(Vector2d pos){
        elementsByY.put(pos, pos.y);
        elementsByX.put(pos, pos.x);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        clear_position(oldPosition);
        add_position(newPosition);
    }

    public Vector2d getlowerleft_corner(){
        return new Vector2d(elementsByX.firstKey().x,elementsByY.firstKey().y);
    }
    public Vector2d getuppperright_corner(){
        return new Vector2d(elementsByX.lastKey().x,elementsByY.lastKey().y);
    }
}
