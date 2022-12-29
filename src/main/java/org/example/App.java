package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane rootPane = new GridPane();
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(10);
        vbox.getChildren().add(hbox);
        final ToggleGroup groupMap = new ToggleGroup();
        final ToggleGroup groupGrass = new ToggleGroup();
        final ToggleGroup groupMutation = new ToggleGroup();
        final ToggleGroup groupBehavior = new ToggleGroup();
        Button btn = new Button();
        btn.setText("Start");
        ConfigBar(vbox,groupMap,groupGrass,groupMutation,groupBehavior);
        vbox.getChildren().add(btn);
        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        VBox stats = new VBox();
        stats.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
        SimulationConfig config = new SimulationConfig();

        btn.setOnAction((e)->{
            config.setMapName(groupMap.getSelectedToggle().getUserData().toString());
            config.getGrassConfig().setGrassName(groupGrass.getSelectedToggle().getUserData().toString());
            config.getAnimalConfig().setMutationName(groupMutation.getSelectedToggle().getUserData().toString());
            config.getAnimalConfig().setBehaviorName(groupBehavior.getSelectedToggle().getUserData().toString());
            SimulationEngine engine = new SimulationEngine(config,rootPane, stats);
            Thread startThread = new Thread(engine);
            hbox.getChildren().clear();
            vbox.getChildren().clear();
            vbox.getChildren().add(hbox);
            vbox.getChildren().add(stats);
            hbox.getChildren().add(rootPane);
            startThread.start();
        });

    }
    public void ConfigBar(VBox vbox,ToggleGroup groupMap,ToggleGroup groupGrass,ToggleGroup groupMutation, ToggleGroup groupBehavior){
        HBox hbox1 = new HBox(10);
        HBox hbox2 = new HBox(10);
        HBox hbox3 = new HBox(10);
        HBox hbox4 = new HBox(10);

        RadioButton rb1 = new RadioButton();
        rb1.setText("Globe");
        rb1.setUserData("Globe");
        rb1.setSelected(true);
        rb1.setToggleGroup(groupMap);

        RadioButton rb2 = new RadioButton();
        rb2.setText("Hell");
        rb2.setUserData("Hell");
        rb2.setToggleGroup(groupMap);

        hbox1.getChildren().add(rb1);
        hbox1.getChildren().add(rb2);
        vbox.getChildren().add(hbox1);

        RadioButton rb3 = new RadioButton();
        rb3.setText("Equator");
        rb3.setUserData("Equator");
        rb3.setSelected(true);
        rb3.setToggleGroup(groupGrass);

        RadioButton rb4 = new RadioButton();
        rb4.setText("Toxic");
        rb4.setUserData("Toxic");
        rb4.setToggleGroup(groupGrass);

        hbox2.getChildren().add(rb3);
        hbox2.getChildren().add(rb4);
        vbox.getChildren().add(hbox2);

        RadioButton rb5 = new RadioButton();
        rb5.setText("Random");
        rb5.setUserData("Random");
        rb5.setSelected(true);
        rb5.setToggleGroup(groupMutation);

        RadioButton rb6 = new RadioButton();
        rb6.setText("Correction");
        rb6.setUserData("Correction");
        rb6.setToggleGroup(groupMutation);

        hbox3.getChildren().add(rb5);
        hbox3.getChildren().add(rb6);
        vbox.getChildren().add(hbox3);

        RadioButton rb7 = new RadioButton();
        rb7.setText("Predestination");
        rb7.setUserData("Predestination");
        rb7.setSelected(true);
        rb7.setToggleGroup(groupBehavior);

        RadioButton rb8 = new RadioButton();
        rb8.setText("Madness");
        rb8.setUserData("Madness");
        rb8.setToggleGroup(groupBehavior);

        hbox4.getChildren().add(rb7);
        hbox4.getChildren().add(rb8);
        vbox.getChildren().add(hbox4);

        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);
        hbox3.setAlignment(Pos.CENTER);
        hbox4.setAlignment(Pos.CENTER);
    }
    public static void main(String[] args) {
        launch();
    }

}
