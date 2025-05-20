package com.databaseHelper.controller;

import com.databaseHelper.config.DatabaseConfig;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Lei X Y
 * {@code @date} 2025/5/20 17:21
 */
public class PostgreSqlConfigController implements DatabaseConfig, Initializable {
    @FXML
    private TextField hostField;
    @FXML private TextField portField;
    @FXML private TextField dbNameField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField extraParamsField;
    @FXML private Label statusLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 初始化代码（如果需要）
    }

    @FXML
    public void testConnectionFxml() {
        if (testConnection()) {
            statusLabel.setText("MySQL连接测试成功");
            statusLabel.setStyle("-fx-text-fill: green;");
        } else {
            statusLabel.setText("MySQL连接测试失败");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void saveConfigFxml() {
        saveConfig();
    }

    @FXML
    public void connectFxml() {
        connect();
    }

    @Override
    public String getConnectionUrl() {
        return String.format("jdbc:mysql://%s:%s/%s?%s",
                hostField.getText(),
                portField.getText(),
                dbNameField.getText(),
                extraParamsField.getText());
    }

    @Override
    public String getUsername() {
        return usernameField.getText();
    }

    @Override
    public String getPassword() {
        return passwordField.getText();
    }

    @Override
    public String getDatabaseType() {
        return "MySQL";
    }

    @Override
    public boolean testConnection() {
        // 实现实际的连接测试逻辑
        return true; // 示例返回值
    }

    @Override
    public void saveConfig() {
        // 实现配置保存逻辑
    }

    @Override
    public void connect() {
        // 实现实际连接逻辑
    }
}
