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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.bkl.game.GameLauncher;

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


        titleBar.getChildren().addAll(title, titleSpacer, minimizeButton, closeButton);
        titleBar.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        titleBar.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        // 底部大容器
        HBox content = new HBox();
        content.setStyle(
                "-fx-background-color: pink;" +
                "-fx-background-radius: 0 0 10 10;"
        );

        // 右边容器
        VBox mainContent = new VBox();
        mainContent.setPrefHeight(500);
        mainContent.setPrefWidth(600);
        mainContent.setStyle(
                "-fx-background-image: url('image/bgc.jpg');" +
                "-fx-background-size: cover;" +
                "-fx-background-color: white;" +
                "-fx-padding: 0 0 20 0;"
        );

        HBox startButtonHBox = new HBox();
        startButtonHBox.setPrefHeight(50);
        startButtonHBox.setAlignment(Pos.CENTER);

        ObservableList<String> options = FXCollections.observableArrayList(
            "1.21.8",
                "1.21.7",
                "1.21.1"
        );

        HBox comboBoxHBox = new HBox();
        comboBoxHBox.setPrefWidth(100);
        comboBoxHBox.setPrefHeight(20);
        comboBoxHBox.setAlignment(Pos.CENTER);

        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setPrefHeight(20);
        comboBox.setPrefWidth(60);
// 确保先为comboBox添加custom-combo样式类
        comboBox.getStyleClass().add("custom-combo");

// 修改后的样式设置，包含黑色半透明的选择框
        comboBox.getStylesheets().add(
                "data:text/css," +
                ".custom-combo {" +
                "    -fx-background-color: rgba(0, 0, 0, 0.5);" +  // 主控件黑色半透明
                "    -fx-background-radius: 10;" +
                "}" +
                ".custom-combo .arrow-button {" +
                "    -fx-padding: 0;" +
                "}" +
                ".custom-combo .arrow {" +
                "    -fx-background-color: transparent;" +
                "}" +
                // 主控件中的文字样式
                ".custom-combo .list-cell {" +
                "    -fx-text-fill: white;" +
                "    -fx-background-color: transparent;" +
                "}" +
                ".custom-combo .text-field {" +
                "    -fx-text-fill: white;" +
                "    -fx-background-color: transparent;" +
                "}" +
                // 下拉选择框的黑色半透明样式
                ".custom-combo .combo-box-popup .list-view {" +
                "    -fx-background-color: rgba(0, 0, 0, 0.5);" +  // 选择框黑色半透明
                "    -fx-background-radius: 10;" +
                "    -fx-border-radius: 10;" +
                "}" +
                // 下拉选择框中的选项样式
                ".custom-combo .combo-box-popup .list-cell {" +
                "    -fx-text-fill: white;" +  // 选择框文字保持白色以适配黑色背景
                "    -fx-padding: 3 5 3 5;" +
                "}" +
                // 选择框中选中项的样式
                ".custom-combo .combo-box-popup .list-cell:filled:selected {" +
                "    -fx-background-color: rgba(255, 255, 255, 0.2);" +  // 选中项高亮
                "}" +
                // 选择框中鼠标悬停项的样式
                ".custom-combo .combo-box-popup .list-cell:filled:hover {" +
                "    -fx-background-color: rgba(255, 255, 255, 0.1);" +  // 悬停效果
                "}"
        );

        comboBox.setValue("1.21.8");
        comboBox.setOnAction(e -> {
            String selected = comboBox.getValue();

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
        // 开始游戏按钮
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
        // 开始游戏点击监听事件
        startButton.setOnAction(e -> {
            GameLauncher.start();
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