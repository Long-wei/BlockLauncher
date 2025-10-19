package org.bkl.ui.versionmanage;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.bkl.download.GameVersionManifest;
import org.bkl.log.Logger;
import org.bkl.log.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VersionManageGameInstallPane extends VBox {
    private static final Logger log = LoggerFactory.getLogger(VersionManageGameInstallPane.class.getName());

    private enum Channel { RELEASE, SNAPSHOT, OLD }

    private final List<Button> topButtons = new ArrayList<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private ListView<String> versionListView = new ListView<>();
    private final ProgressIndicator loadingIndicator = new ProgressIndicator();

    private Channel currentChannel = Channel.RELEASE;

    public VersionManageGameInstallPane() {
        this.setPrefSize(600, 470);
        this.setMinSize(600, 470);
        this.setMaxSize(600, 470);
        this.setStyle("""
                -fx-background-color: rgba(245, 245, 245, 0.8);
            """);
        initUi();
        getRemoteVersions(currentChannel);
    }

    private void initUi() {
        HBox top = new HBox();
        top.setPrefSize(600, 60);
        top.setMinSize(600, 60);
        top.setMaxSize(600, 60);
        top.setPadding(new Insets(10));
        top.setAlignment(Pos.CENTER_LEFT);
        top.setStyle("""
                -fx-background-color: rgba(255, 255, 255, 0.5);
                -fx-border-radius: 5 5 0 0;
            """);

        Button btnRelease = createSelectableButton("Release", Channel.RELEASE);
        Button btnSnapshot = createSelectableButton("Snapshot", Channel.SNAPSHOT);
        Button btnOld = createSelectableButton("Old", Channel.OLD);
        Button btnInstalled = createInstalledButton();

        topButtons.addAll(Arrays.asList(btnRelease, btnSnapshot, btnOld, btnInstalled));
        top.getChildren().addAll(topButtons);
        setButtonSelected(btnRelease);

        VBox content = new VBox();
        content.setPrefSize(580, 390);
        content.setMinSize(580, 390);
        content.setMaxSize(580, 390);
        VBox.setMargin(content, new Insets(10, 10, 10, 10));
        content.setStyle("""
                -fx-background-color: rgba(255, 255, 255, 0.5);
                -fx-background-radius: 5;
                -fx-padding: 10;
            """);

        Button titleBtn = new Button("版本列表");
        titleBtn.setPrefSize(560, 30);
        titleBtn.setDisable(true);
        titleBtn.setStyle("""
                -fx-background-color: rgba(230, 230, 230, 0.8);
                -fx-text-fill: #333333;
                -fx-font-weight: 700;
                -fx-border-radius: 3;
                -fx-cursor: default;
            """);
        VBox.setMargin(titleBtn, new Insets(0, 0, 10, 0));

        loadingIndicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        loadingIndicator.setPrefSize(80, 80);
        loadingIndicator.setStyle("""
                -fx-progress-color: #4a90e2;
                -fx-background-color: transparent;
            """);
        loadingIndicator.setVisible(false);

        versionListView.setPrefSize(560, 330);
        versionListView.setMinSize(560, 330);
        versionListView.setMaxSize(560, 330);
        versionListView.setStyle("""
                -fx-font-size: 14px;
                -fx-background-color: white;
                -fx-border-color: #dddddd;
                -fx-border-radius: 3;
                -fx-selection-bar: #f0f0f0;
                -fx-selection-bar-text: #000000;
            """);
        versionListView.setCellFactory(listView -> new ListCell<>() {
            private final HBox itemBox = new HBox();
            private final Label versionLabel = new Label();
            private final Region spacer = new Region();
            private final Button actionBtn = new Button("下载");

            {
                versionLabel.setStyle("-fx-padding: 0 0 0 10;");
                HBox.setHgrow(spacer, Priority.ALWAYS);

                actionBtn.setPrefSize(80, 25);
                actionBtn.setStyle("""
                        -fx-background-color: #4a90e2;
                        -fx-text-fill: white;
                        -fx-font-weight: 500;
                        -fx-border-radius: 3;
                        -fx-cursor: hand;
                    """);
                actionBtn.setPadding(new Insets(0, 10, 0, 0));

                itemBox.getChildren().addAll(versionLabel, spacer, actionBtn);
                itemBox.setPadding(new Insets(5, 0, 5, 0));

                actionBtn.setOnAction(e -> {
                    String ver = versionLabel.getText();
                    Platform.runLater(() -> {
                        loadingIndicator.setVisible(true);
                        versionListView.setVisible(true);
                    });
                    System.out.println(ver);
                });
            }

            @Override
            protected void updateItem(String versionText, boolean empty) {
                super.updateItem(versionText, empty);
                if (empty || versionText == null) {
                    setGraphic(null);
                } else {
                    versionLabel.setText(versionText);
                    setGraphic(itemBox);
                }
            }
        });

        StackPane listAndLoadingPane = new StackPane();
        listAndLoadingPane.setPrefSize(560, 340);
        listAndLoadingPane.getChildren().addAll(versionListView, loadingIndicator);

        content.getChildren().addAll(titleBtn, listAndLoadingPane);
        this.getChildren().addAll(top, content);
    }

    private Button createSelectableButton(String text, Channel channel) {
        Button btn = new Button(text);
        btn.setPrefSize(100, 40);
        setButtonUnselected(btn);
        btn.setOnAction(e -> {
            currentChannel = channel;
            for (Button b : topButtons) setButtonUnselected(b);
            setButtonSelected(btn);
            getRemoteVersions(channel);
        });
        return btn;
    }

    private Button createInstalledButton() {
        Button btn = new Button("已安装");
        btn.setPrefSize(100, 40);
        setButtonUnselected(btn);
        btn.setOnAction(e -> {
            for (Button b : topButtons) setButtonUnselected(b);
            setButtonSelected(btn);
            getInstalledVersions();
        });
        return btn;
    }

    private void getRemoteVersions(Channel channel) {
        Platform.runLater(() -> {
            loadingIndicator.setVisible(true);
        });

        Task<List<String>> task = new Task<>() {
            @Override
            protected List<String> call() throws Exception {
                List<String> list = switch (channel) {
                    case RELEASE -> GameVersionManifest.getReleaseVersions();
                    case SNAPSHOT -> GameVersionManifest.getSnapShotVersions();
                    case OLD -> GameVersionManifest.getOldVersions();
                    default -> null;
                };
                return list;
            }

            @Override
            protected void succeeded() {
                List<String> list = getValue();
                if (list == null || list.isEmpty()) {
                    log.info("no versions found for channel: " + channel);
                }
                updateVersionList(list);
                loadingIndicator.setVisible(false);
                versionListView.setVisible(true);
            }

            @Override
            protected void failed() {

            }
        };
        executor.submit(task);
    }

    private void getInstalledVersions() {
        Platform.runLater(() -> loadingIndicator.setVisible(true));

        Task<List<String>> task = new Task<>() {
            @Override
            protected List<String> call() {
                List<String> list = new ArrayList<>();
                File versionsDir = new File(System.getProperty("user.home"), ".minecraft/versions");
                if (versionsDir.exists() && versionsDir.isDirectory()) {
                    File[] children = versionsDir.listFiles(File::isDirectory);
                    if (children != null) {
                        for (File f : children) {
                            list.add(f.getName());
                        }
                        Collections.sort(list);
                    }
                }
                return list;
            }

            @Override
            protected void succeeded() {
                List<String> list = getValue();
                if (list == null || list.isEmpty()) {
                    log.info("no installed versions found");
                }
                updateVersionList(list);
                loadingIndicator.setVisible(false);
                versionListView.setVisible(true);
            }

            @Override
            protected void failed() {
                Throwable ex = getException();
                log.info("failed to list installed versions -> " + ex);
                loadingIndicator.setVisible(false);
            }
        };

        executor.submit(task);
    }

    private void setButtonSelected(Button btn) {
        btn.setStyle("""
                -fx-background-color: #4a90e2;
                -fx-text-fill: white;
                -fx-font-weight: 700;
                -fx-border-radius: 5;
            """);
    }

    private void setButtonUnselected(Button btn) {
        btn.setStyle("""
                -fx-background-color: #ffffff;
                -fx-text-fill: #000000;
                -fx-font-weight: 500;
                -fx-border-radius: 5;
            """);
    }

    private void updateVersionList(List<String> versions) {
        ObservableList<String> observableVersions = FXCollections.observableArrayList(versions);
        versionListView.setItems(observableVersions);
    }

    // 可在外部关闭 Pane 时调用，避免线程泄漏
    public void shutdown() {
        executor.shutdownNow();
    }
}