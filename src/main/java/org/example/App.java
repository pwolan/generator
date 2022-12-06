package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane rootPane = new GridPane();
        HBox hbox = new HBox();
        hbox.getChildren().add(rootPane);
        VBox vbox = new VBox();
        vbox.getChildren().add(hbox);

        VBox stats = new VBox();
        vbox.getChildren().add(stats);

        Scene scene = new Scene(vbox, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();


        SimulationConfig config = new SimulationConfig();

        SimulationEngine engine = new SimulationEngine(config,rootPane, stats );
        Thread startThread = new Thread(engine);
        startThread.start();
    }

    public static void main(String[] args) {
        launch();
    }

}
