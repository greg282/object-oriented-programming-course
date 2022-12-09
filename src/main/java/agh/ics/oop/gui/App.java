package agh.ics.oop.gui;


import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.awt.*;

public class App extends Application {
    IWorldMap map;
    int width=25;
    int height=25;

    public void init(){
        try{
            MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            this.map = new GrassField(10,positions);
            IEngine engine = new SimulationEngine(directions, map, positions);
            //engine.run();
            System.out.println(map.toString());
        }
        catch (IllegalArgumentException exception){
            exception.printStackTrace();
            Platform.exit();
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        MapBoundary mapBoundary= this.map.getBorders();

        int left_x1 =mapBoundary.getlowerleft_corner().x;
        int bottom_y1 =mapBoundary.getlowerleft_corner().y;

        int right_x2 =mapBoundary.getuppperright_corner().x;
        int top_y2 =mapBoundary.getuppperright_corner().y;

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        Scene scene = new Scene(gridPane, 400, 400);


        Label label_X_Y = new Label("y/x");
        gridPane.add(label_X_Y,0,0);
        gridPane.getColumnConstraints().add(new ColumnConstraints(width));
        gridPane.getRowConstraints().add(new RowConstraints(height));
        GridPane.setHalignment(label_X_Y, HPos.CENTER);

        /*
        adding x labels
         */
        for(int x=left_x1;x<=right_x2;x++){
            Label label=new Label(x+"");
            gridPane.add(label,x-left_x1+1,0);
            gridPane.getColumnConstraints().add(new ColumnConstraints(width));

            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int i = 1; i < top_y2 -  bottom_y1 + 2; i++) {
            int value = top_y2 - i + 1;
            Label label = new Label(value + "");
            gridPane.add(label, 0, i, 1, 1);
            gridPane.getRowConstraints().add(new RowConstraints(height));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = 1; i < right_x2 - left_x1 + 2; i++) {
            for (int j = 1; j < top_y2 - bottom_y1 + 2; j++) {
                Label label;
                Object element = this.map.objectAt(new Vector2d(left_x1 + i - 1, top_y2 - j + 1));

                if (element == null) {
                    label = new Label("");
                }
                else {
                    label = new Label(element.toString());
                }

                gridPane.add(label, i, j, 1, 1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }



        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
