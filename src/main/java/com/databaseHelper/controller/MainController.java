package com.databaseHelper.controller;

import com.databaseHelper.config.DatabaseConfig;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Lei X Y
 * {@code @date} 2025/5/20 16:29
 */

public class MainController implements Initializable {
    @FXML private ComboBox<String> dbTypeComboBox;
    @FXML private StackPane configContainer;
    @FXML private TableView<?> resultTable;
    @FXML private Label statusLabel;

    private Map<String, DatabaseConfig> configControllers = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 初始化数据库类型下拉框
        dbTypeComboBox.getItems().addAll("MySQL", "PostgreSQL", "Oracle", "SQL Server");
        dbTypeComboBox.getSelectionModel().selectFirst();

        // 监听下拉框变化
        dbTypeComboBox.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> switchDatabaseConfig(newVal)
        );

        // 初始加载第一个数据库配置
        switchDatabaseConfig(dbTypeComboBox.getValue());
    }

    private void switchDatabaseConfig(String dbType) {
        try {
            String fxmlFile = "/com/databaseHelper/view/" + dbType.toLowerCase() + "_config.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Node configPane = loader.load();

            // 保存控制器引用
            DatabaseConfig controller = (DatabaseConfig) loader.getController();
            configControllers.put(dbType, controller);

            // 更新界面
            configContainer.getChildren().setAll(configPane);
        } catch (IOException e) {
            statusLabel.setText("加载配置面板失败: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleExit() {
        Platform.exit();
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("关于");
        alert.setHeaderText("数据库连接助手");
        alert.setContentText("版本 1.0\n支持多种数据库连接");
        alert.showAndWait();
    }

    public DatabaseConfig getActiveDatabaseConfig() {
        String selected = dbTypeComboBox.getValue();
        return configControllers.get(selected);
    }
}
