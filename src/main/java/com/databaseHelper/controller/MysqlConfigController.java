package com.databaseHelper.controller;

import com.databaseHelper.config.DatabaseConfig;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author Lei X Y
 * {@code @date} 2025/5/20 17:21
 */
public class MysqlConfigController implements DatabaseConfig, Initializable {
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
        testConnection();
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
    public String getDatabaseType() {
        return "MySQL";
    }

    /**
     * mysql 测试连接操作
     * @return
     */
    @Override
    public boolean testConnection() {
        // 禁用UI组件防止重复点击
        setUiDisabled(true);
        statusLabel.setText("正在测试连接...");
        statusLabel.setStyle("-fx-text-fill: blue;");

        // 使用线程执行测试，避免阻塞UI
        new Thread(() -> {
            try {
                // 获取连接参数
                String url = getConnectionUrl();
                String user = getUsername();
                String pass = getPassword();

                // 测试连接
                boolean success = testMySQLConnection(url, user, pass);
                // 更新UI
                Platform.runLater(() -> {
                    if (success) {
                        statusLabel.setText("连接成功！");
                        statusLabel.setStyle("-fx-text-fill: green;");
                    } else {
                        statusLabel.setText("连接失败，请检查参数");
                        statusLabel.setStyle("-fx-text-fill: red;");
                    }
                    setUiDisabled(false);
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    statusLabel.setText("错误: " + e.getMessage());
                    statusLabel.setStyle("-fx-text-fill: red;");
                    setUiDisabled(false);
                });
            }
        }).start();
        return true;
    }

    private boolean testMySQLConnection(String url, String username, String password) {
        Connection connection = null;
        try {
            // 加载MySQL驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 设置连接超时为5秒
            DriverManager.setLoginTimeout(5);

            // 建立连接
            connection = DriverManager.getConnection(url, username, password);

            // 执行简单查询验证连接
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT 1")) {
                return rs.next(); // 如果有结果返回true
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("找不到MySQL驱动，请检查依赖", e);
        } catch (SQLException e) {
            throw new RuntimeException("数据库连接失败: " + e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // 关闭连接时的错误可以忽略
                }
            }
        }
    }

    private void setUiDisabled(boolean disabled) {
        hostField.setDisable(disabled);
        portField.setDisable(disabled);
        dbNameField.setDisable(disabled);
        usernameField.setDisable(disabled);
        passwordField.setDisable(disabled);
        extraParamsField.setDisable(disabled);
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
    public void saveConfig() {
        // 实现配置保存逻辑
    }

    @Override
    public void connect() {
        // 实现实际连接逻辑
    }
}