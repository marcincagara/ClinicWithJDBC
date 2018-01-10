package pl.sdacademy;

import java.util.InputMismatchException;

public class App {

    public static void main(String[] args) {
        while (Menu.isFinishMain()) {
            try {
                Menu.showMenu();
            } catch (InputMismatchException e) {
                System.out.println("Please try again and choose the available option! Returning to Main Menu..." + "\n");
            }
        }
    }
}
