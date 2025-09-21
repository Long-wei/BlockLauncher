package org.bkl.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.plaf.synth.Region;


public class LeftVersionPane {

    public LeftVersionPane(VBox parent) {

        initUi(parent);
    }

    private void initUi(VBox parent) {
        parent.setStyle("""
                -fx-padding: 10
                """);

        Label accountLabel = new Label("账号");
        accountLabel.setStyle("""
                -fx-padding: 20 0 0 0;
                -fx-font-weight: 500;
                """);
        parent.getChildren().add(accountLabel);
        Separator separator = new Separator();
        separator.setStyle("""
                -fx-background-color: #000000;
                """);
        parent.getChildren().add(separator);

        HBox avatarHBox = new HBox();
        avatarHBox.setPrefSize(200, 100);
        avatarHBox.setAlignment(Pos.CENTER);
        avatarHBox.setStyle("""
                -fx-background-color: pink;
                -fx-padding: 10 10 10 10;
                """);
        VBox avatarContainer = new VBox(1);
        avatarContainer.setPrefSize(50, 50);
        avatarContainer.setMaxSize(50, 50);
        avatarContainer.setMinSize(50, 50);
        avatarContainer.setStyle("""
                -fx-background-color: skyblue;
                """);
        avatarHBox.getChildren().add(avatarContainer);
        VBox accountContainer = new VBox();
        accountContainer.setPrefSize(100, 100);
        accountContainer.setMaxSize(100, 100);
        accountContainer.setMinSize(100, 100);
        VBox.setMargin(accountContainer, new Insets(0, 0, 0 ,10));
        accountContainer.setStyle("""
                -fx-background-color: blue;
                
                """);
        avatarHBox.getChildren().add(accountContainer);
        parent.getChildren().addAll(avatarHBox);
    }

}
