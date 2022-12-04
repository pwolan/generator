package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.helpers.Vector2d;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane rootPane = new GridPane();
        Scene scene = new Scene(rootPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        WorldMap map = new WorldMap(new Vector2d(10, 10));
        SimulationEngine engine = new SimulationEngine(map,rootPane );
        engine.run();
    }

    public static void main(String[] args) {
        launch();
    }

}
