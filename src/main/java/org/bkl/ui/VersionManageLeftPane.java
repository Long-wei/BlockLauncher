package org.bkl.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.bkl.modloader.FabricVersionFetcher;


/**
 * @author LongWei
 * @date 2025/10/2 22:38
 */
public class VersionManageLeftPane extends VBox {
    private static final String BOX_SELECTED_STYLE = "-fx-background-color: rgba(0, 0, 0, 0.1);";
    private static final String BUTTON_SELECTED_STYLE = """
            -fx-background-color: transparent;
            -fx-text-fill: #000000;
            -fx-font-weight: 800;
            """;
    private static final String BOX_UNSELECTED_STYLE = "-fx-background-color: transparent;";
    private static final String BUTTON_UNSELECTED_STYLE = """
            -fx-background-color: transparent;
            -fx-text-fill: #000000;
            -fx-font-weight: 500;
            """;

    private HBox selectedBox;
    private Button selectedButton;
    private BorderPane root;
    private int selectedIndex = 0;

    public VersionManageLeftPane(BorderPane root) {
        this.root = root;
        this.setPrefWidth(200);
        this.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
        root.setCenter(new VersionManageAutoInstallPane());
        initUi();
    }

    public void initUi() {
        HBox autoInstallBox = new HBox();
        Button autoInstallButton = new Button("♕   自动安装");

        autoInstallBox.setStyle(BOX_SELECTED_STYLE);
        autoInstallButton.setStyle(BUTTON_SELECTED_STYLE);
        selectedBox = autoInstallBox;
        selectedButton = autoInstallButton;

        autoInstallBox.getChildren().addAll(autoInstallButton);
        autoInstallBox.setAlignment(Pos.CENTER);
        autoInstallBox.setPrefSize(200, 40);
        VBox.setMargin(autoInstallBox, new Insets(10, 0, 0, 0));

        autoInstallBox.setOnMouseEntered(e -> {
            autoInstallBox.setStyle(BOX_SELECTED_STYLE);
            autoInstallButton.setStyle(BUTTON_SELECTED_STYLE);
        });
        autoInstallBox.setOnMouseExited(e -> {
            if (selectedBox == autoInstallBox) { // 仍选中则保持样式
                autoInstallBox.setStyle(BOX_SELECTED_STYLE);
                autoInstallButton.setStyle(BUTTON_SELECTED_STYLE);
            } else { // 已切换到其他按钮，恢复未选中
                autoInstallBox.setStyle(BOX_UNSELECTED_STYLE);
                autoInstallButton.setStyle(BUTTON_UNSELECTED_STYLE);
            }
        });
        autoInstallButton.setOnMouseClicked(e -> {
            selectedIndex = 0;
            setSelected(autoInstallBox, autoInstallButton); // 切换选中态
        });

        HBox modManageBox = new HBox();
        Button modManageButton = new Button("❦   模组管理");

        modManageBox.setStyle(BOX_UNSELECTED_STYLE);
        modManageButton.setStyle(BUTTON_UNSELECTED_STYLE);

        modManageBox.getChildren().add(modManageButton);
        modManageBox.setAlignment(Pos.CENTER);
        modManageBox.setPrefSize(200, 40);

        modManageBox.setOnMouseEntered(e -> {
            modManageBox.setStyle(BOX_SELECTED_STYLE);
            modManageButton.setStyle(BUTTON_SELECTED_STYLE);
        });
        modManageBox.setOnMouseExited(e -> {
            if (selectedBox == modManageBox) {
                modManageBox.setStyle(BOX_SELECTED_STYLE);
                modManageButton.setStyle(BUTTON_SELECTED_STYLE);
            } else {
                modManageBox.setStyle(BOX_UNSELECTED_STYLE);
                modManageButton.setStyle(BUTTON_UNSELECTED_STYLE);
            }
        });
        modManageButton.setOnMouseClicked(e -> {
            selectedIndex = 1;
            setSelected(modManageBox, modManageButton); // 切换选中态
        });

        this.getChildren().addAll(autoInstallBox, modManageBox);
    }

    /**
     * 切换选中态：设置目标按钮为选中，其他为未选中
     * @param targetBox 目标容器（HBox）
     * @param targetButton 目标按钮（Button）
     */
    private void setSelected(HBox targetBox, Button targetButton) {
        if (selectedBox != null && selectedButton != null) {
            selectedBox.setStyle(BOX_UNSELECTED_STYLE);
            selectedButton.setStyle(BUTTON_UNSELECTED_STYLE);
        }
        targetBox.setStyle(BOX_SELECTED_STYLE);
        targetButton.setStyle(BUTTON_SELECTED_STYLE);
        selectedBox = targetBox;
        selectedButton = targetButton;

        // 根据 selectedIndex 切换右侧内容
        switch (selectedIndex) {
            case 0 -> root.setCenter(new VersionManageAutoInstallPane());
            default -> {}
        }
    }
}