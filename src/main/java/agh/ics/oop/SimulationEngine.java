package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.io.FileNotFoundException;

public class SimulationEngine implements  IEngine,Runnable{
    private MoveDirection[] directions;
    private IWorldMap map;

    private Vector2d[] positions;

    private App app=null;

    public void setDirections(MoveDirection[] directions) {
        this.directions = directions;
    }

    private int moveDelay=500;


    public SimulationEngine(IWorldMap map, Vector2d[] positions, App app,int moveDelay){
        for(Vector2d var :positions){
            map.place(new Animal(map,var));
        }
        this.map=map;
        this.positions=positions;
        this.app=app;
        this.moveDelay=moveDelay;

    }
    public SimulationEngine(IWorldMap map, Vector2d[] positions, App app){
        for(Vector2d var :positions){
            map.place(new Animal(map,var));
        }
        this.map=map;
        this.positions=positions;
        this.app=app;

    }
    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions, App app){
        for(Vector2d var :positions){
            map.place(new Animal(map,var));
        }
        this.map=map;
        this.directions=directions;
        this.positions=positions;
        this.app=app;

    }
    public SimulationEngine(MoveDirection[] directions,IWorldMap map,Vector2d[] positions){
        for(Vector2d var :positions){
            map.place(new Animal(map,var));
        }
        this.map=map;
        this.directions=directions;
        this.positions=positions;

    }



    @Override
    public void run()  {
        if (app == null) {
            int curr_positions_index = 0;
            for (MoveDirection var : directions) {

                Animal tmp = (Animal) map.objectAt(positions[curr_positions_index]);
                tmp.move(var);
                positions[curr_positions_index] = tmp.getPosition();
                curr_positions_index++;
                if (curr_positions_index == positions.length) {
                    curr_positions_index = 0;
                }

            }
        }
        else{
                try {
                    Platform.runLater(app::refreshMap);
                    int curr_positions_index = 0;


                    for (MoveDirection var : directions) {

                        Animal tmp = (Animal) map.objectAt(positions[curr_positions_index]);
                        tmp.move(var);
                        positions[curr_positions_index] = tmp.getPosition();
                        curr_positions_index++;
                        if (curr_positions_index == positions.length) {
                            curr_positions_index = 0;
                        }
                        Platform.runLater(app::refreshMap);
                        Thread.sleep(moveDelay);
                    }
                }catch (InterruptedException exception){
                    System.out.println("application stopped " + exception);
                    exception.printStackTrace();
                }

        }

    }




    public Vector2d[] getPositions(){
        return this.positions;
    }
}
