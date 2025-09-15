package org.bkl.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FirstPage extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) {

        HBox titleBar = new HBox();
        titleBar.setStyle(
                "-fx-background-color: #2196F3;" +
                "-fx-background-radius: 10 10 0 0;"
        );
        titleBar.setSpacing(20);
        titleBar.setMinHeight(20);
        titleBar.setAlignment(Pos.CENTER);
        titleBar.setMinHeight(30);

        Region titleSpacer = new Region();
        HBox.setHgrow(titleSpacer, javafx.scene.layout.Priority.ALWAYS);

        Button closeButton = new Button("×");
        closeButton.setStyle(
                "-fx-background-color: #2196F3;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: 900;" +
                "-fx-background-radius: 0 10 0 0;" +
                "-fx-padding: 0 10 0 0;"
        );
        closeButton.setOnAction(e -> primaryStage.close());

        Button minimizeButton = new Button("-");
        minimizeButton.setStyle(
                "-fx-background-color: #2196F3;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: 900;" +
                        "-fx-background-radius: 0;" +
                        "-fx-padding: 0 10 0 10;"
        );
        minimizeButton.setOnAction(e -> primaryStage.setIconified(true));

        Label title = new Label("BlockLauncher");
        title.setStyle(
                "-fx-text-fill: white;" +
                " -fx-font-size: 16px;" +
                " -fx-font-weight: 500;" +
                "-fx-padding: 0 0 0 10;" +
                "-fx-background-radius: 10 0 0 0;"
        );

        titleBar.getChildren().addAll(title, titleSpacer, minimizeButton, closeButton);
        titleBar.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        titleBar.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        HBox content = new HBox();
        content.setStyle(
                "-fx-background-color: pink;" +
                "-fx-background-radius: 0 0 10 10;"
        );

        VBox mainContent = new VBox();
        mainContent.setPrefHeight(500);
        mainContent.setPrefWidth(600);
        mainContent.setStyle(
                "-fx-background-image: url('image/bgc.jpg');" +
                "-fx-background-size: cover;" +
                "-fx-background-color: white;" +
                "-fx-padding: 0 0 20 0;"
        );


        Button startButton = new Button("开始游戏");
        startButton.setPrefWidth(150);
        startButton.setPrefHeight(50);
        startButton.setStyle(
                "-fx-background-color: #2196F3;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: 500;" +
                "-fx-background-radius: 5;" +
                "-fx-padding: 10 20 10 20;"
        );

        startButton.setOnMouseEntered(e -> startButton.setStyle(
                "-fx-background-color: #45A049;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: 500;" +
                        "-fx-background-radius: 5;" +
                        "-fx-padding: 10 20 10 20;"
        ));
        startButton.setOnMouseExited(e -> startButton.setStyle(
                "-fx-background-color: #2196F3;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: 500;" +
                        "-fx-background-radius: 5;" +
                        "-fx-padding: 10 20 10 20;"
        ));
        startButton.setOnAction(e -> {
            System.out.println("开始游戏被点击了");
        });

        HBox startButtonContainer = new HBox();
        startButtonContainer.setAlignment(Pos.BOTTOM_RIGHT);
        startButtonContainer.setPrefWidth(600);
        startButtonContainer.setStyle("-fx-padding: 0 20 20 0;");
        startButtonContainer.getChildren().add(startButton);

        mainContent.setAlignment(Pos.BOTTOM_CENTER);
        mainContent.getChildren().addAll(startButtonContainer);

        VBox leftPanel = new VBox();
        leftPanel.setPrefHeight(500);
        leftPanel.setPrefWidth(200);
        leftPanel.setStyle(
                "-fx-background-color: skyblue;" +
                "-fx-background-radius: 0 0 0 10;"
        );

        content.getChildren().addAll(leftPanel, mainContent);

        BorderPane root = new BorderPane();
        root.setTop(titleBar);
        root.setCenter(content);
        root.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10 10 10 10;" +
                "-fx-background-insets: 0;"
        );

        Scene scene = new Scene(root, 800, 500);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    public static void launchUi(String[] args) {
        launch(args);
    }
}