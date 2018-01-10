package pl.sdacademy.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pl.sdacademy.javafx.Window;

public class Child2Controller {
    @FXML protected void goToChild1(ActionEvent event) {
        Window.getMainController().setView("child1");
    }
}
