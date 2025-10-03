package org.bkl.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * @author LongWei
 * @date 2025/10/2 22:38
 */
public class VersionManageLeftPane extends VBox {
    public VersionManageLeftPane() {
        this.setPrefWidth(200);
        this.setStyle("""
                -fx-background-color: rgba(255, 255, 255, 0.5);
                """);
        initUi();
    }

    public void initUi() {
        HBox autoInstallBox = new HBox();
        Button autoInstallButton = new Button("♕   自动安装");
        autoInstallButton.setStyle("""
                -fx-background-color: transparent;
                -fx-text-fill: #000000;
                -fx-font-weight: 500;
                """);
        autoInstallBox.getChildren().addAll(autoInstallButton);
        autoInstallBox.setAlignment(Pos.CENTER);
        autoInstallBox.setPrefSize(200, 40);
        VBox.setMargin(autoInstallBox, new Insets(10, 0, 0, 0));
        autoInstallBox.setOnMouseEntered(e -> {
            autoInstallBox.setStyle("""
                    -fx-background-color: rgba(0, 0, 0, 0.1);
                    """);
            autoInstallButton.setStyle("""
                    -fx-background-color: transparent;
                    -fx-text-fill: #000000;
                    -fx-font-weight: 800;
                    """);
        });
        autoInstallBox.setOnMouseExited(e -> {
            autoInstallBox.setStyle("""
                    -fx-background-color: transparent;
                    """);
            autoInstallButton.setStyle("""
                    -fx-background-color: transparent;
                    -fx-text-fill: #000000;
                    -fx-font-weight: 500;
                    """);
        });
        autoInstallButton.setOnMouseClicked(e -> {
            System.out.println("自动安装按钮被点击");
        });

        HBox modManageBox = new HBox();
        Button modManageButton = new Button("❦   模组管理");
        modManageButton.setStyle("""
                -fx-background-color: transparent;
                -fx-text-fill: #000000;
                -fx-font-weight: 500;
                """);
        modManageBox.getChildren().add(modManageButton);
        modManageBox.setAlignment(Pos.CENTER);
        modManageBox.setPrefSize(200, 40);
        modManageBox.setOnMouseEntered(e -> {
            modManageBox.setStyle("""
                    -fx-background-color: rgba(0, 0, 0, 0.1);
                    """);
            modManageButton.setStyle("""
                    -fx-background-color: transparent;
                    -fx-text-fill: #000000;
                        -fx-font-weight: 800;
                        """);
        });
        modManageBox.setOnMouseExited(e -> {
            modManageBox.setStyle("""
                    -fx-background-color: transparent;
                    """);
            modManageButton.setStyle("""
                -fx-background-color: transparent;
                -fx-text-fill: #000000;
                -fx-font-weight: 500;
                    """);
        });

        this.getChildren().addAll(autoInstallBox, modManageBox);

    }

}