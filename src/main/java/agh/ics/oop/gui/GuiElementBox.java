package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox extends Node {

    ImageView imageView;
    VBox box_element;
    public GuiElementBox(IMapElement element) throws FileNotFoundException {
        String filePath = new File("").getAbsolutePath();
        Image image = new Image(new FileInputStream(filePath.concat(element.getPath())));
        this.imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Label label=new Label(element.getLabel());

        this.box_element=new VBox(1,this.imageView,label);
        this.box_element.setAlignment(Pos.CENTER);
    }

    @Override
    public Node getStyleableNode() {
        return this.box_element;
    }
}
