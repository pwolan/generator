package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.helpers.Vector2d;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane rootPane = new GridPane();
        HBox hbox = new HBox();
        hbox.getChildren().add(rootPane);
        VBox vbox = new VBox();
        vbox.getChildren().add(hbox);
        Scene scene = new Scene(vbox, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


        SimulationConfig config = new SimulationConfig();

        SimulationEngine engine = new SimulationEngine(config,rootPane );
        Thread startThread = new Thread(engine);
        startThread.start();
    }

    public static void main(String[] args) {
        launch();
    }

}
