package org.bkl.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class VersionManagePane extends StackPane {

    private static Stage primaryStage;
    private double xOffset = 0;
    private double yOffset = 0;

    public VersionManagePane(Stage primaryStage) {
        this.setStyle("""
                -fx-background-color: pink;
                -fx-background-radius: 10;
                -fx-padding: 0;
                """);

        initUi(primaryStage);
    }

    private void initUi(Stage primaryStage) {
        HBox titleBar = new HBox();
        titleBar.setStyle(
                "-fx-background-color: #2196F3;" +
                "-fx-background-radius: 10 10 0 0;"
        );
        titleBar.setMinHeight(20);
        titleBar.setAlignment(Pos.CENTER);
        titleBar.setMinHeight(30);

        Button returnButton = new Button("◂");
        returnButton.setStyle("""
                -fx-background-color: #2196F3;
                -fx-text-fill: white;
                """);
        returnButton.setOnMouseClicked(e -> {
            FirstPage.show();
        });

        Label title = new Label("游戏版本管理");
        title.setStyle(
                "-fx-text-fill: white;" +
                " -fx-font-size: 16px;" +
                " -fx-font-weight: 500;" +
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

        Button minimizeButton = new Button("━");
        HBox.setMargin(minimizeButton, new Insets(0, 10, 0, 0));
        minimizeButton.setStyle(
                "-fx-background-color: #2196F3;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 0 10 0 0;"
        );
        minimizeButton.setOnAction(e -> primaryStage.setIconified(true));

        titleBar.getChildren().addAll(returnButton, title, titleSpacer, minimizeButton, closeButton);
        titleBar.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        titleBar.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });


        BorderPane root = new BorderPane();
        root.setStyle("""
                -fx-background-radius: 10;
                """);
        this.getChildren().add(root);
        root.setTop(titleBar);

    }
}
