package agh.ics.oop;

public class SimulationEngine implements  IEngine{
    private MoveDirection[] directions;
    private IWorldMap map;

    private Vector2d[] positions;


    public SimulationEngine(MoveDirection[] directions,IWorldMap map,Vector2d[] positions){
        for(Vector2d var :positions){
            map.place(new Animal(map,var));
        }
        this.map=map;
        this.directions=directions;
        this.positions=positions;

    }
    @Override
    public void run() {
        int curr_positions_index=0;
        for(MoveDirection var:directions){

            Animal tmp=(Animal) map.objectAt(positions[curr_positions_index]);
            tmp.move(var);
            positions[curr_positions_index]=tmp.getPosition();
            curr_positions_index++;
            if(curr_positions_index==positions.length){
                curr_positions_index=0;
            }

        }

    }

    public Vector2d[] getPositions(){
        return this.positions;
    }
}
