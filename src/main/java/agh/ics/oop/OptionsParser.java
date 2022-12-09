package agh.ics.oop;

public class OptionsParser {
    public MoveDirection[] parse(String[] args){
        MoveDirection[] moveDirections=new MoveDirection[args.length];
        int index=0;
        for(String var : args){
            MoveDirection temp = switch (var) {
                case "f","forward" -> MoveDirection.FORWARD;
                case "b","backward" -> MoveDirection.BACKWARD;
                case "r","right" -> MoveDirection.RIGHT;
                case "l","left" -> MoveDirection.LEFT;
                default -> throw new IllegalArgumentException(var + " is not legal move specification");
            };
            if(temp != null){
                moveDirections[index]=temp;
                index+=1;
            }

        }

        MoveDirection[] moveDirections_final=new MoveDirection[index];
        index=0;
        for(MoveDirection var:moveDirections){
            if(var != null){
                moveDirections_final[index]=var;
                index+=1;
            }
        }
        return moveDirections_final;
    }
}
