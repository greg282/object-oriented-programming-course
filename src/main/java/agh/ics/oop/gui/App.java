package agh.ics.oop.gui;


import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.text.html.parser.Parser;
import java.awt.*;
import java.io.FileNotFoundException;

public class App extends Application {
    IWorldMap map;
    int width=45;
    int height=45;

    private final GridPane gridPane = new GridPane();

    private final int moveDelay=500;
    private SimulationEngine engine;



    public void init(){
        try{
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            this.map = new GrassField(10,positions);
            this.engine = new SimulationEngine(map, positions,this,moveDelay);

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

        this.gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setGridLinesVisible(true);

        Button start = new Button("Start");
        TextField moveDirections = new TextField();
        HBox guiControls = new HBox(moveDirections, start);
        VBox gui = new VBox(guiControls, this.gridPane);
        guiControls.setAlignment(Pos.TOP_CENTER);
        gui.setSpacing(20);
        start.setOnAction((click) -> {
            MoveDirection[] directions =  new OptionsParser().parse(moveDirections.getText().split(" "));
            this.engine.setDirections(directions);
            Thread simulationThread=new Thread(engine);
            simulationThread.start();
            moveDirections.clear();
        });


        Scene scene = new Scene(gui, 400, 400);


        Label label_X_Y = new Label("y/x");
        gridPane.add(label_X_Y,0,0);
        gridPane.getColumnConstraints().add(new ColumnConstraints(width));
        gridPane.getRowConstraints().add(new RowConstraints(height));
        GridPane.setHalignment(label_X_Y, HPos.CENTER);


        for(int x=left_x1;x<=right_x2;x++){
            Label label=new Label(x+"");
            gridPane.add(label,x-left_x1+1,0);
            gridPane.getColumnConstraints().add(new ColumnConstraints(width));

            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = 1; i < top_y2 -  bottom_y1 + 2; i++) {
            int value = top_y2 - i + 1;
            Label label = new Label(value + "");
            gridPane.add(label, 0, i);
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

                if (element!=null) {gridPane.add(new GuiElementBox((IMapElement) element).getStyleableNode(), i, j);}
                else {
                    gridPane.add(label,i,j);
                    GridPane.setHalignment(label,HPos.CENTER);

                }
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }



        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void refreshMap() {
        this.gridPane.getChildren().clear();

        MapBoundary mapBoundary= this.map.getBorders();

        int left_x1 =mapBoundary.getlowerleft_corner().x;
        int bottom_y1 =mapBoundary.getlowerleft_corner().y;

        int right_x2 =mapBoundary.getuppperright_corner().x;
        int top_y2 =mapBoundary.getuppperright_corner().y;

        this.gridPane.setGridLinesVisible(false);
        this.gridPane.getColumnConstraints().clear();
        this.gridPane.getRowConstraints().clear();
        this.gridPane.getChildren().clear();
        this.gridPane.setGridLinesVisible(true);


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
            gridPane.add(label, 0, i);
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

                if (element!=null) {
                    try {
                        gridPane.add(new GuiElementBox((IMapElement) element).getStyleableNode(), i, j);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    gridPane.add(label,i,j);
                    GridPane.setHalignment(label,HPos.CENTER);

                }
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }


    }
}
