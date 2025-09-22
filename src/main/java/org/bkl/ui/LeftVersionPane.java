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
        Separator separator1 = new Separator();
        separator1.setStyle("""
                -fx-background-color: #000000;
                """);
        parent.getChildren().add(separator1);

        HBox avatarHBox = new HBox();
        avatarHBox.setPrefSize(200, 80);
        avatarHBox.setAlignment(Pos.CENTER);
        avatarHBox.setStyle("""
                -fx-padding: 10 10 10 10;
                """);
        avatarHBox.setOnMouseEntered(e -> {
            avatarHBox.setStyle("""
                    -fx-background-color: rgba(0, 0, 0, 0.1);
                    """);
        });
        avatarHBox.setOnMouseExited(e -> {
            avatarHBox.setStyle("""
                    -fx-background-color: transparent;
                    """);
        });
        VBox avatarContainer = new VBox(1);
        avatarContainer.setPrefSize(35, 35);
        avatarContainer.setMaxSize(35, 35);
        avatarContainer.setMinSize(35, 35);
        avatarContainer.setStyle("""
                -fx-background-image: url("image/avatar/avatar1.png");
                -fx-background-size: cover;
                """);
        avatarHBox.getChildren().add(avatarContainer);
        VBox accountContainer = new VBox();
        accountContainer.setPrefSize(120, 35);
        accountContainer.setMaxSize(120, 35);
        accountContainer.setMinSize(120, 35);
        accountContainer.setStyle("""
                -fx-padding: 0 0 0 10;
                """);
        avatarHBox.getChildren().add(accountContainer);
        Label accountName = new Label("没有游戏账户");
        accountName.setStyle("""
                -fx-font-size: 14px;
                -fx-font-weight: 800;
                """);
        Label addAccount = new Label("点击添加游戏账户");
        accountContainer.getChildren().addAll(accountName, addAccount);

        parent.getChildren().addAll(avatarHBox);
    }

}
