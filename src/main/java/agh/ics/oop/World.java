package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartowal");


        /* kod z lab1
        Direction[] directions = conversion(args);
        run(directions);
        */
        System.out.println("system zakonczyl dzialanie");
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
