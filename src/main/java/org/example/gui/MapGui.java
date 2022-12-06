package org.example.gui;



import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.example.classes.Square;
import org.example.classes.Squares;
import org.example.helpers.Vector2d;



public class MapGui {

    private Squares squares;
    private GridPane rootPane;
    private int CELL_SIZE = 40;
    public MapGui(Squares squares, GridPane rootPane) {
        this.squares = squares;
        this.rootPane = rootPane;
        Platform.runLater(()->{
            rootPane.setHgap(5);
            rootPane.setVgap(5);
            rootPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        });
        render();
    }
    public void render(){
        Platform.runLater(()->{
            rootPane.getColumnConstraints().clear();
            rootPane.getRowConstraints().clear();
            rootPane.getChildren().clear();

        for (Square sq : this.squares.getSquaresIterable()) {

            Vector2d pos = sq.getPosition();
            GuiElementBox elementBox = new GuiElementBox(sq,pos);

                elementBox.renderElement(this.rootPane);
                if(pos.x() == 0){
                    rootPane.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
                }
                if(pos.y()==0){
                    rootPane.getRowConstraints().add(new RowConstraints(CELL_SIZE));
                }
        }
        });




    }
}
