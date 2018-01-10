package pl.sdacademy.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.sdacademy.javafx.controller.MainController;

public class Window extends Application {
    private static MainController mainController;

    public static MainController getMainController() {
        return mainController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();

        mainController = loader.getController();

        Scene scene = new Scene(root);

        stage.setTitle("Multi view");
        stage.setScene(scene);
        stage.show();
    }

    public static void main( String[] args ) {
        launch(args);
    }
}
