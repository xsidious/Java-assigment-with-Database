

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.font.Decoration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main extends Application {

    public static void main(String[] args) throws Exception {


        launch(args);





    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("firstViewCollectInfo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
}
