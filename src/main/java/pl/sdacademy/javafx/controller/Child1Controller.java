package pl.sdacademy.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pl.sdacademy.javafx.Window;

public class Child1Controller {
    @FXML protected void goToChild2(ActionEvent event) {
        Window.getMainController().setView("child2");
    }
}
