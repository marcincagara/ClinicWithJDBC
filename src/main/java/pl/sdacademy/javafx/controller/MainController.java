package pl.sdacademy.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML private BorderPane container;

    private Map<String, Parent> views = new HashMap<>();

    private void loadView(String name, String file) throws IOException {
        views.put(name, FXMLLoader.load(getClass().getResource(file)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadView("child1", "child1.fxml");
            loadView("child2", "child2.fxml");
            container.setCenter(views.get("child1"));
        } catch (IOException e) {
            throw new RuntimeException("Can't load views");
        }
    }

    @FXML protected void updateView(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        setView(item.getId());
    }

    public void setView(String name) {
        container.setCenter(views.get(name));
    }
}
