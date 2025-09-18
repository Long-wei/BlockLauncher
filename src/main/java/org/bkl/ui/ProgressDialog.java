package org.bkl.ui;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProgressDialog extends HBox {

    private static final Logger log = LoggerFactory.getLogger(ProgressDialog.class);

    public ProgressDialog() {
        setPrefSize(200, 200);
        setMinSize(200, 200);
        setMaxSize(200, 200);
        setAlignment(Pos.CENTER);
        initUi();
    }

    private void initUi() {
        HBox hBox = new HBox();
        hBox.setPrefHeight(200);
        hBox.setPrefWidth(200);
        hBox.setStyle(
                "-fx-background-color: pink;"
        );

        Label label = new Label("游戏正在启动中");

        hBox.getChildren().add(label);
        this.getChildren().addAll(hBox);
    }

    public void show(Pane parent) {
        parent.getChildren().add(this);
    }

}
