package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map.toString());
    }

    public static void run(Direction[] directions){
        System.out.println("Start");

        for(Direction var : directions){
            String message = switch (var) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tylu";
                case RIGHT -> "Zwierzak skreca w prawo";
                case LEFT ->  "Zwierzak skreca w lewo";
                default -> "Nieznana komenda";
            };
            System.out.println(message);
        }

        System.out.println("Stop");
    }

    public static Direction[] conversion(String[] args) {
        Direction[] directions = new Direction[args.length];
        int index=0;
        for(String var : args){
            Direction temp = switch (var) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> null;
            };
            if(temp != null){
                directions[index]=temp;
                index+=1;
            }

        }
        return directions;
    }

}
