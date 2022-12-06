package org.example.gui;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.example.classes.SimulationStats;

public class StatsGui {
    final private int labelAmount = 5;

    final private String[] labelTexts = new String[]{
            "Liczba wszystkich zwierząt: ",
            "Liczba wszytkich roślin: ",
            "Liczba wolnych pól: ",
            "Średni poziom energii dla żyjących zwierząt: ",
            "Średni poziom energii dla martwych zwierząt: "
    };
    final private String l6 = "Najpopularniejszy genotyp: ";
    private Label[] labels = new Label[labelAmount];
    final private Label label6 = new Label("---");
    private Pane rootContainer;
    private SimulationStats stats;

    public StatsGui(Pane rootContainer, SimulationStats simulationStats) {
        this.rootContainer = rootContainer;
        this.stats = simulationStats;
        for (int i = 0; i < labelAmount; i++) {
            labels[i] = new Label(labelTexts[i]);
        }


        rootContainer.getChildren().addAll(labels);
        rootContainer.getChildren().add(label6);

        render();

    }

    public void render() {
        Platform.runLater(() -> {
            for (int i = 0; i < labelAmount; i++) {
                labels[i].setText(labelTexts[i] + stats.get(i));
            }
            label6.setText(l6 + stats.getBestGene());

        });
    }

}
