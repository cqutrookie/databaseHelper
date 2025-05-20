package com.databaseHelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Lei X Y
 * {@code @date} 2025/5/20 16:26
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // 加载主界面FXML
        Parent root = FXMLLoader.load(getClass().getResource("/com/databaseHelper/view/main.fxml"));

        // 设置主窗口
        primaryStage.setTitle("数据库操作工具");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.getIcons().add(new Image("/com/databaseHelper/view/icon.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
