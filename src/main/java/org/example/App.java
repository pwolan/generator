package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Simulation starter");
        Button btn = new Button("Open Simulation!");
        btn.setPadding(new Insets(20,50,20,50));
        btn.setFont(new Font(26));
        VBox vBox = new VBox(4, btn);
        vBox.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(vBox, 400,400));
        primaryStage.show();
        btn.setOnAction((e)->{
            Stage stage = new Stage();
            Runnable app2 = new Simulation(stage);
            Thread thread = new Thread(app2);
            thread.start();
            stage.setOnCloseRequest(ev->{
                thread.stop();
            });
            primaryStage.setOnCloseRequest(ev->{
                Platform.exit();
                System.exit(0);
            });
        });

    }
    public static void main(String[] args) {
        launch();
    }
}
