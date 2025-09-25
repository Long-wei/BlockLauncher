package org.bkl.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.bkl.game.GameLauncher;
import org.bkl.game.MCVersionChecker;

public class FirstPage extends Application {

    private static Stage primaryStage;

    private double xOffset = 0;
    private double yOffset = 0;
    private static ObservableList<String> options = null;
    private StackPane stackPane = null;

    public FirstPage() {
        FirstPage.options = FXCollections.observableArrayList(MCVersionChecker.getVersionNameList());
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        HBox titleBar = new HBox();
        titleBar.setStyle(
                "-fx-background-color: #2196F3;" +
                "-fx-background-radius: 10 10 0 0;"
        );
        titleBar.setSpacing(20);
        titleBar.setMinHeight(20);
        titleBar.setAlignment(Pos.CENTER);
        titleBar.setMinHeight(30);

        Label title = new Label("BlockLauncher");
        title.setStyle(
                "-fx-text-fill: white;" +
                " -fx-font-size: 16px;" +
                " -fx-font-weight: 500;" +
                "-fx-padding: 0 0 0 10;" +
                "-fx-background-radius: 10 0 0 0;"
        );


        Region titleSpacer = new Region();
        HBox.setHgrow(titleSpacer, javafx.scene.layout.Priority.ALWAYS);

        Button closeButton = new Button("✕");
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
                "-fx-background-radius: 0 10 0 0;" +
                "-fx-padding: 0 10 0 0;"
        );
        minimizeButton.setOnAction(e -> primaryStage.setIconified(true));

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
                "-fx-background-radius: 0 0 10 10;"
        );

        VBox mainContent = new VBox();
        mainContent.setPrefHeight(500);
        mainContent.setPrefWidth(600);
        mainContent.setStyle(
                "-fx-padding: 0 0 20 0;"
        );

        HBox startButtonHBox = new HBox();
        startButtonHBox.setPrefHeight(50);
        startButtonHBox.setAlignment(Pos.CENTER);


        HBox comboBoxHBox = new HBox();
        comboBoxHBox.setPrefWidth(100);
        comboBoxHBox.setPrefHeight(20);
        comboBoxHBox.setAlignment(Pos.CENTER);

        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setPrefHeight(20);
        comboBox.setPrefWidth(70);
        comboBox.getStyleClass().add("custom-combo");

        comboBox.getStylesheets().add(
                "data:text/css," +
                ".custom-combo {" +
                "    -fx-background-color: rgba(0, 0, 0, 0.5);" +
                "    -fx-background-radius: 10;" +
                "}" +
                ".custom-combo .arrow-button {" +
                "    -fx-padding: 0;" +
                "}" +
                ".custom-combo .arrow {" +
                "    -fx-background-color: transparent;" +
                "}" +
                ".custom-combo .list-cell {" +
                "    -fx-text-fill: white;" +
                "    -fx-background-color: transparent;" +
                "}" +
                ".custom-combo .text-field {" +
                "    -fx-text-fill: white;" +
                "    -fx-background-color: transparent;" +
                "}" +
                ".custom-combo .combo-box-popup .list-view {" +
                "    -fx-background-color: rgba(0, 0, 0, 0.5);" +
                "    -fx-border-radius: 10;" +
                "}" +
                ".custom-combo .combo-box-popup .list-cell {" +
                "    -fx-text-fill: white;" +
                "    -fx-padding: 3 5 3 5;" +
                "}" +
                ".custom-combo .combo-box-popup .list-cell:filled:selected {" +
                "    -fx-background-color: rgba(255, 255, 255, 0.2);" +
                "}" +
                ".custom-combo .combo-box-popup .list-cell:filled:hover {" +
                "    -fx-background-color: rgba(255, 255, 255, 0.1);" +
                "}"
        );

        if (!FirstPage.options.isEmpty()) {
            comboBox.setValue(options.get(0));
            GameLauncher.setVersion(options.get(0));
        } else {
            comboBox.setValue("");
        }
        comboBox.setOnAction(e -> {
            String selected = comboBox.getValue();
            GameLauncher.setVersion(selected);
        });

        comboBoxHBox.getChildren().add(comboBox);
        VBox startButtonVBox = new VBox();
        startButtonVBox.setPrefWidth(150);
        startButtonVBox.setPrefHeight(50);
        startButtonVBox.setAlignment(Pos.CENTER);
        startButtonVBox.setStyle(
                "-fx-background-color: #2196F3;" +
                "-fx-background-radius: 25;"
        );
        Button startButton = new Button("启动游戏");
        startButton.setPrefWidth(150);
        startButton.setPrefHeight(50);
        startButton.setStyle(
                "-fx-background-color: #2196F3;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: 500;" +
                "-fx-background-radius: 25 25 25 25;" +
                "-fx-padding: 10 20 10 20;"
        );
        startButton.setOnAction(e -> {
            ProgressDialog progressDialog = new ProgressDialog();
            progressDialog.show(stackPane);
            GameLauncher.start(progressDialog);
        });
        startButtonVBox.getChildren().addAll(startButton);
        startButtonHBox.getChildren().addAll(comboBoxHBox, startButtonVBox);

        HBox startButtonContainer = new HBox();
        startButtonContainer.setAlignment(Pos.BOTTOM_RIGHT);
        startButtonContainer.setPrefWidth(600);
        startButtonContainer.setStyle("-fx-padding: 0 20 20 0;");
        startButtonContainer.getChildren().add(startButtonHBox);

        mainContent.setAlignment(Pos.BOTTOM_CENTER);
        mainContent.getChildren().addAll(startButtonContainer);

        VBox leftPanel = new VBox();
        leftPanel.setPrefHeight(500);
        leftPanel.setPrefWidth(200);
        leftPanel.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.5);" +
                "-fx-background-radius: 0 0 0 10;"
        );
        LeftMainPane leftMainPane = new LeftMainPane(leftPanel);
        content.getChildren().addAll(leftPanel, mainContent);

        StackPane stackPane = new StackPane();
        this.stackPane = stackPane;
        stackPane.setStyle(
                "-fx-background-radius: 10 10 10 10;" +
                "-fx-background-color: transparent"
        );
        BorderPane root = new BorderPane();
        root.setTop(titleBar);
        root.setCenter(content);
        root.setStyle(
                "-fx-background-image: url('image/bgc.jpg');" +
                "-fx-background-size: cover;" +
                "-fx-background-radius: 10 10 10 10;" +
                "-fx-background-insets: 0;"
        );
        stackPane.getChildren().add(root);
        // set radius for background
        Rectangle clip = new Rectangle();
        root.setClip(clip);
        clip.widthProperty().bind(root.widthProperty());
        clip.heightProperty().bind(root.heightProperty());
        clip.setArcWidth(10);
        clip.setArcHeight(10);

        Scene scene = new Scene(stackPane, 800, 500);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    public static void launchUi(String[] args) {
        launch(args);
    }

    public static void showVersionManegePane() {
        Scene secondPage = new Scene(new VersionManagePane(), 800, 500);
        primaryStage.setScene(secondPage);
    }

}