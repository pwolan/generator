package org.example;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.helpers.Vector2d;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Simulation implements Runnable{
    final ToggleGroup groupMap = new ToggleGroup();
    final ToggleGroup groupGrass = new ToggleGroup();
    final ToggleGroup groupMutation = new ToggleGroup();
    final ToggleGroup groupBehavior = new ToggleGroup();
    final FileChooser fileChooser = new FileChooser();
    String textHeight = "10";
    TextField textFieldHeight = new TextField(textHeight);
    String textWidth = "10";
    TextField textFieldWidth = new TextField(textWidth);
    String textGrassEnergy = "50";
    TextField textFieldGrassEnergy = new TextField(textGrassEnergy);
    String textReadyReproductionEnergy = "30";
    TextField textFieldReadyReproductionEnergy = new TextField(textReadyReproductionEnergy);
    String textReproductionEnergy = "20";
    TextField textFieldReproductionEnergy = new TextField(textReproductionEnergy);
    String textAnimalStartEnergy = "80";
    TextField textFieldAnimalStartEnergy = new TextField(textAnimalStartEnergy);
    String textMaxEnergy = "100";
    TextField textFieldMaxEnergy = new TextField(textMaxEnergy);
    String textDailyEnergy = "10";
    TextField textFieldDailyEnergy = new TextField(textDailyEnergy);
    String textGenesLength = "8";
    TextField textFieldGenesLength = new TextField(textGenesLength);
    String textMinMutation = "1";
    TextField textFieldMinMutation = new TextField(textMinMutation);
    String textMaxMutation = "5";
    TextField textFieldMaxMutation = new TextField(textMaxMutation);
    String textAnimalsAtStart = "6";
    TextField textFieldAnimalsAtStart = new TextField(textAnimalsAtStart);
    String textGrassStart  = "5";
    TextField textFieldGrassStart = new TextField(textGrassStart);
    String textGrassSpawned = "3";
    TextField textFieldGrassSpawned = new TextField(textGrassSpawned);
    String textRefreshTime = "1000";
    TextField textFieldRefreshTime = new TextField(textRefreshTime);
    CheckBox checkBoxSaveToFile = new CheckBox("Save to file");
    Stage primaryStage;
    public Simulation(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
    public void run(){
        Platform.runLater(()->{
            textFieldHeight.setUserData(false);
            textFieldWidth.setUserData(false);
            textFieldGrassEnergy.setUserData(false);
            textFieldReadyReproductionEnergy.setUserData(false);
            textFieldReproductionEnergy.setUserData(false);
            textFieldAnimalStartEnergy.setUserData(false);
            textFieldMaxEnergy.setUserData(false);
            textFieldDailyEnergy.setUserData(false);
            textFieldGenesLength.setUserData(false);
            textFieldMinMutation.setUserData(false);
            textFieldMaxMutation.setUserData(false);
            textFieldAnimalsAtStart.setUserData(false);
            textFieldGrassStart.setUserData(false);
            textFieldGrassSpawned.setUserData(false);
            textFieldRefreshTime.setUserData(false);

            GridPane rootPane = new GridPane();
            primaryStage.setTitle("Simulation config");

            ScrollPane scrollPane = new ScrollPane();
            HBox hbox = new HBox(10);
            VBox stats = new VBox();
            VBox vbox = new VBox(10);


            Button btn = new Button();
            Button btnSave = new Button();
            Button btnUpload = new Button();
            btn.setText("Start");
            btnSave.setText("SaveOptions");
            btnUpload.setText("LoadOptions");




            ConfigBar(vbox);
            vbox.getChildren().add(hbox);
            hbox.getChildren().addAll(btn,btnSave,btnUpload);

            hbox.setAlignment(Pos.CENTER);
            vbox.setAlignment(Pos.CENTER);

            stats.setAlignment(Pos.CENTER);
            scrollPane.setContent(vbox);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            Scene scene = new Scene(scrollPane, 800, 900);

            primaryStage.setScene(scene);
            primaryStage.show();

            SimulationConfig config = new SimulationConfig();

            btn.setOnAction((e)->{
                config.setMapName(groupMap.getSelectedToggle().getUserData().toString());
                config.getGrassConfig().setGrassName(groupGrass.getSelectedToggle().getUserData().toString());
                config.getAnimalConfig().setMutationName(groupMutation.getSelectedToggle().getUserData().toString());
                config.getAnimalConfig().setBehaviorName(groupBehavior.getSelectedToggle().getUserData().toString());
                config.setMapSize(new Vector2d(Integer.parseInt(textFieldWidth.textProperty().getValue()),Integer.parseInt(textFieldHeight.textProperty().getValue())));
                config.getGrassConfig().setEnergyPerGrass(Integer.parseInt(textFieldGrassEnergy.textProperty().getValue()));
                config.getAnimalConfig().setReadyToReproduceEnergy(Integer.parseInt(textFieldReadyReproductionEnergy.textProperty().getValue()));
                config.getAnimalConfig().setReproduceEnergy(Integer.parseInt(textFieldReproductionEnergy.textProperty().getValue()));
                config.getAnimalConfig().setStartEnergy(Integer.parseInt(textFieldAnimalStartEnergy.textProperty().getValue()));
                config.getAnimalConfig().setMaxAnimalEnergy(Integer.parseInt(textFieldMaxEnergy.textProperty().getValue()));
                config.getAnimalConfig().setForDayEnergy(Integer.parseInt(textFieldDailyEnergy.textProperty().getValue()));
                config.getAnimalConfig().setGenesLength(Integer.parseInt(textFieldGenesLength.textProperty().getValue()));
                config.getAnimalConfig().setMinMutation(Integer.parseInt(textFieldMinMutation.textProperty().getValue()));
                config.getAnimalConfig().setMaxMutation(Integer.parseInt(textFieldMaxMutation.textProperty().getValue()));
                config.getAnimalConfig().setStartAnimals(Integer.parseInt(textFieldAnimalsAtStart.textProperty().getValue()));
                config.getGrassConfig().setStartGrasses(Integer.parseInt(textFieldGrassStart.textProperty().getValue()));
                config.getGrassConfig().setNewGrassesPerDay(Integer.parseInt(textFieldGrassSpawned.textProperty().getValue()));
                config.setRefreshTime(Integer.parseInt(textFieldRefreshTime.textProperty().getValue()));
                config.setSaveToFile(checkBoxSaveToFile.isSelected());

                SimulationEngine engine = null;
                try {
                    engine = new SimulationEngine(config,rootPane, stats);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Thread startThread = new Thread(engine);
                startThread.setDaemon(true);
                primaryStage.setTitle("Simulation");

                hbox.getChildren().clear();
                vbox.getChildren().clear();
                vbox.getChildren().add(hbox);
                vbox.getChildren().add(stats);
                hbox.getChildren().add(rootPane);

                startThread.start();
                primaryStage.setOnCloseRequest(ev->{
                    startThread.stop();
                });
            });
            btnSave.setOnAction((e)->{
                    try {
                            SaveConfig();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
            });
            btnUpload.setOnAction((e)->{
                    UploadConfig(primaryStage);
            });

        });
    }
    public void SaveConfig() throws IOException {
        String name = "SaveConfig.json";
        File f = new File(name);
        int counter = 0;
        while (f.exists()){
            name = "SaveConfig"+ counter + ".json";
            f = new File(name);
            counter++;
        }
        PrintWriter out = new PrintWriter(new FileWriter(name, false));
        JSONObject obj = new JSONObject();
        obj.put("groupMap",groupMap.getSelectedToggle().getUserData().toString());
        obj.put("groupGrass",groupGrass.getSelectedToggle().getUserData().toString());
        obj.put("groupMutation",groupMutation.getSelectedToggle().getUserData().toString());
        obj.put("groupBehavior",groupBehavior.getSelectedToggle().getUserData().toString());
        obj.put("mapSize", List.of(Integer.parseInt(textFieldWidth.textProperty().getValue()),Integer.parseInt(textFieldHeight.textProperty().getValue())));
        obj.put("GrassEnergy",Integer.parseInt(textFieldGrassEnergy.textProperty().getValue()));
        obj.put("ReadyReproductionEnergy",Integer.parseInt(textFieldReadyReproductionEnergy.textProperty().getValue()));
        obj.put("ReproductionEnergy",Integer.parseInt(textFieldReproductionEnergy.textProperty().getValue()));
        obj.put("AnimalStartEnergy",Integer.parseInt(textFieldAnimalStartEnergy.textProperty().getValue()));
        obj.put("MaxEnergy",Integer.parseInt(textFieldMaxEnergy.textProperty().getValue()));
        obj.put("DailyEnergy",Integer.parseInt(textFieldDailyEnergy.textProperty().getValue()));
        obj.put("GenesLength",Integer.parseInt(textFieldGenesLength.textProperty().getValue()));
        obj.put("MinMutation",Integer.parseInt(textFieldMinMutation.textProperty().getValue()));
        obj.put("MaxMutation",Integer.parseInt(textFieldMaxMutation.textProperty().getValue()));
        obj.put("AnimalsAtStart",Integer.parseInt(textFieldAnimalsAtStart.textProperty().getValue()));
        obj.put("GrassStart",Integer.parseInt(textFieldGrassStart.textProperty().getValue()));
        obj.put("GrassSpawned",Integer.parseInt(textFieldGrassSpawned.textProperty().getValue()));
        obj.put("RefreshTime",Integer.parseInt(textFieldRefreshTime.textProperty().getValue()));
        obj.put("SaveToFile",checkBoxSaveToFile.isSelected());
        out.write(obj.toString());

        out.close();
    }
    public void UploadConfig(Stage primaryStage){
        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            openFile(file);
        }
    }
    public void openFile(File file) {

        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject json = new JSONObject(content);
            List<Toggle> listMap = groupMap.getToggles();
            List<Toggle> listGrass = groupGrass.getToggles();
            List<Toggle> listMutation = groupMutation.getToggles();
            List<Toggle> listBehavior = groupBehavior.getToggles();
            for (Toggle toggle : listMap) {
                if (toggle.getUserData().equals(json.getString("groupMap"))) {
                    groupMap.selectToggle(toggle);
                }
            }
            for (Toggle grass : listGrass) {
                if (grass.getUserData().equals(json.getString("groupGrass"))) {
                    groupGrass.selectToggle(grass);
                }
            }
            for (Toggle toggle : listMutation) {
                if (toggle.getUserData().equals(json.getString("groupMutation"))) {
                    groupMutation.selectToggle(toggle);
                }
            }
            for (Toggle toggle : listBehavior) {
                if (toggle.getUserData().equals(json.getString("groupBehavior"))) {
                    groupBehavior.selectToggle(toggle);
                }
            }
            JSONArray listMapSize = json.getJSONArray("mapSize");
            textFieldWidth.setText(listMapSize.get(0).toString());
            textFieldHeight.setText(listMapSize.get(1).toString());
            textFieldGrassEnergy.setText(String.valueOf(json.getInt("GrassEnergy")));
            textFieldReadyReproductionEnergy.setText(String.valueOf(json.getInt("ReadyReproductionEnergy")));
            textFieldReproductionEnergy.setText(String.valueOf(json.getInt("ReproductionEnergy")));
            textFieldAnimalStartEnergy.setText(String.valueOf(json.getInt("AnimalStartEnergy")));
            textFieldMaxEnergy.setText(String.valueOf(json.getInt("MaxEnergy")));
            textFieldDailyEnergy.setText(String.valueOf(json.getInt("DailyEnergy")));
            textFieldGenesLength.setText(String.valueOf(json.getInt("GenesLength")));
            textFieldMinMutation.setText(String.valueOf(json.getInt("MinMutation")));
            textFieldMaxMutation.setText(String.valueOf(json.getInt("MaxMutation")));
            textFieldAnimalsAtStart.setText(String.valueOf(json.getInt("AnimalsAtStart")));
            textFieldGrassStart.setText(String.valueOf(json.getInt("GrassStart")));
            textFieldGrassSpawned.setText(String.valueOf(json.getInt("GrassSpawned")));
            textFieldRefreshTime.setText(String.valueOf(json.getInt("RefreshTime")));
            checkBoxSaveToFile.setSelected(json.getBoolean("SaveToFile"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }
    public void ConfigBar(VBox vbox){
        HBox hbox1 = new HBox(10);
        HBox hbox2 = new HBox(10);
        HBox hbox3 = new HBox(10);
        HBox hbox4 = new HBox(10);

        EventListeners();

        Text text1 = new Text("Map properties");
        text1.setFont(new Font(16));
        vbox.getChildren().add(text1);

        HBox hboxHeight = new HBox(10);
        HBox hboxWidth = new HBox(10);
        hboxHeight.getChildren().addAll(new Text("Height"),textFieldHeight);
        hboxWidth.getChildren().addAll(new Text("Width"),textFieldWidth);

        vbox.getChildren().addAll(hboxHeight,hboxWidth);

        Text text2 = new Text("Energy properties");
        text2.setFont(new Font(16));
        vbox.getChildren().add(text2);

        HBox hboxGrassEnergy = new HBox(10);
        HBox hboxReadyReproductionEnergy = new HBox(10);
        HBox hboxReproductionEnergy = new HBox(10);
        HBox hboxAnimalStartEnergy = new HBox(10);
        HBox hboxMaxEnergy = new HBox(10);
        HBox hboxDailyEnergy = new HBox(10);
        hboxGrassEnergy.getChildren().addAll(new Text("Grass energy profit"),textFieldGrassEnergy);
        hboxReadyReproductionEnergy.getChildren().addAll(new Text("Minimum energy to reproduce"),textFieldReadyReproductionEnergy);
        hboxReproductionEnergy.getChildren().addAll(new Text("Energy taken during reproduction"),textFieldReproductionEnergy);
        hboxAnimalStartEnergy.getChildren().addAll(new Text("Animal start energy"),textFieldAnimalStartEnergy);
        hboxMaxEnergy.getChildren().addAll(new Text("Animal max energy"),textFieldMaxEnergy);
        hboxDailyEnergy.getChildren().addAll(new Text("Daily energy taken"),textFieldDailyEnergy);
        vbox.getChildren().addAll(hboxGrassEnergy,hboxReadyReproductionEnergy,hboxReproductionEnergy,hboxAnimalStartEnergy,hboxMaxEnergy,hboxDailyEnergy);

        Text text3 = new Text("Genes properties");
        text3.setFont(new Font(16));
        vbox.getChildren().add(text3);

        HBox hboxGenesLength = new HBox(10);
        HBox hboxMinMutation = new HBox(10);
        HBox hboxMaxMutation = new HBox(10);
        hboxGenesLength.getChildren().addAll(new Text("Length of genes"),textFieldGenesLength);
        hboxMinMutation.getChildren().addAll(new Text("Minimum number of mutation"),textFieldMinMutation);
        hboxMaxMutation.getChildren().addAll(new Text("Maximum number of mutation"),textFieldMaxMutation);
        vbox.getChildren().addAll(hboxGenesLength,hboxMinMutation,hboxMaxMutation);

        Text text4 = new Text("Spawning properties");
        text4.setFont(new Font(16));
        vbox.getChildren().add(text4);

        HBox hboxAnimalsAtStart = new HBox(10);
        HBox hboxGrassSpawned = new HBox(10);
        HBox hboxGrassStart = new HBox(10);
        hboxAnimalsAtStart.getChildren().addAll(new Text("Animals spawning at the start"),textFieldAnimalsAtStart);
        hboxGrassStart.getChildren().addAll(new Text("Grass spawning at the start"),textFieldGrassStart);
        hboxGrassSpawned.getChildren().addAll(new Text("Grass spawning each day"),textFieldGrassSpawned);
        vbox.getChildren().addAll(hboxAnimalsAtStart,hboxGrassStart,hboxGrassSpawned);

        Text text5 = new Text("Variances");
        text5.setFont(new Font(16));
        vbox.getChildren().add(text5);

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

        Text text6 = new Text("Other");
        text6.setFont(new Font(16));
        vbox.getChildren().add(text6);

        HBox hboxRefreshTime = new HBox(10);
        hboxRefreshTime.getChildren().addAll(new Text("Refresh time [ms]"),textFieldRefreshTime);
        vbox.getChildren().addAll(hboxRefreshTime,checkBoxSaveToFile);

        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);
        hbox3.setAlignment(Pos.CENTER);
        hbox4.setAlignment(Pos.CENTER);
        hboxHeight.setAlignment(Pos.CENTER);
        hboxWidth.setAlignment(Pos.CENTER);
        hboxGrassEnergy.setAlignment(Pos.CENTER);
        hboxReadyReproductionEnergy.setAlignment(Pos.CENTER);
        hboxReproductionEnergy.setAlignment(Pos.CENTER);
        hboxAnimalStartEnergy.setAlignment(Pos.CENTER);
        hboxMaxEnergy.setAlignment(Pos.CENTER);
        hboxDailyEnergy.setAlignment(Pos.CENTER);
        hboxGenesLength.setAlignment(Pos.CENTER);
        hboxMinMutation.setAlignment(Pos.CENTER);
        hboxMaxMutation.setAlignment(Pos.CENTER);
        hboxAnimalsAtStart.setAlignment(Pos.CENTER);
        hboxGrassStart.setAlignment(Pos.CENTER);
        hboxGrassSpawned.setAlignment(Pos.CENTER);
        hboxRefreshTime.setAlignment(Pos.CENTER);
    }
    public void EventListeners(){
        textFieldHeight.textProperty().addListener(Listener(textFieldHeight,textHeight));
        textFieldWidth.textProperty().addListener(Listener(textFieldWidth,textWidth));
        textFieldGrassEnergy.textProperty().addListener(Listener(textFieldGrassEnergy,textGrassEnergy));
        textFieldReadyReproductionEnergy.textProperty().addListener(Listener(textFieldReadyReproductionEnergy,textReadyReproductionEnergy));
        textFieldReproductionEnergy.textProperty().addListener(Listener(textFieldReproductionEnergy,textReproductionEnergy));
        textFieldAnimalStartEnergy.textProperty().addListener(Listener2(textFieldAnimalStartEnergy,textAnimalStartEnergy));
        textFieldMaxEnergy.textProperty().addListener(Listener(textFieldMaxEnergy,textMaxEnergy));
        textFieldDailyEnergy.textProperty().addListener(Listener(textFieldDailyEnergy,textDailyEnergy));
        textFieldGenesLength.textProperty().addListener(Listener(textFieldGenesLength,textGenesLength));
        textFieldMinMutation.textProperty().addListener(Listener(textFieldMinMutation,textMinMutation));
        textFieldMaxMutation.textProperty().addListener(Listener(textFieldMaxMutation,textMaxMutation));
        textFieldAnimalsAtStart.textProperty().addListener(Listener(textFieldAnimalsAtStart,textAnimalsAtStart));
        textFieldGrassStart.textProperty().addListener(Listener(textFieldGrassStart,textGrassStart));
        textFieldGrassSpawned.textProperty().addListener(Listener(textFieldGrassSpawned,textGrassSpawned));
        textFieldRefreshTime.textProperty().addListener(Listener(textFieldRefreshTime,textRefreshTime));
    }
    public ChangeListener<String> Listener(TextField textField,String text){
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (!newValue.equals("") && Integer.parseInt(newValue) == 0){
                    textField.setText(oldValue);
                }
                if (newValue.equals("") && !(boolean) textField.getUserData()){
                    textField.setUserData(true);
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    textField.setUserData(false);
                                    if(textField.getText().equals("")){
                                        textField.setText(text);
                                    }
                                    // your code here
                                }
                            },
                            1000
                    );
                }
            }
        };
    }
    public ChangeListener<String> Listener2(TextField textField, String text){
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (!newValue.equals("") && Integer.parseInt(newValue) == 0){
                    textField.setText(oldValue);
                }
                if (!newValue.equals("") && Integer.parseInt(newValue) > Integer.parseInt(textFieldMaxEnergy.getText())){
                    textField.setText(oldValue);
                }
                if (newValue.equals("") && !(boolean) textField.getUserData()){
                    textField.setUserData(true);
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    textField.setUserData(false);
                                    if(textField.getText().equals("")){
                                        textField.setText(text);
                                    }
                                    // your code here
                                }
                            },
                            1000
                    );
                }
            }
        };
    }


}
